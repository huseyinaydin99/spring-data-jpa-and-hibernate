package tr.com.huseyinaydin.domain;

import jakarta.persistence.*;
import tr.com.huseyinaydin.config.SpringContextHelper;
import tr.com.huseyinaydin.services.EncryptionService;

public class CreditCardJPACallback {

    @PrePersist
    @PreUpdate
    public void beforeInsertOrUpdate(CreditCard creditCard) {
        System.out.println("UPDATE ve INSERT öncesi(pre)...");
        creditCard.setCreditCardNumber(getEncryptionService().encrypt(creditCard.getCreditCardNumber()));
    }

    @PostPersist
    @PostLoad
    @PostUpdate
    public void postLoad(CreditCard creditCard) {
        System.out.println("INSERT, SELECT, UPDATE sonrası(post)...");
        creditCard.setCreditCardNumber(getEncryptionService().decrypt(creditCard.getCreditCardNumber()));
    }

    private EncryptionService getEncryptionService(){
        return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
    }
}