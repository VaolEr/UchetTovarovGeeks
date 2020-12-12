package ru.geekbrains.strorehouse.dao;

import org.springframework.stereotype.Repository;
import ru.geekbrains.strorehouse.model.Product;

import java.util.*;

@Repository
public class ProductDAO {

    private static final Map<String, Product> productMap = new HashMap<String, Product>();

    static {
        initProducts();
    }

    private static void initProducts() {
        Product product = new Product(1,"New Article","New Name",1,1, 1);

        productMap.put(String.valueOf(product.getId()),product);
    }

    public List<Product> getAllProducts(){
        Collection<Product> c = productMap.values();
        List<Product> list = new ArrayList<Product>();
        list.addAll(c);
        return list;
    }
}
