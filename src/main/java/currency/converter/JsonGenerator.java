package currency.converter;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonGenerator {
    private final Gson gson = new Gson();

    // Metodo para generar JSON de una sola conversión
    public String generarJson(String monedaOrigen, String monedaDestino, double cantidadOrigen, double cantidadDestino) {
        Conversion conversion = new Conversion(monedaOrigen, monedaDestino, cantidadOrigen, cantidadDestino);
        return gson.toJson(conversion);  // Convierte el objeto Conversion a JSON
    }

    // Método para generar el JSON con todas las conversiones
    public String generarJsonLista(List<Conversion> conversiones) {
        return gson.toJson(conversiones);  // Convierte la lista de objetos Conversion a JSON
    }

    // Método para guardar el JSON en un archivo
    public void guardarJsonEnArchivo(String json, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo JSON: " + e.getMessage());
        }
    }

    // Clase interna Conversion que representa una conversión de moneda
    static class Conversion {
        private final String monedaOrigen;
        private final String monedaDestino;
        private final double cantidadOrigen;
        private final double cantidadDestino;

        public Conversion(String monedaOrigen, String monedaDestino, double cantidadOrigen, double cantidadDestino) {
            this.monedaOrigen = monedaOrigen;
            this.monedaDestino = monedaDestino;
            this.cantidadOrigen = cantidadOrigen;
            this.cantidadDestino = cantidadDestino;
        }
    }
}
