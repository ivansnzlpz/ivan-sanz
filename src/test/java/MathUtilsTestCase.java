import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.MathUtils;

public class MathUtilsTestCase {

    @Test
    // Multiplication test case
    public void productTestCase() {
        int result = MathUtils.product(4, 5);
        Assertions.assertEquals(20, result); // Verify 4 * 5 = 20
        int zeroResult = MathUtils.product(7, 0);
        Assertions.assertEquals(0, zeroResult); // Verify multiplication by zero
        int negativeResult = MathUtils.product(-3, 5);
        Assertions.assertEquals(-15, negativeResult); // Verify negative values
    }

    @Test
    // Join strings test case
    public void joinStringsTestCase() {
        String result = MathUtils.joinStrings("Hello", "World");
        Assertions.assertEquals("HelloWorld", result);
        String nullResult = MathUtils.joinStrings(null, "World");
        Assertions.assertEquals("empty", nullResult);
        String otherNullResult = MathUtils.joinStrings("Hello", null);
        Assertions.assertEquals("empty", otherNullResult);
    }

    @Test
    // Sum test case
    public void addTestCase() {
        double result = MathUtils.add(3.0, 5.0);
        Assertions.assertEquals(8.0, result);
        double negativeResult = MathUtils.add(-5.0, 3.0);
        Assertions.assertEquals(-2.0, negativeResult);
    }

    @Test
    // Discount test case
    public void applyDiscountTestCase() {
        double result = MathUtils.applyDiscount(200.0, 10.0);
        Assertions.assertEquals(180.0, result); // Verify that 10% of 200 is 20, so the result is 180
        double zeroResult = MathUtils.applyDiscount(150.0, 0.0);
        Assertions.assertEquals(150.0, zeroResult); // Verify that 0% discount returns the same amount
        double fullResult = MathUtils.applyDiscount(100.0, 100.0);
        Assertions.assertEquals(0.0, fullResult); // Verify that 100% discount returns 0
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MathUtils.applyDiscount(100.0, -5.0);
        }); // Verify that negative percentage throws exception
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MathUtils.applyDiscount(100.0, 150.0);
        }); // Verify that percentage over 100 throws exception
    }

    @Test
    // Sum all test case
    public void sumAllTestCase() {
        List<Double> values = List.of(10.0, 20.0, 30.0);
        double total = MathUtils.sumAll(values);
        Assertions.assertEquals(60.0, total); // Verify that the sum is 60

        List<Double> emptyValues = List.of();
        double emptyTotal = MathUtils.sumAll(emptyValues);
        Assertions.assertEquals(0.0, emptyTotal); // Verify that the sum of an empty list is 0
    }
}

