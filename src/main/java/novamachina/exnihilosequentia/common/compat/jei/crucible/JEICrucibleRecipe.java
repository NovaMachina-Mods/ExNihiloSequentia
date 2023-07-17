package novamachina.exnihilosequentia.common.compat.jei.crucible;

import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.blockentity.crucible.CrucibleTypeEnum;

public class JEICrucibleRecipe {

  private int amount;
  private CrucibleTypeEnum crucibleType;
  private List<ItemStack> input;
  private FluidStack resultFluid;

  public JEICrucibleRecipe(
      int amount, CrucibleTypeEnum crucibleType, List<ItemStack> input, FluidStack resultFluid) {
    this.amount = amount;
    this.crucibleType = crucibleType;
    this.input = input;
    this.resultFluid = resultFluid;
  }

  public int getAmount() {
    return amount;
  }

  public CrucibleTypeEnum getCrucibleType() {
    return crucibleType;
  }

  public List<ItemStack> getInputs() {
    return input;
  }

  public FluidStack getResultFluid() {
    return resultFluid;
  }
}
