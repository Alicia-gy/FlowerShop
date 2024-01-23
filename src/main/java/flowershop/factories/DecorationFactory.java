package flowershop.factories;

import flowershop.domain.Product;
import flowershop.utilities.TypeSelecter;

public class DecorationFactory implements ProductFactory {

    //TODO configure
    public static Product createProduct() {
        Decoration decoration = new Decoration();
        TypeSelecter.askMaterial();
        return decoration;
    }
}
