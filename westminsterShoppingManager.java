//importing necessary modules
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JTable;

public class westminsterShoppingManager implements shoppingManager{
    private JFrame frame; //first JFrame
    private static boolean isMenuDisplayed;
    private JPanel panel; //panel
    private JLabel lbl; //label
    private JComboBox<String> cb; //drag-and-drop
    private JTable productTable; //table which stores product details inside the GUI
    private JButton shoppingcart; //GUI shoppingcart
    private JButton addtocart; //GUI addtocart buttton
    private List<Product> productList; //productList which displays the products as a list
    private static final int MAX_PRODUCTS = 50; //maximum number of products is 50
    private static final Scanner scanner = new Scanner(System.in); //calling a Scanner
    private ShoppingCart shoppingCart; //shoppingCart
    private JPanel detailsPanel; //detailsPanel which is a JPanel

    //constructor
    public westminsterShoppingManager(){
        this.productList = new ArrayList<>();
        this.shoppingCart = new ShoppingCart();
    }

    //main method
    public static void main(String[] args) {
        westminsterShoppingManager shoppingManager = new westminsterShoppingManager();
        shoppingManager.start();
    }

    //to start the console
    public void start() {
        int choice = 0; //choice is defined

        while (choice != 1 && choice != 2) {
            System.out.println("\nAre you a manager or a user? Press 1 for manager and press 2 for user..... ");
            try {
                choice = scanner.nextInt();
                //error handling
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter 1 for admin or 2 for user.");
                scanner.next();
            } finally {
                scanner.nextLine();
            }
        }

        //choice 1 is for managers
        if (choice == 1) {
            displayMenu();
        } else {
            isMenuDisplayed = false;
            gui(); //display gui for users
        }
    }

    //declaring displayMenu method
    public void displayMenu(){
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println(); //how the menu looks like
            System.out.println("============ Shopping manager menu ============");
            System.out.println("1. Add a new product");
            System.out.println("2. Delete a product");
            System.out.println("3. Print list of products");
            System.out.println("4. Save products to file");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){ //switch cases to handle the input of the mananger
                case 1:
                    addNewProduct(scanner);
                    break;

                case 2:
                    deleteProduct(scanner);
                    break;

                case 3:
                    printProducts();
                    break;

                case 4:
                    saveProductsToFile();
                    break;

                case 5:
                    System.out.println("Thank you for using!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        } while(choice != 5);
        scanner.close();
    }

    //method to add a new product through the console by the manager
    public void addNewProduct(Scanner scanner) {
        System.out.println(); //to leave a space to prevent the cramped look
        if(productList.size() >= MAX_PRODUCTS){ //check if productList size is greater than or equal to 50
            System.out.println("Maximum limit reached."); //if so print this line
            return;
        }

        System.out.println();
        System.out.println("Choose product type to add: "); //this is the first option that the manager gets
        System.out.println("1. Electronics");
        System.out.println("2. Clothing");
        int typeChoice = scanner.nextInt();
        scanner.nextLine();

        String productId, productName;
        int noOfItemsAvailable;
        int price;

        System.out.println();
        System.out.print("Enter product ID: ");
        productId = scanner.nextLine();

        System.out.println();
        System.out.print("Enter product name: ");
        productName = scanner.nextLine();

        System.out.println();
        System.out.print("Enter available items: ");
        noOfItemsAvailable = scanner.nextInt();
        scanner.nextLine();

        System.out.println();
        System.out.print("Enter price: ");
        price = scanner.nextInt();
        scanner.nextLine();

        System.out.println();
        if(typeChoice == 1){
            System.out.println("Enter brand: ");
            String brand = scanner.nextLine();

            System.out.println();
            System.out.print("Enter warranty period: ");
            int warrantyPeriod = scanner.nextInt();
            scanner.nextLine();

            System.out.println();
            Electronics electronicProduct = new Electronics(productId, productName, noOfItemsAvailable, price, brand, warrantyPeriod); //how to print an electronic prduct to the console
            productList.add(electronicProduct);
            System.out.println("Electronic product added successfully."); //successful message
        } else if(typeChoice == 2){ //if manager entered the option to add a clothing item, then this will be displaued
            System.out.println();
            System.out.println("Enter size: ");
            String size = scanner.nextLine(); //for the size of the clothing

            System.out.println();
            System.out.println("Enter color: ");
            String color = scanner.nextLine(); //for the color of the clothing

            System.out.println();
            Clothing clothingProduct = new Clothing(productId, productName, noOfItemsAvailable, price, size, color); //the way how a clothing product eill br displayed
            productList.add(clothingProduct);
            System.out.println("Clothing product added successfully."); //successful message
        } else{
            System.out.println();
            System.out.println("Invalid choice."); //if the choice is any other than 1 or 2, the error will be handled in this way
        }
    }

    //method to delete a product
    public void deleteProduct(Scanner scanner) {
        System.out.println();
        System.out.println("Enter product ID to delete: ");
        String productIdToDelete = scanner.nextLine();

        //searching for a product with a specific id in the productList
        Product foundProduct = null;
        for (Product product : productList) {
            if (product.getProductId().equalsIgnoreCase(productIdToDelete)) {
                //if a product with the specified ID is found, store it in foundProduct and exit the loop
                foundProduct = product;
                break;
            }
        }

        //checking if a product with the specified ID was found
        if (foundProduct != null) {
            //checking the type of the found product using instanceof
            if (foundProduct instanceof Electronics) {
                System.out.println();
                System.out.println("Deleted electronic product: "); //to differentiate here
                System.out.println(foundProduct.toString());
            } else if (foundProduct instanceof Clothing) {
                System.out.println();
                System.out.println("Deleted clothing product: ");
                System.out.println(foundProduct.toString());
            }
            productList.remove(foundProduct); //deleting the prduct
            System.out.println();
            System.out.println("Total number of products left: " + productList.size()); //updating
        } else {
            System.out.println();
            System.out.println("Product with ID " + productIdToDelete + " not found."); //if it was not found, print the error message
        }
    }


    //method to print products from the text file
    public void printProducts() {
        System.out.println();

        boolean hasProducts = false; // Flag to check if any products are found

        try {
            System.out.println("List of products in the system: ");

            // Reading the products in the file
            File myObj = new File("products.txt");
            Scanner myReader = new Scanner(myObj);
            //looping through each line in the file
            while (myReader.hasNextLine()) {
                //reading a line from the file
                String data = myReader.nextLine();
                //printing the line to the cisole
                System.out.println(data);
                hasProducts = true; // Set flag to true if any product is found
            }
            myReader.close();

            // If no products found, print a message
            if (!hasProducts) {
                System.out.println("No products in the system.");
            }
        } catch (FileNotFoundException e) {
            //error handling
            System.out.println();
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    //method to save products to the text file
    public void saveProductsToFile() {
        System.out.println();
        //trying FileWriter to write the product information saved in the console
        try (FileWriter fileWriter = new FileWriter("products.txt", true)) {
            //sorting based on the product IDs using comparator
            Collections.sort(productList, Comparator.comparing(Product::getProductId));
            //looping through each product in the sorted productList
            for (Product product : productList) {
                //checking the type of the product to separate them
                if (product instanceof Electronics) {
                    fileWriter.write("Electronics: " + product.toString() + "\n");
                } else if (product instanceof Clothing) {
                    fileWriter.write("Clothing: " + product.toString() + "\n");
                }
            }
            //printing a message on success
            System.out.println();
            System.out.println("Products saved to file");
        } catch (IOException e) {
            //error handling if the saving wasn't successful
            System.out.println();
            System.out.println("Error saving products to file: " + e.getMessage());
        }
    }

    //method to add a product
    public void addProduct(Product product) {
        if (productList.size() < 50) { //checks if the productList size is less than 50
            productList.add(product); //if so add the product
        } else {
            System.out.println(); //else say cannot add more products
            System.out.println("Cannot add more products. Limit reached.");
        }
    }

    //method to call the GUI where the user interacts with the system with
    public void gui() {
        frame = new JFrame("Westminster Shopping Center"); //the first JFrame which will be displayed to the user
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit on clos
        frame.setSize(600, 600); //setting up the height and the width

        panel = new JPanel(); //new panel
        frame.add(panel);

        createComponents(); //calling createComponents here


        //the table which prints the products inside the file
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Product ID", "Name", "Category", "Price", "Info"}, 0);
        productTable = new JTable(model);

        //widths of the columns of the table
        int[] columnWidths = {60, 70, 140, 100, 380}; // Adjust the values based on your preferences

        //to set the preferred sizes
        for (int i = 0; i < columnWidths.length; i++) {
            productTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }


        initializeFrame(); //calls this here

        //loading products from file
        productList = getProductsFromFile();

        //checking if the productList is successfully loaded from the file
        if (productList != null) {
            setProductList(productList);
            getProductsFromFile();
        } else {
            //print error message if there's an issue in loading info from the file
            System.out.println("Error loading products from file.");
        }
        frame.setVisible(true); //making the frame visible

        //actionListener for the "Add to cart" button
        addtocart.addActionListener(e -> {
            int selectedRow = productTable.getSelectedRow();

            //checking if a row is selected in the product table
            if (selectedRow != -1) {
                //getting th product ID from the selected row
                String productId = (String) productTable.getValueAt(selectedRow, 0);
                //retrieving the selected product based on product Id
                Product selectedProduct = getProductById(productId);

                //checking if the selected product is not null
                if (selectedProduct != null) {
                    int maxQuantity = selectedProduct.getNoOfProductsAvailable();
                    //prompting the user for the quanityt of the selected product
                    int quantitySelected = promptForQuantity();

                    //checking if a positive quantity is selected
                    if (quantitySelected > 0) {
                        //adding the selected product to the shopping cartwith the specified quantity
                        shoppingCart.addProduct(selectedProduct, quantitySelected);
                        //updating and displaying the contens of the shopping cart
                        updateAndShowShoppingCart();
                    }
                }
            }
        });

    }

    //method to prompt the user to select a quantity using a JComboBox
    public int promptForQuantity() {
        //creating an array of integers from 1 to 10
        Integer[] quantityOptions = new Integer[10];
        for(int i = 0; i < 10; i++) {
            quantityOptions[i] = i + 1;
        }

        //creating the JComboBox(drop-down)
        JComboBox<Integer> quantityComboBox = new JComboBox<>(quantityOptions);
        //creating a JPanel with a grid layout to display the quantity and the label
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(new JLabel("Select Quantity"));
        panel.add(quantityComboBox);

        //displaying a JOptionPane with the quantity selection panel
        int result = JOptionPane.showConfirmDialog(null, panel, "Select quantity", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        //returning the selected quantity if the user clicks OK
        if (result == JOptionPane.OK_OPTION) {
            return (Integer) quantityComboBox.getSelectedItem();
        } else {
            //returninf 0 if the user cancels the selection
            return 0;
        }
    }

    //methd to update and show the shopping cart
    public void updateAndShowShoppingCart() {
        //creatinf a new JFrame for the shopping cart
        JFrame cartFrame = new JFrame("Shopping Cart");
        cartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cartFrame.setSize(400, 400);

        //creating a DefaultTableModel and JTable for the shopping cart items
        DefaultTableModel cartModel = new DefaultTableModel(new Object[]{"Product", "Quantity", "Price"}, 0);
        JTable cartTable = new JTable(cartModel);

        //creating a JScrollPane to contain the cartTable
        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        cartFrame.add(cartScrollPane);
        cartFrame.setVisible(true);

        //variables to track total prices and quantities
        double totalOriginalPrice = 0;
        double totalDiscountedAmount = 0;

        // Use a Map to keep track of the quantity for each category
        Map<String, Integer> categoryQuantityMap = new HashMap<>();

        //iterating through each cart item in thee shopping cart
        for (ProductCartItem cartItem : shoppingCart.getCartItems()) {
            //adding the cart item information to the cardModel
            Object[] rowData = new Object[]{cartItem.getProduct().getProductName(), cartItem.getQuantity(), cartItem.getTotalPrice()};
            cartModel.addRow(rowData);

            //updating the total original price
            totalOriginalPrice += cartItem.getTotalPrice();

            // Update the category quantity map
            String category = getCategory(cartItem.getProduct());
            categoryQuantityMap.put(category, categoryQuantityMap.getOrDefault(category, 0) + cartItem.getQuantity());
        }

        // Calculate discounts based on the category quantity
        for (int quantity : categoryQuantityMap.values()) {
            if (quantity >= 3) {
                //Applyting a 20% discount
                totalDiscountedAmount += 0.2 * totalOriginalPrice; // 20% discount
                break;  // Only apply discount for one category (the first one encountered with enough quantity)
            }
        }

        //calculating discounted price and the final total
        double discountedPrice = totalOriginalPrice - totalDiscountedAmount;
        double finalTotal = totalOriginalPrice - totalDiscountedAmount;

        // Display labels for each part of the price
        JLabel originalPriceLabel = new JLabel("Total Price (Without Discount): £" + totalOriginalPrice);
        JLabel discountedAmountLabel = new JLabel("Discounted Amount (20%): £" + totalDiscountedAmount);
        JLabel discountedPriceLabel = new JLabel("Discounted Price: £" + discountedPrice);
        JLabel finalTotalLabel = new JLabel("Final Total (Including Discount): £" + finalTotal);

        // setting layout of the cartFrane abd adding labels
        cartFrame.setLayout(new GridLayout(5,1, 1 , 1));
        cartFrame.add(originalPriceLabel);
        cartFrame.add(discountedAmountLabel);
        cartFrame.add(discountedPriceLabel);
        cartFrame.add(finalTotalLabel);
    }

    // Helper method to get the category of a product
    private String getCategory(Product product) {
        return (product instanceof Electronics) ? "Electronics" : "Clothing";
    }

    //class represting a shopping cart containing ProductCartItem objects
    public class ShoppingCart {
        private List<ProductCartItem> cartItems; //List to store items in the shopping cart

        //Constructor initializing the shopping cart with an empty list
        public ShoppingCart() {
            this.cartItems = new ArrayList<>();
        }

        //Method to add a product to the shopping cart with a specified quantity
        public void addProduct(Product product, int quantity) {
            boolean found = false;
            //Iterating through each item in the cartItems list
            for (ProductCartItem cartItem : cartItems) {
                //checking if the product is already in the cart
                if (cartItem.getProduct().equals(product)) {
                    //if found, updating the quantity of the existing cartItem
                    cartItem.setQuantity(cartItem.getQuantity() + quantity);
                    found = true;
                    break;
                }
            }
            //if it wasnt found, adding a new ProductCartItem
            if (!found) {
                cartItems.add(new ProductCartItem(product, quantity));
            }
        }

        //method to get the list of item in the shopping cart
        public List<ProductCartItem> getCartItems() {
            return cartItems;
        }
    }

    //method to set the productList with a given list of products
    public void setProductList(List<Product> products) {
        productList = products;
    }

    //Method to read products from a file and populate a list of Product objects
    public List<Product> getProductsFromFile() {
        List<Product> products = new ArrayList<>();

        //Getting the DefaultTableModel from the productTable
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();

        try {
            //Reading products from the "products.txt" file
            File myObj = new File("products.txt");
            Scanner myReader = new Scanner(myObj);
            //Iterating through each line in the file
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine().trim(); //Trim to remove leading/trailing whitespaces

                //Checking if the line indicates an Electronics product
                if (line.equals("Electronics: Product information:")) {
                    //Extracting information for creating an Electronics object
                    String productId = myReader.nextLine().substring(12).trim();
                    String productName = myReader.nextLine().substring(14).trim();
                    double price = Double.parseDouble(myReader.nextLine().substring(23).trim());
                    String brand = myReader.nextLine().substring(22).trim();
                    int warrantyPeriod = Integer.parseInt(myReader.nextLine().substring(18).trim());

                    //Creating an Electronics object and adding it to the products list
                    Electronics electronicProduct = new Electronics(productId, productName, 0, price, brand, warrantyPeriod);
                    products.add(electronicProduct);
                    //Adding the product to the DefaultTableModel
                    addToTableModel(model, electronicProduct);

                } else if (line.equals("Clothing: Product information:")) {
                    //Extracting information for creating a Clothing object
                    String productId = myReader.nextLine().substring(12).trim();
                    String productName = myReader.nextLine().substring(14).trim();
                    double price = Double.parseDouble(myReader.nextLine().substring(22).trim());
                    String size = myReader.nextLine().substring(19).trim();
                    String color = myReader.nextLine().substring(20).trim();

                    //Creating a Clothing object and adding it to the products list
                    Clothing clothingProduct = new Clothing(productId, productName, 0, price, size, color);
                    products.add(clothingProduct);
                    //Adding the product to the DefaultTableModel
                    addToTableModel(model, clothingProduct);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            //Error handling
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return products;
    }


    //Method to add a product to a DefaultTableModel, updating the table with product information
    private void addToTableModel(DefaultTableModel model, Product product) {
        //Check if the product ID already exists in the model
        boolean productExists = false;
        for (int i = 0; i < model.getRowCount(); i++) {
            if (product.getProductId().equals(model.getValueAt(i, 0))) {
                productExists = true;
                break;
            }
        }

        //If the product ID does not exist, add a new row for the product
        if (!productExists) {
            //Creating an array of Object to store the product information
            Object[] rowData = new Object[]{product.getProductId(), product.getProductName(), "", product.getPriceOfTheProduct(), ""};

            //Checking the type of the product and updating the array accordingly
            if (product instanceof Electronics) {
                rowData[2] = "Electronics";
                rowData[4] = "Brand: " + ((Electronics) product).getBrand() + ", Warranty: " + ((Electronics) product).getWarrantyPeriod() + " months";
            } else if (product instanceof Clothing) {
                rowData[2] = "Clothing";
                rowData[4] = "Size: " + ((Clothing) product).getSize() + ", Color: " + ((Clothing) product).getColor();
            }
            //Adding the array as a new row to the model
            model.addRow(rowData);
        }
    }


    //Method to create and initialize GUI components
    public void createComponents() {
        //Creating a JLabel for selecting the product category
        lbl = new JLabel("Select Product Category");
        lbl.setVisible(true);

        //Creating a JComboBox with choices "All", "Electronics", and "Clothing"
        String[] choices = { "All", "Electronics", "Clothing" };
        cb = new JComboBox<>(choices);
        cb.setVisible(true);

        //Creating a JButton for accessing the shopping cart
        shoppingcart = new JButton("Shopping Cart");
        shoppingcart.setVisible(true);

        //Creating a JButton for adding products to the shopping cart
        addtocart = new JButton("Add to Shopping Cart");
        addtocart.setVisible(true);
    }

    //method to update the product details displayed int he detailsPanel
    public void updateProductDetails(int selectedRow) {
        //Remove all components from the detailsPanel
        detailsPanel.removeAll();
        //Get the product ID from the selected row in the productTable
        String productId = (String) productTable.getValueAt(selectedRow, 0);
        //Get the selected product based on the product ID
        Product selectedProduct = getProductById(productId);
        //to set the preferred size for the detailsPanel
        detailsPanel.setPreferredSize(new Dimension(200, 200));

        //Check if a product is selected
        if(selectedProduct != null) {
            //Remove all components from the detailsPanel (redundant code)
            detailsPanel.removeAll();

            //Get the product ID from the selected row (repeated code)
            String selectedProductId = (String) productTable.getValueAt(selectedRow, 0);
            //Get the selected product based on the product ID (repeated code)
            Product selectedProductItem = getProductById(selectedProductId);

            //Check if the selected product is not null (repeated code)
            if (selectedProduct != null) {
                //Add details labels for common product information
                addDetailLabel("Product ID:", selectedProductItem.getProductId());
                addDetailLabel("Product Name:", selectedProductItem.getProductName());
                addDetailLabel("Category:", (selectedProductItem instanceof Electronics) ? "Electronics" : "Clothing");
                addDetailLabel("Price:", String.valueOf(selectedProductItem.getPriceOfTheProduct()));

                //Add details specific to the product category (Electronics or Clothing)
                if (selectedProductItem instanceof Electronics) {
                    Electronics electronicProduct = (Electronics) selectedProductItem;
                    addDetailLabel("Brand:", electronicProduct.getBrand());
                    addDetailLabel("Warranty Period:", String.valueOf(electronicProduct.getWarrantyPeriod()) + " months");
                } else if (selectedProductItem instanceof Clothing) {
                    Clothing clothingProduct = (Clothing) selectedProductItem;
                    addDetailLabel("Size:", clothingProduct.getSize());
                    addDetailLabel("Color:", clothingProduct.getColor());
                }

                detailsPanel.revalidate();  // Refresh the panel
                detailsPanel.repaint();
            }
        }
    }

    //method to add a pair of JLabels to the detailsPanel
    private void addDetailLabel(String label, String value) {
        //Add a new JLabel for the label text to the detailsPanel
        detailsPanel.add(new JLabel(label));
        //Add a new JLabel for the value text to the detailsPanel
        detailsPanel.add(new JLabel(value));
    }

    //method to initialize the main frame and add components to the panel
    public void initializeFrame() {
        panel.add(lbl); //to select product categories
        panel.add(cb); //JComboBox which shows the category choices
        panel.add(shoppingcart); //JButton to access the shopping cart panel
        panel.add(addtocart); //JButton to add prodcuts to the shopping cart

        // Use the JScrollPane instead of adding productTable directly
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        panel.add(scrollPane);

        //create a new JPanel for displaying product details with a GridLayout
        detailsPanel = new JPanel(new GridLayout(0, 2)); // GridLayout with two columns
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Product Details"));
        panel.add(detailsPanel); //add the detailsPanel to the main panel

        // Add a listener to update product details when a row is selected
        productTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow != -1) {
                updateProductDetails(selectedRow);
            }
        });
    }

    //class representing an item in the shopping cart
    public class ProductCartItem {
        //instance variables to store product and quantity
        private Product product;
        private int quantity;

        //constructoe to initialize product and quantity
        public ProductCartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        //getter mthod to retrieve the product
        public Product getProduct() {
            return product;
        }

        //getter method to retrieve the quantity
        public int getQuantity() {
            return quantity;
        }

        //setter method to update the product
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        //Method to calculate the total price for the item
        public double getTotalPrice() {
            return product.getPriceOfTheProduct() * quantity;
        }
    }


    //Method to retrieve a product based on its ID from the productList
    public Product getProductById(String productId) {
        //Iterate through the productList to find the matching product
        for (Product product : productList) {
            //Check if the product ID matches the provided ID
            if (product.getProductId().equals(productId)) {
                return product; //Return the matching product
            }
        }
        return null; // Product not found
    }
}
