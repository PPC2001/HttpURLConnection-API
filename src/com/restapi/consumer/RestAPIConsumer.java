package com.restapi.consumer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestAPIConsumer {
	public static void main(String[] args) {
		try {
			// 1. Create a URL object with the URL of the REST API endpoint	
			URL url = new URL("https://jsonplaceholder.typicode.com/todos/1");

			// 2. Open a connection to the URL using HttpURLConnection
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// 3. Set the request method (GET, POST, PUT, DELETE, etc.) and any necessary
			// request headers
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Authorization", "Bearer <your_access_token>");

			// 4. Send the request
			int responseCode = connection.getResponseCode();

			// 5. Read the response
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// 6. Process the response
				System.out.println("Response: " + response.toString());
			} else {
				System.out.println("Error: HTTP " + responseCode);
			}

			// 7. Close the connection
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
