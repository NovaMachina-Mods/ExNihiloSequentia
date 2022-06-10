package novamachina.exnihilosequentia.common.compat.waila;

import javax.annotation.Nonnull;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import novamachina.exnihilosequentia.common.blockentity.SieveEntity;
import novamachina.exnihilosequentia.common.item.mesh.MeshType;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;
import novamachina.exnihilosequentia.common.utility.StringUtils;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class SieveComponentProvider implements IBlockComponentProvider,
    IServerDataProvider<BlockEntity> {

  @Override
  public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig iPluginConfig) {
    CompoundTag tag = accessor.getServerData().getCompound("Sieve");

    if (tag.contains("progress") && tag.contains("block")) {
      tooltip.add(Component.literal(tag.getString("progress")));
      tooltip.add(Component.literal(tag.getString("block")));
    }
    if (tag.contains("mesh")) {
      tooltip.add(Component.literal(tag.getString("mesh")));
    }
  }

  @Override
  public ResourceLocation getUid() {
    return new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, "sieve");
  }

  @Override
  public void appendServerData(CompoundTag compoundTag, ServerPlayer serverPlayer, Level level,
      BlockEntity blockEntity, boolean b) {
    CompoundTag tag = new CompoundTag();
    if (blockEntity instanceof SieveEntity sieveEntity) {
      if (!sieveEntity.getBlockStack().isEmpty()) {
        tag.putString("progress", Component.translatable("waila.progress", StringUtils
            .formatPercent(sieveEntity.getProgress())).getString());
        tag.putString("block", Component.translatable("waila.sieve.block",
            Component.translatable(sieveEntity.getBlockStack().getDescriptionId())).getString());
      }
      if (sieveEntity.getMeshType() != MeshType.NONE) {
        tag.putString("mesh", Component.translatable("waila.sieve.mesh", Component.translatable(
            "item." + ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + "." + sieveEntity.getMeshType()
                .getMeshName())).getString());
      }
    }
    compoundTag.put("Sieve", tag);
  }
}
