package com.example.labs.search;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that searches a dictionary for anagrams.
 * Implements {@link DictionarySearch} to provide simple dictionary search functionality.
 */
public class SimpleDictionarySearch implements DictionarySearch {

    private final List<String> dictionary;

    /**
     * Constructor for the DictionarySearch class.
     * @param dictionary the dictionary to search   
     */
    public SimpleDictionarySearch(final List<String> dictionary) {
        this.dictionary = dictionary;
    }

    /** 
     * Search for all anagrams of the query in the dictionary.
     * Implementation of {@link DictionarySearch#searchAll(String)} that finds all words
     * that are anagrams of the given query by comparing sorted character arrays.
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
        final List<String> result = new ArrayList<>();
        for (String word : dictionary) {
            if (DictionaryUtils.isAnagram(word, query)) {
                result.add(word);
            }
        }
        
        return result;
    }
}