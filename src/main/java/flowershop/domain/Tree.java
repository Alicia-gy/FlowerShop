package flowershop.domain;

public class Tree extends Product {
	//TODO delete duplicated atributes
	private String name;
	private double price;
	private double height;
	private int amount;

	public Tree(String name, double price, double height,int amount) {
		super(name, price,amount);

		this.height = height;
	

	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Tree [name=" + super.getName() + ", price=" + super.getPrice() + 
				", height=" + this.height + ", Id: " + super.getId() + "]";
	}

	//TODO add serializable
}
