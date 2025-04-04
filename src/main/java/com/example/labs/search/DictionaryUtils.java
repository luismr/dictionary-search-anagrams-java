package com.example.labs.search;

import java.util.Arrays;

/**
 * Utility class for dictionary operations.
 */
public final class DictionaryUtils {

    private DictionaryUtils() {
        // Private constructor to prevent instantiation
    }

    /**
     * Check if a word is an anagram of the query.
     * @param word the word to check
     * @param query the query to check against
     * @return true if the word is an anagram of the query, false otherwise
     */
    public static boolean isAnagram(final String word, final String query) {
        if (word == null || query == null || word.isEmpty() || query.isEmpty()) {
            return false;
        }
        final char[] wordChars = getSortedLowerCaseChars(word);
        final char[] queryChars = getSortedLowerCaseChars(query);
        
        return Arrays.equals(wordChars, queryChars);
    }

    /**
     * Get the sorted lower case characters of a string.
     * @param str the string to get the sorted lower case characters of
     * @return the sorted lower case characters of the string
     */
    public static char[] getSortedLowerCaseChars(final String str) {
        if (str == null) {
            return new char[0];
        }
        return str.toLowerCase().chars()
            .sorted()
            .mapToObj(i -> (char) i)
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            .toString()
            .toCharArray();
    }
} 