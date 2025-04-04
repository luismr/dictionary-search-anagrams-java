package com.example.labs.search;

import java.util.List;

/**
 * Interface for dictionary search operations.
 */
public interface DictionarySearch {
    /**
     * Search for all anagrams of the query in the dictionary.
     * @param query the query to search for
     * @return a list of all anagrams of the query in the dictionary
     */
    List<String> searchAll(String query);
}
