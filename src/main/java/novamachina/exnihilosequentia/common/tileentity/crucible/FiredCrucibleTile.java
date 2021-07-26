package novamachina.exnihilosequentia.common.tileentity.crucible;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.utility.Config;

public class FiredCrucibleTile extends BaseCrucibleTile {

    public FiredCrucibleTile(BlockPos pos, BlockState state) {
        super(ExNihiloTiles.CRUCIBLE_FIRED.get(), pos, state);
    }

    @Override
    public int getHeat() {
        BlockState source = level.getBlockState(worldPosition.below());
        int blockHeat = ExNihiloRegistries.HEAT_REGISTRY.getHeatAmount(source.getBlock());
        if(source.getBlock() instanceof LiquidBlock) {
            int level = 8 - source.getValue(BlockStateProperties.LEVEL);
            double partial = (double)blockHeat / 8;
            int returnVal = (int)Math.ceil(partial * level);
            return returnVal;
        }
        return blockHeat;
    }

    @Override
    public CrucilbeTypeEnum getCrucibleType() {
        return CrucilbeTypeEnum.FIRED;
    }

    @Override
    public int getSolidAmount() {
        if (!currentItem.isEmpty()) {
            int itemCount = inventory.getStackInSlot(0).getCount();
            return solidAmount + (itemCount * ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipe(currentItem.getItem())
                .getAmount());
        }
        return solidAmount;
    }

    @Override
    public boolean canAcceptFluidTemperature(FluidStack fluidStack) {
        return true;
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState, BaseCrucibleTile blockEntity) {
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

                    solidAmount = ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipe(currentItem.getItem())
                        .getAmount();
                } else {
                    return;
                }
            }

            if (!inventory.getStackInSlot(0).isEmpty() && inventory.getStackInSlot(0)
                .sameItem(currentItem)) {
                while (heat > solidAmount && !inventory.getStackInSlot(0).isEmpty()) {
                    solidAmount += ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipe(currentItem.getItem())
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
                .isMeltable(currentItem.getItem(), getCrucibleType().getLevel())) {
                FluidStack fluidStack = new FluidStack(
                    ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipe(currentItem.getItem()).getResultFluid(), heat);
                int filled = tank.fill(fluidStack, FluidAction.EXECUTE);
                solidAmount -= filled;
            }
        }
        level.sendBlockUpdated(blockPos, blockState, blockEntity.getBlockState(), 2);
    }
}
