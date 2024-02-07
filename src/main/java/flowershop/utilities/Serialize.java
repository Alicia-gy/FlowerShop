package flowershop.utilities;

import flowershop.domain.*;
import flowershop.enums.Colours;
import flowershop.enums.Materials;

import java.util.HashMap;

public class Serialize {

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

    public static String serialize(HashMap<Product, Integer> map){
        StringBuilder mapAsString = new StringBuilder();
        for (Product key : map.keySet()) {
            mapAsString.append(key.serialize() + "=" + map.get(key) + ", ");
        }
        mapAsString.delete(mapAsString.length()-2, mapAsString.length());
        return mapAsString.toString();
    }
}
