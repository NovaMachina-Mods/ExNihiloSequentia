package novamachina.exnihilosequentia.common.tileentity.crucible;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.utility.Config;

import javax.annotation.Nonnull;

public class WoodCrucibleTile extends BaseCrucibleTile {

    public WoodCrucibleTile(BlockPos pos, BlockState state) {
        this(ExNihiloTiles.CRUCIBLE_WOOD.get(), pos, state);
    }

    public WoodCrucibleTile(BlockEntityType<? extends WoodCrucibleTile> tile, BlockPos pos, BlockState state) {
        super(tile, pos, state);
    }

    public void tickServer() {
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
                @Nonnull final CrucibleRecipe recipe = ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipeByItemStack(currentItem);
                if (recipe != null) {
                    FluidStack fluidStack = new FluidStack(recipe.getResultFluid(), heat);
                    int filled = tank.fill(fluidStack, FluidAction.EXECUTE);
                    solidAmount -= filled;
                }
            }
        }
        @Nonnull final BaseCrucibleTileState currentState = new BaseCrucibleTileState(this);
        if (!currentState.equals(lastSyncedState)) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
            lastSyncedState = currentState;
        }
    }

    @Override
    public int getHeat() {
        return Math.max(super.getHeat(), Config.getWoodHeatRate());
    }

    @Override
    @Nonnull
    public CrucibleTypeEnum getCrucibleType() {
        return CrucibleTypeEnum.WOOD;
    }

    @Override
    public int getSolidAmount() {
        if(!currentItem.isEmpty()) {
            @Nonnull final CrucibleRecipe recipe = ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipeByItemStack(currentItem);
            if (recipe != null) {
                int itemCount = inventory.getStackInSlot(0).getCount();
                return solidAmount + (itemCount * recipe.getAmount());
            }
        }
        return solidAmount;
    }

    @Override
    public boolean canAcceptFluidTemperature(@Nonnull final FluidStack fluidStack) {
        return fluidStack.getFluid().getAttributes().getTemperature() <= Config.getWoodBarrelMaxTemp();
    }

}
