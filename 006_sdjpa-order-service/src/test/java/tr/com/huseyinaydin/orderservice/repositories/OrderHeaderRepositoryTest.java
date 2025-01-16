package tr.com.huseyinaydin.orderservice.repositories;

import org.junit.jupiter.api.BeforeEach;
import tr.com.huseyinaydin.orderservice.domain.OrderHeader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import tr.com.huseyinaydin.orderservice.domain.OrderLine;
import tr.com.huseyinaydin.orderservice.domain.Product;
import tr.com.huseyinaydin.orderservice.domain.ProductStatus;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderHeaderRepositoryTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    ProductRepository productRepository;

    Product product;

    @BeforeEach
    void setUp() {
        Product newProduct = new Product();
        newProduct.setProductStatus(ProductStatus.NEW);
        newProduct.setDescription("Ürün testi");
        product = productRepository.saveAndFlush(newProduct);
    }

    @Test
    void testSaveOrderWithLine() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer("Yeni Müşteri");

        OrderLine orderLine = new OrderLine();
        orderLine.setQuantityOrdered(5);
        orderLine.setProduct(product);

        orderHeader.setOrderLines(Set.of(orderLine));
        orderLine.setOrderHeader(orderHeader);

        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

        orderHeaderRepository.flush();

        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());
        assertNotNull(savedOrder.getOrderLines());
        assertEquals(savedOrder.getOrderLines().size(), 1);

        OrderHeader fetchedOrder = orderHeaderRepository.getById(savedOrder.getId());

        assertNotNull(fetchedOrder);
        assertEquals(fetchedOrder.getOrderLines().size(), 1);
    }

    @Test
    void testSaveOrder() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer("Yeni Müşteri");
        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());

        OrderHeader fetchedOrder = orderHeaderRepository.getById(savedOrder.getId());

        assertNotNull(fetchedOrder);
        assertNotNull(fetchedOrder.getId());
        assertNotNull(fetchedOrder.getCreatedDate());
        assertNotNull(fetchedOrder.getLastModifiedDate());
    }
}