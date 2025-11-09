import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.Calculator;

public class testCalculator {

    @Test
    public void testMultiplicacion() {
        Calculator calc = new Calculator();
        int resultado = calc.multiply(2, 3);
        Assertions.assertEquals(6, resultado);
    }

    @Test
    public void testConcatenacion() {
        Calculator calc = new Calculator();
        String resultado = calc.concat("Hola ", "Mundo");
        Assertions.assertEquals("Hola Mundo", resultado);
    }

    @Test 
    public void testSuma() {
        Calculator calc = new Calculator();
        double resultado = calc.sum(2.5, 3.5);
        Assertions.assertEquals(6.0, resultado);
    }
}

