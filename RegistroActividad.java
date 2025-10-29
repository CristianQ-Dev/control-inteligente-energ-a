
import java.util.ArrayList;
import java.util.List;

public class RegistroActividad extends DecoradorDispositivo {
    private List<String> historial;
    private long tiempoTotal;

    public RegistroActividad(Dispositivo dispositivo) {
        super(dispositivo);
        historial = new ArrayList<>();
        tiempoTotal = 0;
    }

    @Override
    public void encender() {
        historial.add("Encendido");
        System.out.println("[RegistroActividad] Encendido registrado.");
        dispositivo.encender();
    }

    @Override
    public void apagar() {
        historial.add("Apagado");
        System.out.println("[RegistroActividad] Apagado registrado.");
        dispositivo.apagar();
    }

    public void registrarAccion(String accion) {
        historial.add(accion);
        System.out.println("Acción registrada: " + accion);
    }

    public void obtenerReporte() {
        System.out.println("--- Reporte de Actividad ---");
        for (String h : historial) System.out.println(h);
        System.out.println("Tiempo total activo: " + tiempoTotal + " unidades.");
    }
}
