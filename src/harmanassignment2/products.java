package harmanassignment2;

/**
 * model class for products table
 * @author Harmandeep
 */
public class products {
    private String make, productName, productType;
     private int price;

    public products(String make, String productName, String productType, int price) {
        this.make = make;
        this.productName = productName;
        this.productType = productType;
        this.price = price;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

   
    
}
