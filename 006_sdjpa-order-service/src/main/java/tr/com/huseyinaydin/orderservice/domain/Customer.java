package tr.com.huseyinaydin.orderservice.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.Length;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Customer extends BaseEntity {

    @Length(max = 50)
    private String customerName;

    /*
    Doğrulama (validation), verinin güvenilir ve tutarlı olmasını sağlamak için doğru zamanda ve doğru yerde yapılmalıdır.
    Backend tarafında, veri tabanına kaydedilmeden önce service veya repository katmanında doğrulama yapmak, hatalı verinin sistemde yayılmasını önler.
    Controller seviyesinde @Valid ile giriş verisini doğrulamak, gereksiz işlem yükünü azaltır ve erken hata tespiti sağlar.
    Frontend tarafında ise, kullanıcı deneyimini iyileştirmek için anlık doğrulama (örneğin, form alanlarında anında hata gösterme) yapılmalıdır.
    Doğrulamanın yeri ve zamanı, sistemin mimarisine ve ihtiyaçlarına göre belirlenmelidir. 🚀
    @Valid annotation'ı, bir entity üzerinde belirtilen validasyon kurallarının (örneğin, @NotNull, @Size gibi) Spring Boot tarafından otomatik olarak kontrol edilmesini sağlar ve geçersiz veri durumunda hata fırlatır.
     */
    //@Valid
    @Embedded
    private Address address;

    @Length(max = 20)
    private String phone;
    private String email;

    @Version
    private Integer version;

    @OneToMany(mappedBy = "customer")
    private Set<OrderHeader> orders = new LinkedHashSet<>();

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<OrderHeader> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderHeader> orders) {
        this.orders = orders;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}