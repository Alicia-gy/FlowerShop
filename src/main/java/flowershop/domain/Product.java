package flowershop.domain;

public abstract class Product {

	private String name;
	private double price;
	private int amount;
	private int id;
	private static int idSiguiente = 1;

	public Product(String name, double price, int amount) {

		this.name = name;
		this.price = price;
		this.amount = amount;
		id = idSiguiente;
		idSiguiente++;

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

	public void setCantidad(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Product [name=" + this.name + ", price=" + this.price + ", amount " 
				+ this.amount + ", id=" + this.id + "]";
	}

	

}
