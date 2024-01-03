package novamachina.exnihilosequentia.common.network;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.NetworkEvent;
import net.neoforged.neoforge.network.simple.SimpleChannel;
import novamachina.exnihilosequentia.world.item.Ore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExNihiloHandshakeHandler {

  private static Logger log = LoggerFactory.getLogger(ExNihiloHandshakeHandler.class);

  private ExNihiloHandshakeHandler() {}

  public static void handleAcknowledge(
      @Nonnull final HandshakeMessages.C2SAcknowledge message,
      @Nonnull final NetworkEvent.Context ctx) {
    log.debug("Received acknowledgement from client. " + message);
    ctx.setPacketHandled(true);
  }

  public static void handleOreList(
      @Nonnull final HandshakeMessages.S2COreList msg, @Nonnull final NetworkEvent.Context ctx)
      throws InterruptedException {
    log.debug("Recieved ore data from server");

    @Nonnull final AtomicBoolean updatedOreList = new AtomicBoolean(false);
    @Nonnull final CountDownLatch block = new CountDownLatch(1);
    ctx.enqueueWork(
        () -> {
          updatedOreList.set(true);

          if (!Ore.updateEnabledOres(msg)) {
            updatedOreList.set(false);
          }
          block.countDown();
        });

    try {
      block.await();
    } catch (InterruptedException e) {
      //noinspection ResultOfMethodCallIgnored
      Thread.interrupted();
      log.error(e.getMessage());
      throw e;
    }

    ctx.setPacketHandled(true);

    if (updatedOreList.get()) {
      log.debug("Successfully synchronized ore list from server.");
      @Nullable final SimpleChannel handshakeChannel = PacketHandler.getHandshakeChannel();
      if (handshakeChannel != null) {
        handshakeChannel.reply(new HandshakeMessages.C2SAcknowledge(), ctx);
      }
    } else {
      log.debug("Failed to synchronize ore list from server.");
      ctx.getNetworkManager()
          .disconnect(
              Component.literal(
                  "Connection closed - [Ex Nihilo: Sequentia] Failed to synchronize ore list from server."));
    }
  }
}
