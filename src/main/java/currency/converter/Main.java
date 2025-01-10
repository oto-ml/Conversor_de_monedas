package currency.converter;

import com.google.gson.JsonObject;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        currencyService service = new currencyService();
        JsonGenerator generator = new JsonGenerator();
        List<JsonGenerator.Conversion> conversiones = new ArrayList<>();

        System.out.println("Bienvenido al conversor de monedas.");

        while (true) {
            System.out.print("Ingrese la moneda origen (USD, EUR, MXN, COP, BRL, PEN): ");
            String monedaOrigen = scanner.nextLine().toUpperCase();

            System.out.print("Ingrese la moneda destino (USD, EUR, MXN, COP, BRL, PEN): ");
            String monedaDestino = scanner.nextLine().toUpperCase();

            System.out.print("Ingrese la cantidad a convertir: ");
            double cantidadOrigen = scanner.nextDouble();
            scanner.nextLine(); // Limpiar el buffer del scanner

            try {
                // Lógica de conversión
                double cantidadDestino = service.convertirMoneda(monedaOrigen, monedaDestino, cantidadOrigen);

                // Crear un objeto Conversion para la conversión actual
                JsonGenerator.Conversion conversion = new JsonGenerator.Conversion(monedaOrigen, monedaDestino, cantidadOrigen, cantidadDestino);

                // Agregar el objeto Conversion a la lista
                conversiones.add(conversion);

            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }

            System.out.print("¿Deseas hacer otra conversión? (s/n): ");
            String respuesta = scanner.nextLine().toLowerCase();

            if (respuesta.equals("n")) {
                // Generar y guardar el archivo JSON con todas las conversiones
                try {
                    String jsonFinal = generator.generarJsonLista(conversiones);  // Generar el JSON para todas las conversiones
                    generator.guardarJsonEnArchivo(jsonFinal, "conversiones_totales.json");  // Guardar el archivo JSON
                    System.out.println("Las conversiones se han guardado en el archivo conversiones_totales.json");
                } catch (Exception e) {
                    System.out.println("Ocurrió un error al guardar el archivo: " + e.getMessage());
                }
                break;
            }
        }
    }
}
