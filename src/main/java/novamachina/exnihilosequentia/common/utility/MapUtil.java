package novamachina.exnihilosequentia.common.utility;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {

    /* This only exists for beauty reasons.
    * Instead of adding all the values calling Map::put(k, v), this accepts a two arrays with keys and values
    *
    * Example from heat recipe: (Ugly af)
    *
    * Map<String, Integer> heatByLevel = new HashMap<>();
    * heatByLevel.put("8", 4);
    * heatByLevel.put("7", 4);
    * heatByLevel.put("6", 4);
    * heatByLevel.put("5", 3);
    * heatByLevel.put("4", 2);
    * heatByLevel.put("3", 2);
    * heatByLevel.put("2", 1);
    * heatByLevel.put("1", 1);
    *
    * With this method:
    *
    * String[] keys =  {"8", "7", "6", "5", "4", "3", "2", "1"};
    * Integer[] values =  {4, 4, 3, 3, 2, 2, 1, 1}
    *
    * Map<String, Integer> heatByLevel = MapUtils.fromArrays(keys, values);
    *
    * The method already takes care of key duplication and the equality of the number of keys and values
    * This are the two only cases that could go wrong when writing this.
    *
    * @author lazy @ 29/05/2021 (D/M/Y)
    */
    public static <K, V> Map<K, V> fromArrays(K[] keys, V[] values){
        Map<K, V> map = new HashMap<>();
        if(keys.length != values.length) throw new IllegalStateException("The number of keys and values needs to be the same! You can't have entries with a single value or key.");
        for (int i = 0; i < keys.length; i++) {
            K key = keys[i];
            V value = values[i];
            if(map.containsKey(key)) System.err.println("Key " + key + " is already specified with value " + map.get(key) + ".");
            else map.put(key, value);
        }
        return map;
    }
}
