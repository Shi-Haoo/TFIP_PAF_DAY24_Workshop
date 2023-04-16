package practice.workshop24.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import practice.workshop24.model.Product;
import practice.workshop24.model.PurchaseOrderDetails;

import static practice.workshop24.repository.DBQueries.*;

@Repository
public class PurchaseOrderDetailsRepository {
    
    @Autowired
    JdbcTemplate template;

    public List<Product> getAllProducts(){
        
        List<Product> products = new ArrayList<>();

        SqlRowSet rs = template.queryForRowSet(GET_ALL_PRODUCTS);

        while(rs.next()){
            products.add(Product.createFromSQLResults(rs));
        }

        return products;

    }

    public void insertPurchaseOrderDetails(List<PurchaseOrderDetails> pods, int primaryKey){

        //set up the list of Object[] that will be used to replace 
        //query parameter '?' in the sql query statement

        List<Object[]> data = pods.stream()
                .map(pod -> {
                    Object[] obj = new Object[5];
                    obj[0] = pod.getProduct();
                    obj[1] = pod.getUnitPrice();
                    obj[2] = pod.getDiscount();
                    obj[3] = pod.getQuantity();
                    obj[4] = primaryKey;

                    return obj;
                }).toList();
        
        //insert multiple items record from a specific  
        //checkout order into Purchase_Order_Details table

        template.batchUpdate(INSERT_PURCHASE_ORDER_DETAILS, data);

    }



}
