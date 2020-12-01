package novamachina.exnihilosequentia.common.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.FMLHandshakeHandler;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import novamachina.exnihilosequentia.common.utility.Constants;

public class PacketHandler {
    private static SimpleChannel handshakeChannel;

    public static void registerMessages() {
        handshakeChannel = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "handshake"))
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
            .consumer(FMLHandshakeHandler.biConsumerFor((handler, msg, supplier) -> HandshakeHandler.handleOreList(msg, supplier)))
            .markAsLoginPacket()
            .add();
    }

    public static SimpleChannel getHandshakeChannel() {
        return handshakeChannel;
    }
}
