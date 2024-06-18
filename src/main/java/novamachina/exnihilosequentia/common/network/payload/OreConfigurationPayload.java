package novamachina.exnihilosequentia.common.network.payload;

import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.ExNihiloSequentia;

public record OreConfigurationPayload(List<String> oreList) implements CustomPacketPayload {

  private static StreamCodec<ByteBuf, List<String>> ORE_LIST =
      new StreamCodec<>() {
        @Override
        public List<String> decode(ByteBuf byteBuf) {
          FriendlyByteBuf buf = new FriendlyByteBuf(byteBuf);
          return buf.readCollection(ArrayList::new, FriendlyByteBuf::readUtf);
        }

        @Override
        public void encode(ByteBuf o, List<String> payload) {
          FriendlyByteBuf buf = new FriendlyByteBuf(o);
          buf.writeCollection(payload, FriendlyByteBuf::writeUtf);
        }
      };

  public static final CustomPacketPayload.Type<OreConfigurationPayload> TYPE =
      new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(ExNihiloSequentia.MOD_ID, "ore_list"));
  public static final StreamCodec<ByteBuf, OreConfigurationPayload> STREAM_CODEC =
      StreamCodec.composite(
          ORE_LIST, OreConfigurationPayload::oreList, OreConfigurationPayload::new);

  @Override
  public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
    return TYPE;
  }
}
