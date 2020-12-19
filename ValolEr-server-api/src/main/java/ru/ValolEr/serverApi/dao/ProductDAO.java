package ru.ValolEr.serverApi.dao;

import org.springframework.stereotype.Component;
import ru.ValolEr.serverApi.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDAO {
    private static int PRODUCTS_COUNT;
//    private List<Product> products;
//
//    {
//        products = new ArrayList<>();
//        products.add(new Product(++PRODUCTS_COUNT,"8848", "TShirt Red with dragon", "Clothes", "Adidas", "SPB-3"));
//        products.add(new Product(++PRODUCTS_COUNT,"13584", "Dog toy without tail", "Toys", "K&G Corp.", "MSK-17"));
//        products.add(new Product(++PRODUCTS_COUNT,"1321U785", "Perfume de la France", "Perfume", "La'cos'te", "SPB-1"));
//    }

    private static final String URL = "jdbc:mysql://localhost:3306/uchet_tovarov_geeks_database?useUnicode=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "36352";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables){
            throwables.printStackTrace();
            System.out.println(throwables.getSQLState());
        }
    }

    public List<Product> index(){
        //return products;
        List<Product> products = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM products;";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setArticle(resultSet.getString("article"));
                product.setType(String.format("%d",resultSet.getInt("type_id")));
                product.setSupplier(String.format("%d",resultSet.getInt("supplier_id")));
                product.setWarehouse(String.format("%d",resultSet.getInt("warehouse_id")));

                products.add(product);
                System.out.println(product.toString());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return products;
    }

    public Product show(int id){
        //return products.stream().filter(products -> products.getId() == id).findAny().orElse(null);
        return null;
    }

    public void save(Product product){
//        product.setId(++PRODUCTS_COUNT);
//        products.add(product);
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO products VALUES(" + 4 +   ",'" + product.getArticle() + "'" +
                                                                ",'" + product.getName() + "'" +
                                                                ",'" + Integer.parseInt(product.getType()) + "'" +
                                                                ",'" + Integer.parseInt(product.getSupplier()) + "'" +
                                                                ",'" + Integer.parseInt(product.getWarehouse()) + "'" + ")";
            statement.executeUpdate(SQL);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, Product product){
//        Product productToBeUpdated = show(id);
//
//        productToBeUpdated.setName(product.getName());
//        productToBeUpdated.setArticle(product.getArticle());
//        productToBeUpdated.setType(product.getType());
//        productToBeUpdated.setSupplier(product.getSupplier());
//        productToBeUpdated.setWarehouse(product.getWarehouse());
    }

    public void delete(int id){
        //products.removeIf(product -> product.getId() == id);
    }
}
