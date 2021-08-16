package novamachina.exnihilosequentia.common.item.pebbles;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SnowballItem;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.Level;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;
import novamachina.exnihilosequentia.common.init.ExNihiloSounds;
import novamachina.exnihilosequentia.common.utility.Config;

import javax.annotation.Nonnull;
import java.util.Random;

public class PebbleItem extends SnowballItem {

    public PebbleItem() {
        super(new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP));
    }

    @Nonnull
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, @Nonnull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        //TODO Placeable like redstone?
        world.playSound(null, player.getX(), player.getY(), player.getZ(), ExNihiloSounds.PEBBLE_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (new Random().nextFloat() * 0.4F + 0.8F));
        if (!world.isClientSide) {
            Snowball snowballEntity = new Snowball(world, player){
                @Override
                public void onHitEntity(EntityHitResult entityRayTraceResult) {
                    Entity entity = entityRayTraceResult.getEntity();
                    entity.hurt(DamageSource.thrown(this, this.getOwner()), (float) Config.getPebbleDamage());
                }
            };
            snowballEntity.setItem(itemstack);
            snowballEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            world.addFreshEntity(snowballEntity);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, world.isClientSide());
    }

}
