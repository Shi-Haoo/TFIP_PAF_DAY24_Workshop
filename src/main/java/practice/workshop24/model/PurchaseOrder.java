package practice.workshop24.model;

import java.time.LocalDate;

import jakarta.servlet.http.HttpServletRequest;

public class PurchaseOrder {
    
    private int orderId;
    private LocalDate orderDate;
    private String customerName;
    private String shipAddress;
    private String notes;
    private double tax;
    
    public PurchaseOrder() {
    }

    public PurchaseOrder(int orderId, LocalDate orderDate, String customerName, String shipAddress, String notes,
            double tax) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.shipAddress = shipAddress;
        this.notes = notes;
        this.tax = tax;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "PurchaseOrder [orderId=" + orderId + ", orderDate=" + orderDate + ", customerName=" + customerName
                + ", shipAddress=" + shipAddress + ", notes=" + notes + ", tax=" + tax + "]";
    }

    //set user input from form into fields in PurchaseOrder class
    //HttpServletRequest help to retrieve user input from form
    //Another way is to use @RequestBody
    
    public static PurchaseOrder createFromRequest(HttpServletRequest request){
        PurchaseOrder po = new PurchaseOrder();

        po.setCustomerName(request.getParameter("name"));
        po.setShipAddress(request.getParameter("ship_address"));
        po.setNotes(request.getParameter("notes"));

        return po;
    }
    

    
    



}
