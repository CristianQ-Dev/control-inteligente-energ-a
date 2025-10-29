
import java.util.Random;
import java.util.Scanner;

public class SensorMovimiento extends DecoradorDispositivo {
    private boolean detectado;
    private int sensibilidad;

    public SensorMovimiento(Dispositivo dispositivo) {
        super(dispositivo);
        this.sensibilidad = 5;
    }

    @Override
    public void encender() {
        System.out.println("[SensorMovimiento] Encendiendo dispositivo...");
        detectarMovimiento();
        if (detectado) System.out.println("Movimiento detectado. Encendiendo automáticamente.");
        dispositivo.encender();
    }

    @Override
    public void apagar() {
        System.out.println("[SensorMovimiento] Apagando dispositivo...");
        dispositivo.apagar();
    }

    public void detectarMovimiento() {
        Random random = new Random();
        detectado = random.nextBoolean();
        System.out.println("Sensor: " + (detectado ? "Movimiento detectado." : "Sin movimiento."));
    }

    public void ajustarSensibilidad(int nivel) {
        sensibilidad = nivel;
        System.out.println("Sensibilidad ajustada a: " + sensibilidad);
    }
}

