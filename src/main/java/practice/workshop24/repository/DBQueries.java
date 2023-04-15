package practice.workshop24.repository;

public class DBQueries {
    
    public static final String GET_ALL_PRODUCTS = "select * from fruits_products;";

    //NOW() gives current date and time when record is inserted. DATE() help to 
    // extract only the date portion of current date and time
    //Alt method is use DATE(SYSDATE())

    public static final String INSERT_PURCHASE_ORDER = """
            insert into purchase_order (order_date, customer_name, ship_address, notes)
            values
            (DATE(NOW()), ?, ?, ?);
            """;

    public static final String INSERT_PURCHASE_ORDER_DETAILS = """
            insert into purchase_order_details (product, unit_price, discount, quantity, order_id)
            values
            (?, ?, ?, ?, ?)
            """;

}
