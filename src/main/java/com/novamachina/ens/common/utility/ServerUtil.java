package com.novamachina.ens.common.utility;

import net.minecraft.client.Minecraft;

public class ServerUtil {

    public static boolean isServer() {
        return Minecraft.getInstance().world.isRemote;
    }

}
