package practice.workshop24.model;

//set values from fruit_products table into fields of this class

public class Product {
    
    private int id;
    private String name;
    private double standardPrice;
    private double discount;
    
    public Product() {
    }

    public Product(int id, String name, double standardPrice, double discount) {
        this.id = id;
        this.name = name;
        this.standardPrice = standardPrice;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(double standardPrice) {
        this.standardPrice = standardPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", standardPrice=" + standardPrice + ", discount=" + discount
                + "]";
    }

    
    
    

}
