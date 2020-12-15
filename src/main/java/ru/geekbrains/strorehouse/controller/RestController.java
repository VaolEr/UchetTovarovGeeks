package ru.geekbrains.strorehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.strorehouse.dao.ProductDAO;
import ru.geekbrains.strorehouse.model.Product;
import ru.geekbrains.strorehouse.model.ProductWithoutID;
import ru.geekbrains.strorehouse.model.StatusCode;
import ru.geekbrains.strorehouse.model.StatusCodeType;

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

    //Метод для получения списка всех продуктов.
    //В дальнейшем, возможно, предусмотрим получение списка всех продуктов с определёнными параметрами
    //(напр, список продуктов по каждому поставщику)
    @RequestMapping(value = "/products",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Product> getProducts(){
        List<Product> list = productDAO.getAllProducts();
        return list;
    }

    @RequestMapping(value = "/product/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Product addProduct(@PathVariable("id") String id) {
        return productDAO.getProduct(Long.valueOf(id));
    }

    @RequestMapping(value = "/product",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public StatusCode addProduct(@RequestBody ProductWithoutID product){
        return new StatusCode(productDAO.addProduct(product));
    }

    @RequestMapping(value = "/product/{id}",
            method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public StatusCode updateProduct(@PathVariable("id") String id, @RequestBody ProductWithoutID product){
            return new StatusCode(productDAO.updateProduct(Long.valueOf(id), product));
    }

    @RequestMapping(value = "/product/{id}",
            method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public StatusCode deleteProduct(@PathVariable("id") String id){
        return new StatusCode(productDAO.deleteProduct(Long.valueOf(id)));
    }




}
