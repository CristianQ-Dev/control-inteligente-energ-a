
public class DispositivoBase implements Dispositivo {
    private String nombre;
    private boolean activo;

    public DispositivoBase(String nombre) {
        this.nombre = nombre;
        this.activo = false;
    }

    @Override
    public void encender() {
        activo = true;
        System.out.println(nombre + " encendido.");
    }

    @Override
    public void apagar() {
        activo = false;
        System.out.println(nombre + " apagado.");
    }

    @Override
    public String obtenerEstado() {
        return activo ? "ENCENDIDO" : "APAGADO";
    }

    public String getNombre() {
        return nombre;
    }
}

