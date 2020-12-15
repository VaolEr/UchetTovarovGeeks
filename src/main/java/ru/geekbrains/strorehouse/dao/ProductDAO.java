package ru.geekbrains.strorehouse.dao;

import org.springframework.stereotype.Repository;
import ru.geekbrains.strorehouse.model.Product;
import ru.geekbrains.strorehouse.model.ProductWithoutID;
import ru.geekbrains.strorehouse.model.StatusCodeType;

import java.util.*;

@Repository
public class ProductDAO {

    private static final Map<Long, Product> productMap = new HashMap<Long, Product>();
    private long productCount = 3;

    static {
        initProducts();
    }

    private static void initProducts() {
        //Здесь, как я предполагаю, будем подключаться к БД по JDBC
        Product product1 = new Product(1,"New Article 1","New Name 1",1,1, 1);
        Product product2 = new Product(2,"New Article 2","New Name 2",2,2, 2);
        Product product3 = new Product(3,"New Article 3","New Name 3",3,3, 3);


        productMap.put(product1.getId(),product1);
        productMap.put(product2.getId(),product2);
        productMap.put(product3.getId(),product3);
    }

    public Product createProduct(ProductWithoutID product){
        productCount++;
        return new Product(productCount,product.getArticle(),product.getName(),
                product.getTypeID(), product.getSupplierID(), product.getWarehouseID());
    }

    public List<Product> getAllProducts(){
        Collection<Product> c = productMap.values();
        List<Product> list = new ArrayList<Product>();
        list.addAll(c);
        return list;
    }

    public Product getProduct(Long id) {
        return productMap.get(id);
    }

    public StatusCodeType addProduct(ProductWithoutID productWithoutID) {
        try{
            Product product = createProduct(productWithoutID);
            productMap.put(product.getId(), product);
        } catch (Exception ex){
            ex.printStackTrace();
            return StatusCodeType.DB_ERROR;
        }
        return StatusCodeType.SUCCESS;
    }

    public StatusCodeType updateProduct(Long id, ProductWithoutID productWithoutID) {

        Product product = productMap.put(id, new Product(id, productWithoutID.getArticle(),
                productWithoutID.getName(), productWithoutID.getTypeID(),
                productWithoutID.getSupplierID(), productWithoutID.getWarehouseID()));
        if (product == null){
            return StatusCodeType.DB_ERROR;
        } else {
            return StatusCodeType.SUCCESS;
        }
    }

    public StatusCodeType deleteProduct(Long id) {
        Product p = productMap.remove(id);
        if (p == null){
            return StatusCodeType.DB_ERROR;
        } else {
            return StatusCodeType.SUCCESS;

        }
    }
}
