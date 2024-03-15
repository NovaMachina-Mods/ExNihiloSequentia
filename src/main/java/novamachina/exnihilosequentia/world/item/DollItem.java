package novamachina.exnihilosequentia.world.item;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

public class DollItem extends Item {

  @Nonnull private final String entityModId;
  @Nonnull private final String entityName;
  @Nonnull private final String fluidModId;
  @Nonnull private final String fluidName;
  @Nonnull private final String tooltip;
  private final double yOffset;

  public DollItem(
      @Nonnull final String entityModId,
      @Nonnull final String entityName,
      @Nonnull final String fluidModId,
      @Nonnull final String fluidName,
      double yOffset,
      @Nonnull final String tooltip) {
    super(new Item.Properties());
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
      @Nullable final Level worldIn,
      @Nonnull final List<Component> tooltip,
      @Nonnull final TooltipFlag flagIn) {
    super.appendHoverText(stack, worldIn, tooltip, flagIn);
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
    final ResourceLocation fluidLocation = new ResourceLocation(this.fluidModId, this.fluidName);

    if (BuiltInRegistries.FLUID.containsKey(fluidLocation)) {

      return BuiltInRegistries.FLUID.get(fluidLocation);
    }
    return Fluids.EMPTY;
  }

  public boolean spawnMob(@Nonnull final Level world, @Nonnull final BlockPos pos) {
    @Nonnull
    ResourceLocation spawneeResourceLocation =
        new ResourceLocation(this.entityModId, this.entityName);

    if (BuiltInRegistries.ENTITY_TYPE.containsKey(spawneeResourceLocation)) {
      @Nullable
      final EntityType<?> entityType = BuiltInRegistries.ENTITY_TYPE.get(spawneeResourceLocation);
      if (entityType != null) {
        @Nullable final Entity spawnee = entityType.create(world);
        if (spawnee != null) {
          spawnee.setPos(pos.getX(), pos.getY() + this.yOffset, pos.getZ());
          return world.addFreshEntity(spawnee);
        }
      }
    }
    return false;
  }
}
