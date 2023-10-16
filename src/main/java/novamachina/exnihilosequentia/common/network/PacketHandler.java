package novamachina.exnihilosequentia.common.network;

import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.HandshakeHandler;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PacketHandler {

  private static Logger log = LoggerFactory.getLogger(PacketHandler.class);
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
                (handler, msg, s) -> ExNihiloHandshakeHandler.handleAcknowledge(msg, s)))
        .add();

    handshakeChannel
        .messageBuilder(HandshakeMessages.S2COreList.class, 1)
        .loginIndex(
            HandshakeMessages.LoginIndexedMessage::getLoginIndex,
            HandshakeMessages.LoginIndexedMessage::setLoginIndex)
        .encoder(HandshakeMessages.S2COreList::encode)
        .decoder(HandshakeMessages.S2COreList::decode)
        .consumerNetworkThread(
            HandshakeHandler.biConsumerFor(
                (handler, msg, supplier) -> {
                  try {
                    ExNihiloHandshakeHandler.handleOreList(msg, supplier);
                  } catch (InterruptedException e) {
                    log.error(e.getMessage());
                    Thread.currentThread().interrupt();
                  }
                }))
        .markAsLoginPacket()
        .add();
  }
}
