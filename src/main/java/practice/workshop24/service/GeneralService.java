package practice.workshop24.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import practice.workshop24.exception.OrderException;
import practice.workshop24.model.Product;
import practice.workshop24.model.PurchaseOrder;
import practice.workshop24.model.PurchaseOrderDetails;
import practice.workshop24.repository.PORepository;
import practice.workshop24.repository.PurchaseOrderDetailsRepository;

@Service
public class GeneralService {
    
    @Autowired
    PurchaseOrderDetailsRepository repo;

    @Autowired
    PORepository poRepo;

    //Assume all tax is the same
    private static final Double tax = 0.05;
    
    public List<Product> getAllProducts(){
        
        return repo.getAllProducts();
    }

    public Product setPriceAndDiscount(Product product, List<Product> productDB){
        
        for(Product p : productDB){
            if(product.getName().equalsIgnoreCase(p.getName())){
                product.setStandardPrice(p.getStandardPrice());
                product.setDiscount(p.getDiscount());
            }
            else{
                continue;
            }
        }

        return product;

    }

    public List<Product> addItemToList(Product newProduct, List<Product> existingProducts){
        for(Product p : existingProducts){
            
            //If product alr added before, increase quantity
            if(newProduct.getName().equalsIgnoreCase(p.getName())){
                p.setQuantity(p.getQuantity()+newProduct.getQuantity());

                return existingProducts;
            }

            else{
                continue;
            }
            
        }

        //If product has not been added into cart before, add newProduct into list
        existingProducts.add(newProduct);

        return existingProducts;

    }

    public Double calculateUnitPrice(Product p){
        double unitPrice = p.getStandardPrice()*(1-p.getDiscount())*(1+tax);
        return unitPrice;
    }

    public List<PurchaseOrderDetails> setFieldsOfPods(List<Product> products){

        PurchaseOrderDetails pods = new PurchaseOrderDetails();
        List<PurchaseOrderDetails> podsList = new ArrayList<>();

        for(Product p : products){
            pods.setUnitPrice(calculateUnitPrice(p));
            pods.setProduct(p.getName());
            pods.setDiscount(p.getDiscount());
            pods.setQuantity(p.getQuantity());

            podsList.add(pods);

        }
        return podsList;

    }

    
    @Transactional(rollbackFor = OrderException.class)
    public Integer insertOrder(PurchaseOrder po, List<PurchaseOrderDetails> pods) throws OrderException{
        
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

        return primaryKey;

    }


}
