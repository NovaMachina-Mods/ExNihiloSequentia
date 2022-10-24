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
@ZenCodeType.Name("mods.exnihilosequentia.ZenSieveRecipe")
@Document("mods/ExNihiloSequentia/Sifting")
public class ZenSeiveRecipe {

 @Nonnull
 private final SieveRecipe internal;

 private ZenSeiveRecipe(@Nonnull final ResourceLocation recipeId) {
   this.internal = new SieveRecipe(recipeId, Ingredient.EMPTY, ItemStack.EMPTY, new
ArrayList<>(),
       false);
 }

 @ZenCodeType.Method
 @Nonnull
 public static ZenSeiveRecipe builder(@Nonnull final ResourceLocation recipeId) {
   return new ZenSeiveRecipe(recipeId);
 }

 @ZenCodeType.Method
 @Nonnull
 @SuppressWarnings("unused")
 public ZenSeiveRecipe addDrop(@Nonnull final IItemStack drop) {
   internal.setDrop(drop.getInternal());
   return this;
 }

 @ZenCodeType.Method
 @Nonnull
 public ZenSeiveRecipe setInput(@Nonnull final IIngredient input) {
   internal.setInput(input.asVanillaIngredient());
   return this;
 }

 @ZenCodeType.Method
 @Nonnull
 @SuppressWarnings("unused")
 public ZenSeiveRecipe addRoll(@Nonnull final String mesh, final float chance) {
   internal.addRoll(mesh, chance);
   return this;
 }

 @Nonnull
 public SieveRecipe build() {
   return internal;
 }

 @ZenCodeType.Method
 @Nonnull
 @SuppressWarnings("unused")
 public ZenSeiveRecipe setWaterlogged() {
   internal.setWaterlogged();
   return this;
 }
}
