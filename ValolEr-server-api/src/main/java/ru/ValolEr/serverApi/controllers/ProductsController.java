package ru.ValolEr.serverApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ValolEr.serverApi.dao.ProductDAO;
import ru.ValolEr.serverApi.models.Product;

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
        //get all products from DAO and send it to view
        model.addAttribute("products", productDAO.index());
        return "products/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        // get one product by id from DAO and show it in view
        model.addAttribute("product", productDAO.show(id));
        return "products/show";
    }

    @GetMapping("/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "products/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("product", productDAO.show(id));
        return "products/edit";
    }


    @PostMapping()
    public String create(@RequestParam(value = "name", required = false) String name,
                         @RequestParam(value = "article", required = false) String article,
                         @RequestParam(value = "type", required = false) String type,
                         @RequestParam(value = "supplier", required = false) String supplier,
                         @RequestParam(value = "warehouse", required = false) String warehouse,
                         Model model){

        Product product = new Product();

        if(article.equals("")){
            product.setArticle("error_article");
        } else{
            product.setArticle(article);
        }

        if(name.equals("")){
            product.setName("error_name");
        } else{
            product.setName(name);
        }

        if(type.equals("")){
            product.setType("error_type");
        } else{
            product.setType(type);
        }

        if(supplier.equals("")){
            product.setSupplier("error_supplier");
        } else {
            product.setSupplier(supplier);
        }

        if(warehouse.equals("")){
            product.setWarehouse("error_warehouse");
        } else{
            product.setWarehouse(warehouse);
        }

        productDAO.save(product);

        model.addAttribute("product", product);

        return "redirect:/products";
    }

//    @PostMapping()
//    public String create(@ModelAttribute("product") Product product){
//            productDAO.save(product);
//        //add product to DataBase
//        return "redirect:/products";
//    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("product") Product product,
                         @PathVariable("id") int id){
        productDAO.update(id, product);
        return "redirect:/products";

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        productDAO.delete(id);
        return "redirect:/products";
    }
}
