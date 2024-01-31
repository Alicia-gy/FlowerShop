package flowershop.factories;

import flowershop.domain.Product;
import flowershop.enums.ProductType;

public class FactorySelecter {

    public static Product createProductWithFactory(ProductType productType) {
        ProductFactory factory = null;
        try {
            switch (productType) {
                case ProductType.TREE:
                    factory = new TreeFactory();
                case ProductType.FLOWER:
                    factory = new FlowerFactory();
                case ProductType.DECORATION:
                    factory = new DecorationFactory();
            }
            return factory.createProduct();

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}

