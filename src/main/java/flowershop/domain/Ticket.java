package flowershop.domain;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Ticket {

	private int id;
	private double totalPrice;
	private int totalProducts;
	private final Date saleDate;
	private HashMap<Product, Integer> tickets;

	public Ticket() {

		this.tickets = new HashMap<>();
		this.saleDate= new GregorianCalendar().getTime();
		this.totalPrice = 0;
		this.totalProducts = 0;
		this.id = 0;
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

	public HashMap<Product, Integer> getTickets() {
		return tickets;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + this.id + ", totalPrice=" + this.totalPrice + ", totalProducts=" + this.totalProducts
				+ ", saleDate=" + this.saleDate + "]";

	}

	public void setId(int id) {
		this.id = id;
	}

	public void addProduct(Product p, int cantidad) {
		if (p.getAmount() >= cantidad) {
			this.tickets.put(p, cantidad);
			this.totalProducts += cantidad;
			this.totalPrice += p.getPrice() * cantidad;
			p.setAmount(p.getAmount() - cantidad);

		} else {
			this.tickets.put(p, p.getAmount());
			this.totalProducts += p.getAmount();
			this.totalPrice += p.getPrice() * p.getAmount();
			p.setAmount(0);
		}
	}
}
