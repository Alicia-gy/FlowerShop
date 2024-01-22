package flowershop.factories;

import flowershop.domain.Product;

public class TreeFactory implements ProductFactory {

    //TODO configure

    public static Product createProduct() {
        Tree tree = new Tree();
        return tree;
    }
}
