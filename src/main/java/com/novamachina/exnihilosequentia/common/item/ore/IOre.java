package com.novamachina.exnihilosequentia.common.item.ore;

import com.novamachina.exnihilosequentia.common.utility.Color;
import net.minecraft.util.ResourceLocation;

public interface IOre {
   
    
    boolean isEnabled();

    String getName();

    Color getColor();

    String getChunkName();

    String getPieceName();

    String getIngotName();
}
