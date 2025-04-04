package com.example.labs.search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

abstract class DictionarySearchTest {

    protected DictionarySearch dictionarySearch;
    protected final List<String> dictionary = Arrays.asList(
        "StEt", "tEts", "SETt", "etTS", "EstT", "TSeT", "tsTe", "TtSe", "TeTs", "TeS",
        "Set", "eST", "sTe", "EtS", "ST", "Ts", "tE", "Et", "T", "s", "sTeT", "Te sT",
        "S EtT", "t SEt", "e TsT", "T eSt", "St eT", "ET sT", "Tt eS", "ES tT", "st TE",
        "TS eT", "E StT", "Ts tE", "S tEt", "TE tS", "eT tS", "T Ts", "TS T", "s Tt",
        "Tt S", "TST e", "t E St", "sTt e", "TT S e", "S t ET", "E t St", "t s ET"
    );

    protected abstract DictionarySearch createDictionarySearch(List<String> dictionary);

    @BeforeEach
    void setUp() {
        dictionarySearch = createDictionarySearch(dictionary);
    }

    @Test
    void testAnagrams() {
        List<String> result = dictionarySearch.searchAll("test");
        assertTrue(result.containsAll(Arrays.asList("StEt", "tEts", "SETt", "etTS", "EstT")));
        assertTrue(result.size() >= 5);
    }

    @Test
    void testAnagramsCaseInsensitive() {
        List<String> result = dictionarySearch.searchAll("TEST");
        assertTrue(result.containsAll(Arrays.asList("StEt", "tEts", "SETt", "etTS", "EstT")));
        assertTrue(result.size() >= 5);
    }

    @Test
    void testNoAnagrams() {
        List<String> result = dictionarySearch.searchAll("xyz");
        assertTrue(result.isEmpty());
    }

    @Test
    void testNullQuery() {
        List<String> result = dictionarySearch.searchAll(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testEmptyQuery() {
        List<String> result = dictionarySearch.searchAll("");
        assertTrue(result.isEmpty());
    }

    @Test
    void testSingleCharacterAnagrams() {
        List<String> result = dictionarySearch.searchAll("T");
        assertTrue(result.containsAll(Arrays.asList("T")));
    }
} 