package novamachina.exnihilosequentia.common.compat.waila;

import java.util.List;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;
import novamachina.exnihilosequentia.world.level.block.entity.BarrelBlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class BarrelComponentProvider
    implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {

  @Override
  public void appendTooltip(
      ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
    CompoundTag tag = blockAccessor.getServerData().getCompound("Barrel");
    int count = tag.getInt("count");
    for (int i = 0; i < count; i++) {
      iTooltip.add(Component.literal(tag.getString("info" + i)));
    }
  }

  @Override
  public ResourceLocation getUid() {
    return new ResourceLocation(ModIds.EX_NIHILO_SEQUENTIA, "barrel");
  }

  @Override
  public void appendServerData(CompoundTag compoundTag, BlockAccessor accessor) {
    BlockEntity blockEntity = accessor.getBlockEntity();
    if (blockEntity instanceof BarrelBlockEntity barrelEntity) {
      CompoundTag compound = new CompoundTag();
      List<Component> info = barrelEntity.getWailaInfo();
      compound.putInt("count", info.size());
      for (int i = 0; i < info.size(); i++) {
        compound.putString("info" + i, info.get(i).getString());
      }
      compoundTag.put("Barrel", compound);
    }
  }
}
