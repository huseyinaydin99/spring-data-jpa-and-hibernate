package tr.com.huseyinaydin.orderservice.repositories;

import tr.com.huseyinaydin.orderservice.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
}