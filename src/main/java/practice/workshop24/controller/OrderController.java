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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import practice.workshop24.exception.OrderException;
import practice.workshop24.model.Product;
import practice.workshop24.model.PurchaseOrder;
import practice.workshop24.model.PurchaseOrderDetails;
import practice.workshop24.service.GeneralService;


@Controller
@RequestMapping
public class OrderController {
    

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

    @PostMapping(path="/checkout")
    public String checkout(Model model, HttpSession session, @ModelAttribute Product product, HttpServletRequest request) throws OrderException{
        
        List<Product> items = (List<Product>) session.getAttribute("items");

        //set the fields of PurchaseOrder Object with form input from user
        PurchaseOrder po = PurchaseOrder.createFromRequest(request);
        
        //set the fields product,unitPrice,discount,quantity of 
        //PurchaseOrderDetails Object with values from Product Object
        List<PurchaseOrderDetails> pods = svc.setFieldsOfPods(items);

        //insert PurchaseOrder and PurchaseOrderDetails record into database
        int primaryKey = svc.insertOrder(po, pods);

        //to display total no. of items
        model.addAttribute("total", items.size());

        //to display fields in PurchaseOrder
        model.addAttribute("orderId", primaryKey);

        //to display items checked out
        model.addAttribute("items", items);
        
        return "checkout";
    }


}
