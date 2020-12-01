package novamachina.exnihilosequentia.common.network;

import net.minecraft.network.PacketBuffer;
import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;

public class HandshakeMessages {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    static class LoginIndexedMessage implements IntSupplier {
        private int loginIndex;

        int getLoginIndex() {
            return loginIndex;
        }

        void setLoginIndex(final int loginIndex) {
            this.loginIndex = loginIndex;
        }

        @Override
        public int getAsInt() {
            return getLoginIndex();
        }
    }

    static class C2SAcknowledge extends LoginIndexedMessage {
        void encode(PacketBuffer buf) {}
        static C2SAcknowledge decode(PacketBuffer buf) {
            return new C2SAcknowledge();
        }
    }

    public static class S2COreList extends LoginIndexedMessage {
        private List<EnumOre> oreList;

        public S2COreList() {
        }

        static S2COreList decode(PacketBuffer buffer) {
            S2COreList message = new S2COreList();
            message.oreList = new ArrayList<>();
            int count = buffer.readInt();
            for (int i = 0; i < count; i++) {
                EnumOre ore = buffer.readEnumValue(EnumOre.class);
                message.oreList.add(ore);
            }
            logger.debug("Created ore list: " + message.oreList);
            return message;
        }

        public List<EnumOre> getOreList() {
            return oreList;
        }

        void encode(PacketBuffer buffer) {
            oreList = Arrays.stream(EnumOre.values()).filter(EnumOre::isEnabled).collect(Collectors.toList());
            logger.debug("Writing ore list: " + oreList);
            buffer.writeInt(oreList.size());
            for (EnumOre ore : oreList) {
                buffer.writeEnumValue(ore);
            }
        }
    }
}
