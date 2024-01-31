package flowershop.controller;

import flowershop.domain.Product;
import flowershop.domain.Ticket;
import flowershop.factories.FactorySelecter;
import flowershop.repository.iShopRepository;
import flowershop.utilities.TypeSelecter;

import java.util.List;
import java.util.stream.Collectors;

public class ShopController {

    private final iShopRepository shopRepository;

    public ShopController(iShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }



    public void addProduct() {
        shopRepository.insert(FactorySelecter
                .createProductWithFactory(TypeSelecter.askProductType()));
    }

    public void retireProduct() {
        shopRepository.delete(FactorySelecter
                .createProductWithFactory(TypeSelecter.askProductType()));
    }

    public void showStock() {
        List<Product> products = shopRepository.findAllProducts();

        String toShow = products.stream()
                .map(Product::toString)
                .collect(Collectors.joining("/n"));

        System.out.println(toShow);
    }

    public double getStockValue() {
        List<Product> products = shopRepository.findAllProducts();
        double totalValue = 0;

        for(Product product:products){
            totalValue +=
                    (product.getPrice() * product.getAmount());
        }

        return totalValue;
    }

    //TODO create method
    public void createTicket() {
    }

    public void showTickets() {
        List<Ticket> tickets = shopRepository.findAllTickets();

        String toShow = tickets.stream()
                .map(Ticket::toString)
                .collect(Collectors.joining("/n"));

        System.out.println(toShow);
    }

    public double getTotalTicketValue() {
        List<Ticket> tickets = shopRepository.findAllTickets();
        double totalValue = 0;

        for(Ticket ticket:tickets){
            totalValue += ticket.getTotalPrice();
        }

        return totalValue;
    }

}
