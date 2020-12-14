package ru.ValolEr.serverApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ValolEr.serverApi.dao.ProductDAO;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductDAO productDAO;

    @Autowired
    public ProductsController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping()
    public String index(Model model){
        //Get all products from DAO and send it to view
        model.addAttribute("products", productDAO.index());
        return "products/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        // get one product by id from DAO and show it in view
        model.addAttribute("product", productDAO.show(id));
        return "products/show";
    }
}
