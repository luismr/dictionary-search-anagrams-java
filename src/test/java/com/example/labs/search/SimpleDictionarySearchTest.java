package com.example.labs.search;

import java.util.List;

class SimpleDictionarySearchTest extends DictionarySearchTest {

    @Override
    protected DictionarySearch createDictionarySearch(List<String> dictionary) {
        return new SimpleDictionarySearch(dictionary);
    }

} 