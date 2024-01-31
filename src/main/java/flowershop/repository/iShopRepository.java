package flowershop.repository;

import flowershop.domain.Product;
import flowershop.domain.Ticket;

import java.util.List;

public interface iShopRepository {
    void insert(Product product);
    void delete(Product product);
    List<Product> findAllProducts();
    List<Ticket> findAllTickets();
    Product findById(long id);
    void update(Product product);
}
