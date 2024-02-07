package flowershop.repository;

import flowershop.domain.Product;
import flowershop.domain.Ticket;
import flowershop.service.impl.Serialize;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static flowershop.service.impl.Serialize.deserialize;

public class ShopTxtRepository implements iShopRepository {

    private final File productFile;
    private final File ticketFile;

    public ShopTxtRepository(File productFile, File ticketFile) {
        this.productFile = productFile;
        this.ticketFile = ticketFile;
    }

    @Override
    public void insert(Product product) {
        if (product.getId() == 0){
            product.setId((int) calculateNextProductId());
        }
        //Abro en modo append (añado al final sin sobreescribir)
        //no necesito cerrar porque lo hace el try (try with resources)
        productFile.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(productFile, true)) {
            writer.append(product.serialize());
            writer.append("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertTicket(Ticket ticket) {
        ticketFile.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(ticketFile, true)) {
            writer.append(Serialize.serialize(ticket));
            writer.append("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Product product) {
        Iterator<Product> it = findAllProducts().iterator();
        productFile.delete();
        while (it.hasNext()){
            Product anotherProd = it.next();
            if (anotherProd.getId() != product.getId()){
                insert(anotherProd);
            }
        }
    }

    @Override
    public List<Product> findAllProducts() {
        if (!productFile.exists()){
            return Collections.emptyList();
        }
        List<Product> products = new ArrayList<>();
        try(Scanner reader = new Scanner(productFile)){
            while(reader.hasNextLine()){
                Product product = deserialize(reader.nextLine());
                if (product != null){
                    products.add(product);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    /*public List<Ticket> findAllTickets() {
        List<Ticket> products = new ArrayList<>();
        try(Scanner reader = new Scanner(file)){
            String text = "";
            while(reader.hasNextLine()){
                text = reader.nextLine();
                if(text.contains("TICKET")){
                    products.add(deserializeTicket(text));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return products;
    }*/
    public List<String> findAllTickets(){
        List<String> tickets = new ArrayList<>();
        try(Scanner reader = new Scanner(ticketFile)){
            while(reader.hasNextLine()){
                tickets.add(reader.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }

    @Override
    public Product findById(long id) {
        try(Scanner reader = new Scanner(productFile)){
            while(reader.hasNextLine()){
                Product product = deserialize(reader.nextLine());
                if (product.getId() == id) {
                    return product;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(Product product) {
        Iterator<Product> it = findAllProducts().iterator();
        productFile.delete();
        while (it.hasNext()){
            Product anotherProd = it.next();
            insert(anotherProd.getId() == product.getId() ? product : anotherProd);
        }
    }

    private long calculateNextProductId(){
        List<Product> products = findAllProducts();
        long max = 0;
        for (Product product : products){
            if (max < product.getId()){
                max = product.getId();
            }
        }
        return max + 1;
    }

    /*private int findLine(String toFind){
        int count = 0;
        try(Scanner reader = new Scanner(productFile)){
            String text = "";
            while(reader.hasNextLine()){
                count++;
                text = reader.nextLine();
                if(text.contains(toFind)){
                    return count;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return count;
    }*/
}