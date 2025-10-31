import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Clase Temporizador que extiende DecoradorDispositivo.
 * Permite programar una hora de apagado automático y simular su ejecución.
 */
public class Temporizador extends DecoradorDispositivo {
    private LocalTime horaProgramada;
    private boolean apagadoAutomatico;
    private boolean enEjecucion;
    private Thread hiloTemporizador; // para simular el paso del tiempo

    public Temporizador(Dispositivo dispositivo) {
        super(dispositivo);
        this.apagadoAutomatico = false;
        this.enEjecucion = false;
    }

    @Override
    public void encender() {
        System.out.println("[Temporizador] Encendiendo dispositivo con función de temporizador...");
        dispositivo.encender();

        if (apagadoAutomatico && horaProgramada != null) {
            iniciarTemporizador();
        } else {
            System.out.println("[Temporizador] No hay una hora programada. Puedes configurarla con 'programarTiempo()'.");
        }
    }

    @Override
    public void apagar() {
        System.out.println("[Temporizador] Apagando dispositivo...");
        dispositivo.apagar();

        if (hiloTemporizador != null && hiloTemporizador.isAlive()) {
            hiloTemporizador.interrupt();
            enEjecucion = false;
        }
    }

    /**
     * Programa una hora específica para el apagado automático del dispositivo.
     * El usuario puede introducir la hora manualmente.
     */
    public void programarTiempo(LocalTime hora) {
        horaProgramada = hora;
        apagadoAutomatico = true;
        System.out.println("[Temporizador] Apagado automático programado para las " + horaProgramada);
    }

    /**
     * Método para que el usuario configure el temporizador desde la consola.
     */
    public void configurarDesdeUsuario() {
       
        try {
            System.out.println("=== CONFIGURAR TEMPORIZADOR ===");
            System.out.print("Ingrese la hora de apagado (HH:mm): ");
            String entrada = Main.sc.nextLine();
            LocalTime hora = LocalTime.parse(entrada);
            programarTiempo(hora);
        } catch (Exception e) {
            System.out.println("[Error] Formato de hora inválido. Use HH:mm, por ejemplo 22:30");
        }
    }

   
    public void cancelar() {
        apagadoAutomatico = false;
        horaProgramada = null;
        enEjecucion = false;
        if (hiloTemporizador != null && hiloTemporizador.isAlive()) {
            hiloTemporizador.interrupt();
        }
        System.out.println("[Temporizador] Temporizador cancelado.");
    }

    /**
     * Inicia la simulación del temporizador.
     * Calcula el tiempo restante hasta la hora programada y espera hasta llegar a ella.
     */
    private void iniciarTemporizador() {
        if (enEjecucion) {
            System.out.println("[Temporizador] Ya hay un temporizador en ejecución.");
            return;
        }

        LocalTime ahora = LocalTime.now();
        long segundosRestantes = ChronoUnit.SECONDS.between(ahora, horaProgramada);

        if (segundosRestantes <= 0) {
            System.out.println("[Temporizador] La hora programada ya ha pasado. Cancela o programa una nueva.");
            return;
        }

        System.out.println("[Temporizador] El dispositivo se apagará automáticamente en " + segundosRestantes + " segundos.");

        hiloTemporizador = new Thread(() -> {
            try {
                enEjecucion = true;
                Thread.sleep(segundosRestantes * 1000);
                System.out.println("\n[Temporizador] Tiempo cumplido. Apagando dispositivo automáticamente...");
                apagar();
            } catch (InterruptedException e) {
                System.out.println("[Temporizador] El temporizador fue interrumpido o cancelado.");
            } finally {
                enEjecucion = false;
            }
        });

        hiloTemporizador.start();
    }

    /**
     * Devuelve una descripción del estado actual del temporizador.
     */
    @Override
    public String obtenerEstado() {
        String estadoBase = dispositivo.obtenerEstado();
        String infoTemp = apagadoAutomatico
                ? "Temporizador activo, programado para las " + horaProgramada
                : "Temporizador inactivo";
        return estadoBase + " | " + infoTemp;
    }
}
