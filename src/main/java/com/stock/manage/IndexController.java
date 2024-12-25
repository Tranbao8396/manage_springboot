package com.stock.manage;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class IndexController {
    @Autowired
    private SupplierRepsitory supplierRepsitory;

    @Autowired
    private ImportRepository importRepository;

    @Autowired
    private InvoicesRepository invoiceRepository;

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("activeHome","active");
        model.addAttribute("suppliers_count", supplierRepsitory.count());

        var PriceImport = importRepository.getPriceImportAll() != null ? importRepository.getPriceImportAll() : 0;
        var Totalprice = invoiceDetailRepository.getTotalPriceAll() != null ? invoiceDetailRepository.getTotalPriceAll() : 0;
        var QuantityImport = importRepository.getQuantityImportAll() != null ? importRepository.getQuantityImportAll() : 0;
        model.addAttribute("income", String.format("%.0f", Totalprice - PriceImport));
        model.addAttribute("stock", QuantityImport);
        model.addAttribute("outcome", String.format("%.0f", PriceImport));
        return "home";
    }

    @GetMapping("/api/getInvoices")
    @ResponseBody
    public List<Invoices> getApiInvoices() {
        return invoiceRepository.findAll();
    }

    @GetMapping("/api/getInvoicesDetail")
    @ResponseBody
    public List<InvoiceDetail> getApiInvoicesDetail() {
        return invoiceDetailRepository.findAll();
    }
}
