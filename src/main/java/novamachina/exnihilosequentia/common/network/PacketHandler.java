package novamachina.exnihilosequentia.common.network;

import javax.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.HandshakeHandler;
import net.neoforged.neoforge.network.NetworkRegistry;
import net.neoforged.neoforge.network.simple.SimpleChannel;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

@Slf4j
public class PacketHandler {
  @Nullable private static SimpleChannel handshakeChannel;

  private PacketHandler() {}

  @Nullable
  public static SimpleChannel getHandshakeChannel() {
    return handshakeChannel;
  }

  public static void registerMessages() {
    handshakeChannel =
        NetworkRegistry.ChannelBuilder.named(
                new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "handshake"))
            .networkProtocolVersion(() -> "1")
            .clientAcceptedVersions(s -> true)
            .serverAcceptedVersions(s -> true)
            .simpleChannel();

    handshakeChannel
        .messageBuilder(HandshakeMessages.C2SAcknowledge.class, 99)
        .loginIndex(
            HandshakeMessages.LoginIndexedMessage::getLoginIndex,
            HandshakeMessages.LoginIndexedMessage::setLoginIndex)
        .encoder(HandshakeMessages.C2SAcknowledge::encode)
        .decoder(HandshakeMessages.C2SAcknowledge::decode)
        .consumerNetworkThread(
            HandshakeHandler.indexFirst(
                (handler, msg, context) ->
                    ExNihiloHandshakeHandler.handleAcknowledge(msg, context)))
        .add();

    handshakeChannel
        .messageBuilder(HandshakeMessages.S2COreList.class, 1)
        .loginIndex(
            HandshakeMessages.LoginIndexedMessage::getLoginIndex,
            HandshakeMessages.LoginIndexedMessage::setLoginIndex)
        .encoder(HandshakeMessages.S2COreList::encode)
        .decoder(HandshakeMessages.S2COreList::decode)
        .consumerNetworkThread(
            HandshakeHandler.consumerFor(
                (handler, msg, context) -> {
                  try {
                    ExNihiloHandshakeHandler.handleOreList(msg, context);
                  } catch (InterruptedException e) {
                    log.error(e.getMessage());
                    Thread.currentThread().interrupt();
                  }
                }))
        .markAsLoginPacket()
        .add();
  }
}
