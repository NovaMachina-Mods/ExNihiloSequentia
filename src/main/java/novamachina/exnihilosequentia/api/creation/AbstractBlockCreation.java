package novamachina.exnihilosequentia.api.creation;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.block.*;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.tileentity.SieveTile;
import novamachina.exnihilosequentia.common.tileentity.barrel.StoneBarrelTile;
import novamachina.exnihilosequentia.common.tileentity.barrel.WoodBarrelTile;
import novamachina.exnihilosequentia.common.tileentity.crucible.FiredCrucibleTile;
import novamachina.exnihilosequentia.common.tileentity.crucible.WoodCrucibleTile;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class AbstractBlockCreation {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister
            .create(ForgeRegistries.BLOCKS, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);
    public static RegistryObject<BaseFallingBlock> createFallingBlock(String name, Material material, Float strength, SoundType sound, ToolType tool) {
        return BLOCKS.register(name, () -> new BaseFallingBlock(new BlockBuilder().properties(AbstractBlock.Properties.of(material)
                .strength(strength).sound(sound)).harvestLevel(tool, 0)));
    }
    public static RegistryObject<BaseBlock> createWoodenBarrelBlock(String name, Material material, Float strength, SoundType sound) {
        return BLOCKS.register(name, () -> new BlockBarrel(new BlockBuilder().harvestLevel(ToolType.AXE, 0)
                        .properties(AbstractBlock.Properties.of(material).strength(strength).sound(sound))
                    .tileEntitySupplier(WoodBarrelTile::new)));
    }
    public static RegistryObject<BaseBlock> createStoneBarrelBlock(String name, Material material, Float strength, SoundType sound) {
        return BLOCKS.register(name, () -> new BlockBarrel(new BlockBuilder().harvestLevel(ToolType.AXE, 0)
                .properties(AbstractBlock.Properties.of(material).strength(strength).sound(sound))
                .tileEntitySupplier(StoneBarrelTile::new)));
    }
    public static RegistryObject<BaseBlock> createWoodenCrucibleBlock(String name, Material material, Float strength, SoundType sound) {
        return BLOCKS.register(name, () -> new CrucibleBaseBlock(new BlockBuilder().properties(
                AbstractBlock.Properties.of(material).strength(strength).sound(sound).noOcclusion())
                .harvestLevel(ToolType.AXE, 0).tileEntitySupplier(WoodCrucibleTile::new)));
    }
    public static RegistryObject<BaseBlock> createFiredCrucibleBlock(String name, Material material, Float strength, SoundType sound, ToolType tool) {
        return BLOCKS.register(name, () -> new CrucibleBaseBlock(new BlockBuilder().properties(
                AbstractBlock.Properties.of(material).strength(strength).sound(sound).noOcclusion())
                .harvestLevel(tool, 0).tileEntitySupplier(FiredCrucibleTile::new)));
    }
    public static RegistryObject<BlockSieve> createSieve(String sieve, Material material, SoundType sound) {
        return BLOCKS.register(sieve, () -> new BlockSieve(new BlockBuilder().properties(
                AbstractBlock.Properties.of(material).strength(0.7F)
                        .sound(sound).noOcclusion().isRedstoneConductor((state, reader, pos) -> false)
                        .isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false))
                .harvestLevel(ToolType.AXE, 0).tileEntitySupplier(SieveTile::new)));
    }
}
