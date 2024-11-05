package com.stock.manage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/suppliers")
public class SuppliersController {
    @Autowired
    private SupplierRepsitory supplierRepsitory;

    @GetMapping("")
    public String suppliers(Model model) {
        model.addAttribute("suppliers", supplierRepsitory.findAll());
        model.addAttribute("activeSupplier","active");
        return "suppliers";
    }

    @PostMapping("")
    public String addSupplier(@ModelAttribute BookSupplier supplier_form, Model model) {
        supplierRepsitory.save(supplier_form);
        return "redirect:/suppliers";
    }

    @GetMapping("/delete")
    public String deleteSupplier(@RequestParam int id) {
        supplierRepsitory.deleteById(id);
        return "redirect:/suppliers";
    }

    @PostMapping("/update")
    @ResponseBody
    public String editSupplier(@RequestParam int id, String name, String number, boolean status) {
        BookSupplier supplier_form = supplierRepsitory.findById(id).get();
        supplier_form.setSupplier(name);
        supplier_form.setSupplier_number(number);
        supplier_form.setStatus(status);
        supplierRepsitory.save(supplier_form);
        return "success";
    }
}
