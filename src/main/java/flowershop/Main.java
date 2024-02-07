package flowershop;

import flowershop.controller.ShopController;
import flowershop.controller.View;
import flowershop.repository.ShopTxtRepository;
import flowershop.repository.iShopRepository;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String filepath0 = "src/Products.txt";
        String filepath1 = "src/Tickets.txt";
        iShopRepository ishopRepository = new ShopTxtRepository(new File(filepath0), new File(filepath1));
        ShopController shopController = new ShopController(ishopRepository);
        View view = new View(shopController);

        view.menu();
    }
}