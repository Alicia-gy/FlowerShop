package flowershop.repository;

import flowershop.domain.Product;
import flowershop.enums.ProductType;

import java.util.List;

public interface iShopRepository {
    void insert(Product product);
    void delete(Product product);
    List<Product> findAll(ProductType productType);
    Product findById(long id);
    void update(Product product);
}
