package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;
//
// import com.blamejared.crafttweaker.api.annotation.ZenRegister;
// import com.blamejared.crafttweaker.api.fluid.IFluidStack;
// import com.blamejared.crafttweaker.api.item.IItemStack;
// import javax.annotation.Nonnull;
// import net.minecraft.resources.ResourceLocation;
// import net.minecraft.world.item.ItemStack;
// import net.minecraftforge.fluids.FluidStack;
// import novamachina.exnihilosequentia.common.crafting.fluidontop.FluidOnTopRecipe;
// import org.openzen.zencode.java.ZenCodeType;
//
// @ZenRegister
// @ZenCodeType.Name("mods.exnihilosequentia.ZenFluidOnTopRecipe")
// public class ZenFluidOnTopRecipe {
//
//  @Nonnull
//  private final FluidOnTopRecipe internal;
//
//  private ZenFluidOnTopRecipe(@Nonnull final ResourceLocation recipeId) {
//    this.internal = new FluidOnTopRecipe(recipeId, FluidStack.EMPTY, FluidStack.EMPTY,
//        ItemStack.EMPTY);
//  }
//
//  @ZenCodeType.Method
//  @Nonnull
//  public static ZenFluidOnTopRecipe builder(@Nonnull final ResourceLocation recipeId) {
//    return new ZenFluidOnTopRecipe(recipeId);
//  }
//
//  @Nonnull
//  public FluidOnTopRecipe build() {
//    return internal;
//  }
//
//  @ZenCodeType.Method
//  @Nonnull
//  public ZenFluidOnTopRecipe setFluidInTank(@Nonnull final IFluidStack fluidInTank) {
//    internal.setFluidInTank(fluidInTank.getInternal());
//    return this;
//  }
//
//  @ZenCodeType.Method
//  @Nonnull
//  public ZenFluidOnTopRecipe setFluidOnTop(@Nonnull final IFluidStack fluidOnTop) {
//    internal.setFluidOnTop(fluidOnTop.getInternal());
//    return this;
//  }
//
//  @ZenCodeType.Method
//  @Nonnull
//  public ZenFluidOnTopRecipe setResult(@Nonnull final IItemStack result) {
//    internal.setResult(result.getInternal());
//    return this;
//  }
// }
