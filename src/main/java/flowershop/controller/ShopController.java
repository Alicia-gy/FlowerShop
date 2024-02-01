package flowershop.controller;

import flowershop.domain.Product;
import flowershop.domain.Ticket;
import flowershop.factories.FactorySelecter;
import flowershop.repository.iShopRepository;
import flowershop.utilities.TypeSelecter;

import java.util.List;
import java.util.Scanner;
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

    public void createTicket() {
        Ticket ticket = new Ticket();
        List<Product> products = shopRepository.findAllProducts();
        Scanner scanner = new Scanner(System.in);
        Boolean breaker = true;
        while (breaker) {
            System.out.println("Select the number of the item wanted: ");
            for (int i = 0; i < products.size(); i++) {
                System.out.println(i + 1 + "- " + products.get(i).toString());
            }
            Product product = products.get(scanner.nextInt());
            scanner.nextLine();
            System.out.println("Select quantity of same product: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            ticket.addProduct(product, quantity);
            System.out.println("If don't want to add more items, press \"Y\", anything else to continue: ");
            breaker = scanner.nextLine().equals("Y");
        }
        shopRepository.insertTicket(ticket);
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
