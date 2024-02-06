package flowershop.factories;

import flowershop.domain.Decoration;
import flowershop.domain.Product;
import flowershop.enums.Materials;
import flowershop.utilities.InputScanner;
import flowershop.utilities.TypeSelecter;

import java.util.InputMismatchException;

public class DecorationFactory implements ProductFactory {

    @Override
    public Product createProduct() {

        String name = "";
        double price = 0;
        int quantity = 0;
        Materials material = Materials.WOOD;
        Boolean exit = true;

        while (exit) {
            try {
                name = InputScanner.askString("Insert product name");
                price = InputScanner.askDouble("Insert price");
                quantity = InputScanner.askInt("Insert quantity of products");
                material = TypeSelecter.askMaterial();
                exit = false;
            } catch (InputMismatchException e){
                System.out.println("Type mismatch, please try again");
            }
        }

        Decoration decoration = new Decoration(name, price, quantity, material);
        return decoration;
    }
}
