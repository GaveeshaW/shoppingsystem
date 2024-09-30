//importing necessary packages
import java.util.List;
import java.util.Scanner;

//declaring an interface named shoppingManager
public interface shoppingManager {
    void displayMenu(); //declaring method to display the menu
    void addNewProduct(Scanner scanner); //declaring method to add a new product
    void deleteProduct(Scanner scanner); //declaring method to delete a product
    void printProducts(); //declaring method to print products
    void saveProductsToFile(); //declaring method to save products to a text file
    void addProduct(Product product); //to add a product to the shoppingManager
    void gui(); //for GUI functionality
    void createComponents(); //to create GUI components
    void initializeFrame(); //to initialize a GUI frame
    List<Product> getProductsFromFile(); //declaring a method to get products from a file and return a list of products
    void setProductList(List<Product> products); //declaring method to set the list of products
}