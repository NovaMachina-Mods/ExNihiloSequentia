package novamachina.exnihilosequentia.common.utility;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class TankUtil {
    private static final ItemStack WATER_BOTTLE;

    static {
        WATER_BOTTLE = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.WATER);
    }

    public static boolean drainWaterIntoBottle(TileEntity tileEntity, PlayerEntity player, IFluidHandler tank) {
        if (player.getHeldItemMainhand().getItem() == Items.GLASS_BOTTLE) {
            if(tank.getFluidInTank(0).getFluid() != null && tank.getFluidInTank(0).getAmount() >= 250 && tank.getFluidInTank(0).getFluid().getFluid() == Fluids.WATER) {
                if(player.addItemStackToInventory(WATER_BOTTLE.copy())){
                    if(!player.isCreative()) {
                        player.getHeldItemMainhand().shrink(1);
                    }
                    tank.drain(250, IFluidHandler.FluidAction.EXECUTE);
                    tileEntity.markDirty();
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean drainWaterFromBottle(TileEntity tileEntity, PlayerEntity player, IFluidHandler tank) {
        if (player.getHeldItemMainhand().getItem() == Items.POTION && WATER_BOTTLE.getTag().equals(player.getHeldItemMainhand().getTag())) {
            FluidStack water = new FluidStack(Fluids.WATER, 250);

            if (tank.fill(water, IFluidHandler.FluidAction.SIMULATE) == water.getAmount()) {
                if (player.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE))) {

                    if (!player.isCreative())
                        player.getHeldItemMainhand().shrink(1);
                    tank.fill(water, IFluidHandler.FluidAction.EXECUTE);

                    tileEntity.markDirty();
                    return true;
                }
            }
        }

        return false;
    }
}
