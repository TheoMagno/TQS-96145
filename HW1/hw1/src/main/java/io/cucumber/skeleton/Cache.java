package io.cucumber.skeleton;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;

public class Cache {
    private HashMap<String, List<String>> map;
    private int cacheRequests = 0;
    private int cacheMiss = 0;
    private int cacheHits = 0;
    private long timeToLive = 30L;

    public Cache () {
        map = new HashMap<>();
    }

    public String checkCache(String country) {
        this.cacheRequests += 1;
        if (map.containsKey(country)) {
            if (checkExpiration(country)) {
                map.remove(country);
            }
            else {
                this.cacheHits += 1;
                return map.get(country).get(0);
            }
        }
        this.cacheMiss += 1;
        return "";
    }

    public boolean checkExpiration(String country) {
        return Instant.now().getEpochSecond()>=Integer.parseInt(map.get(country).get(1))+timeToLive;
    }

    public void put(String name, List<String> list) {
        map.put(name, list);
    }

    public int getCacheRequests() {
        return cacheRequests;
    }

    public int getCacheMiss() {
        return cacheMiss;
    }

    public int getCacheHits() {
        return cacheHits;
    }
}
