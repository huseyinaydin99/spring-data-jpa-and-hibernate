package tr.com.huseyinaydin.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.huseyinaydin.orderservice.domain.OrderApproval;

public interface OrderApprovalRepository extends JpaRepository<OrderApproval, Long> {
}