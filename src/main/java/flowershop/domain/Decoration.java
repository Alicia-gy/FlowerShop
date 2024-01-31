package flowershop.domain;

import flowershop.enums.Materials;

public class Decoration extends Product {
	private Materials material;
	
	public Decoration(String name,double price,int amount,Materials material) {
		super(name,price,amount);
		
		this.material = material;
	}

	public Decoration(int id, String name, double price, int amount, Materials material) {
		super(id, name, price, amount);
		this.material = material;
	}

	public Materials getMaterial() {
		return material;
	}


	public void setMaterial(Materials type) {
		this.material = material;
	}


	@Override
	public String toString() {
		return "Decoration [name=" + super.getName() + ", material=" + this.getMaterial() + ", price=" + super.getPrice()
		+ ", amount=" + super.getAmount() +  ", Id: " + super.getId() + "]";
	}


	//TODO add serializable
}
