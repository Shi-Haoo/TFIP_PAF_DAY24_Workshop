package practice.workshop24.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import practice.workshop24.model.Product;
import practice.workshop24.repository.OrderRepository;

@Service
public class GeneralService {
    
    @Autowired
    OrderRepository repo;
    
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


}
