
public abstract class DecoradorDispositivo implements Dispositivo {
    protected Dispositivo dispositivo;

    public DecoradorDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    @Override
    public void encender() {
        dispositivo.encender();
    }

    @Override
    public void apagar() {
        dispositivo.apagar();
    }

    @Override
    public String obtenerEstado() {
        return dispositivo.obtenerEstado();
    }
}
