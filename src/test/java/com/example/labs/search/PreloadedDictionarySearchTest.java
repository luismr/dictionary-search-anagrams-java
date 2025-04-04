package com.example.labs.search;

import java.util.List;

class PreloadedDictionarySearchTest extends DictionarySearchTest {

    @Override
    protected DictionarySearch createDictionarySearch(List<String> dictionary) {
        return new PreloadedDictionarySearch(dictionary);
    }
} 