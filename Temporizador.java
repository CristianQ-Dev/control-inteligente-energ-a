
import java.time.LocalTime;

public class Temporizador extends DecoradorDispositivo {
    private LocalTime horaProgramada;
    private boolean apagadoAutomatico;

    public Temporizador(Dispositivo dispositivo) {
        super(dispositivo);
    }

    @Override
    public void encender() {
        System.out.println("[Temporizador] Dispositivo encendido con temporizador.");
        dispositivo.encender();
    }

    @Override
    public void apagar() {
        System.out.println("[Temporizador] Dispositivo apagado.");
        dispositivo.apagar();
    }

    public void programarTiempo(LocalTime hora) {
        horaProgramada = hora;
        apagadoAutomatico = true;
        System.out.println("Apagado automático programado para las " + horaProgramada);
    }

    public void cancelar() {
        apagadoAutomatico = false;
        horaProgramada = null;
        System.out.println("Temporizador cancelado.");
    }
}
