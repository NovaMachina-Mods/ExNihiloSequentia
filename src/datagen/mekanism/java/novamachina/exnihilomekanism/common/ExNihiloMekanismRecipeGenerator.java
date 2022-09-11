package novamachina.exnihilomekanism.common;

import java.util.function.Consumer;
import javax.annotation.Nullable;
import mekanism.common.registries.MekanismItems;
import mekanism.common.resource.PrimaryResource;
import mekanism.common.resource.ResourceType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilomekanism.common.init.ExNihiloMekanismItems;
import novamachina.exnihilomekanism.common.utility.ExNihiloMekanismConstants;
import novamachina.exnihilomekanism.common.utility.ExNihiloMekanismConstants.ModIds;
import novamachina.exnihilosequentia.api.datagen.AbstractRecipeGenerator;
import novamachina.exnihilosequentia.common.crafting.sieve.MeshWithChance;
import novamachina.exnihilosequentia.common.crafting.sieve.SieveRecipeBuilder;
import novamachina.exnihilosequentia.common.item.mesh.MeshType;
import org.jetbrains.annotations.NotNull;

public class ExNihiloMekanismRecipeGenerator extends AbstractRecipeGenerator {

  public ExNihiloMekanismRecipeGenerator(DataGenerator generator) {
    super(generator, ExNihiloMekanismConstants.ModIds.EX_NIHILO_MEKANISM);
  }

  @Override
  protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
    registerCrafting(consumer);
    registerSieve(consumer);
  }

  private void registerCrafting(Consumer<FinishedRecipe> consumer) {
    Item item = ExNihiloMekanismItems.OSMIUM_PIECES.get();
    ShapedRecipeBuilder.shaped(
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.OSMIUM))
        .pattern("xx")
        .pattern("xx")
        .define('x', item)
        .unlockedBy("has_piece", InventoryChangeTrigger.TriggerInstance.hasItems(item))
        .save(
            consumer,
            new ResourceLocation(
                ModIds.EX_NIHILO_MEKANISM,
                prependRecipePrefix(ForgeRegistries.ITEMS.getKey(item).getPath())));
  }

  private void registerSieve(Consumer<FinishedRecipe> consumer) {
    @Nullable final Item osmiumPiece = ExNihiloMekanismItems.OSMIUM_PIECES.get();
    if (osmiumPiece == null) {
      return;
    }
    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.GRAVEL))
        .drop(osmiumPiece)
        .addRoll(new MeshWithChance(MeshType.IRON, 0.05F))
        .addRoll(new MeshWithChance(MeshType.DIAMOND, 0.1F))
        .build(consumer, sieveLoc("osmium_pieces"));

    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.GRAVEL))
        .drop(MekanismItems.FLUORITE_GEM.asItem())
        .addRoll(new MeshWithChance(MeshType.IRON, 0.025F))
        .addRoll(new MeshWithChance(MeshType.DIAMOND, 0.05F))
        .build(consumer, sieveLoc(MekanismItems.FLUORITE_GEM.getRegistryName().getPath()));

    SieveRecipeBuilder.builder()
        .input(Ingredient.of(Blocks.SAND))
        .drop(MekanismItems.SALT.asItem())
        .addRoll(new MeshWithChance(MeshType.FLINT, 0.01F))
        .addRoll(new MeshWithChance(MeshType.IRON, 0.02F))
        .addRoll(new MeshWithChance(MeshType.DIAMOND, 0.4F))
        .build(consumer, sieveLoc(MekanismItems.SALT.getRegistryName().getPath()));
  }
}
