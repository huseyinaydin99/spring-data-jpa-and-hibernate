package tr.com.huseyinaydin.orderservice;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import tr.com.huseyinaydin.orderservice.domain.*;
import tr.com.huseyinaydin.orderservice.repositories.CustomerRepository;
import tr.com.huseyinaydin.orderservice.repositories.OrderHeaderRepository;
import tr.com.huseyinaydin.orderservice.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataLoadTest {
    final String PRODUCT_D1 = "Ürün 1";
    final String PRODUCT_D2 = "Ürün 2";
    final String PRODUCT_D3 = "Ürün 3";

    final String TEST_CUSTOMER = "TEST MÜŞTERİ";

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    /*
    select * from persons where id = 1 for update;
FOR UPDATE, SELECT sorgusuna eklenerek, sorgu tarafından döndürülen satırları kilitlemek için kullanılır.

Amaç: Başka işlemlerin aynı satırları değiştirmesini engellemek.
Çalışma Şekli: Satırları exclusive lock (özel kilit) ile kilitler, güncelleme veya silme işlemleri bitene kadar diğer işlemler bu satırlara erişemez.
Örnek Kullanım: Transactional (ACID) sistemlerde veri bütünlüğünü korumak için sıkça kullanılır.
Not: Sadece transaction içinde (START TRANSACTION;) çalışır!
Kilit sorgu bitince değil, transaction tamamlanınca (COMMIT veya ROLLBACK kullanınca) kalkar.
select sorgusu vermemizin nedeni ilgili kaydı belirtip kilitleyebilmek için gerekiyor.
Hangi satırların kilitleneceğini belirtmek için SELECT ... FOR UPDATE kullanılır. Sorgu sonucu dönen satırlar kilitlenir ve başka işlemler tarafından güncellenemez veya silinemez.
    */
    @Test
    void testDBLock() {
        Long id = 55l;

        OrderHeader orderHeader = orderHeaderRepository.findById(id).get();

        Address billTo = new Address();
        billTo.setAddress("A Mah. B Sokak. C APT. Kat 99. No 99. Evren/Merkez");
        orderHeader.setBillToAddress(billTo);
        orderHeaderRepository.saveAndFlush(orderHeader);

        System.out.println("Sipariş güncellendi");
    }

    @Disabled
    @Rollback(value = false)
    @Test
    void testDataLoader() {
        List<Product> products = loadProducts();
        Customer customer = loadCustomers();

        int ordersToCreate = 100;

        for (int i = 0; i < ordersToCreate; i++){
            System.out.println("Sipariş oluşturuluyor #: " + i);
            saveOrder(customer, products);
        }

        orderHeaderRepository.flush();
    }

    @Test
    void testN_PlusOneProblem() {
        Customer customer = customerRepository.findCustomerByCustomerNameIgnoreCase(TEST_CUSTOMER).get();

        IntSummaryStatistics totalOrdered = orderHeaderRepository.findAllByCustomer(customer).stream()
                .flatMap(orderHeader -> orderHeader.getOrderLines().stream())
                .collect(Collectors.summarizingInt(ol -> ol.getQuantityOrdered()));

        System.out.println("toplam sipariş: " + totalOrdered.getSum());
    }

    @Test
    void testLazyVsEager() {
        OrderHeader orderHeader = orderHeaderRepository.getById(52l);
        System.out.println("Sipariş Id: " + orderHeader.getId());
        System.out.println("Müşteri Name: " + orderHeader.getCustomer().getCustomerName());
    }

    private OrderHeader saveOrder(Customer customer, List<Product> products){
        Random random = new Random();

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);

        products.forEach(product -> {
            OrderLine orderLine = new OrderLine();
            orderLine.setProduct(product);
            orderLine.setQuantityOrdered(random.nextInt(20));
            //orderHeader.getOrderLines().add(orderLine);
            orderHeader.addOrderLine(orderLine);
        });

        return orderHeaderRepository.save(orderHeader);
    }

    private Customer loadCustomers() {
        return getOrSaveCustomer(TEST_CUSTOMER);
    }

    private Customer getOrSaveCustomer(String customerName) {
        return customerRepository.findCustomerByCustomerNameIgnoreCase(customerName)
                .orElseGet(() -> {
                    Customer c1 = new Customer();
                    c1.setCustomerName(customerName);
                    c1.setEmail("test@ornek.com");
                    Address address = new Address();
                    address.setAddress("123 Bir yer");
                    address.setCity("Niğde");
                    address.setState("TR");
                    c1.setAddress(address);
                    return customerRepository.save(c1);
                });
    }

    private List<Product> loadProducts(){
        List<Product> products = new ArrayList<>();

        products.add(getOrSaveProduct(PRODUCT_D1));
        products.add(getOrSaveProduct(PRODUCT_D2));
        products.add(getOrSaveProduct(PRODUCT_D3));

        return products;
    }

    private Product getOrSaveProduct(String description) {
        return productRepository.findByDescription(description)
                .orElseGet(() -> {
                    Product p1 = new Product();
                    p1.setDescription(description);
                    p1.setProductStatus(ProductStatus.NEW);
                    return productRepository.save(p1);
                });
    }
}