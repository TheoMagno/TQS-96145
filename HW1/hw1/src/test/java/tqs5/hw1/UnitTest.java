package tqs5.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UnitTest {
    private Cache cache;
    @BeforeEach
    void init() {
        cache = new Cache();
    }

    @Test
    void check_expiration_test() throws InterruptedException {
        ArrayList<String> brazil = new ArrayList<>();
        brazil.add("Test");
        brazil.add(String.valueOf(Instant.now().getEpochSecond()));
        cache.put("Brazil", brazil);
        TimeUnit.SECONDS.sleep(30);
        assertEquals("", cache.checkCache("Brazil"));
    }

    @Test
    void check_cache_stats_test() throws InterruptedException {
        ArrayList<String> brazil = new ArrayList<>();
        brazil.add("Test");
        brazil.add(String.valueOf(Instant.now().getEpochSecond()));
        cache.put("Brazil", brazil);
        for (int i=0; i<3; i++) {
            TimeUnit.SECONDS.sleep(10);
            cache.checkCache("Brazil");
        }
        assertEquals(3, cache.getCacheRequests());
        assertEquals(2, cache.getCacheHits());
        assertEquals(1, cache.getCacheMiss());
    }

    @Test
    void check_cache_test() {
        ArrayList<String> brazil = new ArrayList<>();
        brazil.add("Test");
        brazil.add(String.valueOf(Instant.now().getEpochSecond()));
        cache.put("Brazil", brazil);
        assertEquals("Test", cache.checkCache("Brazil"));
    }
}
