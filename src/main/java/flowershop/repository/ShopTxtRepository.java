package flowershop.repository;

import flowershop.domain.Product;
import flowershop.domain.Ticket;
import flowershop.service.impl.Serialize;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static flowershop.service.impl.Serialize.deserialize;
import static flowershop.service.impl.Serialize.deserializeTicket;

public class ShopTxtRepository implements iShopRepository {

    private final File file;

    public ShopTxtRepository(File file) {
        this.file = file;
    }


    @Override
    public void insert(Product product) {
        if (product.getId() == 0){
            product.setId((int) calculateNextId());
        }
        //Abro en modo append (añado al final sin sobreescribnir)
        //no necesito cerrar porque lo hace el try (try with resources)
        file.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.append(product.serialize());
            writer.append("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertTicket(Ticket ticket) {
        file.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.append(Serialize.serialize(ticket));
            writer.append("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Product product) {
        Iterator<Product> it = findAllProducts().iterator();
        file.delete();
        while (it.hasNext()){
            Product anotherProd = it.next();
            if (anotherProd.getId() != product.getId()){
                insert(anotherProd);
            }
        }
    }

    @Override
    public List<Product> findAllProducts() {
        if (!file.exists()){
            return Collections.emptyList();
        }
        List<Product> products = new ArrayList<>();
        try(Scanner reader = new Scanner(file)){
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

    public List<Ticket> findAllTickets() {
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
    }

    @Override
    public Product findById(long id) {
        try(Scanner reader = new Scanner(file)){
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
        file.delete();
        while (it.hasNext()){
            Product anotherProd = it.next();
            insert(anotherProd.getId() == product.getId() ? product : anotherProd);
        }
    }

    private long calculateNextId(){
        List<Product> products = findAllProducts();
        long max = 0;
        for (Product product : products){
            if (max < product.getId()){
                max = product.getId();
            }
        }
        return max + 1;
    }
}