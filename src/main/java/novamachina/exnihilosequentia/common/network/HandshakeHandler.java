package novamachina.exnihilosequentia.common.network;

import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.fmllegacy.network.NetworkEvent;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class HandshakeHandler {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private HandshakeHandler() {
    }

    public static void handleAcknowledge(HandshakeMessages.C2SAcknowledge message, Supplier<NetworkEvent.Context> ctx) {
        logger.debug("Received acknowledgement from client. " + message);
        ctx.get().setPacketHandled(true);
    }

    public static void handleOreList(HandshakeMessages.S2COreList msg, Supplier<NetworkEvent.Context> ctx) throws InterruptedException {
        logger.debug("Received ore data from server");

        AtomicBoolean updatedOreList = new AtomicBoolean(false);
        CountDownLatch block = new CountDownLatch(1);
        ctx.get().enqueueWork(() -> {
            updatedOreList.set(true);

            if (!EnumOre.updateEnabledOres(msg)) {
                updatedOreList.set(false);
            }
            block.countDown();
        });

        try {
            block.await();
        } catch (InterruptedException e) {
            //TODO remove???
            //Thread.interrupted();
            logger.error(e.getMessage());
            throw e;
        }

        ctx.get().setPacketHandled(true);

        if (updatedOreList.get()) {
            logger.debug("Successfully synchronized ore list from server.");
            PacketHandler.getHandshakeChannel().reply(new HandshakeMessages.C2SAcknowledge(), ctx.get());
        } else {
            logger.debug("Failed to synchronize ore list from server.");
            ctx.get().getNetworkManager().disconnect(new TextComponent("Connection closed - [Ex Nihilo: Sequentia] Failed to synchronize ore list from server."));
        }
    }
}
