package novamachina.exnihilosequentia.common.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fmllegacy.network.FMLHandshakeHandler;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;
import novamachina.exnihilosequentia.api.utility.ExNihiloConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PacketHandler {
    private static final Logger logger = LogManager.getLogger(PacketHandler.class);
    private static SimpleChannel handshakeChannel;

    private PacketHandler() {
    }

    public static SimpleChannel getHandshakeChannel() {
        return handshakeChannel;
    }

    public static void registerMessages() {
        handshakeChannel = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "handshake"))
                .networkProtocolVersion(() -> "1")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        handshakeChannel.messageBuilder(HandshakeMessages.C2SAcknowledge.class, 99)
                .loginIndex(HandshakeMessages.LoginIndexedMessage::getLoginIndex, HandshakeMessages.LoginIndexedMessage::setLoginIndex)
                .encoder(HandshakeMessages.C2SAcknowledge::encode)
                .decoder(HandshakeMessages.C2SAcknowledge::decode)
                .consumer(FMLHandshakeHandler.indexFirst((handler, msg, s) -> HandshakeHandler.handleAcknowledge(msg, s)))
                .add();

        handshakeChannel.messageBuilder(HandshakeMessages.S2COreList.class, 1)
                .loginIndex(HandshakeMessages.LoginIndexedMessage::getLoginIndex, HandshakeMessages.LoginIndexedMessage::setLoginIndex)
                .encoder(HandshakeMessages.S2COreList::encode)
                .decoder(HandshakeMessages.S2COreList::decode)
                .consumer(FMLHandshakeHandler.biConsumerFor((handler, msg, supplier) -> {
                    try {
                        HandshakeHandler.handleOreList(msg, supplier);
                    } catch (InterruptedException e) {
                        logger.error(e.getMessage());
                    }
                }))
                .markAsLoginPacket()
                .add();
    }
}
