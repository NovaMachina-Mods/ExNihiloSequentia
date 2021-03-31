package novamachina.exnihilosequentia.common.loot.modifier;

import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.ExNihiloTags;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.block.InfestedLeavesBlock;
import novamachina.exnihilosequentia.common.item.resources.EnumResource;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class UseCrookModifier extends LootModifier {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private final Random random = new Random();

    public UseCrookModifier(ILootCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        logger.debug("Fired Crook Modifier");
        ItemStack tool = context.getParamOrNull(LootParameters.TOOL);
        BlockState blockState = context.getParamOrNull(LootParameters.BLOCK_STATE);
        BlockPos pos = new BlockPos(context.getParamOrNull(LootParameters.ORIGIN).x(), context.getParamOrNull(LootParameters.ORIGIN).y(), context.getParamOrNull(LootParameters.ORIGIN).z());
        List<ItemStack> newLoot = new ArrayList<>();

        if (tool != null && blockState != null && ExNihiloTags.CROOK.contains(tool.getItem()) && ExNihiloRegistries.CROOK_REGISTRY.isCrookable(blockState.getBlock())) {
            for (int i = 0; i < Config.getVanillaSimulateDropCount(); i++) {
                List<ItemStack> items = Block
                        .getDrops(blockState, context.getLevel().getServer().getLevel(context.getLevel().dimension()),
                                pos, null);
                newLoot.addAll(items.stream().filter(drop -> !drop.getItem().getRegistryName().equals(blockState.getBlock().getRegistryName())).collect(Collectors.toList()));
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
                            .add(new ItemStack(EnumResource.SILKWORM.getRegistryObject().get()));
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

    public static class Serializer extends GlobalLootModifierSerializer<UseCrookModifier> {

        @Override
        public UseCrookModifier read(ResourceLocation location, JsonObject object,
                                      ILootCondition[] ailootcondition) {
            return new UseCrookModifier(ailootcondition);
        }

        @Override
        public JsonObject write(UseCrookModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}
