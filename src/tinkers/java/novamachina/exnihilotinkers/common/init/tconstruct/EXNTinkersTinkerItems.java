package novamachina.exnihilotinkers.common.init.tconstruct;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilotinkers.common.tinkers.ToolDefinitions;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;
import org.apache.logging.log4j.LogManager;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.TinkerModule;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

import java.util.function.Supplier;

public class EXNTinkersTinkerItems extends TinkerModule {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    public static final ItemDeferredRegisterExtension ITEMS = new ItemDeferredRegisterExtension(EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS);
    private static final Item.Properties PART_PROPERTIES = new Item.Properties().tab(TinkerToolParts.TAB_TOOL_PARTS);
    private static final Item.Properties SMELTERY_PROPERTIES = new Item.Properties().tab(TinkerSmeltery.TAB_SMELTERY);
    private static final Supplier<Item.Properties> TOOL = () -> new Item.Properties().tab(TinkerTools.TAB_TOOLS);

    public static final ItemObject<ModifiableItem> exnCrook = ITEMS.register("exn_crook", () -> new ModifiableItem(TOOL.get(), ToolDefinitions.exnCrook));
    public static final ItemObject<ModifiableItem> exnHammer = ITEMS.register("exn_hammer", () -> new ModifiableItem(TOOL.get(), ToolDefinitions.exnHammer));
    public static final ItemObject<ToolPartItem> crookHead = ITEMS.register("crook_head", () -> new ToolPartItem(PART_PROPERTIES, HeadMaterialStats.ID));
    public static final CastItemObject crookHeadCast = ITEMS.registerCast("crook_head", SMELTERY_PROPERTIES);

    public static void init(IEventBus modEventBus) {
        logger.debug("Register Ex Nihilo Tools to Tinkers");
        ITEMS.register(modEventBus);
    }

}
