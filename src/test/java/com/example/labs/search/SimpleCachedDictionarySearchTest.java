package com.example.labs.search;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCachedDictionarySearchTest extends DictionarySearchTest {

    @Override
    protected DictionarySearch createDictionarySearch(List<String> dictionary) {
        return new SimpleCachedDictionarySearch(dictionary);
    }

    @Test
    void testCacheHit() {
        // First search to populate cache
        List<String> firstResult = dictionarySearch.searchAll("test");
        assertTrue(firstResult.containsAll(Arrays.asList("StEt", "tEts", "SETt", "etTS", "EstT")));
        assertTrue(firstResult.size() >= 5);

        // Second search should use cache
        List<String> secondResult = dictionarySearch.searchAll("test");
        assertTrue(secondResult.containsAll(Arrays.asList("StEt", "tEts", "SETt", "etTS", "EstT")));
        assertTrue(secondResult.size() >= 5);
        assertEquals(firstResult, secondResult);
    }

    @Test
    void testCacheHitCaseInsensitive() {
        // First search to populate cache
        List<String> firstResult = dictionarySearch.searchAll("test");
        assertTrue(firstResult.containsAll(Arrays.asList("StEt", "tEts", "SETt", "etTS", "EstT")));
        assertTrue(firstResult.size() >= 5);

        // Second search with different case should use cache
        List<String> secondResult = dictionarySearch.searchAll("TEST");
        assertTrue(secondResult.containsAll(Arrays.asList("StEt", "tEts", "SETt", "etTS", "EstT")));
        assertTrue(secondResult.size() >= 5);
        assertEquals(firstResult, secondResult);
    }

    @Test
    void testCacheMiss() {
        // First search to populate cache
        List<String> firstResult = dictionarySearch.searchAll("test");
        assertTrue(firstResult.containsAll(Arrays.asList("StEt", "tEts", "SETt", "etTS", "EstT")));
        assertTrue(firstResult.size() >= 5);

        // Different search should not use cache
        List<String> secondResult = dictionarySearch.searchAll("Set");
        assertTrue(secondResult.contains("Set"));
        assertTrue(secondResult.size() >= 1);
        assertNotEquals(firstResult, secondResult);
    }

    @Test
    void testNullQueryCache() {
        // First search with null
        List<String> firstResult = dictionarySearch.searchAll(null);
        assertTrue(firstResult.isEmpty());

        // Second search with null should use cache
        List<String> secondResult = dictionarySearch.searchAll(null);
        assertTrue(secondResult.isEmpty());
        assertEquals(firstResult, secondResult);
    }

    @Test
    void testEmptyQueryCache() {
        // First search with empty string
        List<String> firstResult = dictionarySearch.searchAll("");
        assertTrue(firstResult.isEmpty());

        // Second search with empty string should use cache
        List<String> secondResult = dictionarySearch.searchAll("");
        assertTrue(secondResult.isEmpty());
        assertEquals(firstResult, secondResult);
    }
} 