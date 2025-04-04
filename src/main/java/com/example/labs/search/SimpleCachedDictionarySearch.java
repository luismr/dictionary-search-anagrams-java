package com.example.labs.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that caches the results of a dictionary search.
 * Implements {@link DictionarySearch} to provide cached dictionary search functionality.
 */
public class SimpleCachedDictionarySearch implements DictionarySearch {
    private final DictionarySearch delegate;
    private final Map<String, List<String>> anagramCache;

    /**
     * Constructor for the SimpleCachedDictionarySearch class.
     * @param dictionary the dictionary to search
     */
    public SimpleCachedDictionarySearch(final List<String> dictionary) {
        this.delegate = new SimpleDictionarySearch(dictionary);
        this.anagramCache = new HashMap<>();
    }

    /**
     * Constructor for the CachedDictionarySearch class.
     * @param delegate the delegate dictionary search
     */
    public SimpleCachedDictionarySearch(final DictionarySearch delegate) {
        this.delegate = delegate;
        this.anagramCache = new HashMap<>();
    }

    /**
     * Search for all anagrams of the query in the dictionary.
     * @param query the query to search for
     * @return a list of all anagrams of the query in the dictionary
     */
    @Override
    public List<String> searchAll(final String query) {
        if (query == null || query.isEmpty()) {
            return List.of();
        }
        final String key = query.toLowerCase();
        return anagramCache.computeIfAbsent(key, k -> delegate.searchAll(key));
    }
}
