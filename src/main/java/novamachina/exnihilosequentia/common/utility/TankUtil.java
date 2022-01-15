package novamachina.exnihilosequentia.common.utility;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nonnull;

public class TankUtil {
    private static final ItemStack WATER_BOTTLE;

    static {
        WATER_BOTTLE = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER);
    }

    // TODO: Figure out how to completely fill and drain
    public static boolean drainWaterIntoBottle(@Nonnull final TileEntity tileEntity, @Nonnull final PlayerEntity player,
                                               @Nonnull final IFluidHandler tank) {
        int waterAmount = 333;
        if(tank.getFluidInTank(0).getAmount() % FluidAttributes.BUCKET_VOLUME == 334) {
            waterAmount = 334;
        }
        if (player.getMainHandItem().getItem() == Items.GLASS_BOTTLE) {
            if(tank.getFluidInTank(0).getFluid() != null
                    && tank.getFluidInTank(0).getAmount() >= waterAmount
                    && tank.getFluidInTank(0).getFluid().getFluid() == Fluids.WATER) {
                if(player.addItem(WATER_BOTTLE.copy())){
                    if(!player.isCreative()) {
                        player.getMainHandItem().shrink(1);
                    }
                    tank.drain(waterAmount, IFluidHandler.FluidAction.EXECUTE);
                    tileEntity.setChanged();
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean drainWaterFromBottle(@Nonnull final TileEntity tileEntity, @Nonnull final PlayerEntity player,
                                               @Nonnull final IFluidHandler tank) {
        int waterAmount = 333;
        if(tank.getFluidInTank(0).getAmount() % FluidAttributes.BUCKET_VOLUME == 666) {
            waterAmount = 334;
        }
        if (player.getMainHandItem().getItem() == Items.POTION
                && WATER_BOTTLE.getTag() != null
                && WATER_BOTTLE.getTag().equals(player.getMainHandItem().getTag())) {
            FluidStack water = new FluidStack(Fluids.WATER, waterAmount);

            if (tank.fill(water, IFluidHandler.FluidAction.SIMULATE) == water.getAmount()) {
                if (player.addItem(new ItemStack(Items.GLASS_BOTTLE))) {

                    if (!player.isCreative())
                        player.getMainHandItem().shrink(1);
                    tank.fill(water, IFluidHandler.FluidAction.EXECUTE);

                    tileEntity.setChanged();
                    return true;
                }
            }
        }

        return false;
    }
}
