package novamachina.exnihilosequentia.common.network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import net.minecraft.network.PacketBuffer;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;

public class HandshakeMessages {
    private static final ExNihiloLogger logger = new ExNihiloLogger(HandshakeMessages.class);

    private HandshakeMessages() {

    }

    public static class S2COreList extends LoginIndexedMessage {
        private List<EnumOre> oreList;

        public S2COreList() {
            // NOOP
        }

        public List<EnumOre> getOreList() {
            return oreList;
        }

        static S2COreList decode(PacketBuffer buffer) {
            S2COreList message = new S2COreList();
            message.oreList = new ArrayList<>();
            int count = buffer.readInt();
            for (int i = 0; i < count; i++) {
                EnumOre ore = buffer.readEnum(EnumOre.class);
                message.oreList.add(ore);
            }
            logger.debug("Created ore list: " + message.oreList);
            return message;
        }

        void encode(PacketBuffer buffer) {
            oreList = Arrays.stream(EnumOre.values()).filter(EnumOre::isEnabled).collect(Collectors.toList());
            logger.debug("Writing ore list: " + oreList);
            buffer.writeInt(oreList.size());
            for (EnumOre ore : oreList) {
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
        static C2SAcknowledge decode(PacketBuffer buf) {
            return new C2SAcknowledge();
        }

        void encode(PacketBuffer buf) {
            // NOOP
        }
    }
}
