package flowershop.factories;

import flowershop.domain.Flower;
import flowershop.domain.Product;
import flowershop.enums.Colours;
import flowershop.utilities.InputScanner;
import flowershop.utilities.TypeSelecter;

public class FlowerFactory implements ProductFactory {

    @Override
    public Product createProduct() {
        String name = InputScanner.askString("Insert product name");
        double price = InputScanner.askDouble("Insert price");
        int quantity = InputScanner.askInt("Insert quantity of products");
        Colours colour = TypeSelecter.askColour();

        Flower flower = new Flower(name, price, quantity, colour);
        return flower;
    }
}
