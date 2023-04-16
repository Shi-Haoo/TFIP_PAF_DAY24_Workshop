package practice.workshop24.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import practice.workshop24.model.PurchaseOrder;
import static practice.workshop24.repository.DBQueries.*;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class PORepository {
    
    @Autowired
    JdbcTemplate template;

    KeyHolder keyholder = new GeneratedKeyHolder();
    public Integer insertPurchaseOrder(PurchaseOrder po){
                   
        //insert new PurchaseOrder record into Order table in mysql
        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(INSERT_PURCHASE_ORDER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, po.getCustomerName());
            ps.setString(2, po.getShipAddress());
            ps.setString(3, po.getNotes());
            return ps;

        }, keyholder);

        BigInteger primaryKey = (BigInteger) keyholder.getKey();
        return primaryKey.intValue();
    }
}
