package flowershop.factories;

import flowershop.domain.Product;
import flowershop.domain.Tree;
import flowershop.utilities.InputScanner;

public class TreeFactory implements ProductFactory {

    @Override
    public Product createProduct() {
        String name = InputScanner.askString("Insert product name");
        double price = InputScanner.askDouble("Insert price");
        int quantity = InputScanner.askInt("Insert quantity of products");
        double height = InputScanner.askDouble("Insert product height");

        Tree tree = new Tree(name, height, price, quantity);
        return tree;
    }
}
