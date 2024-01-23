package flowershop.repository;

import flowershop.domain.Product;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ShopTxtRepository implements iShopRepository {

    private final File file;

    public ShopTxtRepository(File file) {
        this.file = file;
    }

    private String serialize(Product product) {
        return product.getId() + '\0' + product.getNombre();
    }

    private Product deserialize(String data) {
        String[] parts = data.split("\0", 2);
        return new Product(Long.parseLong(parts[0]), parts[1]);
    }

    @Override
    public void insert(Product product) {
        //Abro en modo append (añado al final sin sobreescribnir)
        //No necesito cerrar porque lo hace el try (try with resources)
        file.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.append(serialize(product));
            writer.append("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Product product) {
        Iterator<Product> it = findAll().iterator();
        file.delete();
        while (it.hasNext()){
            Product anotherProd = it.next();
            if (anotherProd.getId() != product.getId()){
                insert(anotherProd);
            }
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try(Scanner reader = new Scanner(file)){
            while(reader.hasNextLine()){
                products.add(deserialize(reader.nextLine()));
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
        Iterator<Product> it = findAll().iterator();
        file.delete();
        while (it.hasNext()){
            Product anotherProd = it.next();
            insert(anotherProd.getId() == product.getId() ? product : anotherProd);
        }
    }
}