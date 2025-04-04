package com.example.labs.search;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ExpiringCacheDictionarySearchTest extends DictionarySearchTest {

    @Override
    protected DictionarySearch createDictionarySearch(List<String> dictionary) {
        return new ExpiringCacheDictionarySearch(new SimpleDictionarySearch(dictionary), 5);
    }

    @Test
    void testCacheExpiration() throws InterruptedException {
        // First search to populate cache
        List<String> firstResult = dictionarySearch.searchAll("test");
        assertTrue(firstResult.containsAll(Arrays.asList("StEt", "tEts", "SETt", "etTS", "EstT")));
        assertTrue(firstResult.size() >= 5);

        // Wait for cache to expire
        TimeUnit.SECONDS.sleep(6);

        // Second search should not use cache
        List<String> secondResult = dictionarySearch.searchAll("test");
        assertTrue(!secondResult.isEmpty());
        assertEquals(firstResult, secondResult);
    }

    @Test
    void testCacheNotExpired() throws InterruptedException {
        // First search to populate cache
        List<String> firstResult = dictionarySearch.searchAll("test");
        assertTrue(firstResult.containsAll(Arrays.asList("StEt", "tEts", "SETt", "etTS", "EstT")));
        assertTrue(firstResult.size() >= 5);

        // Wait less than expiration time
        TimeUnit.SECONDS.sleep(2);

        // Second search should use cache
        List<String> secondResult = dictionarySearch.searchAll("test");
        assertTrue(secondResult.containsAll(Arrays.asList("StEt", "tEts", "SETt", "etTS", "EstT")));
        assertTrue(secondResult.size() >= 5);
        assertEquals(firstResult, secondResult);
    }
} 