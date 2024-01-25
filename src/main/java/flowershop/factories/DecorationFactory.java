package flowershop.factories;

import flowershop.domain.Decoration;
import flowershop.domain.Product;
import flowershop.enums.Materials;
import flowershop.utilities.InputScanner;
import flowershop.utilities.TypeSelecter;

public class DecorationFactory implements ProductFactory {

    @Override
    public Product createProduct() {
        String name = InputScanner.askString("Insert product name");
        double price = InputScanner.askDouble("Insert price");
        int quantity = InputScanner.askInt("Insert quantity of products");
        Materials material = TypeSelecter.askMaterial();

        Decoration decoration = new Decoration(name, price, quantity, material);
        return decoration;
    }
}
