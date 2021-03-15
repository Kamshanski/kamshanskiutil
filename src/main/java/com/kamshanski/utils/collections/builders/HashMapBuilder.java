package com.kamshanski.utils.collections.builders;

import java.util.HashMap;

public class HashMapBuilder<K, V> {
    private HashMap<K, V> map = new HashMap<>();

    public HashMapBuilder<K, V> put(K keys, V value) {
        map.put(keys, value);
        return this;
    }

    // returns hashMap and clears all inner data. can be reused to create new hashMap
    public HashMap<K, V> finish() {
        return this.map;
    }
}
