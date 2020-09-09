package com.novamachina.exnihilosequentia.common.item.ore;

import com.novamachina.exnihilosequentia.common.utility.Color;
import net.minecraftforge.fml.RegistryObject;

public interface IOre {

    boolean isEnabled();

    String getName();

    Color getColor();

    String getChunkName();

    String getPieceName();

    String getIngotName();

    RegistryObject<OreItem> getPieceItem();

    void setPieceItem(RegistryObject<OreItem> pieceItem);

    RegistryObject<OreItem> getChunkItem();

    void setChunkItem(RegistryObject<OreItem> chunkItem);
}
