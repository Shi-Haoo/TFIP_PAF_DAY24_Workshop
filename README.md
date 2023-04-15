In Controller:

Q1: 
@GetMapping(path="/")
    public String getIndexPg(Model model, HttpSession session){
        
        session.invalidate();

        List<Product> products = svc.getAllProducts();

        model.addAttribute("product", new Product());
        model.addAttribute("products", products);

        return "index";
    }

    @PostMapping(path="/order")
    public String addToCart(Model model, Httpsession session, @ModelAttribute Product product){

...
}

Does the name of class Product in PostMapping have to be the same as key name "product" in model.addAttribute in GetMapping?

Ans 1:
No, the name of the class Product in the PostMapping does not have to be the same as the key name "product" in the model.addAttribute in GetMapping. The model.addAttribute method is used to add an attribute to the model that is passed to the view, and the key name is used to retrieve the value of that attribute in the view.

In the PostMapping method, the @ModelAttribute annotation is used to bind the form data to the Product object, regardless of the key name used in the model.addAttribute method.