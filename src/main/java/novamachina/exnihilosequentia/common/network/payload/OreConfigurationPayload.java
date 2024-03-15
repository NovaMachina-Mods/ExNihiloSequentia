package novamachina.exnihilosequentia.common.network.payload;

import java.util.List;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.ExNihiloSequentia;

public record OreConfigurationPayload(List<String> oreList) implements CustomPacketPayload {
  public static final ResourceLocation ID = ExNihiloSequentia.makeId("ore_list");

  public OreConfigurationPayload(FriendlyByteBuf buf) {
    this(buf.readList(FriendlyByteBuf::readUtf));
  }

  @Override
  public void write(FriendlyByteBuf friendlyByteBuf) {
    friendlyByteBuf.writeCollection(this.oreList, FriendlyByteBuf::writeUtf);
  }

  @Override
  public ResourceLocation id() {
    return ID;
  }
}
