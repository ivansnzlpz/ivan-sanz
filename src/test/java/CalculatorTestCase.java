import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.example.Calculator;

public class CalculatorTestCase {

    @Test
    // Test de multiplicacion
    public void testMultiplicacion() {
        Calculator calc = new Calculator(); // Objeto de la clase Calculator
        int resultadoNormal = calc.multiply(2, 3);
        Assertions.assertEquals(6, resultadoNormal); // Verifica que 2 * 3 = 6
        int resultadoCero = calc.multiply(0, 5);
        Assertions.assertEquals(0, resultadoCero); // Verifica que 0 * 5 = 0
        int resultadoNegativo = calc.multiply(-2, 3);
        Assertions.assertEquals(-6, resultadoNegativo); // Verifica que -2 * 3 = -6
    }
    
    @Test
    // Test de concatenacion
    public void testConcatenacion() {
        Calculator calc = new Calculator(); // Ojeto de la clase Calculator
        String resultadoNormal = calc.concat("Hola ", "Mundo"); // Concatenacion normal
        Assertions.assertEquals("Hola Mundo", resultadoNormal);
        String resultadoVacio = calc.concat("hola", null); // Concatenacion con null
        Assertions.assertEquals(Calculator.EMPTY, resultadoVacio);
    }

    @Test 
    // Test de suma
    public void testSuma() {
        Calculator calc = new Calculator(); // Objeto de la clase Calculator
        double resultado = calc.sum(2.5, 3.5); // Suma normal
        Assertions.assertEquals(6.0, resultado);
        double resultadoNegativo = calc.sum(-2.0, -3.0); // Suma con negativos
        Assertions.assertEquals(-5.0, resultadoNegativo);
    }

    @Test
    // Test de descuento
    public void testDescuento() {
        Calculator calc = new Calculator(); // Objeto de la clase Calculator
        double resultado = calc.discount(200.0, 10.0);
        Assertions.assertEquals(180.0, resultado); // Verifica que 10% de 200 es 20, por lo que el resultado es 180
        double resultadoCero = calc.discount(150.0, 0.0);
        Assertions.assertEquals(150.0, resultadoCero); // Verifica que 0% de 150 es 0, por lo que el resultado es 150
        double resultadoCompleto = calc.discount(100.0, 100.0);
        Assertions.assertEquals(0.0, resultadoCompleto); // Verifica que 100% de 100 es 100, por lo que el resultado es 0
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calc.discount(100.0, -5.0);
        }); // Verifica que lanza una excepcion para porcentajes negativos
    }

    @Test
    // Test de lista de totales
    public void testCalculateTotal() {
        Calculator calc = new Calculator(); // Objeto de la clase Calculator
        List<Double> importes = List.of(10.0, 20.0, 30.0); // Crea lista de importes
        double total = calc.calculateTotal(importes); 
        Assertions.assertEquals(60.0, total); // Verifica que la suma es correcta

        List<Double> importesVacios = List.of(); // Crea lista vacía
        double totalVacio = calc.calculateTotal(importesVacios); 
        Assertions.assertEquals(0.0, totalVacio); // Verifica que la suma de una lista vacia es 0
    }
}

