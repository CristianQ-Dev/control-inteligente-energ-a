
public class AhorroEnergetico extends DecoradorDispositivo {
    private boolean modoAhorro;
    private double consumo;

    public AhorroEnergetico(Dispositivo dispositivo) {
        super(dispositivo);
        this.modoAhorro = false;
        this.consumo = 0.0;
    }

    @Override
    public void encender() {
        System.out.println("[AhorroEnergetico] Encendiendo dispositivo...");
        dispositivo.encender();
        if (modoAhorro) {
            System.out.println("Modo ahorro activado. Consumo reducido.");
            consumo += 0.5;
        } else {
            consumo += 1.0;
        }
    }

    @Override
    public void apagar() {
        System.out.println("[AhorroEnergetico] Apagando dispositivo...");
        dispositivo.apagar();
    }

    public void activarAhorro() {
        modoAhorro = true;
        System.out.println("Modo ahorro activado.");
    }

    public void desactivarAhorro() {
        modoAhorro = false;
        System.out.println("Modo ahorro desactivado.");
    }

    public double obtenerConsumo() {
        return consumo;
    }
}
