package flowershop.factories;

import flowershop.domain.Product;

public class FactorySelecter {

    //TODO configure with classes, add import

    public Product createProductWithFactory(ProductType productType) {
        switch(ProductType) {
            case ProductType.Tree:
                return TreeFactory.createProduct();
            case ProductType.Flower:
                return FlowerFactory.createProduct();
            case ProductType.Decoration:
                return DecorationFactory.createProduct();
            default:
                throw new IllegalArgumentException("Product type not found");

        }
    }
}

}
