package ru.geekbrains.strorehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.geekbrains.strorehouse.dao.ProductDAO;
import ru.geekbrains.strorehouse.model.Product;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private ProductDAO productDAO;

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome";
    }

    @RequestMapping(value = "/products", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Product> getProducts(){
        List<Product> list = productDAO.getAllProducts();
        return list;
    }

}
