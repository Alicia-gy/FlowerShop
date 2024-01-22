package flowershop.controller;

import flowershop.utilities.InputScanner;

public class ShopController {

    //TODO add methods

    public static void menu() {
        boolean exit = false;

        while (!exit) {
            switch (InputScanner.askByte(showMenuOptions())) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                default:
                    exit = true;
                    break;
            }
        }
    }

    static String showMenuOptions() {
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
