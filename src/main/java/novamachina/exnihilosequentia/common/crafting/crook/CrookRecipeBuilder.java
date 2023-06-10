package novamachina.exnihilosequentia.common.crafting.crook;

import javax.annotation.Nonnull;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.common.crafting.ExNihiloFinishedRecipe;
import novamachina.exnihilosequentia.common.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.world.item.crafting.EXNRecipeSerializers;

public class CrookRecipeBuilder extends ExNihiloFinishedRecipe<CrookRecipeBuilder> {

  private CrookRecipeBuilder() throws NullPointerException {
    //noinspection ConstantConditions
    super(EXNRecipeSerializers.CROOK_RECIPE_SERIALIZER);
    setMultipleResults(Integer.MAX_VALUE);
  }

  public static CrookRecipeBuilder builder() {
    return new CrookRecipeBuilder();
  }

  @Nonnull
  public CrookRecipeBuilder addDrop(@Nonnull final ItemLike drop, final float chance) {
    return this.addResult(new ItemStackWithChance(new ItemStack(drop), chance));
  }

  @Nonnull
  public CrookRecipeBuilder input(@Nonnull final Block input) {
    return this.addInput(input);
  }

  @Nonnull
  public CrookRecipeBuilder input(@Nonnull final TagKey<Item> tag) {
    return this.addInput(tag);
  }
}
