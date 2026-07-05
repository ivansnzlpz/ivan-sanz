import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.model.Booking;
import com.example.model.Service;

public class BookingTestCase {

    @Test
    // Several services, without discount
    public void getTotalCostMultipleServicesTestCase() {
        Service hotel = new Service("Hotel", 2, 100.0, 0.0);       // 200.0
        Service transport = new Service("Transporte", 1, 30.0, 0.0); // 30.0
        Booking booking = new Booking("B001", List.of(hotel, transport));

        Assertions.assertEquals(230.0, booking.getTotalCost());
    }

    @Test
    // Empty service list
    public void getTotalCostEmptyListTestCase() {
        Booking booking = new Booking("B002", List.of());

        Assertions.assertEquals(0.0, booking.getTotalCost());
        Assertions.assertEquals(0.0, booking.getTotalWithDiscount());
    }

    @Test
    // Every service has a discount
    public void getTotalWithDiscountAllDiscountedTestCase() {
        Service hotel = new Service("Hotel", 2, 100.0, 10.0);       // 200 -> 180
        Service excursion = new Service("Excursion", 1, 50.0, 20.0); // 50 -> 40
        Booking booking = new Booking("B003", List.of(hotel, excursion));

        Assertions.assertEquals(250.0, booking.getTotalCost());
        Assertions.assertEquals(220.0, booking.getTotalWithDiscount());
    }

    @Test
    // Mixed services: some with discount, some without
    public void getTotalWithDiscountMixedServicesTestCase() {
        Service hotel = new Service("Hotel", 1, 200.0, 5.0);        // 200 -> 190
        Service transport = new Service("Transporte", 3, 20.0, 0.0); // 60 -> 60
        Booking booking = new Booking("B004", List.of(hotel, transport));

        Assertions.assertEquals(260.0, booking.getTotalCost());
        Assertions.assertEquals(250.0, booking.getTotalWithDiscount());
    }
}
