package flowershop.domain;

import flowershop.enums.Colours;
import flowershop.enums.Materials;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Product nuevo = new Flower("Rose", 2, 20, Colours.YELLOW);
		
		System.out.println(nuevo);
		
		Product nuevo1 = new Decoration("Cenefa", 4,6,Materials.PLASTIC);
		
		System.out.println(nuevo1);
		
		Product nuevo2 = new Tree("bonsai", 6.8, 0.60,10);
		
		System.out.println(nuevo2);
		
		Ticket ticket = new Ticket();
		
		ticket.addProduct(nuevo2, 3);
		
		System.out.println(ticket);
		
			
	}

}
