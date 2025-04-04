package com.example.labs.search;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A class that preloads a dictionary and provides anagram search functionality.
 * Implements {@link DictionarySearch} to provide preloaded dictionary anagram search functionality.
 */
public class PreloadedDictionarySearch implements DictionarySearch {
    private final List<String> dictionary;
    private final Map<String, List<String>> cache;

    /**
     * Constructor for the PreloadedDictionarySearch class.
     * Initializes the cache by preloading all anagrams.
     * @param dictionary the dictionary to search
     */
    public PreloadedDictionarySearch(final List<String> dictionary) {
        this.dictionary = dictionary;
        this.cache = preloadAnagrams();
    }

    /**
     * Preload anagrams for all words in the dictionary into a cache.
     * For each word, find all other words that are anagrams and store them.
     * @return Map of words to their anagrams
     */
    private Map<String, List<String>> preloadAnagrams() {
        Map<String, List<String>> cache = new HashMap<>();
        
        for (String word : dictionary) {
            String key = new String(DictionaryUtils.getSortedLowerCaseChars(word));
            if (!cache.containsKey(key)) {
                List<String> anagrams = dictionary.stream()
                    .filter(dictWord -> DictionaryUtils.isAnagram(word, dictWord))
                    .collect(Collectors.toList());
                cache.put(key, anagrams);
            }
        }
        
        return cache;
    }

    /**
     * Search for all anagrams of the query in the dictionary using the preloaded cache.
     * If query not in cache, returns empty list.
     * @param query the query to search for
     * @return a list of all anagrams of the query in the dictionary or empty list if not found
     */
    @Override
    public List<String> searchAll(final String query) {
        if (query == null) {
            return new ArrayList<>();
        }

        String term = new String(DictionaryUtils.getSortedLowerCaseChars(query));
        List<String> results = cache.getOrDefault(term, new ArrayList<>());

        return results;
    }
}
