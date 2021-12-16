package novamachina.exnihilosequentia.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NBTDynamicOps;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WitchWaterBlock extends FlowingFluidBlock {

    public WitchWaterBlock() {
        super(ExNihiloFluids.WITCH_WATER,
                AbstractBlock.Properties.of(Material.WATER).noCollission()
                        .strength(100.0F).noDrops());
    }

    /**
     * @deprecated Ask Mojang
     */
    @Deprecated
    @Override
    public void entityInside(@Nonnull final BlockState state, @Nonnull final World worldIn, @Nonnull final BlockPos pos,
                             @Nonnull final Entity entityIn) {
        if (worldIn.isClientSide() || !entityIn.isAlive()) {
            return;
        }

        if (entityIn instanceof SkeletonEntity) {
            replaceMob(worldIn, (SkeletonEntity) entityIn,
                    new WitherSkeletonEntity(EntityType.WITHER_SKELETON, worldIn));
        }

        if (entityIn instanceof CreeperEntity && !((CreeperEntity) entityIn).isPowered()) {
            entityIn.thunderHit((ServerWorld) worldIn, EntityType.LIGHTNING_BOLT.create(worldIn));
            ((CreeperEntity) entityIn).setHealth(((CreeperEntity) entityIn).getMaxHealth());
        }

        // TODO Slime

        if (entityIn instanceof SpiderEntity && !(entityIn instanceof CaveSpiderEntity)) {
            replaceMob(worldIn, (SpiderEntity) entityIn,
                    new CaveSpiderEntity(EntityType.CAVE_SPIDER, worldIn));
        }

        if (entityIn instanceof SquidEntity) {
            replaceMob(worldIn, (SquidEntity) entityIn, new GhastEntity(EntityType.GHAST, worldIn));
        }

        if (entityIn instanceof VillagerEntity) {
            VillagerEntity villagerEntity = (VillagerEntity) entityIn;
            VillagerProfession profession = villagerEntity.getVillagerData().getProfession();

            if (profession == VillagerProfession.CLERIC) {
                replaceMob(worldIn, villagerEntity, new WitchEntity(EntityType.WITCH, worldIn));
            } else if (profession == VillagerProfession.BUTCHER) {
                replaceMob(worldIn, villagerEntity,
                        new VindicatorEntity(EntityType.VINDICATOR, worldIn));
            } else if (profession == VillagerProfession.LIBRARIAN) {
                replaceMob(worldIn, villagerEntity, new EvokerEntity(EntityType.EVOKER, worldIn));
            } else {
                ZombieVillagerEntity zombieVillagerEntity = new ZombieVillagerEntity(
                        EntityType.ZOMBIE_VILLAGER, worldIn);
                zombieVillagerEntity.setVillagerData(villagerEntity.getVillagerData());
                zombieVillagerEntity.setGossips(villagerEntity.getGossips().store(NBTDynamicOps.INSTANCE).getValue());
                zombieVillagerEntity.setTradeOffers(villagerEntity.getOffers().createTag());
                zombieVillagerEntity.setVillagerXp(villagerEntity.getVillagerXp());
                replaceMob(worldIn, villagerEntity, zombieVillagerEntity);
            }
        }

        // TODO Cows

        if (entityIn instanceof AnimalEntity) {
            @Nullable final LightningBoltEntity lightningBolt =  EntityType.LIGHTNING_BOLT.create(worldIn);
            if (lightningBolt != null)
                entityIn.thunderHit((ServerWorld) worldIn, lightningBolt);
        }

        if (entityIn instanceof PlayerEntity) {
            applyPotion((PlayerEntity) entityIn, new EffectInstance(Effects.BLINDNESS, 210, 0));
            applyPotion((PlayerEntity) entityIn, new EffectInstance(Effects.WEAKNESS, 210, 2));
            applyPotion((PlayerEntity) entityIn, new EffectInstance(Effects.WITHER, 210, 0));
            applyPotion((PlayerEntity) entityIn, new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 210, 0));
        }
    }

    private void applyPotion(@Nonnull final PlayerEntity entityIn, @Nonnull final EffectInstance potionEffect) {
        @Nullable final EffectInstance currentEffect = entityIn.getEffect(potionEffect.getEffect());
        if (currentEffect != null
                && currentEffect.getDuration() <= potionEffect.getDuration() - 20) {
            entityIn.addEffect(potionEffect);
        }
    }

    private void replaceMob(@Nonnull final World world, @Nonnull final LivingEntity toKill,
                            @Nonnull final LivingEntity toSpawn) {
        toSpawn.moveTo(toKill.getX(), toKill.getY(), toKill.getZ(),
                toKill.yRot, toKill.xRot);
        toSpawn.yBodyRot = toKill.yBodyRot;
        toSpawn.setHealth(toSpawn.getMaxHealth() * toKill.getHealth() / toKill.getMaxHealth());

        toKill.remove();
        world.addFreshEntity(toSpawn);
    }
}
