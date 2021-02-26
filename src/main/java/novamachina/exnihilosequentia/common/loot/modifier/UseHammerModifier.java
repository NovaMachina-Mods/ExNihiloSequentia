package novamachina.exnihilosequentia.common.loot.modifier;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.Nonnull;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.ExNihiloTags;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.common.item.tools.hammer.HammerBaseItem;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class UseHammerModifier extends LootModifier {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private final Random random = new Random();

    public UseHammerModifier(ILootCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        logger.debug("Fired Hammer Modifier");
        ItemStack tool = context.get(LootParameters.TOOL);
        BlockState blockState = context.get(LootParameters.BLOCK_STATE);
        List<ItemStack> newLoot = new ArrayList<>();

        if (tool != null && blockState != null && ExNihiloTags.HAMMER.contains(tool.getItem()) && ExNihiloRegistries.HAMMER_REGISTRY.isHammerable(blockState.getBlock())) {
            List<ItemStackWithChance> list = ExNihiloRegistries.HAMMER_REGISTRY.getResult(blockState.getBlock());
            for (ItemStackWithChance stackWithChance : list) {
                if (random.nextFloat() <= stackWithChance.getChance()) {
                    if (stackWithChance.getStack() != ItemStack.EMPTY) {
                        newLoot.add(stackWithChance.getStack());
                    }
                }
            }
        }
        if (!newLoot.isEmpty()) {
            logger.debug("Adding new loot");
            generatedLoot = newLoot;
        }
        logger.debug("Hammer Generated Loot: " + generatedLoot);
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<UseHammerModifier> {


        @Override
        public UseHammerModifier read(ResourceLocation location, JsonObject object,
                                      ILootCondition[] ailootcondition) {
            return new UseHammerModifier(ailootcondition);
        }

        @Override
        public JsonObject write(UseHammerModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}
