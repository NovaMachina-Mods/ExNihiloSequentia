package novamachina.exnihilosequentia.common.loot.modifier;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.tags.ExNihiloTags;
import novamachina.exnihilosequentia.world.item.crafting.ItemStackWithChance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UseHammerModifier extends LootModifier {

  private static Logger log = LoggerFactory.getLogger(UseHammerModifier.class);

  public static final Supplier<Codec<UseHammerModifier>> CODEC =
      Suppliers.memoize(
          () ->
              RecordCodecBuilder.create(
                  inst -> codecStart(inst).apply(inst, UseHammerModifier::new)));

  private final Random random = new SecureRandom();

  public UseHammerModifier(@Nonnull final LootItemCondition[] conditionsIn) {
    super(conditionsIn);
  }

  @Nonnull
  @Override
  public ObjectArrayList<ItemStack> doApply(
      @Nonnull ObjectArrayList<ItemStack> generatedLoot, @Nonnull final LootContext context) {
    log.debug("Fired Hammer Modifier");
    @Nullable final ItemStack tool = context.getParamOrNull(LootContextParams.TOOL);
    @Nullable final BlockState blockState = context.getParamOrNull(LootContextParams.BLOCK_STATE);
    @Nonnull final ObjectArrayList<ItemStack> newLoot = new ObjectArrayList<>();

    if (tool != null
        && blockState != null
        && tool.getItem().getDefaultInstance().is(ExNihiloTags.HAMMER)
        && ExNihiloRegistries.HAMMER_REGISTRY.isHammerable(blockState.getBlock())) {
      @Nonnull
      final List<ItemStackWithChance> list =
          ExNihiloRegistries.HAMMER_REGISTRY.getResult(blockState.getBlock());
      for (@Nonnull final ItemStackWithChance stackWithChance : list) {
        if (random.nextFloat() <= stackWithChance.getChance()
            && stackWithChance.getStack() != ItemStack.EMPTY) {
          newLoot.add(stackWithChance.getStack());
        }
      }
    }
    if (!newLoot.isEmpty()) {
      log.debug("Adding new loot");
      generatedLoot = newLoot;
    }
    log.debug("Hammer Generated Loot: " + generatedLoot);
    return generatedLoot;
  }

  @Override
  public Codec<? extends IGlobalLootModifier> codec() {
    return CODEC.get();
  }
}
