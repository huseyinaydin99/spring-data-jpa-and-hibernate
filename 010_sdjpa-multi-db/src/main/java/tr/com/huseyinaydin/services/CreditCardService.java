package tr.com.huseyinaydin.services;

import tr.com.huseyinaydin.domain.creditcard.CreditCard;

public interface CreditCardService {
    CreditCard getCreditCardById(Long id);
}