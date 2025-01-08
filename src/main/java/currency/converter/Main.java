package currency.converter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Scanner;

public class Main {

    // URL base de la API de ExchangeRate
    private static final String BASE_URL = "https://api.exchangerate-api.com/v4/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la moneda base (por ejemplo, USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Ingrese la moneda a convertir (por ejemplo, EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Ingrese la cantidad a convertir: ");
        double amount = scanner.nextDouble();

        try {
            String jsonResponse = get(BASE_URL + baseCurrency);
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            double exchangeRate = jsonObject.getAsJsonObject("rates").get(targetCurrency).getAsDouble();
            double convertedAmount = amount * exchangeRate;
            System.out.printf("%.2f %s son equivalentes a %.2f %s\n", amount, baseCurrency, convertedAmount, targetCurrency);
        } catch (Exception e) {
            System.out.println("Error al obtener la tasa de cambio: " + e.getMessage());
        }

        scanner.close();
    }

    private static String get(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HTTP response code: " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();

        return content.toString();
    }
}
