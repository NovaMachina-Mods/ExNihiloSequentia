package novamachina.exnihilosequentia.world.item;

import java.security.SecureRandom;
import javax.annotation.Nonnull;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SnowballItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.sounds.EXNSoundEvents;

public class PebbleItem extends SnowballItem {

  public PebbleItem() {
    super(new Item.Properties());
  }

  @Override
  @Nonnull
  public InteractionResultHolder<ItemStack> use(
      @Nonnull final Level world,
      @Nonnull final Player player,
      @Nonnull final InteractionHand hand) {
    @Nonnull final ItemStack itemstack = player.getItemInHand(hand);
      if (Config.enableThrowable()) {
        world.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                EXNSoundEvents.PEBBLE_THROW,
                SoundSource.NEUTRAL,
                0.5F,
                0.4F / (new SecureRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClientSide) {
          @Nonnull final Snowball snowball =
                  new Snowball(world, player) {
                    @Override
                    public Component getName() {
                      return Component.translatable("item.exnihilosequentia.pebble");
                    }

                    @Override
                    public void onHitEntity(EntityHitResult entityRayTraceResult) {
                      @Nonnull final Entity entity = entityRayTraceResult.getEntity();
                      entity.hurt(this.damageSources().generic(), Config.getPebbleDamage());
                    }
                  };
          snowball.setItem(itemstack);
          snowball.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, 1.5F, 1.0F);
          world.addFreshEntity(snowball);
        }

      player.awardStat(Stats.ITEM_USED.get(this));
      if (!player.getAbilities().instabuild) {
        itemstack.shrink(1);
      }
        return InteractionResultHolder.sidedSuccess(itemstack, world.isClientSide());
    }
    return InteractionResultHolder.pass(itemstack);
  }
}
