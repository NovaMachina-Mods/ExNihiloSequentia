package novamachina.exnihilosequentia.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.SetTag;
import net.minecraft.world.effect.MobEffect;
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
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;

public class WitchWaterBlock extends FlowingFluid {

    public WitchWaterBlock() {
        super(ExNihiloFluids.WITCH_WATER,
                Block.Properties.of(Material.WATER).noCollission()
                        .strength(100.0F).noDrops());
    }

    @Override
    public boolean isEntityInside(FluidState state, LevelReader worldIn, BlockPos pos, Entity entityIn, double yToTest, SetTag<Fluid> tag, boolean testingHead) {
        if (worldIn.isClientSide() || !entityIn.isAlive()) {
            return false;
        }
        Level levelIn = (Level) worldIn;

        if (entityIn instanceof Skeleton) {
            replaceMob(levelIn, (Skeleton) entityIn,
                    new WitherSkeleton(EntityType.WITHER_SKELETON, levelIn));
        }

        if (entityIn instanceof Creeper && !((Creeper) entityIn).isPowered()) {
            entityIn.thunderHit((ServerLevel) worldIn, EntityType.LIGHTNING_BOLT.create(levelIn));
            ((Creeper) entityIn).setHealth(((Creeper) entityIn).getMaxHealth());
        }

        // TODO Slime

        if (entityIn instanceof Spider && !(entityIn instanceof CaveSpider)) {
            replaceMob(levelIn, (Spider) entityIn,
                    new CaveSpider(EntityType.CAVE_SPIDER, levelIn));
        }

        if (entityIn instanceof Squid) {
            replaceMob(levelIn, (Squid) entityIn, new Ghast(EntityType.GHAST, levelIn));
        }

        if (entityIn instanceof Villager) {
            Villager villagerEntity = (Villager) entityIn;
            VillagerProfession profession = villagerEntity.getVillagerData().getProfession();

            if (profession == VillagerProfession.CLERIC) {
                replaceMob(levelIn, villagerEntity, new Witch(EntityType.WITCH, levelIn));
            } else if (profession == VillagerProfession.BUTCHER) {
                replaceMob(levelIn, villagerEntity,
                        new Vindicator(EntityType.VINDICATOR, levelIn));
            } else if (profession == VillagerProfession.LIBRARIAN) {
                replaceMob(levelIn, villagerEntity, new Evoker(EntityType.EVOKER, levelIn));
            } else {
                ZombieVillager zombieVillagerEntity = new ZombieVillager(
                        EntityType.ZOMBIE_VILLAGER, levelIn);
                zombieVillagerEntity.setVillagerData(villagerEntity.getVillagerData());
                replaceMob(levelIn, villagerEntity, zombieVillagerEntity);
            }
        }

        // TODO Cows

        if (entityIn instanceof Animal) {
            entityIn.thunderHit((ServerLevel) worldIn, EntityType.LIGHTNING_BOLT.create(levelIn));
        }

        if (entityIn instanceof Player) {
            applyPotion((Player) entityIn, new MobEffectInstance(MobEffects.BLINDNESS, 210, 0));
            applyPotion((Player) entityIn, new MobEffectInstance(MobEffects.WEAKNESS, 210, 2));
            applyPotion((Player) entityIn, new MobEffectInstance(MobEffects.WITHER, 210, 0));
            applyPotion((Player) entityIn, new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 210, 0));
        }
        return true;
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

        toKill.remove(false);
        world.addFreshEntity(toSpawn);
    }
}
