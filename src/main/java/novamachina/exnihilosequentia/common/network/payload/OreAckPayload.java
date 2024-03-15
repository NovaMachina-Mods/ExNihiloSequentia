package novamachina.exnihilosequentia.common.network.payload;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.ExNihiloSequentia;

public record OreAckPayload() implements CustomPacketPayload {
  public static final ResourceLocation ID = ExNihiloSequentia.makeId("ore_ack");

  public OreAckPayload(FriendlyByteBuf buf) {
    this();
  }

  @Override
  public void write(FriendlyByteBuf friendlyByteBuf) {}

  @Override
  public ResourceLocation id() {
    return ID;
  }
}
