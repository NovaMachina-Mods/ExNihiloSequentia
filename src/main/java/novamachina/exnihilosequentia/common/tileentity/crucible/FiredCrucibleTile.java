package novamachina.exnihilosequentia.common.tileentity.crucible;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.utility.Config;

public class FiredCrucibleTile extends BaseCrucibleTile {

    public FiredCrucibleTile() {
        super(ExNihiloTiles.CRUCIBLE_FIRED.get());
    }

    @Override
    public CrucilbeTypeEnum getCrucibleType() {
        return CrucilbeTypeEnum.FIRED;
    }

    @Override
    public int getSolidAmount() {
        if (!currentItem.isEmpty()) {
            final int itemCount = inventory.getStackInSlot(0).getCount();
            final CrucibleRecipe recipe = ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipeByItemStack(currentItem);
            if (recipe != null)
                return solidAmount + (itemCount * recipe.getAmount());
        }
        return solidAmount;
    }

    @Override
    public boolean canAcceptFluidTemperature(FluidStack fluidStack) {
        return true;
    }

    @Override
    public void tick() {
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

                    final CrucibleRecipe recipe = ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipeByItemStack(currentItem);
                    if (recipe != null)
                        solidAmount = recipe.getAmount();
                } else {
                    return;
                }
            }

            if (!inventory.getStackInSlot(0).isEmpty() && inventory.getStackInSlot(0)
                .sameItem(currentItem)) {
                while (heat > solidAmount && !inventory.getStackInSlot(0).isEmpty()) {
                    final CrucibleRecipe recipe = ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipeByItemStack(currentItem);
                    if (recipe != null) {
                        solidAmount += recipe.getAmount();
                        inventory.getStackInSlot(0).shrink(1);

                        if (inventory.getStackInSlot(0).isEmpty()) {
                            inventory.setStackInSlot(0, ItemStack.EMPTY);
                        }
                    }
                }
            }

            if (heat > solidAmount) {
                heat = solidAmount;
            }

            if (heat > 0 && ExNihiloRegistries.CRUCIBLE_REGISTRY
                .isMeltableByItemStack(currentItem, getCrucibleType().getLevel())) {
                final CrucibleRecipe recipe = ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipeByItemStack(currentItem);
                if (recipe != null) {
                    FluidStack fluidStack = new FluidStack(recipe.getResultFluid(), heat);
                    int filled = tank.fill(fluidStack, FluidAction.EXECUTE);
                    solidAmount -= filled;
                }
            }
        }
        final BaseCrucibleTileState currentState = new BaseCrucibleTileState(this);
        if (!currentState.equals(lastSyncedState)) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
            lastSyncedState = currentState;
        }
    }
}
