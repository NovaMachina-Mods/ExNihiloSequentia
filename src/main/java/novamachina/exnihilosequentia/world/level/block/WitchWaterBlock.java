package novamachina.exnihilosequentia.world.level.block;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.world.level.material.EXNFluids;

public class WitchWaterBlock extends LiquidBlock {

  public WitchWaterBlock() {
    super(
        () -> EXNFluids.WITCH_WATER.getStillFluid(),
        BlockBehaviour.Properties.of().noCollission().strength(100.0F).noLootTable());
  }

  /**
   * @deprecated Ask Mojang
   */
  @Deprecated
  @Override
  public void entityInside(
      @Nonnull final BlockState state,
      @Nonnull final Level worldIn,
      @Nonnull final BlockPos pos,
      @Nonnull final Entity entityIn) {
    if (worldIn.isClientSide() || !entityIn.isAlive()) {
      return;
    }

    if (entityIn instanceof Skeleton) {
      replaceMob(
          worldIn, (Skeleton) entityIn, new WitherSkeleton(EntityType.WITHER_SKELETON, worldIn));
    }

    if (entityIn instanceof Creeper && !((Creeper) entityIn).isPowered()) {
      entityIn.thunderHit((ServerLevel) worldIn, EntityType.LIGHTNING_BOLT.create(worldIn));
      ((Creeper) entityIn).setHealth(((Creeper) entityIn).getMaxHealth());
    }

    // TODO Slime

    if (entityIn instanceof Spider && !(entityIn instanceof CaveSpider)) {
      replaceMob(worldIn, (Spider) entityIn, new CaveSpider(EntityType.CAVE_SPIDER, worldIn));
    }

    if (entityIn instanceof Squid) {
      replaceMob(worldIn, (Squid) entityIn, new Ghast(EntityType.GHAST, worldIn));
    }

    if (entityIn instanceof Villager) {
      Villager villagerEntity = (Villager) entityIn;
      VillagerProfession profession = villagerEntity.getVillagerData().getProfession();

      if (profession == VillagerProfession.CLERIC) {
        replaceMob(worldIn, villagerEntity, new Witch(EntityType.WITCH, worldIn));
      } else if (profession == VillagerProfession.BUTCHER) {
        replaceMob(worldIn, villagerEntity, new Vindicator(EntityType.VINDICATOR, worldIn));
      } else if (profession == VillagerProfession.LIBRARIAN) {
        replaceMob(worldIn, villagerEntity, new Evoker(EntityType.EVOKER, worldIn));
      } else {
        ZombieVillager zombieVillagerEntity =
            new ZombieVillager(EntityType.ZOMBIE_VILLAGER, worldIn);
        zombieVillagerEntity.setVillagerData(villagerEntity.getVillagerData());
        zombieVillagerEntity.setGossips(villagerEntity.getGossips().store(NbtOps.INSTANCE));
        zombieVillagerEntity.setTradeOffers(villagerEntity.getOffers().createTag());
        zombieVillagerEntity.setVillagerXp(villagerEntity.getVillagerXp());
        replaceMob(worldIn, villagerEntity, zombieVillagerEntity);
      }
    }

    // TODO Cows

    if (entityIn instanceof Animal) {
      @Nullable final LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(worldIn);
      if (lightningBolt != null) {
        entityIn.thunderHit((ServerLevel) worldIn, lightningBolt);
      }
    }

    if (entityIn instanceof Player) {
      applyPotion((Player) entityIn, new MobEffectInstance(MobEffects.BLINDNESS, 210, 0));
      applyPotion((Player) entityIn, new MobEffectInstance(MobEffects.WEAKNESS, 210, 2));
      applyPotion((Player) entityIn, new MobEffectInstance(MobEffects.WITHER, 210, 0));
      applyPotion((Player) entityIn, new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 210, 0));
    }
  }

  private void applyPotion(
      @Nonnull final Player entityIn, @Nonnull final MobEffectInstance potionEffect) {
    @Nullable final MobEffectInstance currentEffect = entityIn.getEffect(potionEffect.getEffect());
    if (currentEffect != null && currentEffect.getDuration() <= potionEffect.getDuration() - 20) {
      entityIn.addEffect(potionEffect);
    }
  }

  private void replaceMob(
      @Nonnull final Level world,
      @Nonnull final LivingEntity toKill,
      @Nonnull final LivingEntity toSpawn) {
    toSpawn.moveTo(toKill.getX(), toKill.getY(), toKill.getZ(), toKill.getYRot(), toKill.getXRot());
    toSpawn.yBodyRot = toKill.yBodyRot;
    toSpawn.setHealth(toSpawn.getMaxHealth() * toKill.getHealth() / toKill.getMaxHealth());

    toKill.remove(Entity.RemovalReason.DISCARDED);
    world.addFreshEntity(toSpawn);
  }
}
