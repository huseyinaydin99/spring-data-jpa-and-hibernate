package tr.com.huseyinaydin.orderservice.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import tr.com.huseyinaydin.orderservice.domain.Product;
import tr.com.huseyinaydin.orderservice.domain.ProductStatus;
import tr.com.huseyinaydin.orderservice.services.ProductService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackageClasses = {ProductService.class})
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Test
    void testGetCategory() {
        Product product = productRepository.findByDescription("PRODUCT1").get();

        assertNotNull(product);
        assertNotNull(product.getCategories());
    }

    @Test
    void testSaveProduct() {
        Product product = new Product();
        product.setDescription("Ürünüm");
        product.setProductStatus(ProductStatus.NEW);

        Product savedProduct = productRepository.save(product);

        Product fetchedProduct = productRepository.getById(savedProduct.getId());

        assertNotNull(fetchedProduct);
        assertNotNull(fetchedProduct.getDescription());
        assertNotNull(fetchedProduct.getCreatedDate());
        assertNotNull(fetchedProduct.getLastModifiedDate());
    }

    @Test
    void addAndUpdateProduct() {
        Product product = new Product();
        product.setDescription("Ürün");
        product.setProductStatus(ProductStatus.NEW);

        Product savedProduct = productService.saveProduct(product);

        Product savedProduct2 = productService.updateQOH(savedProduct.getId(), 25);

        System.out.println(savedProduct2.getQuantityOnHand());
    }

    @Test
    void concurrentUpdateTest() throws InterruptedException {
        Product product = new Product();
        product.setDescription("Test Ürünü");
        product.setProductStatus(ProductStatus.NEW);

        Product savedProduct = productService.saveProduct(product);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable task1 = () -> productService.updateQOH(savedProduct.getId(), 50);
        Runnable task2 = () -> productService.updateQOH(savedProduct.getId(), 100);

        executor.submit(task1);
        executor.submit(task2);

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        Product updatedProduct = productRepository.findById(savedProduct.getId()).orElseThrow(/*() -> new RuntimeException()*/);
        System.out.println("Nihai miktar: " + updatedProduct.getQuantityOnHand());
    }
}