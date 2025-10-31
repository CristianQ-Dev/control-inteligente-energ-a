import java.util.ArrayList;
import java.util.List;

public class ControlPorVoz extends DecoradorDispositivo {
    private final List<String> comandos;
    private boolean porVoz = false;

    public ControlPorVoz(Dispositivo dispositivo) {
        super(dispositivo);
        comandos = new ArrayList<>(List.of("encender", "apagar"));
    }

    @Override
    public void encender() {
        String nombre = (dispositivo instanceof DispositivoBase base) ? base.getNombre() : "dispositivo";
        if (porVoz) {
            System.out.println("[ControlPorVoz] Encendiendo " + nombre + " mediante comando de voz...");
            porVoz = false;
        } else {
            System.out.println("[ControlPorVoz] Encendiendo " + nombre + " manualmente...");
        }
        dispositivo.encender();
    }

    @Override
    public void apagar() {
        String nombre = (dispositivo instanceof DispositivoBase base) ? base.getNombre() : "dispositivo";
        if (porVoz) {
            System.out.println("[ControlPorVoz] Apagando " + nombre + " mediante comando de voz...");
            porVoz = false;
        } else {
            System.out.println("[ControlPorVoz] Apagando " + nombre + " manualmente...");
        }
        dispositivo.apagar();
    }

    public void procesarComando(String comando) {
        if (comando == null || comando.trim().isEmpty()) {
            System.out.println("[ControlPorVoz] No se detectó ningún comando.");
            return;
        }

        comando = comando.trim().toLowerCase();

        if (!comandos.contains(comando)) {
            System.out.println("[ControlPorVoz] El comando '" + comando + "' no está registrado.");
            return;
        }

        porVoz = true;
        switch (comando) {
            case "encender" -> encender();
            case "apagar" -> apagar();
            default -> System.out.println("[ControlPorVoz] Comando no reconocido.");
        }
    }

    public void escuchar() {
        int opcion = -1;
        do {
            try {
                System.out.println(
                        """
                 ===== CONTROL POR VOZ ===
                  1. Decir "Encender
                  2. Decir "Apagar"
                  0. Salir
                  """);
                System.out.print("Seleccione una opción: ");
                String entrada = Main.sc.nextLine();
                if (entrada.isEmpty())
                    continue;

                opcion = Integer.parseInt(entrada);

                switch (opcion) {
                    case 1 -> procesarComando("encender");
                    case 2 -> procesarComando("apagar");
                    case 0 -> System.out.println("Saliendo del control por voz...");
                    default -> System.out.println("Opción inválida, intente nuevamente.");
                }
                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número.");
            }
        } while (opcion != 0);
    }
}
