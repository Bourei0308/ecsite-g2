package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.Dao;
import com.example.demo.user.SiteUser;

@Controller
public class ManagerController {

    private final Dao dao;

    public ManagerController(Dao dao) {
        this.dao = dao;
    }

    @GetMapping("/manager/customerlist")
    public String showCustomerList(Model model) {
        List<SiteUser> userList = dao.getAllUsers();
        model.addAttribute("userList", userList);
        return "manager_customer_list";
    }
    @GetMapping("/manager/customer/{id}")
    public String showCustomerDetail(@PathVariable int id, Model model) {
        SiteUser user = dao.getUserById(id);
        model.addAttribute("user", user);
        return "manager_customer_detail";
    }

    @GetMapping("/manager/customer/delete/{id}")
    public String deleteCustomer(@PathVariable int id) {
        dao.deleteUserById(id);
        return "manager_customer_complete";
    }
    @GetMapping("/manager/customer/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        SiteUser user = dao.getUserById(id);
        model.addAttribute("user", user);
        return "manager_customer_edit";
    }

    @PostMapping("/manager/customer/edit/{id}")
    public String updateCustomer(@PathVariable int id,
                                 @RequestParam String password,
                                 @RequestParam String nickName,
                                 @RequestParam(required = false) Boolean adminFlag,
                                 @RequestParam(required = false) Boolean deleteFlag,
                                 @RequestParam String email,
                                 @RequestParam String phone_number) {

        dao.updateUser(id, "password", password);
        dao.updateUser(id, "nickName", nickName);
        dao.updateUser(id, "adminFlag", (adminFlag != null && adminFlag) ? "1" : "0");
        dao.updateUser(id, "deleteFlag", (deleteFlag != null && deleteFlag) ? "1" : "0");
        dao.updateUser(id, "email", email);
        dao.updateUser(id, "phone_number", phone_number);

        return "manager_customer_complete";
    }


}
