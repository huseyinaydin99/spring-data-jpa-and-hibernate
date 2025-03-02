package tr.com.huseyinaydin.repositories.cardholder;

import tr.com.huseyinaydin.domain.cardholder.CreditCardHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardHolderRepository extends JpaRepository<CreditCardHolder, Long> {
}