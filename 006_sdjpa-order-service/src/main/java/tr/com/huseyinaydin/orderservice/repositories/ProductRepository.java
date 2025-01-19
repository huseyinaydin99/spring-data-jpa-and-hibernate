package tr.com.huseyinaydin.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.huseyinaydin.orderservice.domain.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByDescription(String description);
}