package com.stock.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/vouchers")
public class VoucherController {
    @Autowired
    private VoucherRepository voucherRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("activeVouchers", "active");
        model.addAttribute("vouchers", voucherRepository.findAll());
        return "vouchers/index";
    }

    @PostMapping("")
    public String addVouchers(@ModelAttribute Voucher cate_form, Model model) {
        voucherRepository.save(cate_form);
        return "redirect:/vouchers";
    }

    @PostMapping("/delete")
    public String deleteVouchers(@RequestParam int id) {
        voucherRepository.deleteById(id);
        return "redirect:/vouchers";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Voucher getAjaxvoucher(@PathVariable int id) {
        return voucherRepository.findById(id).get();
    }
}
