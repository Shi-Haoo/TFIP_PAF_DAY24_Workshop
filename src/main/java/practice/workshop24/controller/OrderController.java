package practice.workshop24.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import practice.workshop24.model.Product;
import practice.workshop24.model.PurchaseOrderDetails;
import practice.workshop24.service.GeneralService;
import practice.workshop24.service.POUtility;

@Controller
@RequestMapping
public class OrderController {
    @Autowired
    POUtility poUtil;

    @Autowired
    GeneralService svc;


    @GetMapping(path="/")
    public String getIndexPg(Model model, HttpSession session){
        
        session.invalidate();

        //retrieve all products from database to display it in options
        List<Product> products = svc.getAllProducts();

        model.addAttribute("product", new Product());
        model.addAttribute("products", products);

        return "index";
    }

    @PostMapping(path="/order")
    public String addToCart(Model model, HttpSession session, @ModelAttribute Product product){

        List<Product> items = (List<Product>) session.getAttribute("items");
        
        if(items == null){
            items = new ArrayList<Product>();
            session.setAttribute("items", items);
        }

        //product object has fields name and quantity alr binded to form. Now we just need set values for 
        //fields standardPrice and discount in product object.
        Product pdt = svc.setPriceAndDiscount(product,svc.getAllProducts());
        
        //Add latest product into existing list of products
        items = svc.addItemToList(pdt, items);

        //to bind user input to Product
        model.addAttribute("product", new Product());
        //to display all fruits options
        model.addAttribute("products", svc.getAllProducts());
        //to display items in cart
        model.addAttribute("items", items);


        return "cart";

    }


}
