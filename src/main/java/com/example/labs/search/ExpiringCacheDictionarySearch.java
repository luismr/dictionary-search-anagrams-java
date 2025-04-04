package com.example.labs.search;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * A dictionary search implementation that caches anagram results with expiration.
 * Implements {@link DictionarySearch} to provide dictionary anagram search functionality
 * with caching that expires after a specified time.
 */
public class ExpiringCacheDictionarySearch implements DictionarySearch {

    private final DictionarySearch delegate;
    private final long expirationTimeSeconds;
    private final Map<String, CacheEntry<List<String>>> anagramCache;

    public ExpiringCacheDictionarySearch(final List<String> dictionary, final long expirationTimeSeconds) {
        this.delegate = new SimpleDictionarySearch(dictionary);
        this.expirationTimeSeconds = expirationTimeSeconds;
        this.anagramCache = new ConcurrentHashMap<>();
    }

    /**
     * Constructor for the ExpiringCacheDictionarySearch class.
     * @param delegate the dictionary search to delegate to
     * @param expirationTimeSeconds the time in seconds after which cached results expire
     */
    public ExpiringCacheDictionarySearch(final DictionarySearch delegate, final long expirationTimeSeconds) {
        this.delegate = delegate;
        this.expirationTimeSeconds = expirationTimeSeconds;
        this.anagramCache = new ConcurrentHashMap<>();
    }

    /**
     * Search for all anagrams of the query in the dictionary.
     * Implementation of {@link DictionarySearch#searchAll(String)} that caches results
     * with expiration.
     * 
     * @param query the query to search for
     * @return a list of all anagrams of the query in the dictionary
     * @see DictionarySearch#searchAll(String)
     */
    @Override
    public List<String> searchAll(final String query) {
        if (query == null || query.isEmpty()) {
            return List.of();
        }
        final String key = new String(DictionaryUtils.getSortedLowerCaseChars(query));
        final CacheEntry<List<String>> cached = anagramCache.get(key);
        if (cached != null && !cached.isExpired()) {
            return cached.getResult();
        }
        final List<String> result = delegate.searchAll(query);
        anagramCache.put(key, new CacheEntry<>(result));
        return result;
    }

    /**
     * A class that represents a cache entry with expiration.
     * @param <T> the type of the cached result
     */
    private class CacheEntry<T> {
        private final T result;
        private final long timestamp;

        /**
         * Constructor for the CacheEntry class.
         * @param result the result to cache
         */
        CacheEntry(final T result) {
            this.result = result;
            this.timestamp = System.currentTimeMillis();
        }

        /**
         * Get the cached result.
         * @return the cached result
         */
        T getResult() {
            return result;
        }

        /**
         * Check if the cache entry has expired.
         * @return true if the cache entry has expired, false otherwise
         */
        boolean isExpired() {
            return System.currentTimeMillis() - timestamp > TimeUnit.SECONDS.toMillis(expirationTimeSeconds);
        }
    }
}
