package novamachina.exnihilosequentia.common.loot.modifier;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.ExNihiloTags;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class UseHammerModifier extends LootModifier {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private final Random random = new Random();

    public UseHammerModifier(@Nonnull final LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    public List<ItemStack> doApply(@Nonnull List<ItemStack> generatedLoot, @Nonnull final LootContext context) {
        logger.debug("Fired Hammer Modifier");
        @Nullable final ItemStack tool = context.getParamOrNull(LootContextParams.TOOL);
        @Nullable final BlockState blockState = context.getParamOrNull(LootContextParams.BLOCK_STATE);
        @Nonnull final List<ItemStack> newLoot = new ArrayList<>();

        if (tool != null && blockState != null && tool.getItem().getDefaultInstance().is(ExNihiloTags.HAMMER)
                && ExNihiloRegistries.HAMMER_REGISTRY.isHammerable(blockState.getBlock())) {
            @Nonnull final List<ItemStackWithChance> list = ExNihiloRegistries.HAMMER_REGISTRY
                    .getResult(blockState.getBlock());
            for (@Nonnull final ItemStackWithChance stackWithChance : list) {
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
        @Nonnull
        public UseHammerModifier read(@Nonnull final ResourceLocation location, @Nonnull final JsonObject object,
                                      @Nonnull final LootItemCondition[] ailootcondition) {
            return new UseHammerModifier(ailootcondition);
        }

        @Override
        public JsonObject write(@Nonnull final UseHammerModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}
