package flowershop.service.impl;

import flowershop.domain.*;
import flowershop.enums.Colours;
import flowershop.enums.Materials;
import flowershop.enums.ProductType;
import flowershop.factories.FactorySelecter;

import java.sql.Date;
import java.util.HashMap;

public class Serialize {
    public static String serialize(Product product) {
        return "PRODUCT\0" + product.getId() + '\0' + product.getName() + '\0' + product.getPrice() + '\0'
                + product.getAmount();
    }

    public static String serialize(Tree tree){
        return "TREE\0" + tree.getId() + '\0' + tree.getName() + '\0' + tree.getPrice() + '\0' +
                tree.getAmount() + '\0' + tree.getHeight();
    }

    public static String serialize(Flower flower){
        return "FLOWER\0" + flower.getId() + '\0' + flower.getName() + '\0' + flower.getPrice() + '\0' +
                flower.getAmount() + '\0' + flower.getColours();
    }

    public static String serialize(Decoration decoration){
        return "DECORATION\0" + decoration.getId() + '\0' + decoration.getName() + '\0' + decoration.getPrice() + '\0' +
                decoration.getAmount() + '\0' + decoration.getMaterial();
    }

    public static String serialize(Ticket ticket){
        return "TICKET\0" + ticket.getId() + '\0' + ticket.getTotalProducts() + '\0' +
                String.valueOf(ticket.getTotalPrice()) + '\0' + ticket.getSaleDate();
    }

    private Product deserialize(String data) {
        String[] parts = data.split("\0", 6);
        if(ticket)
        {}
        else {return FactorySelecter();}
    }

    public static Tree deserializeTree(String data) {
        String[] parts = data.split("\0", 5);
        return new Tree(parts[1], Double.parseDouble(parts[2]),
                Double.parseDouble(parts[3]), Integer.parseInt(parts[4]));
    }

    public static Flower deserializeFlower(String data) {
        String[] parts = data.split("\0", 5);
        return new Flower(parts[1], Double.parseDouble(parts[2]),
                Integer.parseInt(parts[3]), Colours.valueOf(parts[4]));
    }

    public static Decoration deserializeDecoration(String data) {
        String[] parts = data.split("\0", 5);
        return new Decoration(parts[1], Double.parseDouble(parts[2]),
                Integer.parseInt(parts[3]), Materials.valueOf(parts[4]));
    }

    public static Ticket deserializeTicket(String data) {
        String[] parts = data.split("\0", 5);
        String[] hash = parts[5].split(", ");
        HashMap<Product, Integer> map = new HashMap<>();
        for(int i = 0; i < hash.length; i++) {
            String[] hm = hash[i].split("=");
            String[] prod = hm[0].split("\0", 5);
            //TODO ver como decirle a la fabrica que tipo de producto le entra
            Product product = FactorySelecter.createProductWithFactory(parts[0]);
            map.put(product, Integer.valueOf(hm[1]));
        }

        return new Ticket(Integer.valueOf(parts[0]), Double.parseDouble(parts[1]), Integer.parseInt(parts[2]),
                Date.valueOf(parts[3]), map);
    }

    public static String serialize(HashMap<Product, Integer> map){
        StringBuilder mapAsString = new StringBuilder();
        for (Product key : map.keySet()) {
            mapAsString.append(serialize(key) + "=" + map.get(key) + ", ");
        }
        mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("\0");
        return mapAsString.toString();
    }
}
