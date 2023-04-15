package practice.workshop24.model;

public class PurchaseOrderDetails {
    
    private int id;
    private String product;
    private double unitPrice;
    private double discount;
    private int quantity;
    
    public PurchaseOrderDetails() {
    }

    public PurchaseOrderDetails(int id, String product, double unitPrice, double discount, int quantity) {
        this.id = id;
        this.product = product;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PurchaseOrderDetails [id=" + id + ", product=" + product + ", unitPrice=" + unitPrice + ", discount="
                + discount + ", quantity=" + quantity + "]";
    }

    
    
}
