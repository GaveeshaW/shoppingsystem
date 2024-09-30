import java.io.Serializable; //importing Serializable interface from java.io to provide flexibility

//defining a class named clothing that inherits from product class and implements serializable
public class Clothing extends Product implements Serializable{
    private String size; //variables
    private String color;

    //constructor for the class
    public Clothing(String productId, String productName, int noOfProductsAvailable, double priceOfTheProduct, String size, String color) {
        super(productId, productName, noOfProductsAvailable, priceOfTheProduct); //calling constructor of the super class, Product
        this.size = size; //initializing its own field
        this.color = color;
    }

    //setter method for size variable
    public void setSize(String size) {
        this.size = size;
    }

    //getter method for size variable"
    public String getSize() {
        return size;
    }


    //setter method for color variable
    public void setColor(String color) {
        this.color = color;
    }

    //getter method for color variable
    public String getColor() {
        return color;
    }

    //overriding the toString method which gives the output string of this class
    public String toString() {
        return("Product information: " +
                "\nProduct ID: " + getProductId() +
                "\nProduct name: " + getProductName() +
                "\nPrice of the product: " + getPriceOfTheProduct() +
                "\nSize of the cloth: " + getSize() +
                "\nColor of the cloth: " + getColor());
    }
}
