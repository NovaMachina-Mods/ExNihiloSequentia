package novamachina.exnihilosequentia.common.datagen;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.loot.conditions.SurvivesExplosion;
import novamachina.exnihilosequentia.api.datagen.AbstractLootTableGenerator;
import novamachina.exnihilosequentia.common.init.ExNihiloBlocks;
import novamachina.exnihilosequentia.common.item.resources.EnumResource;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class ExNihiloLootTableGenerator extends AbstractLootTableGenerator {

    public ExNihiloLootTableGenerator(DataGenerator generator) {
        super(generator, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
    }

    @Override
    protected void createLootTables() {
        registerSelfDrops();
        registerInfestedLeaves();
    }

    private void registerInfestedLeaves() {
        registerLeaves(ExNihiloBlocks.INFESTING_LEAVES.get());
        registerLeaves(ExNihiloBlocks.INFESTED_LEAVES.get());
    }

    private void registerLeaves(Block block) {
        LootPool.Builder stringPool = createLootPoolBuilder();
        stringPool.setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(Items.STRING));
        stringPool.when(SurvivesExplosion.survivesExplosion());

        LootPool.Builder silkWormPool = createLootPoolBuilder();
        silkWormPool.setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(EnumResource.SILKWORM.getRegistryObject().get()));
        silkWormPool.when(RandomChance.randomChance(0.7F));

        register(block, stringPool, silkWormPool);
    }

    private void registerSelfDrops() {
        registerSelfDrop(ExNihiloBlocks.BARREL_ACACIA.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_BIRCH.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_DARK_OAK.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_JUNGLE.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_SPRUCE.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_OAK.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_CRIMSON.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_WARPED.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_STONE.get());
        /*registerSelfDrop(ExNihiloBlocks.BARREL_GLASS.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_GLASS_WHITE.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_GLASS_RED.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_GLASS_YELLOW.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_GLASS_PURPLE.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_GLASS_PINK.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_GLASS_ORANGE.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_GLASS_MAGENTA.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_GLASS_LIME.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_GLASS_LIGHT_GRAY.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_GLASS_LIGHT_BLUE.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_GLASS_GREEN.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_GLASS_GRAY.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_GLASS_CYAN.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_GLASS_BROWN.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_GLASS_BLUE.get());
        registerSelfDrop(ExNihiloBlocks.BARREL_GLASS_BLACK.get());*/
        registerSelfDrop(ExNihiloBlocks.CRUCIBLE_FIRED.get());
        registerSelfDrop(ExNihiloBlocks.CRUCIBLE_UNFIRED.get());
        registerSelfDrop(ExNihiloBlocks.CRUCIBLE_ACACIA.get());
        registerSelfDrop(ExNihiloBlocks.CRUCIBLE_BIRCH.get());
        registerSelfDrop(ExNihiloBlocks.CRUCIBLE_DARK_OAK.get());
        registerSelfDrop(ExNihiloBlocks.CRUCIBLE_JUNGLE.get());
        registerSelfDrop(ExNihiloBlocks.CRUCIBLE_SPRUCE.get());
        registerSelfDrop(ExNihiloBlocks.CRUCIBLE_OAK.get());
        registerSelfDrop(ExNihiloBlocks.CRUSHED_ANDESITE.get());
        registerSelfDrop(ExNihiloBlocks.CRUSHED_DIORITE.get());
        registerSelfDrop(ExNihiloBlocks.CRUSHED_END_STONE.get());
        registerSelfDrop(ExNihiloBlocks.CRUSHED_GRANITE.get());
        registerSelfDrop(ExNihiloBlocks.CRUSHED_NETHERRACK.get());
        registerSelfDrop(ExNihiloBlocks.DUST.get());
        registerSelfDrop(ExNihiloBlocks.SIEVE_ACACIA.get());
        registerSelfDrop(ExNihiloBlocks.SIEVE_BIRCH.get());
        registerSelfDrop(ExNihiloBlocks.SIEVE_DARK_OAK.get());
        registerSelfDrop(ExNihiloBlocks.SIEVE_JUNGLE.get());
        registerSelfDrop(ExNihiloBlocks.SIEVE_SPRUCE.get());
        registerSelfDrop(ExNihiloBlocks.SIEVE_OAK.get());
    }
}
