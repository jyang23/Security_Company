package com.jy.template;

import com.jy.template.Beans.Company;
import com.jy.template.Beans.User;
import com.jy.template.Configurations.UserService;
import com.jy.template.Repository.CompanyRepository;
import com.jy.template.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;

    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping("/")
    public String index(Model model)
    {
        model.addAttribute("companies", companyRepository.findAll());

        if(userService.getUser() != null)
        {
            model.addAttribute("user_id", userService.getUser().getId());
        }
        return "index";
    }
    //------------------------------------------------------------------------------------------------------------------
    @GetMapping("/add")
    public String employeeForm(Model model)
    {
        model.addAttribute("company",new Company());
        return "employeeform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Company company, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "employeeform";
        }
        company.setUser(userService.getUser());
        companyRepository.save(company);
        return "redirect:/";
    }
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping("details/{id}")
    public String showEmployee(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("company", companyRepository.findById(id).get());
        return "details";
    }

    @RequestMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("company", companyRepository.findById(id).get());
        return "employeeform";
    }

    @RequestMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") long id)
    {
        companyRepository.deleteById(id);
        return "redirect:/";
    }
    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------

}
