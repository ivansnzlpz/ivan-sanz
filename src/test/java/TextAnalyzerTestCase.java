import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.TextAnalyzer;

public class TextAnalyzerTestCase {

    @Test
    // Contains word test case
    public void containsWordTestCase() {
        List<String> data = List.of("Estoy muy despierto hoy", "Hola mundo");
        boolean existingResult = TextAnalyzer.containsWord("despierto", data);
        Assertions.assertTrue(existingResult); // Verify that "despierto" appears in one of the sentences
        boolean missingResult = TextAnalyzer.containsWord("dormido", data);
        Assertions.assertFalse(missingResult); // Verify that "dormido" does not appear anywhere
    }

    @Test
    // Get element at test case
    public void getElementAtTestCase() {
        List<String> data = List.of("uno", "dos", "tres");
        String validResult = TextAnalyzer.getElementAt(data, 1);
        Assertions.assertEquals("dos", validResult); // Verify that index 1 returns "dos"
        String negativeResult = TextAnalyzer.getElementAt(data, -1);
        Assertions.assertNull(negativeResult); // Verify that a negative index returns null
        String outOfRangeResult = TextAnalyzer.getElementAt(data, 10);
        Assertions.assertNull(outOfRangeResult); // Verify that an out-of-range index returns null
    }

    @Test
    // Find with prefix test case
    public void findWithPrefixTestCase() {
        List<String> data = List.of("perro", "pera", "gato", "perla");
        List<String> resultsList = TextAnalyzer.findWithPrefix("per", data);
        List<String> expectedList = List.of("perro", "pera", "perla");
        Assertions.assertEquals(expectedList, resultsList); // Verify that results match expected results
        Assertions.assertFalse(resultsList.contains("gato")); // Verify that "gato" is not in the results
    }

    @Test
    // Filter containing test case
    public void filterContainingTestCase() {
        List<String> data = List.of("manzana roja", "pera verde", "manzana verde");
        List<String> resultsList = TextAnalyzer.filterContaining("manzana", data);
        List<String> expectedList = List.of("manzana roja", "manzana verde");
        Assertions.assertEquals(expectedList, resultsList); // Verify that results match expected results
        List<String> emptyResultList = TextAnalyzer.filterContaining("kiwi", data);
        Assertions.assertTrue(emptyResultList.isEmpty()); // Verify that no results are found for a non-existing term
    }

    @Test
    // Find exact sentence test case (avanzado)
    public void findExactSentenceTestCase() {
        List<String> data = List.of("Hola", "Adios", "Buenos dias");
        // Case 1: the sentence is the first element
        boolean firstResult = TextAnalyzer.findExactSentence("Hola", data);
        Assertions.assertTrue(firstResult); // Verify that "Hola" is found because it's at position 0
        // Case 2: the sentence exists but is not the first element
        boolean secondResult = TextAnalyzer.findExactSentence("Adios", data);
        Assertions.assertFalse(secondResult); // Verify that "Adios" is NOT found due to the method's logic
        // Case 3: the sentence does not exist at all
        boolean missingResult = TextAnalyzer.findExactSentence("Buenas noches", data);
        Assertions.assertFalse(missingResult); // Verify that a non-existing sentence returns false
        /*Conclusion: findExactSentence solo compara con data.get(0). Cualquier frase
        que exista en la lista pero no esté en la primera posición se da como no encontrada.*/
    }
}
