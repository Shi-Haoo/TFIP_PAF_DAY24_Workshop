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

----------------------------------------------------------------------------------------------

In GeneralService:

Q1:
@Transactional(rollbackFor = OrderException.class)
    public void insertOrder(PurchaseOrder po, List<PurchaseOrderDetails> pods) throws OrderException{
        
        //insert PurchaseOrder record
        int primaryKey = poRepo.insertPurchaseOrder(po);

        //if number of items in order > 5, throw OrderException error.
        //All records in insertOrder method will not be updated
        if(pods.size()>5){
            throw new OrderException("cannot buy more than 5 items in 1 order");
        }

        //insert PurchaseOrderDetails record
        //primaryKey is for order_id field of PurchaseOrderDetails class object
        repo.insertPurchaseOrderDetails(pods, primaryKey);

    }

What is @Transactional and rollbackFor = OrderException.class?

Ans:
The @Transactional annotation is used to define the scope of a single database transaction in Spring. The rollbackFor attribute specifies which exception types should cause a transaction rollback.

In this case, rollbackFor = OrderException.class means that if an exception of type OrderException is thrown during the execution of the method 
(i.e public void insertOrder(..)), the transaction should be rolled back. This means that any changes made to the database during the execution of the method will be undone, and the database will be left in the same state it was in before the method was called.

By default, Spring will only roll back a transaction for unchecked exceptions (i.e., exceptions that are not explicitly declared in the method signature). By specifying OrderException.class as a checked exception, the method will trigger a rollback if this exception is thrown, even though it is a checked exception.


----------------------------------------------------------------------------------------------

Q2.
So in this example, if there r other exceptions other than OrderException for this case, changes will still be committed?

Ans:
If another exception is thrown that is not an OrderException, the default behavior of the transaction manager will be to commit the transaction (assuming there are no other rollbackFor attributes specified for other exceptions).

----------------------------------------------------------------------------------------------

Q3.
If we do not specify rollbackfor, then any exception will cause changes to rollback?

Ans:
No, by default, only unchecked exceptions (i.e., exceptions that are not subclasses of java.lang.Exception) cause the transaction to be rolled back. Checked exceptions (i.e., exceptions that are subclasses of java.lang.Exception but not subclasses of java.lang.RuntimeException or java.lang.Error) do not cause the transaction to be rolled back unless they are explicitly listed in a rollbackFor attribute or in the rollbackForClassName attribute of the @Transactional annotation.

In other words, if you do not specify rollbackFor and an unexpected checked exception occurs, the transaction will not be rolled back, and the changes made by the transaction will be committed to the database.