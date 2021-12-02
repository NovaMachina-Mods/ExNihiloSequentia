package novamachina.exnihilosequentia.common.tileentity.crucible;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;

public class FiredCrucibleTile extends BaseCrucibleTile {

    public FiredCrucibleTile(BlockPos pos, BlockState state) {
        super(ExNihiloTiles.CRUCIBLE_FIRED.get(), pos, state);
    }

    @Override
    public int getHeat() {
        assert level != null;
        BlockState source = level.getBlockState(worldPosition.below());
        int blockHeat = ExNihiloRegistries.HEAT_REGISTRY.getHeatAmount(source.getBlock());
        if(source.getBlock() instanceof LiquidBlock) {
            int level = 8 - source.getValue(BlockStateProperties.LEVEL);
            double partial = (double)blockHeat / 8;
            return (int)Math.ceil(partial * level);
        }
        return blockHeat;
    }

    @Override
    public CrucibleTypeEnum getCrucibleType() {
        return CrucibleTypeEnum.FIRED;
    }

    @Override
    public int getSolidAmount() {
        if (!currentItem.isEmpty()) {
            int itemCount = inventory.getStackInSlot(0).getCount();
            return solidAmount + (itemCount * ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipeByItemStack(currentItem)
                .getAmount());
        }
        return solidAmount;
    }

    @Override
    public boolean canAcceptFluidTemperature(FluidStack fluidStack) {
        return true;
    }

    public void tick() {
        assert level != null;
        if (level.isClientSide()) {
            return;
        }

        inventory.setCrucibleHasRoom(tank.getFluidAmount() < MAX_FLUID_AMOUNT);
        ticksSinceLast++;

        if (ticksSinceLast >= Config.getTicksBetweenMelts()) {
            ticksSinceLast = 0;

            int heat = getHeat();
            if (heat <= 0) {
                return;
            }
            if (solidAmount <= 0) {
                if (!inventory.getStackInSlot(0).isEmpty()) {
                    currentItem = inventory.getStackInSlot(0).copy();
                    inventory.getStackInSlot(0).shrink(1);

                    if (inventory.getStackInSlot(0).isEmpty()) {
                        inventory.setStackInSlot(0, ItemStack.EMPTY);
                    }

                    solidAmount = ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipeByItemStack(currentItem)
                        .getAmount();
                } else {
                    return;
                }
            }

            if (!inventory.getStackInSlot(0).isEmpty() && inventory.getStackInSlot(0)
                .sameItem(currentItem)) {
                while (heat > solidAmount && !inventory.getStackInSlot(0).isEmpty()) {
                    solidAmount += ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipeByItemStack(currentItem)
                        .getAmount();
                    inventory.getStackInSlot(0).shrink(1);

                    if (inventory.getStackInSlot(0).isEmpty()) {
                        inventory.setStackInSlot(0, ItemStack.EMPTY);
                    }
                }
            }

            if (heat > solidAmount) {
                heat = solidAmount;
            }

            if (heat > 0 && ExNihiloRegistries.CRUCIBLE_REGISTRY
                .isMeltableByItemStack(currentItem, getCrucibleType().getLevel())) {
                FluidStack fluidStack = new FluidStack(
                    ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipeByItemStack(currentItem).getResultFluid(), heat);
                int filled = tank.fill(fluidStack, FluidAction.EXECUTE);
                solidAmount -= filled;
            }
        }
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
    }
}
