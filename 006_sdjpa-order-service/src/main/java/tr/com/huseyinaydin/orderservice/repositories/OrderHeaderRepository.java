package tr.com.huseyinaydin.orderservice.repositories;

import tr.com.huseyinaydin.orderservice.domain.Customer;
import tr.com.huseyinaydin.orderservice.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
    List<OrderHeader> findAllByCustomer(Customer customer);
}