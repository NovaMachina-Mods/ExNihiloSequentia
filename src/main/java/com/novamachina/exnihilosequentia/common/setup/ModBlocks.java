package com.novamachina.exnihilosequentia.common.setup;

import com.novamachina.exnihilosequentia.common.block.BaseFallingBlock;
import com.novamachina.exnihilosequentia.common.block.BlockSieve;
import com.novamachina.exnihilosequentia.common.block.EndCakeBlock;
import com.novamachina.exnihilosequentia.common.builder.BlockBuilder;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.Constants.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    private static final DeferredRegister<Block> BLOCKS =
        new DeferredRegister<>(ForgeRegistries.BLOCKS, Constants.ModInfo.MOD_ID);

    public static final RegistryObject<BaseFallingBlock> DUST               = BLOCKS
        .register(Constants.Blocks.DUST, () -> new BaseFallingBlock(new BlockBuilder().properties(
            Block.Properties.create(Material.SAND).hardnessAndResistance(0.7F)
                .sound(SoundType.CLOTH)).harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<BaseFallingBlock> CRUSHED_NETHERRACK = BLOCKS
        .register(Constants.Blocks.CRUSHED_NETHERRACK, () -> new BaseFallingBlock(new BlockBuilder()
            .properties(Block.Properties.create(Material.SAND).hardnessAndResistance(0.7F)
                .sound(SoundType.GROUND)).harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<BaseFallingBlock> CRUSHED_END_STONE  = BLOCKS
        .register(Constants.Blocks.CRUSHED_END_STONE, () -> new BaseFallingBlock(new BlockBuilder()
            .properties(Block.Properties.create(Material.SAND).hardnessAndResistance(0.7F)
                .sound(SoundType.GROUND)).harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<BaseFallingBlock> CRUSHED_ANDESITE   = BLOCKS
        .register(Constants.Blocks.CRUSHED_ANDESITE, () -> new BaseFallingBlock(new BlockBuilder()
            .properties(Block.Properties.create(Material.SAND).hardnessAndResistance(0.7F)
                .sound(SoundType.GROUND)).harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<BaseFallingBlock> CRUSHED_DIORITE    = BLOCKS
        .register(Constants.Blocks.CRUSHED_DIORITE, () -> new BaseFallingBlock(new BlockBuilder()
            .properties(Block.Properties.create(Material.SAND).hardnessAndResistance(0.7F)
                .sound(SoundType.GROUND)).harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<BaseFallingBlock> CRUSHED_GRANITE    = BLOCKS
        .register(Constants.Blocks.CRUSHED_GRANITE, () -> new BaseFallingBlock(new BlockBuilder()
            .properties(Block.Properties.create(Material.SAND).hardnessAndResistance(0.7F)
                .sound(SoundType.GROUND)).harvestLevel(ToolType.SHOVEL, 0)));
    public static final RegistryObject<EndCakeBlock>     END_CAKE           = BLOCKS
        .register(Constants.Blocks.END_CAKE, EndCakeBlock::new);
    public static final RegistryObject<BlockSieve>       SIEVE              = BLOCKS
        .register(Blocks.SIEVE, BlockSieve::new);

    public static void init(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
}
