package com.stock.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductsRepository productsRepsitory;

    @Autowired
    private SupplierRepsitory supplierRepsitory;

    @Autowired
    private ImportRepository importRepository;

    @GetMapping("")
    public String getProducts(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, Model model) {
        model.addAttribute("activeProducts","active");
        page = page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(page, pageSize);
        var products = productsRepsitory.findAll(pageable);
        var pageTotal = products.getTotalPages() > 1 ? products.getTotalPages() : 1;
        model.addAttribute("products", products);
        model.addAttribute("pageTotal", pageTotal);
        model.addAttribute("page", page + 1);
        return "products/index";
    }

    @GetMapping("/add")
    public String addView(Model model) {
        model.addAttribute("activeProducts","active");
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("books", importRepository.findAll());
        return "products/add";
    }

    @PostMapping("/add")
    public String addProducts(@ModelAttribute Products prd_form, Model model) {
        productsRepsitory.save(prd_form);
        return "redirect:/products";
    }

    @GetMapping("/delete")
    public String deleteProducts(@RequestParam int id) {
        productsRepsitory.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/category")
    public String getCategory(Model model) {
        model.addAttribute("activeProducts","active");
        model.addAttribute("categories", categoryRepository.findAll());
        return "products/category";
    }

    @PostMapping("/category")
    public String postCategory(@ModelAttribute Categories cate_form, Model model) {
        categoryRepository.save(cate_form);
        return "redirect:/products/category";
    }

    @PostMapping("/category/update")
    @ResponseBody
    public String updateCategory(@RequestParam int id, String name) {
        Categories category_form = categoryRepository.findById(id).get();
        category_form.setCategory_name(name);
        categoryRepository.save(category_form);
        return "success";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Products getProductById(@PathVariable int id) {
        return productsRepsitory.getProductbyBookId(id);
    }

    @GetMapping("/category/delete")
    public String deleteCategory(@RequestParam int id) {
        categoryRepository.deleteById(id);
        return "redirect:/products/category";
    }
}
