
import java.time.LocalTime;
import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Dispositivo> dispositivos = new ArrayList<>();
    private static final Map<String, Dispositivo> mapa = new HashMap<>();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1 -> crearDispositivo();
                case 2 -> listar();
                case 3 -> añadirDecorador();
                case 4 -> encenderApagar();
                case 5 -> ahorroEnergetico();
                case 6 -> sensorMovimiento();
                case 7 -> temporizador();
                case 8 -> controlPorVoz();
                case 9 -> reporte();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("""
                ===== DOMÓTICA INTELIGENTE =====
                1. Crear dispositivo
                2. Listar dispositivos
                3. Añadir decorador
                4. Encender/Apagar
                5. Modo Ahorro Energético
                6. Sensor de Movimiento
                7. Temporizador
                8. Control por Voz
                9. Reporte de Actividad
                0. Salir
                """);
    }

    private static void crearDispositivo() {
        System.out.print("Nombre del dispositivo: ");
        String nombre = sc.nextLine();
        DispositivoBase base = new DispositivoBase(nombre);
        dispositivos.add(base);
        mapa.put(nombre, base);
        System.out.println("Dispositivo '" + nombre + "' creado.");
    }

    private static void listar() {
        for (Dispositivo d : dispositivos)
            if (d instanceof DispositivoBase b)
                System.out.println("- " + b.getNombre() + ": " + b.obtenerEstado());
            else
                System.out.println("- " + d.obtenerEstado());
    }

    private static void añadirDecorador() {
        System.out.print("Nombre del dispositivo: ");
        String nombre = sc.nextLine();
        Dispositivo d = mapa.get(nombre);
        if (d == null) {
            System.out.println("No existe ese dispositivo.");
            return;
        }
        System.out.println("""
                Tipo de decorador:
                1. AhorroEnergetico
                2. SensorMovimiento
                3. Temporizador
                4. ControlPorVoz
                5. RegistroActividad
                """);
        int tipo = Integer.parseInt(sc.nextLine());
        switch (tipo) {
            case 1 -> mapa.put(nombre, new AhorroEnergetico(d));
            case 2 -> mapa.put(nombre, new SensorMovimiento(d));
            case 3 -> mapa.put(nombre, new Temporizador(d));
            case 4 -> mapa.put(nombre, new ControlPorVoz(d));
            case 5 -> mapa.put(nombre, new RegistroActividad(d));
        }
        System.out.println("Decorador añadido a " + nombre);
    }

    private static void encenderApagar() {
        System.out.print("Nombre del dispositivo: ");
        String nombre = sc.nextLine();
        Dispositivo d = mapa.get(nombre);
        if (d == null) return;
        System.out.print("1. Encender  2. Apagar: ");
        int op = Integer.parseInt(sc.nextLine());
        if (op == 1) d.encender(); else d.apagar();
    }

    private static void ahorroEnergetico() {
        System.out.print("Nombre del dispositivo: ");
        String nombre = sc.nextLine();
        Dispositivo d = mapa.get(nombre);
        if (d instanceof AhorroEnergetico ae) {
            System.out.print("1. Activar  2. Desactivar  3. Ver consumo: ");
            int op = Integer.parseInt(sc.nextLine());
            switch (op) {
                case 1 -> ae.activarAhorro();
                case 2 -> ae.desactivarAhorro();
                case 3 -> System.out.println("Consumo: " + ae.obtenerConsumo());
            }
        } else System.out.println("Ese dispositivo no tiene ahorro energético.");
    }

    private static void sensorMovimiento() {
        System.out.print("Nombre del dispositivo: ");
        String nombre = sc.nextLine();
        Dispositivo d = mapa.get(nombre);
        if (d instanceof SensorMovimiento s) {
            System.out.print("1. Ajustar sensibilidad  2. Detectar: ");
            int op = Integer.parseInt(sc.nextLine());
            if (op == 1) s.ajustarSensibilidad(Integer.parseInt(sc.nextLine()));
            else s.detectarMovimiento();
        } else System.out.println("Ese dispositivo no tiene sensor.");
    }

    private static void temporizador() {
        System.out.print("Nombre del dispositivo: ");
        String nombre = sc.nextLine();
        Dispositivo d = mapa.get(nombre);
        if (d instanceof Temporizador t) {
            System.out.print("1. Programar  2. Cancelar: ");
            int op = Integer.parseInt(sc.nextLine());
            if (op == 1) {
                System.out.print("Hora (HH:mm): ");
                t.programarTiempo(LocalTime.parse(sc.nextLine()));
            } else t.cancelar();
        } else System.out.println("Ese dispositivo no tiene temporizador.");
    }

    private static void controlPorVoz() {
        System.out.print("Nombre del dispositivo: ");
        String nombre = sc.nextLine();
        Dispositivo d = mapa.get(nombre);
        if (d instanceof ControlPorVoz c) {
            System.out.print("1. Agregar comando  2. Procesar comando: ");
            int op = Integer.parseInt(sc.nextLine());
            System.out.print("Comando: ");
            String comando = sc.nextLine();
            if (op == 1) c.agregarComando(comando);
            else c.procesarComando(comando);
        } else System.out.println("Ese dispositivo no tiene control por voz.");
    }

    private static void reporte() {
        System.out.print("Nombre del dispositivo: ");
        String nombre = sc.nextLine();
        Dispositivo d = mapa.get(nombre);
        if (d instanceof RegistroActividad r) r.obtenerReporte();
        else System.out.println("Ese dispositivo no tiene registro de actividad.");
    }
}
