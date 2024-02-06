package flowershop.domain;

public class Tree extends Product {
	private double height;

	public Tree(String name, double price, int amount, double height) {
		super(name, price, amount);
		this.height = height;
	}

	public Tree(int id, String name, double price, int amount, double height) {
		super(id, name, price, amount);
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

	@Override
	public String serialize() {
		return "TREE\0" + this.getId() + '\0' + this.getName() + '\0' + this.getPrice() + '\0' +
				this.getAmount() + '\0' + this.getHeight();
	}
}
