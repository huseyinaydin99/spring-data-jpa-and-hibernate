package tr.com.huseyinaydin.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.huseyinaydin.orderservice.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}