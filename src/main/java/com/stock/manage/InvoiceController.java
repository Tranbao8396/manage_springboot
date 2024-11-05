package com.stock.manage;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private InvoicesRepository invoiceRepository;
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    @GetMapping("")
    public String getInvoice(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize, Model model) {
        model.addAttribute("activeInvoices", "active");
        page = page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(page, pageSize);
        var invoices = invoiceRepository.findAll(pageable);
        var pageTotal = invoices.getTotalPages() > 1 ? invoices.getTotalPages() : 1;
        model.addAttribute("invoices", invoices);
        model.addAttribute("invoiceRepo", invoiceDetailRepository);
        model.addAttribute("pageTotal", pageTotal);
        model.addAttribute("page", page + 1);
        return "invoices/index";
    }

    @GetMapping("/{id}")
    public String getInvoiceDetail(@PathVariable int id, Model model) {
        model.addAttribute("activeInvoices", "active");
        model.addAttribute("invoice", invoiceDetailRepository.getInvoiceDetailGroupByInvoiceId(id));
        model.addAttribute("invoiceRepo", invoiceDetailRepository);
        return "invoices/detail";
    }

    @GetMapping("/add")
    public String addInvoicesView(Model model) {
        model.addAttribute("activeInvoices", "active");
        model.addAttribute("products", productsRepository.findAll());
        model.addAttribute("vouchers", voucherRepository.findAll());
        return "invoices/add";
    }

    @PostMapping("/add")
    public String postInvoice(
            @RequestParam("item[]") List<Integer> items,
            @RequestParam("quantity[]") List<Integer> quantities,
            @RequestParam("price[]") List<Float> prices,
            Model model) {

        Invoices invoice = new Invoices();
        invoice.setUser_id(1);
        invoice.setCreated_date(new Date());
        invoiceRepository.save(invoice);
        // invoiceRepository.flush();

        for (int i = 0; i < items.size(); i++) {
            InvoiceDetail invoiceDetail = new InvoiceDetail();
            invoiceDetail.setInvoice_id(invoice.getId());
            invoiceDetail.setItem(items.get(i));
            invoiceDetail.setQuantity(quantities.get(i));
            invoiceDetail.setPrice(prices.get(i));

            Products product = productsRepository.findById(items.get(i)).get();
            // product.setQuantity(product.getQuantity() - quantities.get(i));
            productsRepository.save(product);

            invoiceDetailRepository.save(invoiceDetail);
        }
        return "redirect:/invoices";
    }
}
