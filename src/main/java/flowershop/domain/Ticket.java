package flowershop.domain;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Ticket {

	private int id;
	private int idSiguiente = 1;
	private double totalPrice;
	private int totalProducts;
	private Date saleDate;
	private HashMap<Product, Integer> tickets;

	public Ticket() {

		this.tickets = new HashMap<Product, Integer>();
		this.saleDate= new GregorianCalendar().getTime();
		this.totalPrice = 0;
		this.totalProducts = 0;
		id = idSiguiente;
		idSiguiente++;

	}

	public int getId() {
		return id;
	}

	public double getTotalPrice() {
		return totalPrice;
	}


	public int getTotalProducts() {
		return totalProducts;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + this.id + ", totalPrice=" + this.totalPrice + ", totalProducts=" + this.totalProducts
				+ ", saleDate=" + this.saleDate + "]";

	}
	
	public void addProduct(Product p, int cantidad) {

		this.tickets.put(p, cantidad);
		this.totalProducts += cantidad;
		this.totalPrice += p.getPrice()* cantidad;

	}

}
