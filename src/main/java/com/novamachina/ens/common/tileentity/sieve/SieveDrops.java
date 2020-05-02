package com.novamachina.ens.common.tileentity.sieve;

import com.novamachina.ens.common.item.mesh.EnumMesh;
import com.novamachina.ens.common.utility.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.item.Item;

public class SieveDrops {

    private static final Map<String, List<SieveDropEntry>> stringMeshMap  = new HashMap<>();
    private static final Map<String, List<SieveDropEntry>> flintMeshMap   = new HashMap<>();
    private static final Map<String, List<SieveDropEntry>> ironMeshMap    = new HashMap<>();
    private static final Map<String, List<SieveDropEntry>> diamondMeshMap = new HashMap<>();

    public static void insertItem(String input, Item result, float rarity, EnumMesh meshType) {
        switch (meshType) {
            case STRING:
                insertIntoMap(stringMeshMap, input, result, rarity);
                break;
            case FLINT:
                insertIntoMap(flintMeshMap, input, result, rarity);
                break;
            case IRON:
                insertIntoMap(ironMeshMap, input, result, rarity);
                break;
            case DIAMOND:
                insertIntoMap(diamondMeshMap, input, result, rarity);
                break;
            default:
                LogUtil.warn(String.format("Mesh type \"%s\" does not exist.", meshType.getName()));
                break;
        }
    }

    private static void insertIntoMap(Map<String, List<SieveDropEntry>> map, String input,
        Item result, float rarity) {
        if (map.containsKey(input)) {
            map.get(input).add(new SieveDropEntry(result, rarity));
        } else {
            List<SieveDropEntry> list = new ArrayList<>();
            list.add(new SieveDropEntry(result, rarity));
            map.put(input, list);
        }
    }
}
