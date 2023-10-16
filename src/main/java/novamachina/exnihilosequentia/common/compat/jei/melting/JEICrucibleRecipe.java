package novamachina.exnihilosequentia.common.compat.jei.melting;

import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity.CrucibleType;

public class JEICrucibleRecipe {

  private int amount;
  private CrucibleType crucibleType;
  private List<ItemStack> input;
  private FluidStack resultFluid;

  public JEICrucibleRecipe(
      int amount, CrucibleType crucibleType, List<ItemStack> input, FluidStack resultFluid) {
    this.amount = amount;
    this.crucibleType = crucibleType;
    this.input = input;
    this.resultFluid = resultFluid;
  }

  public int getAmount() {
    return amount;
  }

  public CrucibleType getCrucibleType() {
    return crucibleType;
  }

  public List<ItemStack> getInputs() {
    return input;
  }

  public FluidStack getResultFluid() {
    return resultFluid;
  }
}
