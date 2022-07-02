package novamachina.exnihilotinkers.common;

import java.util.function.Consumer;
import javax.annotation.Nonnull;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import novamachina.exnihilosequentia.api.datagen.AbstractRecipeGenerator;
import novamachina.exnihilosequentia.common.crafting.fluiditem.FluidItemRecipeBuilder;
import novamachina.exnihilosequentia.common.crafting.sieve.MeshWithChance;
import novamachina.exnihilosequentia.common.crafting.sieve.SieveRecipeBuilder;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.init.ExNihiloFluids;
import novamachina.exnihilosequentia.common.item.mesh.MeshType;
import novamachina.exnihilotinkers.common.init.EXNTinkersBlocks;
import novamachina.exnihilotinkers.common.init.EXNTinkersItems;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;
import slimeknights.mantle.recipe.ingredient.FluidIngredient;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.block.SlimeType;
import slimeknights.tconstruct.world.TinkerWorld;

public class EXNTinkersRecipes extends AbstractRecipeGenerator {

  public EXNTinkersRecipes(DataGenerator generator) {
    super(generator, EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS);
  }

  private ResourceLocation tinkersLoc(String id) {
    return new ResourceLocation(EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS,
        "tinkers/casting_" + id);
  }

  @Override
  protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
    createOre(EXNTinkersItems.COBALT, TinkerWorld.rawCobalt.get(), consumer);
    registerSieve(consumer);
    registerFluidItem(consumer);
    registerTConstructAdditions(consumer);
    registerBlockRecipes(consumer);
  }

    private void registerBlockRecipes(Consumer<FinishedRecipe> consumer) {
        //barrels
        createBarrel(consumer, EXNTinkersBlocks.BARREL_SKYROOT, TinkerWorld.skyroot.get().asItem(), TinkerWorld.skyroot.getSlab().asItem());
        createBarrel(consumer, EXNTinkersBlocks.BARREL_BLOODSHROOM, TinkerWorld.bloodshroom.get().asItem(), TinkerWorld.bloodshroom.getSlab().asItem());
        createBarrel(consumer, EXNTinkersBlocks.BARREL_GREENHEART, TinkerWorld.greenheart.get().asItem(), TinkerWorld.greenheart.getSlab().asItem());
        //crucibles
        createCrucible(consumer, EXNTinkersBlocks.CRUCIBLE_SKYROOT, TinkerWorld.skyroot.getLog().asItem(), TinkerWorld.skyroot.getSlab().asItem());
        createCrucible(consumer, EXNTinkersBlocks.CRUCIBLE_BLOODSHROOM, TinkerWorld.bloodshroom.getLog().asItem(), TinkerWorld.bloodshroom.getSlab().asItem());
        createCrucible(consumer, EXNTinkersBlocks.CRUCIBLE_GREENHEART, TinkerWorld.greenheart.getLog().asItem(), TinkerWorld.greenheart.getSlab().asItem());
        //sieves
        createSieve(consumer, EXNTinkersBlocks.SIEVE_SKYROOT, TinkerWorld.skyroot.get().asItem(), TinkerWorld.skyroot.getSlab().asItem());
        createSieve(consumer, EXNTinkersBlocks.SIEVE_BLOODSHROOM, TinkerWorld.bloodshroom.get().asItem(), TinkerWorld.bloodshroom.getSlab().asItem());
        createSieve(consumer, EXNTinkersBlocks.SIEVE_GREENHEART, TinkerWorld.greenheart.get().asItem(), TinkerWorld.greenheart.getSlab().asItem());
    }

    private void registerFluidItem(Consumer<FinishedRecipe> consumer) {
        FluidItemRecipeBuilder.builder().fluidInBarrel(ExNihiloFluids.WITCH_WATER.get()).input(Items.BONE_MEAL)
                .result(Blocks.SLIME_BLOCK).build(consumer, fluidItemLoc("slime_block"));
    }

    private void registerTConstructAdditions(Consumer<FinishedRecipe> consumer) {
        ItemCastingRecipeBuilder.basinRecipe(TinkerWorld.slimeDirt.get(SlimeType.SKY)).setCast(Items.DIRT, true)
                .setFluid(FluidIngredient.of(TinkerFluids.skySlime.get(), 1000))
                .setCoolingTime(40).save(consumer, tinkersLoc("sky_slime_dirt"));
        ItemCastingRecipeBuilder.basinRecipe(TinkerWorld.slimeDirt.get(SlimeType.EARTH)).setCast(Items.DIRT, true)
                .setFluid(FluidIngredient.of(TinkerFluids.earthSlime.get(), 1000))
                .setCoolingTime(40).save(consumer, tinkersLoc("earth_slime_dirt"));
        ItemCastingRecipeBuilder.basinRecipe(TinkerWorld.slimeDirt.get(SlimeType.ICHOR)).setCast(Items.DIRT, true)
                .setFluid(FluidIngredient.of(TinkerFluids.magma.get(), 1000))
                .setCoolingTime(40).save(consumer, tinkersLoc("ichor_slime_dirt"));
        ItemCastingRecipeBuilder.basinRecipe(TinkerWorld.slimeDirt.get(SlimeType.ENDER)).setCast(Items.DIRT, true)
                .setFluid(FluidIngredient.of(TinkerFluids.enderSlime.get(), 1000))
                .setCoolingTime(40).save(consumer, tinkersLoc("ender_slime_dirt"));
    }

    private void registerSieve(Consumer<FinishedRecipe> consumer) {
        //Crushed netherrack to...
        //Cobalt pieces
        assert EXNTinkersItems.COBALT.getPieceItem() != null;
        SieveRecipeBuilder.builder()
            .input(Ingredient.of(ExNihiloBlocks.CRUSHED_NETHERRACK.get()))
            .drop(EXNTinkersItems.COBALT.getPieceItem())
            .addRoll(new MeshWithChance(MeshType.IRON, 0.05F))
            .addRoll(new MeshWithChance(MeshType.DIAMOND, 0.1F))
            .build(consumer, sieveLoc(EXNTinkersItems.COBALT.getPieceName()));

        //Earth Slime Dirt to...
        //Sky Slime Sapling
        createSlimeSaplings(SlimeType.EARTH, SlimeType.SKY)
                .build(consumer, sieveLoc("sky_slime_sapling"));
        //Sky Slime Ball
        createSlimeBalls(SlimeType.EARTH, SlimeType.SKY)
                .build(consumer, sieveLoc("sky_slime_ball"));
        //Slime Ball
        createManySlimeBalls(SlimeType.EARTH, Items.SLIME_BALL)
                .build(consumer, sieveLoc("slime_ball"));

        //Sky Slime Dirt to...
        //Blood Slime Sapling
        createSlimeSaplings(SlimeType.SKY, SlimeType.BLOOD)
                .build(consumer, sieveLoc("blood_slime_sapling"));
        //Sky Slime Grass Seeds
        createSlimeSeeds(SlimeType.SKY, SlimeType.SKY)
                .build(consumer, sieveLoc("sky_slime_seeds"));
        //Sky Slime Ball
        createManySlimeBalls(SlimeType.SKY, SlimeType.SKY)
                .build(consumer, sieveLoc("sky_slime_ball_from_sky_dirt"));
        //Blood Slime Ball
        createSlimeBalls(SlimeType.SKY, SlimeType.BLOOD)
                .build(consumer, sieveLoc("blood_slime_ball"));

        //IChor Slime Dirt to...
        //IChor Slime Grass Seeds
        createSlimeSeeds(SlimeType.ICHOR, SlimeType.ICHOR)
                .build(consumer, sieveLoc("ichor_slime_seeds"));
        //Blood Slime Ball
        createManySlimeBalls(SlimeType.ICHOR, SlimeType.BLOOD)
                .build(consumer, sieveLoc("ichor_slime_ball_from_ichor_dirt"));
        //Ender Slime Ball
        createSlimeBalls(SlimeType.ICHOR, SlimeType.ENDER)
                .build(consumer, sieveLoc("ender_slime_ball"));

        //Ender Slime Dirt to...
        //Ender Slime Sapling
        createSlimeSaplings(SlimeType.ENDER, SlimeType.ENDER)
                .build(consumer, sieveLoc("ender_slime_sapling"));
        //Ender Slime Grass Seeds
        createSlimeSeeds(SlimeType.ENDER, SlimeType.ENDER)
                .build(consumer, sieveLoc("ender_slime_seeds"));
        //Ender Slime Ball
        createManySlimeBalls(SlimeType.ENDER, SlimeType.ENDER)
                .build(consumer, sieveLoc("ender_slime_ball_from_ender_dirt"));
    }

    private SieveRecipeBuilder createManySlimeBalls(Object input, Object output) {
        Block input1;
        if (input instanceof SlimeType) {
            input1 = TinkerWorld.slimeDirt.get((SlimeType) input);
        } else {
            input1 = Blocks.DIRT;
        }

        Item output1;
        if (output instanceof SlimeType) {
            output1 = TinkerCommons.slimeball.get((SlimeType) output);
        } else {
            output1 = Items.SLIME_BALL;
        }

        return SieveRecipeBuilder.builder()
                .input(Ingredient.of(input1))
                .drop(output1)
                .addRoll(new MeshWithChance(MeshType.STRING, 0.75F))
                .addRoll(new MeshWithChance(MeshType.STRING, 0.5F))
                .addRoll(new MeshWithChance(MeshType.STRING, 0.25F));
    }

    private SieveRecipeBuilder createSlimeBalls(Object input, Object output) {
        Block input1;
        if (input instanceof SlimeType) {
            input1 = TinkerWorld.slimeDirt.get((SlimeType) input);
        } else {
            input1 = Blocks.DIRT;
        }

        Item output1;
        if (output instanceof SlimeType) {
            output1 = TinkerCommons.slimeball.get((SlimeType) output);
        } else {
            output1 = Items.SLIME_BALL;
        }

        return SieveRecipeBuilder.builder()
                .input(Ingredient.of(input1))
                .drop(output1)
                .addRoll(new MeshWithChance(MeshType.STRING, 0.5F))
                .addRoll(new MeshWithChance(MeshType.STRING, 0.25F));
    }

    private SieveRecipeBuilder createSlimeSeeds(SlimeType input, SlimeType output) {
        return SieveRecipeBuilder.builder()
                .input(Ingredient.of(TinkerWorld.slimeDirt.get(input)))
                .drop(TinkerWorld.slimeGrassSeeds.get(output))
                .addRoll(new MeshWithChance(MeshType.STRING, 0.25F));
    }

    private SieveRecipeBuilder createSlimeSaplings(SlimeType input, SlimeType output) {
        return SieveRecipeBuilder.builder()
                .input(Ingredient.of(TinkerWorld.slimeDirt.get(input)))
                .drop(TinkerWorld.slimeSapling.get(output))
                .addRoll(new MeshWithChance(MeshType.STRING, 0.25F));
    }
}
