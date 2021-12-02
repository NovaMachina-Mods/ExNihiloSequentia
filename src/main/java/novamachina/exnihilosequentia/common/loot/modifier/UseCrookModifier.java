package novamachina.exnihilosequentia.common.loot.modifier;

import com.google.gson.JsonObject;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.api.utility.ExNihiloTags;
import novamachina.exnihilosequentia.common.block.InfestedLeavesBlock;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class UseCrookModifier extends LootModifier {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private final Random random = new Random();

    public UseCrookModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        logger.debug("Fired Crook Modifier");
        ItemStack tool = context.getParamOrNull(LootContextParams.TOOL);
        BlockState blockState = context.getParamOrNull(LootContextParams.BLOCK_STATE);
        BlockPos pos = new BlockPos(Objects.requireNonNull(context.getParamOrNull(LootContextParams.ORIGIN)).x(), Objects.requireNonNull(context.getParamOrNull(LootContextParams.ORIGIN)).y(), Objects.requireNonNull(context.getParamOrNull(LootContextParams.ORIGIN)).z());
        List<ItemStack> newLoot = new ArrayList<>();

        if (tool != null && blockState != null && ExNihiloTags.CROOK.contains(tool.getItem()) && ExNihiloRegistries.CROOK_REGISTRY.isCrookable(blockState.getBlock())) {
            for (int i = 0; i < Config.getVanillaSimulateDropCount(); i++) {
                List<ItemStack> items = Block
                        .getDrops(blockState, Objects.requireNonNull(context.getLevel().getServer().getLevel(context.getLevel().dimension())),
                                pos, null);
                newLoot.addAll(items.stream().filter(drop -> !Objects.equals(drop.getItem().getRegistryName(), blockState.getBlock().getRegistryName())).collect(Collectors.toList()));
            }

            for (CrookRecipe recipe : ExNihiloRegistries.CROOK_REGISTRY.getDrops(blockState.getBlock())) {
                for(ItemStackWithChance itemStackWithChance : recipe.getOutput()) {
                    if (random.nextFloat() <= itemStackWithChance.getChance()) {
                        if (itemStackWithChance.getStack() != ItemStack.EMPTY) {
                            newLoot.add(itemStackWithChance.getStack());
                        }
                    }
                }
            }

            if (blockState.getBlock() instanceof InfestedLeavesBlock) {
                newLoot.add(new ItemStack(Items.STRING, random
                        .nextInt(Config.getMaxBonusStringCount()) + Config.getMinStringCount()));
                if (random.nextDouble() <= 0.8) {
                    newLoot
                            .add(new ItemStack(ExNihiloItems.SILKWORM.get()));
                }
            }
        }
        if (!newLoot.isEmpty()) {
            logger.debug("Adding new loot");
            generatedLoot = newLoot;
        }
        logger.debug("Crook Generated Loot: " + generatedLoot);
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<UseCrookModifier> {

        @Override
        public UseCrookModifier read(ResourceLocation location, JsonObject object,
                                      LootItemCondition[] AILootCondition) {
            return new UseCrookModifier(AILootCondition);
        }

        @Override
        public JsonObject write(UseCrookModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}
