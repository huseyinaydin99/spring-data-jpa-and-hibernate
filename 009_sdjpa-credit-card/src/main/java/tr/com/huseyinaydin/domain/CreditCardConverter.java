package tr.com.huseyinaydin.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tr.com.huseyinaydin.config.SpringContextHelper;
import tr.com.huseyinaydin.services.EncryptionService;

@Converter
public class CreditCardConverter implements AttributeConverter<String, String> {

    //convertToDatabaseColumn(String attribute) yazma işlemi yapar (Java nesnesini veritabanına kaydetmeden önce şifreler) ✍️
    @Override
    public String convertToDatabaseColumn(String attribute) { //OUT
        return getEncryptionService().encrypt(attribute);
    }

    //convertToEntityAttribute(String dbData) ise okuma işlemi yapar (Veritabanından gelen şifreli veriyi çözüp Java nesnesine dönüştürür) 📖.
    @Override
    public String convertToEntityAttribute(String dbData) { //IN
        return getEncryptionService().decrypt(dbData);
    }

    private EncryptionService getEncryptionService(){
        return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
    }
}