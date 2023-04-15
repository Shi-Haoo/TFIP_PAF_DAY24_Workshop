package practice.workshop24.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import practice.workshop24.model.Product;
import static practice.workshop24.repository.DBQueries.*;

@Repository
public class OrderRepository {
    
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



}
