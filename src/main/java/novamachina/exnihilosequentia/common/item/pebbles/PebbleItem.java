package novamachina.exnihilosequentia.common.item.pebbles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SnowballItem;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;
import novamachina.exnihilosequentia.common.init.ExNihiloSounds;
import novamachina.exnihilosequentia.common.utility.Config;

public class PebbleItem extends SnowballItem {
    public PebbleItem() {
        super(new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP));
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        //TODO Placeable like redstone?
        world.playSound(null, player.getX(), player.getY(), player.getZ(), ExNihiloSounds.PEBBLE_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!world.isClientSide) {
            SnowballEntity snowballentity = new SnowballEntity(world, player){
                @Override
                public void onHitEntity(EntityRayTraceResult entityRayTraceResult) {
                    Entity entity = entityRayTraceResult.getEntity();
                    entity.hurt(DamageSource.thrown(this, this.getOwner()), (float) Config.getPebbleDamage());
                }
            };
            snowballentity.setItem(itemstack);
            snowballentity.shootFromRotation(player, player.xRot, player.yRot, 0.0F, 1.5F, 1.0F);
            world.addFreshEntity(snowballentity);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.abilities.instabuild) {
            itemstack.shrink(1);
        }

        return ActionResult.sidedSuccess(itemstack, world.isClientSide());
    }

}
