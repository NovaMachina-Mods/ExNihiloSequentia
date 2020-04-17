package com.novamachina.ens.common.setup;

import com.novamachina.ens.common.block.BaseFallingBlock;
import com.novamachina.ens.common.builder.BlockBuilder;
import com.novamachina.ens.common.item.SilkWormItem;
import com.novamachina.ens.common.item.tools.crook.CrookBaseItem;
import com.novamachina.ens.common.item.tools.crook.EnumCrook;
import com.novamachina.ens.common.item.tools.hammer.EnumHammer;
import com.novamachina.ens.common.item.tools.hammer.HammerBaseItem;
import com.novamachina.ens.common.utility.Constants;
import com.novamachina.ens.common.utility.LogUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

public class Registration {

    // Registries
    private static final DeferredRegister<Block>             BLOCKS     =
        new DeferredRegister<>(ForgeRegistries.BLOCKS, Constants.ModInfo.MOD_ID);
    private static final DeferredRegister<Item>              ITEMS      =
        new DeferredRegister<>(ForgeRegistries.ITEMS, Constants.ModInfo.MOD_ID);
    private static final DeferredRegister<ContainerType<?>>  CONTAINERS =
        new DeferredRegister<>(ForgeRegistries.CONTAINERS, Constants.ModInfo.MOD_ID);
    private static final DeferredRegister<TileEntityType<?>> TILES      =
        new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Constants.ModInfo.MOD_ID);

    // Blocks
    public static final RegistryObject<BaseFallingBlock> BLOCK_DUST               = BLOCKS
        .register(Constants.Blocks.DUST,
            () -> new BaseFallingBlock(new BlockBuilder().properties(
                Block.Properties.create(Material.SAND).hardnessAndResistance(0.7F)
                    .sound(SoundType.CLOTH))
                .harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<BaseFallingBlock> BLOCK_CRUSHED_NETHERRACK =
        BLOCKS.register(Constants.Blocks.CRUSHED_NETHERRACK,
            () -> new BaseFallingBlock(new BlockBuilder().properties(
                Block.Properties.create(Material.SAND).hardnessAndResistance(0.7F)
                    .sound(SoundType.GROUND))
                .harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<BaseFallingBlock> BLOCK_CRUSHED_END_STONE  =
        BLOCKS.register(Constants.Blocks.CRUSHED_END_STONE,
            () -> new BaseFallingBlock(new BlockBuilder().properties(
                Block.Properties.create(Material.SAND).hardnessAndResistance(0.7F)
                    .sound(SoundType.GROUND))
                .harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<BaseFallingBlock> BLOCK_CRUSHED_ANDESITE   =
        BLOCKS.register(Constants.Blocks.CRUSHED_ANDESITE,
            () -> new BaseFallingBlock(new BlockBuilder().properties(
                Block.Properties.create(Material.SAND).hardnessAndResistance(0.7F)
                    .sound(SoundType.GROUND))
                .harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<BaseFallingBlock> BLOCK_CRUSHED_DIORITE    =
        BLOCKS.register(Constants.Blocks.CRUSHED_DIORITE,
            () -> new BaseFallingBlock(new BlockBuilder().properties(
                Block.Properties.create(Material.SAND).hardnessAndResistance(0.7F)
                    .sound(SoundType.GROUND))
                .harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<BaseFallingBlock> BLOCK_CRUSHED_GRANITE    =
        BLOCKS.register(Constants.Blocks.CRUSHED_GRANITE,
            () -> new BaseFallingBlock(new BlockBuilder().properties(
                Block.Properties.create(Material.SAND).hardnessAndResistance(0.7F)
                    .sound(SoundType.GROUND))
                .harvestLevel(ToolType.SHOVEL, 0)));

    // Items
    public static final RegistryObject<Item> ITEM_DUST               = ITEMS
        .register(Constants.Blocks.DUST,
            () -> new BlockItem(BLOCK_DUST.get(),
                new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> ITEM_CRUSHED_NETHERRACK =
        ITEMS.register(Constants.Blocks.CRUSHED_NETHERRACK,
            () -> new BlockItem(BLOCK_CRUSHED_NETHERRACK.get(),
                new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> ITEM_CRUSHED_END_STONE  =
        ITEMS.register(Constants.Blocks.CRUSHED_END_STONE,
            () -> new BlockItem(BLOCK_CRUSHED_END_STONE.get(),
                new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> ITEM_CRUSHED_ANDESITE   = ITEMS
        .register(Constants.Blocks.CRUSHED_ANDESITE,
            () -> new BlockItem(BLOCK_CRUSHED_ANDESITE.get(),
                new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> ITEM_CRUSHED_DIORITE    = ITEMS
        .register(Constants.Blocks.CRUSHED_DIORITE,
            () -> new BlockItem(BLOCK_CRUSHED_DIORITE.get(),
                new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> ITEM_CRUSHED_GRANITE    = ITEMS
        .register(Constants.Blocks.CRUSHED_GRANITE,
            () -> new BlockItem(BLOCK_CRUSHED_GRANITE.get(),
                new Item.Properties().group(ModSetup.ITEM_GROUP)));

    static {
        for (EnumCrook crook : EnumCrook.values()) {
            ITEMS
                .register(crook.name, () -> new CrookBaseItem(crook.teir, crook.defaultDurability));
        }

        for (EnumHammer hammer : EnumHammer.values()) {
            ITEMS.register(hammer.name,
                () -> new HammerBaseItem(hammer.teir, hammer.defaultDurability));
        }
    }

    public static final RegistryObject<Item> ITEM_SILKWORM = ITEMS
        .register(Constants.Items.SILKWORM, SilkWormItem::new);

    public static void init() {
        LogUtil.info("Registration init");
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @ObjectHolder("ens:use_hammer")
    public static GlobalLootModifierSerializer<?> HAMMER_MODIFIER = null;
}
