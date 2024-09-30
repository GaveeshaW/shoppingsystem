import java.io.Serializable; //importing Serializable interface from java.io to provide flexibility

public abstract class Product implements Serializable{ //abstract class product implements serializable
    private String productId; //declaring variables
    private String productName;
    private int noOfProductsAvailable;
    private double priceOfTheProduct;

    //constructor
    public Product(String productId, String productName, int noOfProductsAvailable, double priceOfTheProduct){
        this.productId = productId;
        this.productName = productName;
        this.noOfProductsAvailable = noOfProductsAvailable;
        this.priceOfTheProduct = priceOfTheProduct;
    }

    //setter method for productId
    public void setProductId(String productId) {
        this.productId = productId;
    }

    //getter method for productId
    public String getProductId() {
        return productId;
    }

    //setter method for productName
    public void setProductName(String productName) {
        this.productName = productName;
    }

    //getter method for productName
    public String getProductName() {
        return productName;
    }

    //setter method for noOfProductsAvailable
    public void setNoOfProductsAvailable(int noOfProductsAvailable) {
        this.noOfProductsAvailable = noOfProductsAvailable;
    }

    //getter method for noOfProductsAvailable
    public int getNoOfProductsAvailable() {
        return noOfProductsAvailable;
    }

    //setter method for priceOfTheProduct
    public void setPriceOfTheProduct(double priceOfTheProduct) {
        this.priceOfTheProduct = priceOfTheProduct;
    }

    //getter method for priceOfTheProduct
    public double getPriceOfTheProduct() {
        return priceOfTheProduct;
    }

    //overriding the toString to get a string output
    public String toString(){
        return("Product information: " +
                "\nProduct name: " + getProductName() +
                "\nProduct ID: " + getProductId() +
                "\nNumber of products available: " + getNoOfProductsAvailable() +
                "\nPrice of the product: " + getPriceOfTheProduct());
    }
}