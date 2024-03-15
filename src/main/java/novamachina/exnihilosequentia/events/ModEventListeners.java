package novamachina.exnihilosequentia.events;

import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.item.capability.BarrelInventoryHandler;
import novamachina.exnihilosequentia.world.item.capability.MeltableItemHandler;
import novamachina.exnihilosequentia.world.level.block.entity.EXNBlockEntityTypes;
import novamachina.exnihilosequentia.world.level.block.entity.mode.BarrelModeRegistry;
import novamachina.exnihilosequentia.world.level.material.capability.BarrelFluidHandler;
import novamachina.exnihilosequentia.world.level.material.capability.CrucibleFluidHandler;
import novamachina.novacore.world.item.ItemDefinition;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(
    modid = ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA,
    bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventListeners {
  private static final Logger log = org.slf4j.LoggerFactory.getLogger(ModEventListeners.class);

  @SubscribeEvent
  public static void setupNonTagBasedRegistries(FMLCommonSetupEvent event) {
    log.debug("FIRED FMLCOMMONSETUPEVENT");
    BarrelModeRegistry.initialize();
    registerVanillaCompost();
    registerDispenserFluids();
    //    EXNStats.register();
  }

  private static void registerDispenserFluids() {
    DispenseItemBehavior idispenseitembehavior =
        new DefaultDispenseItemBehavior() {
          final DefaultDispenseItemBehavior defaultDispenseItemBehavior =
              new DefaultDispenseItemBehavior();

          @Override
          public ItemStack execute(BlockSource pSource, ItemStack pStack) {
            BucketItem bucketitem = (BucketItem) pStack.getItem();
            BlockPos blockpos =
                pSource.pos().relative(pSource.state().getValue(DispenserBlock.FACING));
            Level world = pSource.level();
            if (bucketitem.emptyContents(null, world, blockpos, null)) {
              bucketitem.checkExtraContent(null, world, pStack, blockpos);
              return new ItemStack(Items.BUCKET);
            } else {
              return this.defaultDispenseItemBehavior.dispense(pSource, pStack);
            }
          }
        };
    DispenserBlock.registerBehavior(EXNItems.SEA_WATER_BUCKET, idispenseitembehavior);
    DispenserBlock.registerBehavior(EXNItems.WITCH_WATER_BUCKET, idispenseitembehavior);
  }

  @SubscribeEvent
  public static void registerCapabilities(RegisterCapabilitiesEvent event) {
    log.info("Registering capabilities");
    event.registerBlockEntity(
        Capabilities.ItemHandler.BLOCK,
        EXNBlockEntityTypes.FIRED_CRUCIBLE_ENTITY.getType(),
        (blockEntity, side) -> MeltableItemHandler.getHandler(blockEntity));
    event.registerBlockEntity(
        Capabilities.FluidHandler.BLOCK,
        EXNBlockEntityTypes.FIRED_CRUCIBLE_ENTITY.getType(),
        (blockEntity, side) -> CrucibleFluidHandler.getHandler(blockEntity));
    event.registerBlockEntity(
        Capabilities.ItemHandler.BLOCK,
        EXNBlockEntityTypes.WOODEN_CRUCIBLE_ENTITY.getType(),
        (blockEntity, side) -> MeltableItemHandler.getHandler(blockEntity));
    event.registerBlockEntity(
        Capabilities.FluidHandler.BLOCK,
        EXNBlockEntityTypes.WOODEN_CRUCIBLE_ENTITY.getType(),
        (blockEntity, side) -> CrucibleFluidHandler.getHandler(blockEntity));
    event.registerBlockEntity(
        Capabilities.ItemHandler.BLOCK,
        EXNBlockEntityTypes.WOODEN_BARREL_ENTITY.getType(),
        (blockEntity, side) -> BarrelInventoryHandler.getHandler(blockEntity));
    event.registerBlockEntity(
        Capabilities.FluidHandler.BLOCK,
        EXNBlockEntityTypes.WOODEN_BARREL_ENTITY.getType(),
        (blockEntity, side) -> BarrelFluidHandler.getHandler(blockEntity));
    event.registerBlockEntity(
        Capabilities.ItemHandler.BLOCK,
        EXNBlockEntityTypes.STONE_BARREL_ENTITY.getType(),
        (blockEntity, side) -> BarrelInventoryHandler.getHandler(blockEntity));
    event.registerBlockEntity(
        Capabilities.FluidHandler.BLOCK,
        EXNBlockEntityTypes.STONE_BARREL_ENTITY.getType(),
        (blockEntity, side) -> BarrelFluidHandler.getHandler(blockEntity));
  }

  private static void registerVanillaCompost() {
    createMCCompost(EXNItems.GRASS_SEED);
    createMCCompost(EXNItems.MYCELIUM_SPORE);
    //    createMCCompost(ExNihiloItems.CRIMSON_NYLIUM_SPORE.get().asItem());
    //    createMCCompost(ExNihiloItems.WARPED_NYLIUM_SPORE.get().asItem());
    createMCCompost(EXNItems.SILKWORM);
    createMCCompost(EXNItems.COOKED_SILKWORM);
  }

  private static void createMCCompost(ItemDefinition<? extends Item> item) {
    ComposterBlock.COMPOSTABLES.put(item, (float) 0.3);
  }
}
