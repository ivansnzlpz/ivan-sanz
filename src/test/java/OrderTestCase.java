import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.example.model.Article;
import com.example.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderTestCase {
    
    // Class encapsulate Order object and Articles objects list

    private Order order;
    private List<Article> articles;

    // Setup method to initialize order and articles before each test

    @BeforeEach
    public void setUp() {
        articles = new ArrayList<>(); // Initialize articles list
        articles.add(new Article("Laptop", 2, 1000.0, 10.0)); // Add sample articles
        articles.add(new Article("Smartphone", 3, 500.0, 5.0)); // Add another article
        order = new Order("ORD-001", articles); // Initialize order with articles
        
    }

    @Test
    // Test getters and constructor
    public void gettersTestCase() {
        Assertions.assertEquals("ORD-001", order.getOrderId()); // Verify get orderId
        Assertions.assertEquals(articles, order.getArticles()); // Verify get articles list
        List<Article> emptyArticles = new ArrayList<>(); // Create empty articles list
        order.setArticles(emptyArticles); 
        Assertions.assertNotNull(order.getArticles()); // Verify articles list is not null
        Assertions.assertEquals(0, order.getArticles().size()); // Verify articles list size is 0
        order.setArticles(null);
        Assertions.assertNull(order.getArticles()); // Verify articles list is null
    }

    @Test
    // Test set orderId
    public void setOrderIdTestCase() {
        order.setOrderId("ORD-002");
        Assertions.assertEquals("ORD-002", order.getOrderId()); // Verify set orderId
    }

    @Test
    // Test set articles
    public void setArticlesTestCase() {
        List<Article> newArticles = new ArrayList<>(); // Create new articles list
        newArticles.add(new Article("Tablet", 1, 300.0, 0.0)); // Add article to new list
        order.setArticles(newArticles); 
        Assertions.assertEquals(newArticles, order.getArticles()); // Verify set articles list
    }

    @Test 
    // Test get gross total
    public void getGrossTotalTestCase() {
        double grossTotal = order.getGrossTotal();
        double expectedGrossTotal = (2 * 1000.0) + (3 * 500.0); // Calculate expected gross total
        Assertions.assertEquals(expectedGrossTotal, grossTotal); // Verify gross total
        List<Article> emptyArticles = new ArrayList<>();
        order.setArticles(emptyArticles);
        double grossTotalEmpty = order.getGrossTotal();
        Assertions.assertEquals(0.0, grossTotalEmpty); // Verify gross total with empty
    }

    @Test
    // Test get discounted total
    public void getDiscountedTotalTestCase() {
        double discountedTotal = order.getDiscountedTotal();
        double expectedDiscountedTotal = (2 * 1000.0 * 0.9) + (3 * 500.0 * 0.95); // Calculate expected discounted total
        Assertions.assertEquals(expectedDiscountedTotal, discountedTotal); // Verify discounted total
        List<Article> emptyArticles = new ArrayList<>();
        order.setArticles(emptyArticles);
        double discountedTotalNull = order.getDiscountedTotal();
        Assertions.assertEquals(0.0, discountedTotalNull); // Verify discounted total with null articles
    }
}
