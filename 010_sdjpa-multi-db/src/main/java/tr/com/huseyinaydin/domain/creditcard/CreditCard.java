package tr.com.huseyinaydin.domain.creditcard;

import tr.com.huseyinaydin.domain.CreditCardConverter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CreditCardConverter.class)
    private String cvv;

    @Convert(converter = CreditCardConverter.class)
    private String expirationDate;

    @Transient
    private String creditCardNumber;

    @Transient
    private String firstName;

    @Transient
    private String lastName;

    @Transient
    private String zipCode;
}