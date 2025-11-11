import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Searcher;

public class SearcherTestCase {

    // Class encapsulate Searcher object

    private Searcher searcher;

    // Setup method to initialize searcher before each test

    @BeforeEach
    void setUp() {
        searcher = new Searcher();
    }
    
    @Test
    // Search word test case
    public void searchWordTestCase() {
        List<String> list = List.of("apple", "pear", "orange"); // Create list of fruits
        boolean existingResult = searcher.searchWord("pear", list);
        Assertions.assertTrue(existingResult); // Verify that "pear" exists in the list
        boolean InexistingResult = searcher.searchWord("banana", list);
        Assertions.assertFalse(InexistingResult); // Verify that "banana" does not exist in the list
    }

    @Test
    // Get word by index test case
    public void getWordByIndexTestCase() {
        List<String> list = List.of("apple", "pear", "orange"); // Create list of fruits
        String validResult = searcher.getWordByIndex(list, 1);
        Assertions.assertEquals("pear", validResult); // Verify that index 1 returns "pear"
        String invalidResult = searcher.getWordByIndex(list, 5);
        Assertions.assertNull(invalidResult); // Verify that an invalid index returns null
    }

    @Test
    // Search by prefix test case
    public void searchByPrefixTestCase() {
        List<String> list = List.of("apple", "pear", "orange", "peach"); // Create list of fruits
        List<String> resultsList = searcher.searchByPrefix("pe", list);
        List<String> expectedList = List.of("pear", "peach"); // Create list with expected results
        Assertions.assertEquals(expectedList, resultsList); // Verify that results match expected results
        Assertions.assertFalse(resultsList.contains("apple")); // Verify that "apple" is not in the results
    }

    @Test
    // Filter by keyword test case
    public void filterByKeywordTestCase() {
        List<String> list = List.of("red apple", "green pear", "sweet oranges", "yellow banana"); // Create list of fruits with differents keys
        List<String> resultsList = searcher.filterByKeyword("red", list);
        List<String> expectedList = List.of("red apple"); // Create list with expected results
        Assertions.assertEquals(expectedList, resultsList); // Verify that results match expected results
        List<String> invalidResultList = searcher.filterByKeyword("blue", list);
        Assertions.assertTrue(invalidResultList.isEmpty()); // Verify that no results are found for a non-existing keyword
    }

    @Test
    // Search exact phrase test case
    public void searchExactPhraseTestCase() {
        List<String> list = List.of("apple", "pear", "orange"); // Create list of fruits
        // Case 1: Search for the first phrase
        boolean firstResult = searcher.searchExactPhrase("apple", list);
        Assertions.assertTrue(firstResult); // Verify that apple exists in the list
        // Case 2: Search for a phrase that is not the first
        boolean secondResult = searcher.searchExactPhrase("pear", list);
        Assertions.assertFalse(secondResult); // Verify that pear is not found due to method logic
        // Case 3: Search for a non-existing phrase
        boolean invalidResult = searcher.searchExactPhrase("banana", list);
        Assertions.assertFalse(invalidResult); // Verify that banana does not exist in the list
        /*Conclusion: The method only verifies the first element in the list because if it doesn´t match
        the first element in the loop, the return statement returns the boolean value as false*/
    }
}
