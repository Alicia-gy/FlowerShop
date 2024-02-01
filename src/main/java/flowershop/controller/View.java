package flowershop.controller;

import flowershop.utilities.InputScanner;

public class View {

    private ShopController shopController;

    public View(ShopController shopController) {
        this.shopController = shopController;
    }

    public void menu() {
        boolean exit = false;

        while (!exit) {
            switch (InputScanner.askByte(showMenuOptions())) {
                case 1:
                    shopController.addProduct();
                    System.out.println("Product added");
                    break;
                case 2:
                    shopController.retireProduct();
                    System.out.println("Producto retired");
                    break;
                case 3:
                    shopController.showStock();
                    break;
                case 4:
                    System.out.println("Total Stock Value: /n"
                            + shopController.getStockValue());
                    break;
                case 5:
                    shopController.createTicket();
                    break;
                case 6:
                    shopController.showTickets();
                    break;
                case 7:
                    System.out.println("Total Sales Value: /n"
                            + shopController.getTotalTicketValue());
                    break;
                default:
                    exit = true;
                    break;
            }
        }
    }

    private static String showMenuOptions() {
        return "1- Add product /n" +
                "2- Retire product /n" +
                "3- Show stock /n" +
                "4- Show total stock value /n" +
                "5- Create ticket /n" +
                "6- Show sales history /n" +
                "7- Show total sales value /n" +
                "8- Exit application";
    }
}
