package tr.com.huseyinaydin.repositories.creditcard;

import tr.com.huseyinaydin.domain.creditcard.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}