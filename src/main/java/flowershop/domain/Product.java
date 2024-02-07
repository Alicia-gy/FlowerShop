package flowershop.domain;


public abstract class Product {

	private String name;
	private double price;
	private int amount;
	private int id;

	public Product(String name, double price, int amount) {
		this.id = 0;
		this.name = name;
		this.price = price;
		this.amount = amount;
	}

	public Product(int id, String name, double price, int amount) {
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Product [name=" + this.name + ", price=" + this.price + ", amount " 
				+ this.amount + ", id=" + this.id + "]";
	}

	public abstract String serialize();
}
