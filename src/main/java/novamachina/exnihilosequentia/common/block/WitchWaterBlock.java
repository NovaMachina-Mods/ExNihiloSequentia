package novamachina.exnihilosequentia.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.monster.CaveSpiderEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.EvokerEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.VindicatorEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.monster.ZombieVillagerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import novamachina.exnihilosequentia.common.init.ModFluids;

public class WitchWaterBlock extends FlowingFluidBlock {

    public WitchWaterBlock() {
        super(ModFluids.WITCH_WATER,
            AbstractBlock.Properties.create(Material.WATER).doesNotBlockMovement()
                .hardnessAndResistance(100.0F).noDrops());
    }

    /**
     * @deprecated Ask Mojang
     */
    @Deprecated
    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (worldIn.isRemote() || !entityIn.isAlive()) {
            return;
        }

        if (entityIn instanceof SkeletonEntity) {
            replaceMob(worldIn, (SkeletonEntity) entityIn,
                new WitherSkeletonEntity(EntityType.WITHER_SKELETON, worldIn));
        }

        if (entityIn instanceof CreeperEntity && !((CreeperEntity) entityIn).isCharged()) {
            entityIn.func_241841_a((ServerWorld) worldIn, EntityType.LIGHTNING_BOLT.create(worldIn));
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
            entityIn.func_241841_a((ServerWorld) worldIn, EntityType.LIGHTNING_BOLT.create(worldIn));
        }

        if (entityIn instanceof PlayerEntity) {
            applyPotion((PlayerEntity) entityIn, new EffectInstance(Effects.BLINDNESS, 210, 0));
            applyPotion((PlayerEntity) entityIn, new EffectInstance(Effects.WEAKNESS, 210, 2));
            applyPotion((PlayerEntity) entityIn, new EffectInstance(Effects.WITHER, 210, 0));
            applyPotion((PlayerEntity) entityIn, new EffectInstance(Effects.SLOWNESS, 210, 0));
        }
    }

    private void applyPotion(PlayerEntity entityIn, EffectInstance potionEffect) {
        EffectInstance currentEffect = entityIn.getActivePotionEffect(potionEffect.getPotion());
        if (currentEffect != null
            && currentEffect.getDuration() <= potionEffect.getDuration() - 20) {
            entityIn.addPotionEffect(potionEffect);
        }
    }

    private void replaceMob(World world, LivingEntity toKill, LivingEntity toSpawn) {
        toSpawn.setLocationAndAngles(toKill.getPosX(), toKill.getPosY(), toKill.getPosZ(),
            toKill.rotationYaw, toKill.rotationPitch);
        toSpawn.renderYawOffset = toKill.renderYawOffset;
        toSpawn.setHealth(toSpawn.getMaxHealth() * toKill.getHealth() / toKill.getMaxHealth());

        toKill.remove();
        world.addEntity(toSpawn);
    }
}
