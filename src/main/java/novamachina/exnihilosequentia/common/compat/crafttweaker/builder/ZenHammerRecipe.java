package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;
//
//import com.blamejared.crafttweaker.api.annotation.ZenRegister;
//import com.blamejared.crafttweaker.api.ingredient.IIngredient;
//import com.blamejared.crafttweaker.api.item.IItemStack;
//import java.util.ArrayList;
//import javax.annotation.Nonnull;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.crafting.Ingredient;
//import novamachina.exnihilosequentia.common.crafting.hammer.HammerRecipe;
//import org.openzen.zencode.java.ZenCodeType;
//
//@ZenRegister
//@ZenCodeType.Name("mods.exnihilosequentia.ZenHammerRecipe")
//public class ZenHammerRecipe {
//
//  @Nonnull
//  private final HammerRecipe internal;
//
//  private ZenHammerRecipe(@Nonnull final ResourceLocation recipeId) {
//    this.internal = new HammerRecipe(recipeId, Ingredient.EMPTY, new ArrayList<>());
//  }
//
//  @ZenCodeType.Method
//  @Nonnull
//  public static ZenHammerRecipe builder(@Nonnull final ResourceLocation recipeId) {
//    return new ZenHammerRecipe(recipeId);
//  }
//
//  @ZenCodeType.Method
//  @Nonnull
//  @SuppressWarnings("unused")
//  public ZenHammerRecipe addOutput(@Nonnull final IItemStack output) {
//    internal.addOutput(output.getInternal());
//    return this;
//  }
//
//  @ZenCodeType.Method
//  @Nonnull
//  @SuppressWarnings("unused")
//  public ZenHammerRecipe addOutput(@Nonnull final IItemStack output, final float chance) {
//    internal.addOutput(output.getInternal(), chance);
//    return this;
//  }
//
//  @Nonnull
//  public HammerRecipe build() {
//    return internal;
//  }
//
//  @ZenCodeType.Method
//  @Nonnull
//  public ZenHammerRecipe setInput(@Nonnull final IIngredient input) {
//    internal.setInput(input.asVanillaIngredient());
//    return this;
//  }
//}
