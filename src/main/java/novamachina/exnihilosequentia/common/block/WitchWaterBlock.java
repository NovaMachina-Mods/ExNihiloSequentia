package novamachina.exnihilosequentia.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Material;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;

public class WitchWaterBlock extends FlowingFluid {

    public WitchWaterBlock() {
        super(ExNihiloFluids.WITCH_WATER,
                Block.Properties.of(Material.WATER).noCollission()
                        .strength(100.0F).noDrops());
    }

    /**
     * @deprecated Ask Mojang
     */
    @Deprecated
    @Override
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        if (worldIn.isClientSide() || !entityIn.isAlive()) {
            return;
        }

        if (entityIn instanceof SkeletonEntity) {
            replaceMob(worldIn, (SkeletonEntity) entityIn,
                    new WitherSkeletonEntity(EntityType.WITHER_SKELETON, worldIn));
        }

        if (entityIn instanceof CreeperEntity && !((CreeperEntity) entityIn).isPowered()) {
            entityIn.thunderHit((ServerLevel) worldIn, EntityType.LIGHTNING_BOLT.create(worldIn));
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
                replaceMob(worldIn, villagerEntity, zombieVillagerEntity);
            }
        }

        // TODO Cows

        if (entityIn instanceof AnimalEntity) {
            entityIn.thunderHit((ServerLevel) worldIn, EntityType.LIGHTNING_BOLT.create(worldIn));
        }

        if (entityIn instanceof Player) {
            applyPotion((Player) entityIn, new EffectInstance(Effects.BLINDNESS, 210, 0));
            applyPotion((Player) entityIn, new EffectInstance(Effects.WEAKNESS, 210, 2));
            applyPotion((Player) entityIn, new EffectInstance(Effects.WITHER, 210, 0));
            applyPotion((Player) entityIn, new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 210, 0));
        }
    }

    private void applyPotion(Player entityIn, EffectInstance potionEffect) {
        EffectInstance currentEffect = entityIn.getEffect(potionEffect.getEffect());
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
