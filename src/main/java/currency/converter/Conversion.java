package currency.converter;

public class Conversion {
    private String monedaOrigen;
    private String monedaDestino;
    private double cantidadOrigen;
    private double cantidadDestino;
    private double tasaCambio;

    public Conversion(String monedaOrigen, String monedaDestino, double cantidadOrigen, double cantidadDestino, double tasaCambio) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.cantidadOrigen = cantidadOrigen;
        this.cantidadDestino = cantidadDestino;
        this.tasaCambio = tasaCambio;
    }
}
