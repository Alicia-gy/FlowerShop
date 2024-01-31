package flowershop.repository;

import flowershop.domain.Product;

import java.util.List;

public interface iShopRepository {
    void insert(Product product);
    void delete(Product product);
    List<Product> findAll();
    Product findById(long id);
    void update(Product product);
}
