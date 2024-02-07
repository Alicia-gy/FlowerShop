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
        try {
            shopRepository.insert(FactorySelecter
                    .createProductWithFactory(TypeSelecter.askProductType()));
            System.out.println("Product added");
        } catch (IllegalArgumentException | NullPointerException e){
            System.out.println("Something went wrong, please retry");
            e.printStackTrace();
        }
    }

    public void retireProduct() {
        System.out.println("Pick the id of the product wanted to be removed");
        for (Product prod : shopRepository.findAllProducts()) {
            System.out.println(prod.toString());
        }
        Scanner scanner = new Scanner(System.in);
        Product product = shopRepository.findById(scanner.nextLong());
        scanner.nextLine();
        shopRepository.delete(product);
    }

    public void showStock() {
        List<Product> products = shopRepository.findAllProducts();

        String toShow = products.stream()
                .map(Product::toString)
                .collect(Collectors.joining("\n"));

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
        ticket.setId(calculateNextTicketId());
        List<Product> products = shopRepository.findAllProducts();
        Scanner scanner = new Scanner(System.in);
        Boolean breaker = true;
        while (breaker) {
            System.out.println("Select the number of the item wanted: ");
            for (int i = 0; i < products.size(); i++) {
                System.out.println(i + 1 + "- " + products.get(i).toString());
            }
            Product product = products.get(scanner.nextInt()-1);
            scanner.nextLine();
            System.out.println("Select quantity of same product: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            ticket.addProduct(product, quantity);
            System.out.println("If want to add more items, press \"Y\", anything else to continue: ");
            breaker = scanner.nextLine().toUpperCase().equals("Y");
        }
        shopRepository.insertTicket(ticket);
        ticket.getTickets().keySet().forEach(shopRepository::update);
    }

    /*public void showTickets() {
        List<Ticket> tickets = shopRepository.findAllTickets();

        String toShow = tickets.stream()
                .map(Ticket::toString)
                .collect(Collectors.joining("\n"));

        System.out.println(toShow);
    }*/
    public void showTickets(){
        for (String text : shopRepository.findAllTickets()){
            System.out.println(text);
        }
    }

    /*public double getTotalTicketValue() {
        List<Ticket> tickets = shopRepository.findAllTickets();
        double totalValue = 0;

        for(Ticket ticket:tickets){
            totalValue += ticket.getTotalPrice();
        }

        return totalValue;
    }*/

    public double getTotalTicketValue(){
        double totalValue = 0;

        for (String text : shopRepository.findAllTickets()){
            String[] parts = text.split("\t", 5);
            totalValue += Double.parseDouble(parts[2]);
        }

        return totalValue;
    }

    private int calculateNextTicketId(){
        int max = 0;
        for (String data : shopRepository.findAllTickets()){
            String[] info = data.split("\t");
            max = Math.max(max, Integer.parseInt(info[0]));
        }
        return max + 1;
    }
}
