package tr.com.huseyinaydin.orderservice.bootstrap;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tr.com.huseyinaydin.orderservice.domain.Customer;
import tr.com.huseyinaydin.orderservice.domain.OrderHeader;
import tr.com.huseyinaydin.orderservice.domain.Product;
import tr.com.huseyinaydin.orderservice.domain.ProductStatus;
import tr.com.huseyinaydin.orderservice.repositories.CustomerRepository;
import tr.com.huseyinaydin.orderservice.repositories.OrderHeaderRepository;
import tr.com.huseyinaydin.orderservice.services.ProductService;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    BootstrapOrderService bootstrapOrderService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductService productService;

    private void updateProduct(){
        Product product = new Product();
        product.setDescription("Ürün");
        product.setProductStatus(ProductStatus.NEW);

        Product savedProduct = productService.saveProduct(product);

        Product savedProduct2 = productService.updateQOH(savedProduct.getId(), 25);

        System.out.println("Güncellenen miktar: " + savedProduct2.getQuantityOnHand());
    }

    @Override
    public void run(String... args) throws Exception {
        bootstrapOrderService.readOrderData();

        /*Customer customer = new Customer();
        customer.setCustomerName("Versiyon Testi");
        Customer savedCustomer = customerRepository.save(customer);

        System.out.println("Müşteri kaydı versiyonu: " + savedCustomer.getVersion());

        customerRepository.deleteById(savedCustomer.getId());*/

        Customer customer = new Customer();
        customer.setCustomerName("Versiyon testi");
        Customer savedCustomer = customerRepository.save(customer); //sorun olmaz kayıt yeni giriliyor versiyon no 1'dir.
        System.out.println("Kaydın(satırın) versiyonu: " + savedCustomer.getVersion());

        savedCustomer.setCustomerName("Versiyon testi 2");
        Customer savedCustomer2 = customerRepository.save(savedCustomer); //yeni bir nesne üzerinden yapıldığı(güncelleme) için versiyon no 1 artar.
        System.out.println("Version is: " + savedCustomer2.getVersion());

        savedCustomer2.setCustomerName("Testing Version 3");
        Customer savedCustomer3 = customerRepository.save(savedCustomer2); //yeni bir nesne üzerinden yapıldığı(güncelleme) için versiyon no 1 artar.
        System.out.println("Version is: " + savedCustomer3.getVersion());

        customerRepository.delete(savedCustomer3); //En son oluşturulan müşteri savedCustomer3 siliniyor.
        //eğer savedCustomer nesnesini silmek isteseydik hata alırdık.
        //çünkü
        /*
        Versiyon uyuşmazlığı:
        Eğer versiyon numarasının güncel olmayan bir versiyonla silmeye çalışıyorsanız, yani nesne üzerinde yapılan işlem sırasında versiyon numarası değişmişse (örneğin başka bir işlem tarafından güncellenmişse), JPA bu nesnenin güncel olmadığını fark eder ve OptimisticLockException hatası verir.
        Bu durumda, versiyon numarasındaki uyuşmazlık nedeniyle silme işlemi gerçekleşmez.
        Veritabanındaki versiyon numarasının değişmiş olması:
        Örneğin, savedCustomer3 nesnesi kaydedildikten sonra başka bir işlemle versiyon numarası değiştirilmiş olabilir.
        Bu durumda, silme işlemine geçildiğinde eski versiyon numarasıyla yapılan işlem, güncel versiyonla uyuşmazlık gösterdiği için başarısız olur.
         */
    }

    /*@Transactional
    @Override
    public void run(String... args) throws Exception {
        OrderHeader orderHeader = orderHeaderRepository.findById(1L).get();

        orderHeader.getOrderLines().forEach(ol -> {
            System.out.println(ol.getProduct().getDescription());

            ol.getProduct().getCategories().forEach(cat -> {
                System.out.println(cat.getDescription());
            });
        });
    }*/
}