package novamachina.exnihilothermal.datagen.common;

// import cofh.thermal.core.ThermalCore;

import java.util.function.Consumer;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.datagen.api.AbstractRecipeGenerator;
import novamachina.exnihilothermal.api.ExNihiloThermalTags;
import novamachina.exnihilothermal.common.init.ExNihiloThermalItems;
import novamachina.exnihilothermal.common.utility.ExNihiloThermalConstants;

public class ExNihiloThermalRecipeGenerator extends AbstractRecipeGenerator {

  public ExNihiloThermalRecipeGenerator(DataGenerator generator) {
    super(generator, ExNihiloThermalConstants.ModIds.EX_NIHILO_THERMAL);
  }

  @Override
  protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
    ShapedRecipeBuilder.shaped(ExNihiloThermalItems.BASALZ_DOLL.get())
        .pattern("xvx")
        .pattern("zyz")
        .pattern("xwx")
        .define('x', ExNihiloThermalTags.DUST_OBSIDIAN)
        .define('v', Tags.Items.DUSTS_REDSTONE)
        .define('z', Tags.Items.DUSTS_GLOWSTONE)
        .define('y', ExNihiloItems.CRAFTING_DOLL.get())
        .define('w', Tags.Items.CROPS_NETHER_WART)
        .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
        .unlockedBy(
            "has_doll",
            InventoryChangeTrigger.TriggerInstance.hasItems(ExNihiloItems.CRAFTING_DOLL.get()))
        .save(consumer, createSaveLocation(ExNihiloThermalItems.BASALZ_DOLL.getId()));
    ShapedRecipeBuilder.shaped(ExNihiloThermalItems.BLIZZ_DOLL.get())
        .pattern("xvx")
        .pattern("zyz")
        .pattern("xwx")
        .define('x', Items.SNOWBALL)
        .define('v', Tags.Items.DUSTS_REDSTONE)
        .define('z', Tags.Items.DUSTS_GLOWSTONE)
        .define('y', ExNihiloItems.CRAFTING_DOLL.get())
        .define('w', Tags.Items.CROPS_NETHER_WART)
        .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
        .unlockedBy(
            "has_doll",
            InventoryChangeTrigger.TriggerInstance.hasItems(ExNihiloItems.CRAFTING_DOLL.get()))
        .save(consumer, createSaveLocation(ExNihiloThermalItems.BLIZZ_DOLL.getId()));
    //        ShapedRecipeBuilder.shaped(ExNihiloThermalItems.BLITZ_DOLL.get())
    //                .pattern("xvx")
    //                .pattern("zyz")
    //                .pattern("xwx")
    //                .define('x', ThermalCore.ITEMS.get("niter_dust"))
    //                .define('v', Tags.Items.DUSTS_REDSTONE)
    //                .define('z', Tags.Items.DUSTS_GLOWSTONE)
    //                .define('y', ExNihiloItems.CRAFTING_DOLL.get())
    //                .define('w', Tags.Items.CROPS_NETHER_WART)
    //                .group(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
    //                .unlockedBy("has_doll",
    // InventoryChangeTrigger.TriggerInstance.hasItems(ExNihiloItems.CRAFTING_DOLL.get()))
    //                .save(consumer, createSaveLocation(ExNihiloThermalItems.BLITZ_DOLL.getId()));

    //    HammerRecipeBuilder.builder()
    //        .input(Ingredient.of(Tags.Items.OBSIDIAN))
    //        .addDrop(ExNihiloThermalItems.DUST_OBSIDIAN.get(), 4)
    //        .build(consumer, hammerLoc("dust_obsidian"));

    //    SieveRecipeBuilder.builder()
    //            .input(Ingredient.of(Tags.Items.GRAVEL))
    //            .addResult(ThermalCore.ITEMS.get("niter"))
    //            .addRoll(new MeshWithChance(ExNihiloItems.MESH_FLINT.get().getType(), 0.05F))
    //            .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.1F))
    //            .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.15F))
    //            .build(consumer, sieveLoc("niter"));

    //    SieveRecipeBuilder.builder()
    //            .input(Ingredient.of(Tags.Items.GRAVEL))
    //            .addResult(ThermalCore.ITEMS.get("sulfur"))
    //            .addRoll(new MeshWithChance(ExNihiloItems.MESH_FLINT.get().getType(), 0.05F))
    //            .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.1F))
    //            .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.15F))
    //            .build(consumer, sieveLoc("sulfur"));
    //
    //    SieveRecipeBuilder.builder()
    //            .input(Ingredient.of(Tags.Items.GRAVEL))
    //            .addResult(ThermalCore.ITEMS.get("apatite"))
    //            .addRoll(new MeshWithChance(ExNihiloItems.MESH_FLINT.get().getType(), 0.05F))
    //            .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.1F))
    //            .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.15F))
    //            .build(consumer, sieveLoc("apatite"));
    //
    //    SieveRecipeBuilder.builder()
    //            .input(Ingredient.of(Tags.Items.GRAVEL))
    //            .addResult(ThermalCore.ITEMS.get("cinnabar"))
    //            .addRoll(new MeshWithChance(ExNihiloItems.MESH_FLINT.get().getType(), 0.05F))
    //            .addRoll(new MeshWithChance(ExNihiloItems.MESH_IRON.get().getType(), 0.1F))
    //            .addRoll(new MeshWithChance(ExNihiloItems.MESH_DIAMOND.get().getType(), 0.15F))
    //            .build(consumer, sieveLoc("cinnabar"));
  }
}
