/*
Design and Implement LFU caching
*/

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

class LFUCaching {
    private int capacity;

    /*first hash map keep track of the key value pair in the cache.*/
    private Map<Integer, Integer> valueMap;
    /*   second hash map has the key and the frequency of the key referenced.*/
    private Map<Integer, Integer> countMap;

    /* it has frequency as key and list of key with the frequency at the value.*/
    private Map<Integer, LinkedHashSet<Integer>> frequencyMap;
    private int minFreq;

    public LFUCaching(int capacity) {
        this.capacity = capacity;
        this.valueMap = new HashMap<>();
        this.frequencyMap = new HashMap<>();
        this.countMap = new HashMap<>();
        this.minFreq = 0;
    }

    public int get(int key) {
        if (!valueMap.containsKey(key)) {
            return -1;
        }
        /*/*remove the key from the list of key which is the value of the related frequency
If after removing the key from frequencyMap the list value of the key is empty then remove the key value pair altogether.
increase frequency by 1 and create new record in the frequencyMap else just add the key to the list of the respective frequency
update the value of the key in the count map by increasing the frequency by 1
return the value of the respective key from value map.
* */
        int value = valueMap.get(key);
        int keyFreq = countMap.get(key);
        countMap.put(key, keyFreq + 1);
        frequencyMap.get(keyFreq).remove(key);
        if (keyFreq == minFreq && frequencyMap.get(keyFreq).isEmpty()) {
            minFreq++;
        }
        frequencyMap.computeIfAbsent(keyFreq + 1, k -> new LinkedHashSet<>()).add(key);
        return value;
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        if (valueMap.containsKey(key)) {
            /*If the key already exist in valueMap then just update the value and increase the frequency.*/
            valueMap.put(key, value);
            get(key);
            return;
        }
        if (valueMap.size() >= capacity) {
                         /*  If the size is equal to the capacity then the list frequently used value should be removed from all three data structures.
         If there are multiple value with same frequency the one used the least recently is to be removed.*/
            int evict = frequencyMap.get(minFreq).iterator().next();
            valueMap.remove(evict);
            countMap.remove(evict);
            frequencyMap.get(minFreq).remove(evict);
        }

         /*If the size of the valueMap is less than of the capacity no removal required so just add the values
                 to all three data structure*/
        valueMap.put(key, value);
        countMap.put(key, 1);
        frequencyMap.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
        minFreq = 1;
    }

    public static void main(String[] args) {
        LFUCaching lfuCache = new LFUCaching(5);
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        System.out.println(lfuCache.get(1));
        lfuCache.put(3, 3);
        System.out.println(lfuCache.get(4));
        System.out.println(lfuCache.get(3));
        lfuCache.put(4, 4);
        System.out.println(lfuCache.get(5));
        System.out.println(lfuCache.get(3));
        System.out.println(lfuCache.get(4));

    }
}
