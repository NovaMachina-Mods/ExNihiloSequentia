package novamachina.exnihilosequentia.common.network.payload;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.ExNihiloSequentia;

public record OreAckPayload() implements CustomPacketPayload {
  public static final CustomPacketPayload.Type<OreAckPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(ExNihiloSequentia.MOD_ID, "ore_ack"));
  public static final StreamCodec<ByteBuf, OreAckPayload> STREAM_CODEC = StreamCodec.unit(new OreAckPayload());

  @Override
  public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
    return TYPE;
  }
}
