package com.novamachina.ens.common.proxy;

import com.novamachina.ens.common.utility.LogUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ServerProxy implements IProxy {

    @Override
    public World getClientWorld() {
        LogUtil.error("Attempted to get client world on server side.");
        throw new IllegalStateException("Only run this on the client!");
    }

    @Override
    public PlayerEntity getClientPlayer() {
        LogUtil.error("Attempted to get client player on server side.");
        throw new IllegalStateException("Only run this on the client!");
    }
}
