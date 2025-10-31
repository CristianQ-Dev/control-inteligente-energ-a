import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RegistroActividad extends DecoradorDispositivo {
    private List<String> historial;
    private long tiempoTotal; // en segundos
    private long inicio;      // tiempo de encendido (ms)
    private boolean activo;   // estado interno
    private double consumoTotal; // consumo acumulado estimado (kWh)

    public RegistroActividad(Dispositivo dispositivo) {
        super(dispositivo);
        historial = new ArrayList<>();
        tiempoTotal = 0;
        consumoTotal = 0;
        activo = false;
    }

    @Override
    public void encender() {
        if (activo) {
            System.out.println("[RegistroActividad] El dispositivo ya está encendido.");
            return;
        }

        activo = true;
        inicio = System.currentTimeMillis();

        String fecha = fechaActual();
        historial.add("Encendido a las " + fecha);
        System.out.println("[RegistroActividad] Encendido registrado (" + fecha + ").");

        dispositivo.encender();
    }

    @Override
    public void apagar() {
        if (!activo) {
            System.out.println("[RegistroActividad] El dispositivo ya está apagado.");
            return;
        }

        activo = false;
        long fin = System.currentTimeMillis();
        long duracion = (fin - inicio) / 1000; // segundos
        tiempoTotal += duracion;

        double consumo = duracion * 0.75; // ejemplo simple: 0.75 Wh por segundo
        consumoTotal += consumo;

        String fecha = fechaActual();
        historial.add("Apagado a las " + fecha + " (duración: " + duracion + " seg, consumo: " + consumo + " Wh)");
        System.out.println("[RegistroActividad] Apagado registrado (" + duracion + " segundos, " + consumo + " Wh).");

        dispositivo.apagar();
    }

    public void registrarAccion(String accion) {
        historial.add(fechaActual() + " -> " + accion);
        System.out.println("[RegistroActividad] Acción registrada: " + accion);
    }

    public void obtenerReporte() {
        System.out.println("\n===== REPORTE DE ACTIVIDAD =====");
        if (historial.isEmpty()) {
            System.out.println("No hay registros de actividad aún.");
        } else {
            historial.forEach(System.out::println);
            System.out.println("--------------------------------");
            System.out.println("Tiempo total activo: " + tiempoTotal + " segundos.");
            System.out.println("Consumo total estimado: " + consumoTotal + " Wh.");
        }
        System.out.println("================================\n");
    }

    // 🔹 Método auxiliar para formatear fecha y hora
    private String fechaActual() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}
