import com.example.Searcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class SearcherTestCase {
    
    @Test
    // Test de busqueda
    public void testBusqueda() {
        Searcher search = new Searcher(); // Objeto de la clase Searcher
        List<String> lista = List.of("manzana", "pera", "mandarinas"); // Crea lista de frutas
        boolean resultadoExistente = search.searchWord("pera", lista);
        Assertions.assertTrue(resultadoExistente); // Verifica que "pera" existe en la lista
        boolean resultadoInexistente = search.searchWord("platano", lista);
        Assertions.assertFalse(resultadoInexistente); // Verifica que "platano" no existe en la lista
    }

    @Test
    // Test de busqueda por índice
    public void testBusquedaPorIndice() {
        Searcher search = new Searcher(); // Objeto de la clase Searcher
        List<String> lista = List.of("manzana", "pera", "mandarinas"); // Crea lista de frutas
        String resultadoValido = search.getWordByIndex(lista, 1);
        Assertions.assertEquals("pera", resultadoValido); // Verifica que el elemento en el índice 1 es "pera"
        String resultadoInvalido = search.getWordByIndex(lista, 5);
        Assertions.assertNull(resultadoInvalido); // Verifica que el índice 5 es inválido y retorna null
    }

    @Test
    // Test de busqueda por prefijo
    public void testBusquedaPorPrefijo() {
        Searcher search = new Searcher(); // Objeto de la clase Searcher
        List<String> lista = List.of("manzana", "pera", "mandarinas", "mango"); // Crea lista de frutas
        List<String> resultados = search.searchByPrefix("ma", lista);
        List<String> esperados = List.of("manzana", "mandarinas", "mango"); // Creamos lista con los resultados esperados
        Assertions.assertEquals(esperados, resultados); // Verifica que los resultados coinciden con los resultados esperados
        Assertions.assertFalse(resultados.contains("pera")); // Verifica que "pera" no está en los resultados
    }

    @Test
    // Test de filtrado por palabra clave
    public void testFiltradoPorPalabraClave() {
        Searcher search = new Searcher(); // Objeto de la clase Searcher
        List<String> lista = List.of("manzana roja", "pera verde", "mandarinas dulces", "mango amarillo"); // Crea lista de frutas con claves
        List<String> resultados = search.filterByKeyword("roja", lista);
        List<String> esperados = List.of("manzana roja"); // Creamos lista con los resultados esperados
        Assertions.assertEquals(esperados, resultados); // Verifica que los resultados coinciden con los resultados esperados
        List<String> resultadosNoExiste = search.filterByKeyword("azul", lista);
        Assertions.assertTrue(resultadosNoExiste.isEmpty()); // Verifica que no hay resultados para la palabra clave "azul"
    }

    @Test
    // Test de busqueda de frase exacta
    public void testBusquedaFraseExacta() {
        Searcher search = new Searcher(); // Objeto de la clase Searcher
        List<String> lista = List.of("manzana", "pera", "mandarinas"); // Crea lista de frutas
        // Caso 1: Buscar la primera frase
        boolean resultadoPrimero = search.searchExactPhrase("manzana", lista);
        Assertions.assertTrue(resultadoPrimero); // Verifica que "manzana" existe en la lista
        // Caso 2: Buscar una frase que no sea el primero
        boolean resultadoSegundo = search.searchExactPhrase("pera", lista);
        Assertions.assertFalse(resultadoSegundo); // Verifica que "pera" no es el primero en la lista
        // Caso 3: Buscar una frase que no existe
        boolean resultadoInexistente = search.searchExactPhrase("platano", lista);
        Assertions.assertFalse(resultadoInexistente); // Verifica que "platano" no existe
        /*Conclusión: El método solo verifica el primer elemento de la lista debido a que si no coincide
        con el primer elemento dentro del bucle debido al return devuelve el booleano como falso.*/
    }
}
