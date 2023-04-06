package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import java.util.ArrayList;
import javax.annotation.Nonnull;

import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import novamachina.exnihilosequentia.common.crafting.sieve.SieveRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.SieveRecipe")
@Document("mods/ExNihiloSequentia/Sifting")
public class ZenSieveRecipe {

 @Nonnull
 private final SieveRecipe internal;

 private ZenSieveRecipe(@Nonnull final ResourceLocation recipeId) {
   this.internal = new SieveRecipe(recipeId, Ingredient.EMPTY, ItemStack.EMPTY, new
ArrayList<>(),
       false);
 }

    /**
     * Create a recipe name for the new recipe
     * @param recipeId name of recipe
     */
 @ZenCodeType.Method
 @Nonnull
 public ZenSieveRecipe create(String recipeId) {
     //this is just for creating docs for crafttweaker
     return this;
 }

 @Nonnull
 public static ZenSieveRecipe builder(@Nonnull final ResourceLocation recipeId) {
   return new ZenSieveRecipe(recipeId);
 }

    /**
     * Sets the item that should drop when sifting
     * @param drop dropped item
     */
 @ZenCodeType.Method
 @Nonnull
 @SuppressWarnings("unused")
 public ZenSieveRecipe addDrop(@Nonnull final IItemStack drop) {
   internal.setDrop(drop.getInternal());
   return this;
 }

    /**
     * Sets the input that should be sifted
     * @param input sifted block / item tag
     */
 @ZenCodeType.Method
 @Nonnull
 public ZenSieveRecipe setInput(@Nonnull final IIngredient input) {
   internal.setInput(input.asVanillaIngredient());
   return this;
 }

    /**
     * Sets the mesh type and the chance, how often the item should drop.
     * @param mesh mesh type (only valid meshes are allowed)
     * @param chance the chance betweet 0 and 1 (greater than 1 is not allowed)
     */
 @ZenCodeType.Method
 @Nonnull
 @SuppressWarnings("unused")
 public ZenSieveRecipe addRoll(@Nonnull final String mesh, final float chance) {
   internal.addRoll(mesh.toUpperCase(), chance);
   return this;
 }

 @Nonnull
 public SieveRecipe build() {
   return internal;
 }

    /**
     * (Optional) The drop will only happen when the sieve is waterlogged.
     * @return needs a waterlogged sieve now
     */
 @ZenCodeType.Method
 @Nonnull
 @SuppressWarnings("unused")
 public ZenSieveRecipe setWaterlogged() {
   internal.setWaterlogged();
   return this;
 }
}
