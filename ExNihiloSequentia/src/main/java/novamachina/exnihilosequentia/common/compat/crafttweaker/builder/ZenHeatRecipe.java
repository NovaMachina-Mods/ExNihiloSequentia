package novamachina.exnihilosequentia.common.compat.crafttweaker.builder;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import javax.annotation.Nonnull;

import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.common.crafting.heat.HeatRecipe;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.exnihilosequentia.ZenHeatRecipe")
@Document("mods/ExNihiloSequentia/Heat")
public class ZenHeatRecipe {

 @Nonnull
 private final HeatRecipe internal;

 private ZenHeatRecipe(@Nonnull final ResourceLocation recipeId) {
   this.internal = new HeatRecipe(recipeId, null, 0);
 }

 @ZenCodeType.Method
 @Nonnull
 public static ZenHeatRecipe builder(@Nonnull final ResourceLocation recipeId) {
   return new ZenHeatRecipe(recipeId);
 }

 @Nonnull
 public HeatRecipe build() {
   return internal;
 }

    /**
     * Sets the amount of heat that the given block should generate.
     * @param amount The amount of heat.
     */

 @ZenCodeType.Method
 @Nonnull
 public ZenHeatRecipe setAmount(final int amount) {
   internal.setAmount(amount);
   return this;
 }

    /**
     * Sets the block that should be modified to generate heat.
     * @param input Sets the block.
     */

 @ZenCodeType.Method
 @Nonnull
 public ZenHeatRecipe setBlock(@Nonnull final Block input) {
   internal.setInput(input);
   return this;
 }

    /**
     * (Optional) Sets the state that should be used to generate heat.
     * @param properties Sets special properties.
     */

 @ZenCodeType.Method
 @Nonnull
 public ZenHeatRecipe setProperties(@Nonnull final StatePropertiesPredicate properties) {
   internal.setProperties(properties);
   return this;
 }
}
