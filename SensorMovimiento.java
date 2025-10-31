import java.util.Random;

public class SensorMovimiento extends DecoradorDispositivo {
    private boolean detectado;
    private int sensibilidad;

    public SensorMovimiento(Dispositivo dispositivo) {
        super(dispositivo);
        this.sensibilidad = 5;
    }

    @Override
    public void encender() {
        System.out.println("[SensorMovimiento] Encendiendo sensor...");
        detectarMovimiento();
        if (detectado) {
            System.out.println("Movimiento detectado. Encendiendo automáticamente.");
            dispositivo.encender();
        } else {
            System.out.println("No se detectó movimiento. El dispositivo permanece apagado.");
        }
    }

    @Override
    public void apagar() {
        System.out.println("[SensorMovimiento] Apagando sensor...");
        dispositivo.apagar();
    }

    public void detectarMovimiento() {
        Random random = new Random();
        detectado = random.nextInt(10) < sensibilidad;
        System.out.println("[SensorMovimiento] " + (detectado ? "Movimiento detectado." : "Sin movimiento."));
    }

    public void ajustarSensibilidad(int nivel) {
        if (nivel < 1)
            nivel = 1;
        if (nivel > 10)
            nivel = 10;
        sensibilidad = nivel;
        System.out.println("Sensibilidad ajustada a nivel: " + sensibilidad);
    }
}
