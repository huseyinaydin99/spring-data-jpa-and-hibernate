package tr.com.huseyinaydin.orderservice.services;

import tr.com.huseyinaydin.orderservice.domain.Product;

public interface ProductService {
    Product saveProduct(Product product);

    Product updateQOH(Long id, Integer quantityOnHand);
}