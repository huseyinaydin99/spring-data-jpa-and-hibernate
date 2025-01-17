package tr.com.huseyinaydin.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.huseyinaydin.orderservice.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}