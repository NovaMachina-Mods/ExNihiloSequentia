package novamachina.exnihiloae.common;

//import appeng.core.definitions.AEBlocks;
//import appeng.core.definitions.AEItems;
import java.util.function.Consumer;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluids;
import novamachina.exnihiloae.common.init.ExNihiloAEBlocks;
import novamachina.exnihiloae.common.utility.ExNihiloAEConstants;
import novamachina.exnihilosequentia.api.datagen.AbstractRecipeGenerator;
import novamachina.exnihilosequentia.common.crafting.fluiditem.FluidItemRecipeBuilder;
import novamachina.exnihilosequentia.common.crafting.hammer.HammerRecipeBuilder;
import novamachina.exnihilosequentia.common.crafting.sieve.MeshWithChance;
import novamachina.exnihilosequentia.common.crafting.sieve.SieveRecipeBuilder;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.item.mesh.MeshType;

public class ExNihiloAERecipes extends AbstractRecipeGenerator {

  public ExNihiloAERecipes(DataGenerator generator) {
    super(generator, ExNihiloAEConstants.ModIds.EX_NIHILO_AE);
  }

  @Override
  protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
    registerSieveRecipes(consumer);
    registerFluidItemRecipes(consumer);
    registerHammerRecipes(consumer);
  }

  private void registerHammerRecipes(Consumer<FinishedRecipe> consumer) {
//    HammerRecipeBuilder.builder().input(AEBlocks.SKY_STONE_BLOCK)
//        .addDrop(ExNihiloAEBlocks.CRUSHED_SKYSTONE.get())
//        .build(consumer, hammerLoc(ExNihiloAEConstants.Blocks.CRUSHED_SKYSTONE));
  }

  private void registerFluidItemRecipes(Consumer<FinishedRecipe> consumer) {
//    FluidItemRecipeBuilder.builder().fluidInBarrel(Fluids.LAVA)
//        .input(AEItems.SKY_DUST)
//        .result(AEBlocks.SKY_STONE_BLOCK)
//        .build(consumer, fluidItemLoc("sky_stone"));
  }

  private void registerSieveRecipes(Consumer<FinishedRecipe> consumer) {
//    SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.DUST.get()))
//        .drop(AEItems.SKY_DUST)
//        .addRoll(new MeshWithChance(MeshType.FLINT, 0.1F))
//        .addRoll(new MeshWithChance(MeshType.IRON, 0.2F))
//        .addRoll(new MeshWithChance(MeshType.DIAMOND, 0.3F))
//        .build(consumer, sieveLoc("sky_dust"));
//
//    SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.DUST.get()))
//        .drop(AEItems.CERTUS_QUARTZ_DUST)
//        .addRoll(new MeshWithChance(MeshType.IRON, 0.15F))
//        .build(consumer, sieveLoc("certus_dust"));
//
//    SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloAEBlocks.CRUSHED_SKYSTONE.get()))
//        .drop(AEItems.CERTUS_QUARTZ_CRYSTAL)
//        .addRoll(new MeshWithChance(MeshType.IRON, 0.7F))
//        .addRoll(new MeshWithChance(MeshType.DIAMOND, 0.8F))
//        .build(consumer, sieveLoc("certus_quartz"));
//
//    SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloAEBlocks.CRUSHED_SKYSTONE.get()))
//        .drop(AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED)
//        .addRoll(new MeshWithChance(MeshType.DIAMOND, 0.1F))
//        .build(consumer, sieveLoc("charged_certus_quartz"));
//
//    SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloAEBlocks.CRUSHED_SKYSTONE.get()))
//        .drop(AEItems.CERTUS_CRYSTAL_SEED)
//        .addRoll(new MeshWithChance(MeshType.STRING, 0.35F))
//        .build(consumer, sieveLoc("certus_seed"));
//
//    SieveRecipeBuilder.builder().input(Ingredient.of(ExNihiloBlocks.DUST.get()))
//        .drop(AEItems.FLUIX_DUST)
//        .addRoll(new MeshWithChance(MeshType.DIAMOND, 0.03F))
//        .addRoll(new MeshWithChance(MeshType.EMERALD, 0.06F))
//        .build(consumer, sieveLoc("fluix_dust"));
  }
}
