import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Calculator;

public class CalculatorTestCase {

    // Class encapsulate Calculator object

    private Calculator calculator;

    // Setup method to initialize calculator before each test

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    // Multiplication test case
    public void multiplyTestCase() {
        int result = calculator.multiply(2, 3);
        Assertions.assertEquals(6, result); // Verify 2 * 3 = 6
        int nullResult = calculator.multiply(0, 5);
        Assertions.assertEquals(0, nullResult); // Verify 0 * 5 = 0
        int negativeResult = calculator.multiply(-2, 3);
        Assertions.assertEquals(-6, negativeResult); // Verify -2 * 3 = -6
    }

    @Test
    // Multiplication with double test case
    public void multiplyDoubleTestCase() {
        double result = calculator.multiplyDouble(2, 3.5);
        Assertions.assertEquals(7.0, result); // Verify 2 * 3.5 = 7.0
        double nullResult = calculator.multiplyDouble(0, 5.0);
        Assertions.assertEquals(0.0, nullResult); // Verify 0 * 5.0 = 0.0
        double negativeResult = calculator.multiplyDouble(-2, 3.0);
        Assertions.assertEquals(-6.0, negativeResult); // Verify -2 * 3.0 = -6.0
    }
    
    @Test
    // Concatenation test case
    public void concatTestCase() {
        String result = calculator.concat("Hello ", "World"); // Normal concatenation
        Assertions.assertEquals("Hello World", result);
        String nullResult = calculator.concat("Hello", null); // Concatenation with null
        Assertions.assertEquals(Calculator.EMPTY, nullResult);
    }

    @Test 
    // Sum test case
    public void sumTestCase() {
        double result = calculator.sum(2.5, 3.5); // Normal sum
        Assertions.assertEquals(6.0, result);
        double negativeResult = calculator.sum(-2.0, -3.0); // Sum with negatives
        Assertions.assertEquals(-5.0, negativeResult);
    }

    @Test
    // Discount test case
    public void discountTestCase() {
        double result = calculator.discount(200.0, 10.0);
        Assertions.assertEquals(180.0, result); // Verify that 10% of 200 is 20, so the result is 180
        double nullResult = calculator.discount(150.0, 0.0);
        Assertions.assertEquals(150.0, nullResult); // Verify that 0% discount returns the same amount
        double fullResult = calculator.discount(100.0, 100.0);
        Assertions.assertEquals(0.0, fullResult); // Verify that 100% discount returns 0
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.discount(100.0, -5.0);
        }); // Verify that negative percentage throws exception
    }

    @Test
    // Calculate total test case
    public void calculateTotalTestCase() {
        List<Double> amounts = List.of(10.0, 20.0, 30.0); // Create list of amounts
        double total = calculator.calculateTotal(amounts); 
        Assertions.assertEquals(60.0, total); // Verify that the sum is 60

        List<Double> nullAmounts = List.of(); // Create empty list
        double nullTotal = calculator.calculateTotal(nullAmounts); 
        Assertions.assertEquals(0.0, nullTotal); // Verify that the sum of an empty list is 0
    }
}

