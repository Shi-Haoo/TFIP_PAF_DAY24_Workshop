package practice.workshop24.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import practice.workshop24.model.Product;
import practice.workshop24.service.GeneralService;

@Controller
@RequestMapping
public class OrderController {
    // @Autowired
    // POUtility poUtil;

    @Autowired
    GeneralService svc;


    @GetMapping(path="/")
    public String getIndexPg(Model model, HttpSession session){
        
        session.invalidate();

        List<Product> products = svc.getAllProducts();

        model.addAttribute("product", new Product());
        model.addAttribute("products", products);

        return "index";
    }




}
