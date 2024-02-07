package flowershop.service.impl;

import flowershop.domain.*;
import flowershop.enums.Colours;
import flowershop.enums.Materials;

import java.util.HashMap;

public class Serialize {

    /*public static String serialize(Product product) {
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
    }*/

    public static String serialize(Ticket ticket){
        return ticket.getId() + "\t" + ticket.getTotalProducts() + "\t" + String.valueOf(ticket.getTotalPrice())
                + "\t" + ticket.getSaleDate()  + "\t" + serialize(ticket.getTickets());
    }

    public static Product deserialize(String data) {
        String[] parts = data.split("\0", 6);
        if(parts[0].contentEquals("TREE")) {
            return new Tree(Integer.parseInt(parts[1]), parts[2], Double.parseDouble(parts[3]),
                    Integer.parseInt(parts[4]), Double.parseDouble(parts[5]));
        }else if(parts[0].contentEquals("FLOWER")) {
            return new Flower(Integer.parseInt(parts[1]), parts[2], Double.parseDouble(parts[3]),
                    Integer.parseInt(parts[4]), Colours.valueOf(parts[5]));
        }else if(parts[0].contentEquals("DECORATION")) {
            return new Decoration(Integer.parseInt(parts[1]), parts[2], Double.parseDouble(parts[3]),
                    Integer.parseInt(parts[4]), Materials.valueOf(parts[5]));
        }
        return null;
    }

    /*public static Ticket deserializeTicket(String data) {
        String[] parts = data.split("\t", 6);
        String[] hash = parts[5].split(", ");
        HashMap<Product, Integer> map = new HashMap<>();
        for(int i = 0; i < hash.length; i++) {
            String[] hm = hash[i].split("=");
            Product product = deserialize(hm[0]);
            map.put(product, Integer.valueOf(hm[1]));
        }

        return new Ticket(Integer.parseInt(parts[1]), Double.parseDouble(parts[3]), Integer.parseInt(parts[2]),
                new Date(Date.parse(parts[4])), map);
    }*/

    public static String serialize(HashMap<Product, Integer> map){
        StringBuilder mapAsString = new StringBuilder();
        for (Product key : map.keySet()) {
            //mapAsString.append(serialize(key) + "=" + map.get(key) + ", ");
            mapAsString.append(key.serialize() + "=" + map.get(key) + ", ");
        }
        mapAsString.delete(mapAsString.length()-2, mapAsString.length());
        return mapAsString.toString();
    }
}
