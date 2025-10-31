import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== SISTEMA ENERGETICO INTELIGENTE ===");
        System.out.print("Ingrese el nombre del dispositivo: ");
        String nombre = sc.nextLine();

        Dispositivo dispositivo = new DispositivoBase(nombre);

        // 💡 Mapa para almacenar los decoradores aplicados
        Map<String, DecoradorDispositivo> decoradores = new HashMap<>();

        boolean salir = false;

        while (!salir) {
            System.out.println("""
                    
                    --- MENÚ PRINCIPAL ---
                    1. Modo ahorro energético
                    2. Control por voz
                    3. Sensor de movimiento
                    4. Temporizador
                    5. Registro de actividad
                    6. Encender manualmente
                    7. Apagar manualmente
                    8. Ver estado
                    9. Salir
                    """);
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    AhorroEnergetico ahorro = (AhorroEnergetico) decoradores.get("ahorro");
                    if (ahorro == null) {
                        ahorro = new AhorroEnergetico(dispositivo);
                        dispositivo = ahorro;
                        decoradores.put("ahorro", ahorro);
                    }

                    System.out.println("""
                            --- MODO AHORRO ENERGÉTICO ---
                            1. Activar
                            2. Desactivar
                            3. Ver consumo estimado
                            0. Volver
                            """);
                    int sub = sc.nextInt();
                    sc.nextLine();

                    switch (sub) {
                        case 1 -> ahorro.activarAhorro();
                        case 2 -> ahorro.desactivarAhorro();
                        case 3 -> System.out.printf("Consumo estimado actual: %.2f Wh%n", ahorro.obtenerConsumo());
                        case 0 -> System.out.println("Volviendo al menú principal...");
                        default -> System.out.println("Opción inválida.");
                    }
                }

                case 2 -> {
                    ControlPorVoz voz = (ControlPorVoz) decoradores.get("voz");
                    if (voz == null) {
                        voz = new ControlPorVoz(dispositivo);
                        dispositivo = voz;
                        decoradores.put("voz", voz);
                    }
                    voz.escuchar();
                }

                case 3 -> {
                    SensorMovimiento sensor = (SensorMovimiento) decoradores.get("sensor");
                    if (sensor == null) {
                        sensor = new SensorMovimiento(dispositivo);
                        dispositivo = sensor;
                        decoradores.put("sensor", sensor);
                    }
                    sensor.encender();
                }

                case 4 -> {
                    Temporizador temporizador = (Temporizador) decoradores.get("temporizador");
                    if (temporizador == null) {
                        temporizador = new Temporizador(dispositivo);
                        dispositivo = temporizador;
                        decoradores.put("temporizador", temporizador);
                    }
                    temporizador.configurarDesdeUsuario();
                }

                case 5 -> {
                    RegistroActividad registro = (RegistroActividad) decoradores.get("registro");
                    if (registro == null) {
                        registro = new RegistroActividad(dispositivo);
                        dispositivo = registro;
                        decoradores.put("registro", registro);
                        System.out.println("[RegistroActividad] Activado y listo para registrar acciones.");
                    } else {
                        registro.obtenerReporte();
                    }
                }

                case 6 -> dispositivo.encender();
                case 7 -> dispositivo.apagar();
                case 8 -> System.out.println(dispositivo.obtenerEstado());
                case 9 -> salir = true;
                default -> System.out.println("Opción inválida.");
            }
        }

        System.out.println("Saliendo del sistema...");
        sc.close();
    }
}
