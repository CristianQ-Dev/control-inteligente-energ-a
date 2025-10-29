
import java.util.ArrayList;
import java.util.List;

public class ControlPorVoz extends DecoradorDispositivo {
    private List<String> comandos;

    public ControlPorVoz(Dispositivo dispositivo) {
        super(dispositivo);
        comandos = new ArrayList<>();
    }

    @Override
    public void encender() {
        System.out.println("[ControlPorVoz] Encendiendo por comando de voz...");
        dispositivo.encender();
    }

    @Override
    public void apagar() {
        System.out.println("[ControlPorVoz] Apagando por comando de voz...");
        dispositivo.apagar();
    }

    public void agregarComando(String comando) {
        comandos.add(comando);
        System.out.println("Comando agregado: " + comando);
    }

    public void procesarComando(String comando) {
        if (comando.equalsIgnoreCase("encender")) encender();
        else if (comando.equalsIgnoreCase("apagar")) apagar();
        else System.out.println("Comando no reconocido.");
    }
}
