package tr.com.huseyinaydin.repositories.pan;

import tr.com.huseyinaydin.domain.pan.CreditCardPAN;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardPANRepository extends JpaRepository<CreditCardPAN, Long> {
}