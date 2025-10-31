public class DispositivoBase implements Dispositivo {
    private String nombre;
    private boolean activo;

    public DispositivoBase(String nombre) {
        this.nombre = nombre;
        this.activo = false;
    }

    @Override
    public void encender() {
        if (!activo) {
            activo = true;
            System.out.println("[DispositivoBase] " + nombre + " encendido.");
        } else {
            System.out.println("[DispositivoBase] " + nombre + " ya está encendido.");
        }
    }

    @Override
    public void apagar() {
        if (activo) {
            activo = false;
            System.out.println("[DispositivoBase] " + nombre + " apagado.");
        } else {
            System.out.println("[DispositivoBase] " + nombre + " ya está apagado.");
        }
    }

    @Override
    public String obtenerEstado() {
        return "[DispositivoBase] " + nombre + " está " + (activo ? "ENCENDIDO" : "APAGADO");
    }

    public String getNombre() {
        return nombre;
    }

    public boolean estaActivo() {
        return activo;
    }
}
