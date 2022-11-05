package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import javax.annotation.Nonnull;

import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import novamachina.exnihilosequentia.common.blockentity.crucible.CrucibleTypeEnum;
import novamachina.exnihilosequentia.common.crafting.crucible.CrucibleRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenCrucibleRecipe")
@Document("mods/ExNihiloSequentia/Crucible")
public class ZenCrucibleRecipe {

 @Nonnull
 private final CrucibleRecipe internal;

 private ZenCrucibleRecipe(@Nonnull final ResourceLocation recipeId) {
   this.internal = new CrucibleRecipe(recipeId, Ingredient.EMPTY, 0, FluidStack.EMPTY,
       CrucibleTypeEnum.WOOD);
 }

 @ZenCodeType.Method
 @Nonnull
 public static ZenCrucibleRecipe builder(@Nonnull final ResourceLocation recipeId) {
   return new ZenCrucibleRecipe(recipeId);
 }

 @Nonnull
 public CrucibleRecipe build() {
   return internal;
 }

    /**
     * Sets the amount how much fluid the input will generate
     * @param amount Sets the amount of fuild per input
     */
 @ZenCodeType.Method
 @Nonnull
 public ZenCrucibleRecipe setAmount(final int amount) {
   internal.setAmount(amount);
   return this;
 }

    /**
     * Sets the crucible type. Can only be fired or wood.
     * @param crucibleType Sets the Crucible type (only "fired" or "wood")
     */
 @ZenCodeType.Method
 @Nonnull
 public ZenCrucibleRecipe setCrucibleType(@Nonnull final String crucibleType) {
   internal.setCrucibleType(crucibleType);
   return this;
 }

    /**
     * Sets the input that should be smelted
     * @param input smelting item
     */
 @ZenCodeType.Method
 @Nonnull
 public ZenCrucibleRecipe setInput(@Nonnull final IIngredient input) {
   internal.setInput(input.asVanillaIngredient());
   return this;
 }

    /**
     * Sets the output fluid that should be generated
     * @param fluid generated output
     */
 @ZenCodeType.Method
 @Nonnull
 @SuppressWarnings("unused")
 public ZenCrucibleRecipe setResultFluid(@Nonnull final IFluidStack fluid) {
   internal.setResultFluid(fluid.getInternal());
   return this;
 }
}
