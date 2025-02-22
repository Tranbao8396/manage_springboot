package com.stock.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/imports")
public class ImportController {
    @Autowired
    private ImportRepository importRepository;

    @Autowired
    private SupplierRepsitory supplierRepsitory;

    @GetMapping("")
    public String getImports(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, Model model) {
        model.addAttribute("activeImports", "active");
        page = page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(page, pageSize);
        var imports = importRepository.findAll(pageable);
        var pageTotal = imports.getTotalPages() > 1 ? imports.getTotalPages() : 1;
        model.addAttribute("imports", imports);
        model.addAttribute("pageTotal", pageTotal);
        model.addAttribute("page", page + 1);
        return "imports/index";
    }

    @GetMapping("/add")
    public String getAddProducts(Model model) {
        model.addAttribute("activeImports", "active");
        model.addAttribute("suppliers", supplierRepsitory.findAll());
        return "imports/add";
    }

    @PostMapping("/add")
    public String postProducts(@ModelAttribute Import prd_form, Model model) {
        var quantity = prd_form.getQuantity();

        if (quantity > 0) {
            prd_form.setStatus(true);
        } else {
            prd_form.setStatus(false);
        }

        importRepository.save(prd_form);

        return "redirect:/imports";
    }

    @GetMapping("/delete")
    public String deleteProducts(@RequestParam int id) {
        importRepository.deleteById(id);
        return "redirect:/imports";
    }
}
