package com.test;

//Custom Classes Imported below
import com.test.PurchaseDAO;

// Import necessary classes
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.plugin.bundled.CorsPluginConfig;

public class App {

    // Create a Constructor object to hold user and product info
    Constructor construct = new Constructor("Arul", 24, "India, TN");

    // Counter to track number of products purchased
    int productIndex = 0;

    // Map to store product name as key and price as value
    Map<String, Double> purchasedProductDetails = new HashMap<>();

    // Scanner object to read input from console
    Scanner scanner = new Scanner(System.in);

    PurchaseDAO db = new PurchaseDAO(); // Initializing DB 

    // Method to record a purchase
    public void recordPurchase(String product, double price) {
        productIndex++;  // Increment product count
        construct.setProductName(product);  // Set product name
        construct.setProductPrice(price);   // Set product price
        construct.setTotalProductsPurchased(productIndex);  // Update total
        makeList();  // Add to local Map
        db.insertPurchase(product, price); // Save to DB
    }

    // Read user input from console (for CLI use)
    public void printAndRead() {
        System.out.println("[+] Enter the Product " + productIndex + " Name:");
        String productName = scanner.nextLine();
        System.out.println("[+] Enter price of the Product");
        Double productPrice = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        recordPurchase(productName, productPrice);
    }

    // Add product and price to Map
    public void makeList() {
        purchasedProductDetails.put(construct.getProductName(), construct.getProductPrice());
    }

    // Calculate total from DB and print
    public void calculateTotalProfit() {
        double sum = db.getTotalProfit(); // ✅ From DB
        System.out.println("Total profit: ₹" + sum);
    }

    // 🟢 Start API server
    public void startApi() {
        Javalin app = Javalin.create(config -> {
            // ✅ Enable CORS for frontend origin
            config.plugins.enableCors(cors -> {
                cors.add(CorsPluginConfig::anyHost); // Or use allowHost("http://localhost:8080")
            });
        }).start(7070);

        // Set JSON response type
        app.before(ctx -> ctx.contentType("application/json"));

        // 🔸 POST /purchase → Record purchase
        app.post("/purchase", ctx -> {
            Map<String, Object> data = ctx.bodyAsClass(Map.class);
            String product = data.get("product").toString();
            double price = Double.parseDouble(data.get("price").toString());
            recordPurchase(product, price);
            ctx.status(200).result("Recorded: " + product + " for ₹" + price);
        });

        // 🔹 GET /total → Return total profit
        app.get("/total", ctx -> {
            double sum = db.getTotalProfit();
            ctx.json(Map.of(
                "totalProfit", sum,
                "customerName", construct.name,
                "customerAge", construct.age,
                "customerRegion", construct.region
            ));
        });
    }

    // 🚀 Main Method
    public static void main(String[] args) {
        App app = new App();
        app.startApi();  // Start server

        // Optional CLI (not needed for frontend, just for manual entry)
        int flag = 0;
        while (flag != 1) {
            System.out.println("Proceed Adding Products? (Y/N)");
            String userInput = app.scanner.nextLine();
            if (userInput.equalsIgnoreCase("Y")) {
                app.printAndRead();
            } else {
                app.calculateTotalProfit();
                flag = 1;
                app.scanner.close();
            }
        }
    }
}
