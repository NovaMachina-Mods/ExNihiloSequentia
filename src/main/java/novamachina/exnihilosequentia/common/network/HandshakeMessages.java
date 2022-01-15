package novamachina.exnihilosequentia.common.network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import net.minecraft.network.FriendlyByteBuf;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HandshakeMessages {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private HandshakeMessages() {

    }

    public static class S2COreList extends LoginIndexedMessage {
        @Nullable private List<EnumOre> oreList;

        public S2COreList() {
            // NOOP
        }

        @Nullable
        public List<EnumOre> getOreList() {
            return oreList;
        }

        @Nonnull
        static S2COreList decode(@Nonnull final FriendlyByteBuf buffer) {
            @Nonnull final S2COreList message = new S2COreList();
            message.oreList = new ArrayList<>();
            final int count = buffer.readInt();
            for (int i = 0; i < count; i++) {
                EnumOre ore = buffer.readEnum(EnumOre.class);
                message.oreList.add(ore);
            }
            logger.debug("Created ore list: " + message.oreList);
            return message;
        }

        void encode(@Nonnull final FriendlyByteBuf buffer) {
            oreList = Arrays.stream(EnumOre.values()).filter(EnumOre::isEnabled).collect(Collectors.toList());
            logger.debug("Writing ore list: " + oreList);
            buffer.writeInt(oreList.size());
            for (@Nonnull final EnumOre ore : oreList) {
                buffer.writeEnum(ore);
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
