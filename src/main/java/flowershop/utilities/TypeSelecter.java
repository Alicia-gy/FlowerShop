package flowershop.utilities;

import flowershop.enums.Colours;
import flowershop.enums.Materials;
import flowershop.enums.ProductType;

public class TypeSelecter {

    public static ProductType askProductType() {
        String input = InputScanner.askString(showProductTypeOptions());
        return ProductType.valueOf(input.toLowerCase());
    }

    public static Colours askColour(){
        String input = InputScanner.askString(showColourOptions());
        return Colours.valueOf(input.toUpperCase());
    }

    public static Materials askMaterial(){
        String input = InputScanner.askString(showMaterialOptions());
        return Materials.valueOf(input.toUpperCase());
    }

    static String showProductTypeOptions() {
        return "Flower /n" +
                "Decoration /n" +
                "Tree";
    }

    static String showColourOptions() {
        return "Yellow /n" +
                "Pink /n" +
                "Purple /n" +
                "Blue /n" +
                "Orange /n" +
                "Green /n" +
                "White /n" +
                "Red";
    }

    static String showMaterialOptions() {
        return "Plastic /n +" +
                "Wood";
    }
}
