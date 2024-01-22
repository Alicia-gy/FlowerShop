package flowershop.factories;

import flowershop.domain.Product;
import flowershop.utilities.TypeSelecter;

public class FlowerFactory implements ProductFactory {

    //TODO configure

    public static Product createProduct() {
        Flower flower = new Flower();
        TypeSelecter.askColour();
        return flower;
    }
}
