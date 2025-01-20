package ku.shop;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BuyStepdefs {

    private ProductCatalog catalog;
    private Order order;
    private InsufficientStockException thrownException;

    @Given("the store is ready to service customers")
    public void the_store_is_ready_to_service_customers() {
        catalog = new ProductCatalog();
        order = new Order();
        thrownException = null;  // Initialize exception
    }

    @Given("a product {string} with price {float} and stock of {int} exists")
    public void a_product_exists(String name, double price, int stock) {
        catalog.addProduct(name, price, stock);
    }

    @When("I buy {string} with quantity {int}")
    public void i_buy_with_quantity(String name, int quantity) {
        Product prod = catalog.getProduct(name);
        try {
            order.addItem(prod, quantity);
            thrownException = null;  // Reset if successful
        } catch (InsufficientStockException e) {
            thrownException = e;  // Store the exception
        }
    }

    @Then("total should be {float}")
    public void total_should_be(double total) {
        assertEquals(total, order.getTotal());
    }

    @Then("I cannot buy the product due to insufficient stock")
    public void i_cannot_buy_the_product_due_to_insufficient_stock() {
        assertNotNull(thrownException, "Expected InsufficientStockException but no exception was thrown");
        assertEquals(InsufficientStockException.class, thrownException.getClass(),
                "Expected InsufficientStockException but got " + thrownException.getClass().getSimpleName());
    }
}