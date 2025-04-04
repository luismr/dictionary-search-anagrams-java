# Dictionary Anagram Search

[![Java 21](https://img.shields.io/badge/Java-21-blue.svg)](https://www.java.com)
[![Maven 3](https://img.shields.io/badge/Maven-3-blue.svg)](https://maven.apache.org)
[![JUnit 5](https://img.shields.io/badge/JUnit-5-blue.svg)](https://junit.org/junit5/)  
[![JaCoCo 0.8.x](https://img.shields.io/badge/JaCoCo-Coverage-blue.svg)](https://www.jacoco.org/jacoco/)  

[![Build Status](https://github.com/luismr/dictionary-search-anagrams-java/actions/workflows/maven.yml/badge.svg)](https://github.com/luismr/dictionary-search-anagrams-java/actions/workflows/maven.yml)
[![Coverage](https://github.com/luismr/dictionary-search-anagrams-java/blob/main/badges/jacoco.svg)](https://github.com/luismr/dictionary-search-anagrams-java/actions/workflows/maven.yml)
[![Branches](https://github.com/luismr/dictionary-search-anagrams-java/blob/main/badges/branches.svg)](https://github.com/luismr/dictionary-search-anagrams-java/actions/workflows/maven.yml)

A Java library for searching anagrams in a dictionary with different implementations.

## Features

- Multiple dictionary search implementations:
  - `SimpleDictionarySearch`: Basic implementation for finding anagrams
  - `SimpleCachedDictionarySearch`: Caches anagram search results
  - `PreloadedDictionarySearch`: Preloads all anagrams for faster lookups
  - `ExpiringCacheDictionarySearch`: Caches anagram search results with expiration

## Usage

```java
// Create a dictionary
List<String> dictionary = Arrays.asList("StEt", "tEts", "SETt", "etTS", "EstT");

// Create a dictionary search implementation
DictionarySearch search = new SimpleDictionarySearch(dictionary);

// Search for anagrams
List<String> anagrams = search.searchAll("test");
// Returns: ["StEt", "tEts", "SETt", "etTS", "EstT"]
```

## Implementations

### SimpleDictionarySearch
Basic implementation that searches for anagrams in the dictionary.

### SimpleCachedDictionarySearch
Caches anagram search results to improve performance for repeated searches.

### PreloadedDictionarySearch
Preloads all anagrams during initialization for instant lookups.

### ExpiringCacheDictionarySearch
Caches anagram search results with a configurable expiration time.

## Testing

The project includes comprehensive test coverage for all implementations:
- Base test class `DictionarySearchTest` with common test cases
- Implementation-specific test classes extending the base test
- Additional tests for caching and expiration functionality
- JaCoCo test coverage reporting with minimum 80% line coverage requirement

### Running Tests with Coverage

```bash
mvn clean test
```

This will:
1. Run all tests
2. Generate a coverage report in `target/site/jacoco/`
3. Fail the build if line coverage is below 80%

## Project Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── example/
│               └── labs/
│                   └── search/
│                       ├── DictionarySearch.java
│                       ├── DictionaryUtils.java
│                       ├── ExpiringCacheDictionarySearch.java
│                       ├── PreloadedDictionarySearch.java
│                       ├── SimpleCachedDictionarySearch.java
│                       └── SimpleDictionarySearch.java
└── test/
    └── java/
        └── com/
            └── example/
                └── labs/
                    └── search/
                        ├── DictionarySearchTest.java
                        ├── ExpiringCacheDictionarySearchTest.java
                        ├── PreloadedDictionarySearchTest.java
                        ├── SimpleCachedDictionarySearchTest.java
                        └── SimpleDictionarySearchTest.java
```

## Getting Started

1. Clone the repository:
```bash
git clone https://github.com/yourusername/dictionary-search-anagrams-java.git
cd dictionary-search-anagrams-java
```

2. Build the project:
```bash
mvn clean install
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details. 