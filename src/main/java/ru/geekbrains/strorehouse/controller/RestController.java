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

    //GET на получение списка товара.
    //С параметрами в URL по ограничению выдачи (по складу, по бренду, диапазон).
    //Возвращает json массив с товарами.

    //GET на получение одного товара.
    //В ответ ждем json с его характиристиками

    //POST на создание одного товара.
    //В теле запроса в json передаем характеристики. В ответ получаем код (успех/фейл)

    //POST / PUT на обновление одного товара.
    //В Урле будет id товара, в теле запроса в json его новые параметры.
    //В ответ будем ждать код (успех / фейл)

    //DELETE на удаление товара. Удаление по id из урла.
    //В ответ будем ждать код (успех / фейл)

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
        return productDAO.getProduct(id);
    }

    @RequestMapping(value = "/product",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public StatusCode addProduct(@RequestBody ProductWithoutID product){
        if (product == null) {
            return new StatusCode(StatusCodeType.REQUEST_ERROR);
        } else {
            return new StatusCode(productDAO.addProduct(product));
        }
    }




}
