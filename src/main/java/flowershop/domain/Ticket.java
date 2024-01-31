package flowershop.domain;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Ticket {

	//TODO esperar a tests para saber si funciona
	private int id;
	//TODO rename a nextID
	private int idSiguiente = 1;
	private double totalPrice;
	private int totalProducts;
	private Date saleDate;
	//TODO rename ej: soldProducts, Ezequiel necesita un constructor con parametros para usar en la base de datos
	//ej: Ticket(Hashmap map, double totalPrice, int totalProducts, Date saleDate)
	private HashMap<Product, Integer> tickets;

	public Ticket() {

		this.tickets = new HashMap<Product, Integer>();
		this.saleDate= new GregorianCalendar().getTime();
		this.totalPrice = 0;
		this.totalProducts = 0;
		id = idSiguiente;
		idSiguiente++;
	}

	public Ticket(int id, double totalPrice, int totalProducts, Date saleDate, HashMap<Product, Integer> tickets){
		this.id = id;
		this.totalPrice = totalPrice;
		this.totalProducts = totalProducts;
		this.saleDate = saleDate;
		this.tickets = tickets;
	}

	//TODO prestar atencion a la necesidad de setters
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
	
	public void addProduct(Product p, int cantidad) {

		this.tickets.put(p, cantidad);
		this.totalProducts += cantidad;
		this.totalPrice += p.getPrice()* cantidad;

	}

}
