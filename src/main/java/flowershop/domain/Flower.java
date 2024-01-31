package flowershop.domain;

import flowershop.enums.Colours;

public class Flower extends Product{
	private Colours colors;
	
	public Flower(String name,double price,int amount,Colours colors) {
		super(name, price,amount);
		
		this.colors = colors;
	}

	public Flower(int id, String name, double price, int amount, Colours colors) {
		super(id, name, price, amount);
		this.colors = colors;
	}

	public Colours getColours() {
		return colors;
	}

	public void setColours(Colours color) {
		this.colors = color;
	}

	@Override
	public String toString() {
		return "Flower [name=" + super.getName() + ", price=" 
	+ super.getPrice() + ", cantidad=" + super.getAmount()
	+ ", color=" + this.getColours() + ", Id: " + super.getId() + "]";
	}
}
