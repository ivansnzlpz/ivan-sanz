import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.model.Service;

public class ServiceTestCase {

    private Service service;

    @BeforeEach
    void setUp() {
        service = new Service("Hotel", 3, 50.0, 10.0);
    }

    @Test
    // Normal case test
    public void getTotalCostNormalCaseTestCase() {
        Assertions.assertEquals(150.0, service.getTotalCost()); // 3 * 50
    }

    @Test
    public void getDiscountedCostNormalCaseTestCase() {
        Assertions.assertEquals(135.0, service.getDiscountedCost()); // 150 - 10%
    }

    @Test
    // Edge case: 0 units
    public void getTotalCostZeroQuantityTestCase() {
        Service zeroService = new Service("Transporte", 0, 20.0, 5.0);
        Assertions.assertEquals(0.0, zeroService.getTotalCost());
    }

    @Test
    // Edge case: 0% discount
    public void getDiscountedCostZeroDiscountTestCase() {
        Service noDiscountService = new Service("Excursion", 2, 40.0, 0.0);
        Assertions.assertEquals(noDiscountService.getTotalCost(), noDiscountService.getDiscountedCost());
    }

    @Test
    // Edge case: 100% discount
    public void getDiscountedCostFullDiscountTestCase() {
        Service fullDiscountService = new Service("Excursion", 2, 40.0, 100.0);
        Assertions.assertEquals(0.0, fullDiscountService.getDiscountedCost());
    }

    @Test
    // Invalid case: negative quantity. El bucle no se ejecuta ninguna vez,
    // así que devuelve 0.0 en lugar de un total negativo.
    public void getTotalCostNegativeQuantityTestCase() {
        Service negativeService = new Service("Hotel", -2, 50.0, 10.0);
        Assertions.assertEquals(0.0, negativeService.getTotalCost());
    }

    @Test
    // Invalid case: discount out of [0, 100] range must throw IllegalArgumentException
    public void getDiscountedCostInvalidDiscountTestCase() {
        Service negativeDiscountService = new Service("Hotel", 2, 50.0, -10.0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            negativeDiscountService.getDiscountedCost();
        });
        Service overDiscountService = new Service("Hotel", 2, 50.0, 150.0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            overDiscountService.getDiscountedCost();
        });
    }
}
