package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import java.util.ArrayList;
import javax.annotation.Nonnull;

import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import novamachina.exnihilosequentia.common.crafting.hammer.HammerRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.HammerRecipe")
@Document("mods/ExNihiloSequentia/Hammering")
public class ZenHammerRecipe {

 @Nonnull
 private final HammerRecipe internal;

 private ZenHammerRecipe(@Nonnull final ResourceLocation recipeId) {
   this.internal = new HammerRecipe(recipeId, Ingredient.EMPTY, new ArrayList<>());
 }

    /**
     * Create a recipe name for the new recipe
     * @param recipeId name of recipe
     */
    @ZenCodeType.Method
    @Nonnull
    public ZenHammerRecipe create(String recipeId) {
        //this is just for creating docs for crafttweaker
        return this;
    }

 @Nonnull
 public static ZenHammerRecipe builder(@Nonnull final ResourceLocation recipeId) {
   return new ZenHammerRecipe(recipeId);
 }

    /**
     * Sets the output with 100% drop chance
     * @param output dropped item
     */
 @ZenCodeType.Method
 @Nonnull
 @SuppressWarnings("unused")
 public ZenHammerRecipe addOutput(@Nonnull final IItemStack output) {
   internal.addOutput(output.getInternal());
   return this;
 }

    /**
     * Sets the output with custom drop chance
     * @param output dropped item
     * @param chance drop chance
     */
 @ZenCodeType.Method
 @Nonnull
 @SuppressWarnings("unused")
 public ZenHammerRecipe addOutput(@Nonnull final IItemStack output, final float chance) {
   internal.addOutput(output.getInternal(), chance);
   return this;
 }

 @Nonnull
 public HammerRecipe build() {
   return internal;
 }

 /**
  * Sets the block that should be hammered
  * @param input hammered block
  */
 @ZenCodeType.Method
 @Nonnull
 public ZenHammerRecipe setInput(@Nonnull final IIngredient input) {
   internal.setInput(input.asVanillaIngredient());
   return this;
 }
}
