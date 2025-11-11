import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.example.model.Article;

public class ArticleTestCase {

    // Class encapsulate Article object

    private Article article;

    // Setup method to initialize article before each test

    @BeforeEach
    public void setUp() {
        article = new Article("Laptop", 2, 1000.0, 10.0);
    }

    @Test
    // Test getters and constructor
    public void gettersTestCase() {
        Assertions.assertEquals("Laptop", article.getName());
        Assertions.assertEquals(2, article.getQuantity());
        Assertions.assertEquals(1000.0, article.getPrice());
        Assertions.assertEquals(10.0, article.getDiscount());
    }

    @Test
    // Test setters
    public void setNameTestCase() {
        article.setName("Smartphone");
        Assertions.assertEquals("Smartphone", article.getName()); // Verify name updated
    }

    @Test
    // Test set quantity
    public void setQuantityTestCase() {
        article.setQuantity(5);
        Assertions.assertEquals(5, article.getQuantity()); // Verify quantity updated
    }

    @Test
    // Test set price
    public void setPriceTestCase() {
        article.setPrice(1200.0);
        Assertions.assertEquals(1200.0, article.getPrice()); // Verify price updated
    }

    @Test
    // Test set discount
    public void setDiscountTestCase() {
        article.setDiscount(15.0);
        Assertions.assertEquals(15.0, article.getDiscount()); // Verify discount updated
    }

    @Test
    // Test get gross amount
    public void getGrossAmountTestCase() {
        double grossAmount = article.getGrossAmount();
        Assertions.assertEquals(2000.0, grossAmount); // Verify gross amount calculation
        article.setQuantity(0); // Set quantity to zero
        double expectedGrossAmount = article.getGrossAmount();
        Assertions.assertEquals(0.0, expectedGrossAmount); // Verify gross amount with zero
        article.setQuantity(2); // Reset quantity
        article.setPrice(-500.0); // Set negative price
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            article.getGrossAmount();
        }); // Verify exception for negative price
    }

    @Test
    // Test get discounted amount
    public void getDiscountedAmountTestCase() {
        double discountedAmount = article.getDiscountedAmount();
        Assertions.assertEquals(1800.0, discountedAmount); // Verify discounted amount calculation
        article.setDiscount(0.0); // Set discount to zero
        double noDiscountAmount = article.getDiscountedAmount();
        Assertions.assertEquals(2000.0, noDiscountAmount); // Verify amount with no discount
        article.setDiscount(100.0); // Set discount to 100%
        double fullDiscountAmount = article.getDiscountedAmount();
        Assertions.assertEquals(0.0, fullDiscountAmount); // Verify amount with full discount
        article.setDiscount(-10.0); // Set negative discount
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            article.getDiscountedAmount();
        }); // Verify exception for negative discount
    }
}
