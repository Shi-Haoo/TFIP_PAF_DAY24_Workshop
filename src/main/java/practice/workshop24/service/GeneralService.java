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
}
