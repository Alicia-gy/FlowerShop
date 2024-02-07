package flowershop.factories;

import flowershop.domain.Product;
import flowershop.enums.ProductType;

public class FactorySelecter {

    public static Product createProductWithFactory(ProductType productType) {
        ProductFactory factory = null;
            switch (productType) {
                case ProductType.TREE:
                    factory = new TreeFactory();
                    break;
                case ProductType.FLOWER:
                    factory = new FlowerFactory();
                    break;
                case ProductType.DECORATION:
                    factory = new DecorationFactory();
                    break;
                default:
                    System.out.println("Not valid product type!");
                    break;
            }
            return factory.createProduct();
    }
}

