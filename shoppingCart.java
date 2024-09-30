//importing necessary packages
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//defining a class named shoppingCart
public class shoppingCart{
    private List<Product> products; //list of products
    private Map<String, Double> discounts; //map of string

    //constructor
    public shoppingCart() {
        this.products = new ArrayList<>();
        this.discounts = new HashMap<>();
    }

    //method to add a product with specified quantity
    public void addProduct(Product product, int quantity) {
        //using for loop to add the specified quantity
        for (int i = 0; i < quantity; i++) {
            products.add(product);
        }
        //printing a message that the product has been added to the cart
        System.out.println(product.getProductName() + " added to the cart.");
    }

    //method to remove a specific product from the shopping cart
    public void removeProduct(Product product) {
        if (products.remove(product)) {
            System.out.println(product.getProductName() + " removed from the cart.");
        } else {
            System.out.println("Product not found in the cart");
        }
    }


    //method to calculate the total cost of all the products in the cart
    public double calculateTotalCost() {
        //variable to store total cost
        double totalCost = 0.0;
        for (Product product : products) {
            //adding the pric of each product to the total cost
            totalCost += product.getPriceOfTheProduct();
        }
        //returning the calculated total cost
        return totalCost;
    }
}

