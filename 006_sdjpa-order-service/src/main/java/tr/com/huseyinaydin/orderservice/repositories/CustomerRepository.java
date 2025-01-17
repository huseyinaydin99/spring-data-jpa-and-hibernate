package tr.com.huseyinaydin.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.huseyinaydin.orderservice.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}