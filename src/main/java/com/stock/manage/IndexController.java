package com.stock.manage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class IndexController {
    @Autowired
    private SupplierRepsitory supplierRepsitory;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ImportRepository importRepository;

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("activeHome","active");
        model.addAttribute("suppliers_count", supplierRepsitory.count());
        model.addAttribute("income", String.format("%.0f", importRepository.getPriceImportAll() - invoiceDetailRepository.getTotalPriceAll()));

        model.addAttribute("stock", importRepository.getQuantityImportAll());
        model.addAttribute("outcome", String.format("%.0f", importRepository.getPriceImportAll()));
        return "home";
    }
}
