package novamachina.exnihilosequentia.common.tileentity.crucible;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.utility.Config;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FiredCrucibleTile extends BaseCrucibleTile {

    public FiredCrucibleTile() {
        this(ExNihiloTiles.CRUCIBLE_FIRED.get());
    }

    public FiredCrucibleTile(TileEntityType<? extends FiredCrucibleTile> tile) {
        super(tile);
    }

    @Override
    @Nonnull
    public CrucibleTypeEnum getCrucibleType() {
        return CrucibleTypeEnum.FIRED;
    }

    @Override
    public int getSolidAmount() {
        if (!currentItem.isEmpty()) {
            final int itemCount = inventory.getStackInSlot(0).getCount();
            @Nullable final CrucibleRecipe recipe = ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipeByItemStack(currentItem);
            if (recipe != null)
                return solidAmount + (itemCount * recipe.getAmount());
        }
        return solidAmount;
    }

    @Override
    public boolean canAcceptFluidTemperature(@Nonnull FluidStack fluidStack) {
        return true;
    }

    @Override
    public void tick() {
        if (level == null || level.isClientSide)
            return;

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

                    @Nonnull final CrucibleRecipe recipe = ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipeByItemStack(currentItem);
                    if (recipe != null)
                        solidAmount = recipe.getAmount();
                } else {
                    return;
                }
            }

            if (!inventory.getStackInSlot(0).isEmpty() && inventory.getStackInSlot(0)
                .sameItem(currentItem)) {
                while (heat > solidAmount && !inventory.getStackInSlot(0).isEmpty()) {
                    @Nonnull final CrucibleRecipe recipe = ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipeByItemStack(currentItem);
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
}
