package novamachina.exnihilosequentia.common.compat.waila;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class CrucibleComponentProvider
    implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {

  @Override
  public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig iPluginConfig) {
    CompoundTag tag = accessor.getServerData().getCompound("Crucible");
    if (tag.contains("solidAmount")) {
      tooltip.add(Component.literal(tag.getString("solidAmount")));
    }
    if (tag.contains("fluidAmount")) {
      tooltip.add(Component.literal(tag.getString("fluidAmount")));
    }
    if (tag.contains("heat")) {
      tooltip.add(Component.literal(tag.getString("heat")));
    }
  }

  @Override
  public ResourceLocation getUid() {
    return new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, "crucible");
  }

  @Override
  public void appendServerData(CompoundTag compoundTag, BlockAccessor accessor) {
    CompoundTag tag = new CompoundTag();
    BlockEntity blockEntity = accessor.getBlockEntity();
    if (blockEntity instanceof CrucibleBlockEntity crucibleEntity) {
      if (crucibleEntity.getSolidAmount() > 0) {
        String solidAmount =
            Component.translatable(
                    "waila.crucible.solid",
                    Component.translatable(
                        crucibleEntity.getCurrentItem().getItem().getDescriptionId()),
                    crucibleEntity.getSolidAmount())
                .getString();
        tag.putString("solidAmount", solidAmount);
      }
      if (crucibleEntity.getFluidAmount() > 0 && crucibleEntity.getFluid() != null) {
        String fluidAmount =
            Component.translatable(
                    "waila.crucible.fluid",
                    Component.translatable(
                        crucibleEntity
                            .getFluid()
                            .defaultFluidState()
                            .createLegacyBlock()
                            .getBlock()
                            .getDescriptionId()),
                    crucibleEntity.getFluidAmount())
                .getString();
        tag.putString("fluidAmount", fluidAmount);
      }
      if (crucibleEntity.getHeat() == 0) {
        tag.putString("heat", Component.translatable("waila.crucible.no_heat").getString());
      } else {
        tag.putString(
            "heat",
            Component.translatable("waila.crucible.heat", crucibleEntity.getHeat()).getString());
      }
      compoundTag.put("Crucible", tag);
    }
  }
}
