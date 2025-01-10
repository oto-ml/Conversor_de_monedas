package currency.converter;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class currencyService {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/1418a83c3212ebbc7340466c/latest/";

    public double convertirMoneda(String monedaOrigen, String monedaDestino, double cantidadOrigen) throws Exception {
        String urlStr = API_URL + monedaOrigen;
        URL url = new URL(urlStr);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == 200) {
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            Gson gson = new Gson();

            JsonObject responseJson = gson.fromJson(reader, JsonObject.class);
            reader.close();

            if (responseJson.has("conversion_rates")) {
                JsonObject conversionRates = responseJson.getAsJsonObject("conversion_rates");
                if (conversionRates.has(monedaDestino)) {
                    double tasaCambio = conversionRates.get(monedaDestino).getAsDouble();
                    return cantidadOrigen * tasaCambio;
                } else {
                    throw new Exception("Moneda de destino no encontrada en las tasas de conversión.");
                }
            } else {
                throw new Exception("Error en la respuesta de la API: no se encontraron tasas de conversión.");
            }
        } else {
            throw new Exception("Error al conectarse a la API: " + connection.getResponseCode());
        }
    }
}
