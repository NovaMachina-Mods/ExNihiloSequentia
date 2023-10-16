package novamachina.exnihilosequentia.common.network;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntSupplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.network.FriendlyByteBuf;
import novamachina.exnihilosequentia.world.item.Ore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandshakeMessages {

  private static Logger log = LoggerFactory.getLogger(HandshakeMessages.class);

  private HandshakeMessages() {}

  public static class S2COreList extends LoginIndexedMessage {

    @Nullable private List<String> oreList;

    public S2COreList() {
      // NOOP
    }

    @Nonnull
    static S2COreList decode(@Nonnull final FriendlyByteBuf buffer) {
      @Nonnull final S2COreList message = new S2COreList();
      message.oreList = new ArrayList<>();
      final int count = buffer.readInt();
      for (int i = 0; i < count; i++) {
        String ore = buffer.readUtf();
        message.oreList.add(ore);
      }
      log.debug("Created ore list: " + message.oreList);
      return message;
    }

    @Nullable
    public List<String> getOreList() {
      return oreList;
    }

    void encode(@Nonnull final FriendlyByteBuf buffer) {
      oreList = Ore.getEnabledOres();
      log.debug("Writing ore list: " + oreList);
      buffer.writeInt(oreList.size());
      for (@Nonnull final String ore : oreList) {
        buffer.writeUtf(ore);
      }
    }
  }

  static class LoginIndexedMessage implements IntSupplier {

    private int loginIndex;

    @Override
    public int getAsInt() {
      return getLoginIndex();
    }

    int getLoginIndex() {
      return loginIndex;
    }

    void setLoginIndex(final int loginIndex) {
      this.loginIndex = loginIndex;
    }
  }

  static class C2SAcknowledge extends LoginIndexedMessage {

    @Nonnull
    static C2SAcknowledge decode(@Nonnull final FriendlyByteBuf buf) {
      return new C2SAcknowledge();
    }

    void encode(@Nonnull final FriendlyByteBuf buf) {
      // NOOP
    }
  }
}
