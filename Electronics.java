import java.io.Serializable; //importing Serializable interface from java.io to provide flexibility

//defining a class named Electronics that inherits from product class and implements serializable
public class Electronics extends Product implements Serializable {
    private String brand; //variables
    private int warrantyPeriod;

    //constructor for the class
    public Electronics(String productId, String productName, int noOfProductsAvailable, double priceOfTheProduct, String brand, int warrantyPeriod){
        super(productId, productName, noOfProductsAvailable, priceOfTheProduct); //calling the constructor from the super class Product
        this.brand = brand; //initializing its own specific fields
        this.warrantyPeriod = warrantyPeriod;
    }

    //setter method for brand variable
    public void setBrand(String brand) {
        this.brand = brand;
    }

    //getter method for brand variable
    public String getBrand() {
        return brand;
    }

    //setter method for warrantyPeriod variable
    public void setWarrantyPeriod(int warrantyPeriod){
        this.warrantyPeriod = warrantyPeriod;
    }

    //getter method for warrantyPeriod variable
    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    //overriding the toString method which gives the output string of this class
    public String toString() {
        return("Product information: " +
                "\nProduct ID: " + getProductId() +
                "\nProduct name: " + getProductName() +
                "\nPrice of the product: " + getPriceOfTheProduct() +
                "\nBrand of the product: " + getBrand() +
                "\nWarranty period: " + getWarrantyPeriod());
    }
}
