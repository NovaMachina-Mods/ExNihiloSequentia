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
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.ExNihiloTags;
import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.crook.CrookRecipe;
import novamachina.exnihilosequentia.common.block.InfestedLeavesBlock;
import novamachina.exnihilosequentia.common.init.ExNihiloItems;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class UseCrookModifier extends LootModifier {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    @Nonnull private final Random random = new Random();

    public UseCrookModifier(@Nonnull final ILootCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    public List<ItemStack> doApply(@Nonnull List<ItemStack> generatedLoot, @Nonnull final LootContext context) {
        logger.debug("Fired Crook Modifier");
        @Nullable final ItemStack tool = context.getParamOrNull(LootParameters.TOOL);
        @Nullable final BlockState blockState = context.getParamOrNull(LootParameters.BLOCK_STATE);
        @Nullable final Vector3d origin = context.getParamOrNull(LootParameters.ORIGIN);
        @Nonnull final List<ItemStack> newLoot = new ArrayList<>();

        if (tool != null && blockState != null && ExNihiloTags.CROOK.contains(tool.getItem())
                && ExNihiloRegistries.CROOK_REGISTRY.isCrookable(blockState.getBlock())) {
            for (int i = 0; i < Config.getVanillaSimulateDropCount(); i++) {
                @Nullable final ServerWorld serverWorld = context.getLevel().getServer().getLevel(
                        context.getLevel().dimension());
                if (origin != null && serverWorld != null) {
                    @Nonnull final BlockPos pos = new BlockPos(origin.x(), origin.y(), origin.z());
                    @Nonnull final List<ItemStack> items = Block.getDrops(blockState, serverWorld, pos, null);
                    newLoot.addAll(items.stream().filter(drop -> {
                        @Nullable final ResourceLocation resourceLocation = drop.getItem().getRegistryName();
                        if (resourceLocation == null) {
                            return false;
                        }
                        return !resourceLocation.equals(blockState.getBlock().getRegistryName());
                    }).collect(Collectors.toList()));
                }
            }

            for (@Nonnull final CrookRecipe recipe : ExNihiloRegistries.CROOK_REGISTRY.getDrops(
                    blockState.getBlock())) {
                for (@Nonnull final ItemStackWithChance itemStackWithChance : recipe.getOutput()) {
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
        @Nonnull
        public UseCrookModifier read(@Nonnull final ResourceLocation location, @Nonnull final JsonObject object,
                                     @Nonnull final ILootCondition[] ailootcondition) {
            return new UseCrookModifier(ailootcondition);
        }

        @Override
        @Nonnull
        public JsonObject write(@Nonnull final UseCrookModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}
