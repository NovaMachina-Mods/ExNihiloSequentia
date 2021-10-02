package novamachina.exnihilosequentia.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;

import javax.annotation.Nonnull;
import java.util.Objects;

public class WitchWaterBlock extends LiquidBlock {

    public WitchWaterBlock() {
        super(ExNihiloFluids.WITCH_WATER,
                BlockBehaviour.Properties.of(Material.WATER).noCollission()
                        .strength(100.0F).noDrops());
    }

    /**
     * @deprecated Ask Mojang
     */
    @Deprecated
    @Override
    public void entityInside(@Nonnull BlockState state, Level worldIn, @Nonnull BlockPos pos, @Nonnull Entity entityIn) {
        if (worldIn.isClientSide() || !entityIn.isAlive()) {
            return;
        }

        if (entityIn instanceof Skeleton) {
            replaceMob(worldIn, (Skeleton) entityIn,
                    new WitherSkeleton(EntityType.WITHER_SKELETON, worldIn));
        }

        if (entityIn instanceof Creeper && !((Creeper) entityIn).isPowered()) {
            entityIn.thunderHit((ServerLevel) worldIn, Objects.requireNonNull(EntityType.LIGHTNING_BOLT.create(worldIn)));
            ((Creeper) entityIn).setHealth(((Creeper) entityIn).getMaxHealth());
        }

        // TODO Slime

        if (entityIn instanceof Spider && !(entityIn instanceof CaveSpider)) {
            replaceMob(worldIn, (Spider) entityIn,
                    new CaveSpider(EntityType.CAVE_SPIDER, worldIn));
        }

        if (entityIn instanceof Squid) {
            replaceMob(worldIn, (Squid) entityIn, new Ghast(EntityType.GHAST, worldIn));
        }

        if (entityIn instanceof Villager villagerEntity) {
            VillagerProfession profession = villagerEntity.getVillagerData().getProfession();

            if (profession == VillagerProfession.CLERIC) {
                replaceMob(worldIn, villagerEntity, new Witch(EntityType.WITCH, worldIn));
            } else if (profession == VillagerProfession.BUTCHER) {
                replaceMob(worldIn, villagerEntity,
                        new Vindicator(EntityType.VINDICATOR, worldIn));
            } else if (profession == VillagerProfession.LIBRARIAN) {
                replaceMob(worldIn, villagerEntity, new Evoker(EntityType.EVOKER, worldIn));
            } else {
                ZombieVillager zombieVillagerEntity = new ZombieVillager(
                        EntityType.ZOMBIE_VILLAGER, worldIn);
                zombieVillagerEntity.setVillagerData(villagerEntity.getVillagerData());
                replaceMob(worldIn, villagerEntity, zombieVillagerEntity);
            }
        }

        // TODO Cows

        if (entityIn instanceof Animal) {
            entityIn.thunderHit((ServerLevel) worldIn, Objects.requireNonNull(EntityType.LIGHTNING_BOLT.create(worldIn)));
        }

        if (entityIn instanceof Player) {
            applyPotion((Player) entityIn, new MobEffectInstance(MobEffects.BLINDNESS, 210, 0));
            applyPotion((Player) entityIn, new MobEffectInstance(MobEffects.WEAKNESS, 210, 2));
            applyPotion((Player) entityIn, new MobEffectInstance(MobEffects.WITHER, 210, 0));
            applyPotion((Player) entityIn, new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 210, 0));
        }
    }

    private void applyPotion(Player entityIn, MobEffectInstance potionEffect) {
        MobEffectInstance currentEffect = entityIn.getEffect(potionEffect.getEffect());
        if (currentEffect != null
                && currentEffect.getDuration() <= potionEffect.getDuration() - 20) {
            entityIn.addEffect(potionEffect);
        }
    }

    private void replaceMob(Level world, LivingEntity toKill, LivingEntity toSpawn) {
        toSpawn.moveTo(toKill.getX(), toKill.getY(), toKill.getZ(),
                toKill.getYRot(), toKill.getXRot());
        toSpawn.yBodyRot = toKill.yBodyRot;
        toSpawn.setHealth(toSpawn.getMaxHealth() * toKill.getHealth() / toKill.getMaxHealth());

        toKill.remove(Entity.RemovalReason.DISCARDED);
        world.addFreshEntity(toSpawn);
    }
}
