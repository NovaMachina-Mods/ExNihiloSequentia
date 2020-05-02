package com.novamachina.ens.common.item.pebbles;

import com.novamachina.ens.common.setup.ModInitialization;
import net.minecraft.item.Item;

public class PebbleItem extends Item {

    private final EnumPebbleType type;

    public PebbleItem(EnumPebbleType type) {
        super(new Item.Properties().group(ModInitialization.ITEM_GROUP));
        this.type = type;
    }

    // TODO: Throwable pebbles
//    @Override
//    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn,
//        Hand handIn) {
//        ItemStack stack = playerIn.getHeldItem(handIn);
//
//        stack.shrink(1);
//        worldIn.playSound(playerIn, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(),
//            SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
//
//        if(!worldIn.isRemote()) {
//            ItemStack thrown = stack.copy();
//            thrown.setCount(1);
//
//            ProjectileStone projectile = new ProjectileStone(type, playerIn, worldIn);
//            projectile.setItem(thrown);
//            projectile.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 0.5F);
//            worldIn.addEntity(projectile);
//
//        }
//        return ActionResult.resultSuccess(stack);
//    }
}
