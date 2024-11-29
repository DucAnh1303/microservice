package com.service.microservice.gateway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentRequestTest {

    public static void main(String[] args) {
        String gatewayUrl = "http://localhost:9094/order/send-message";
        int requestCount = 20;

        // Create a thread pool with the specified number of threads
        ExecutorService executorService = Executors.newFixedThreadPool(requestCount);

        for (int i = 0; i < requestCount; i++) {
            // Submit tasks to the executor service
            executorService.submit(() -> sendMessage(gatewayUrl));
        }

        // Shut down the executor service after submitting tasks
        executorService.shutdown();
    }
    private static void sendMessage(String gatewayUrl) {
        // Create a URL object
        try {
            // Create URL object from the given gatewayUrl
            URL url = new URL(gatewayUrl);

            // Open a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set HTTP method to POST
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);  // Allow sending request body

            // Set headers (if necessary)
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer YOUR_TOKEN"); // Use your actual token here

            // Prepare the JSON body (if necessary)
            String jsonInputString = "{\"message\":\"This is a test message\"}";

            // Send the request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);  // Write the JSON data to the output stream
            }

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Message sent, response code: " + responseCode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
