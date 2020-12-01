package novamachina.exnihilosequentia.common.network;

import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class HandshakeHandler {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    public static void handleOreList(HandshakeMessages.S2COreList msg, Supplier<NetworkEvent.Context> ctx) {
        logger.debug("Recieved ore data from server");

        AtomicBoolean updatedOreList = new AtomicBoolean(false);
        CountDownLatch block = new CountDownLatch(1);
        ctx.get().enqueueWork(() -> {
           updatedOreList.set(true);

           if(!EnumOre.updateEnabledOres(msg)) {
               updatedOreList.set(false);
           }
           block.countDown();
        });

        try {
            block.await();
        } catch (InterruptedException e) {
            Thread.interrupted();
        }

        ctx.get().setPacketHandled(true);

        if(updatedOreList.get()) {
            logger.debug("Successfully synchronized ore list from server.");
            PacketHandler.getHandshakeChannel().reply(new HandshakeMessages.C2SAcknowledge(), ctx.get());
        } else {
            logger.debug("Failed to synchronize ore list from server.");
            ctx.get().getNetworkManager().closeChannel(new StringTextComponent("Connection closed - [Ex Nihilo: Sequentia] Failed to synchronize ore list from server."));
        }
    }

    public static void handleAcknowledge(HandshakeMessages.C2SAcknowledge message, Supplier<NetworkEvent.Context> ctx) {
        logger.debug("Received acknowledgement from client.");
        ctx.get().setPacketHandled(true);
    }
}
