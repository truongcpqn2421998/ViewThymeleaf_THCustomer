package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import com.codegym.service.ICustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController {
    private final ICustomerService customerService = new CustomerService();

    @RequestMapping("")
    public String index(Model model){
        List<Customer> customerList=customerService.findAll();
        model.addAttribute("customers",customerList);
        return "/index";
    }

    @GetMapping("create")
    public String create(Model model){
        model.addAttribute("customer",new Customer());
        return "/create";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Customer customer,RedirectAttributes redirectAttributes){
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("success","create success");
        return "redirect:/customer";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable int id,Model model){
        Customer customer=customerService.findById(id);
        model.addAttribute("customer",customer);
        return "/update";
    }

    @PostMapping("update")
    public String saveEdit(@ModelAttribute Customer customer,Model model,RedirectAttributes redirectAttributes){
        customerService.update(customer.getId(),customer);
        redirectAttributes.addFlashAttribute("success","edit success");
        return "redirect:/customer";
    }

    @GetMapping("{id}/delete")
    public String delete(@PathVariable int id,RedirectAttributes redirectAttributes){
        customerService.remove(id);
        redirectAttributes.addFlashAttribute("success","delete success");
        return "redirect:/customer";
    }

    @GetMapping("{id}/view")
    public String view(@PathVariable int id,Model model){
        Customer customer=customerService.findById(id);
        model.addAttribute("customer",customer);
        return "/view";
    }


}
