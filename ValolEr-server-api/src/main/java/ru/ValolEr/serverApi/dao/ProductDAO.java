package ru.ValolEr.serverApi.dao;

import org.springframework.stereotype.Component;
import ru.ValolEr.serverApi.models.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDAO {
    private static int PRODUCTS_COUNT;
    private List<Product> products;

    {
        products = new ArrayList<>();
        products.add(new Product(++PRODUCTS_COUNT,"8848", "TShirt Red with dragon", "Clothes", "Adidas", "SPB-3"));
        products.add(new Product(++PRODUCTS_COUNT,"13584", "Dog toy without tail", "Toys", "K&G Corp.", "MSK-17"));
        products.add(new Product(++PRODUCTS_COUNT,"1321U785", "Perfume de la France", "Perfume", "La'cos'te", "SPB-1"));
    }

    public List<Product> index(){
        return products;
    }

    public Product show(int id){
        return products.stream().filter(products -> products.getId() == id).findAny().orElse(null);
    }

    public void save(Product product){
        product.setId(++PRODUCTS_COUNT);
        products.add(product);
    }
}
