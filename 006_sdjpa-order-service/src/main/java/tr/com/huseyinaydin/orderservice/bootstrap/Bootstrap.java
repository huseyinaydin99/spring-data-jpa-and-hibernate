package tr.com.huseyinaydin.orderservice.bootstrap;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tr.com.huseyinaydin.orderservice.domain.Customer;
import tr.com.huseyinaydin.orderservice.domain.OrderHeader;
import tr.com.huseyinaydin.orderservice.repositories.CustomerRepository;
import tr.com.huseyinaydin.orderservice.repositories.OrderHeaderRepository;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    BootstrapOrderService bootstrapOrderService;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        bootstrapOrderService.readOrderData();

        Customer customer = new Customer();
        customer.setCustomerName("Versiyon Testi");
        Customer savedCustomer = customerRepository.save(customer);

        System.out.println("Müşteri kaydı versiyonu: " + savedCustomer.getVersion());

        customerRepository.deleteById(savedCustomer.getId());
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