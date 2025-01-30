package novamachina.exnihilosequentia.world.item;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

public class DollItem extends Item {

  private final String entityModId;
  private final String entityName;
  private final String fluidModId;
  private final String fluidName;
  private final String tooltip;
  private final double yOffset;

  public DollItem(
      String entityModId,
      String entityName,
      String fluidModId,
      String fluidName,
      double yOffset,
      String tooltip,
      Item.Properties properties) {
    super(properties);
    this.entityModId = entityModId;
    this.entityName = entityName;
    this.fluidModId = fluidModId;
    this.fluidName = fluidName;
    this.yOffset = yOffset;
    this.tooltip = tooltip;
  }

  @Override
  public void appendHoverText(
      @Nonnull final ItemStack stack,
      @Nullable final TooltipContext context,
      @Nonnull final List<Component> tooltip,
      @Nonnull final TooltipFlag flagIn) {
    super.appendHoverText(stack, context, tooltip, flagIn);
    tooltip.add(Component.translatable(this.tooltip));
  }

  @Nonnull
  public String getDollName() {
    return entityName + "_doll";
  }

  @Nonnull
  public String getDollType() {
    return this.entityName;
  }

  public Fluid getSpawnFluid() {
    @Nonnull
    final ResourceLocation fluidLocation =
        ResourceLocation.fromNamespaceAndPath(this.fluidModId, this.fluidName);
    return BuiltInRegistries.FLUID
        .get(fluidLocation)
        .orElse(Fluids.EMPTY.builtInRegistryHolder())
        .value();
  }

  public boolean spawnMob(@Nonnull final Level world, @Nonnull final BlockPos pos) {
    @Nonnull
    ResourceLocation spawneeResourceLocation =
        ResourceLocation.fromNamespaceAndPath(this.entityModId, this.entityName);

    AtomicBoolean success = new AtomicBoolean(false);

    BuiltInRegistries.ENTITY_TYPE
        .get(spawneeResourceLocation)
        .ifPresent(
            holder -> {
              EntityType<?> entityType = holder.value();
              final Entity spawnee = entityType.create(world, EntitySpawnReason.SPAWN_ITEM_USE);
              if (spawnee != null) {
                spawnee.setPos(pos.getX(), pos.getY() + this.yOffset, pos.getZ());
                success.set(world.addFreshEntity(spawnee));
              }
            });
    return success.get();
  }

  @FunctionalInterface
  public interface DollItemFunction {
    DollItem apply(
        String entityModId,
        String entityName,
        String fluidModId,
        String fluidName,
        double yOffset,
        String tooltip,
        Item.Properties properties);
  }
}
