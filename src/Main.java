import java.util.Scanner;

/**
 * CLASE MAIN
 *
 * RESPONSABILIDADES:
 * - Mostrar menús al usuario
 * - Capturar entrada del usuario
 * - Llamar a los métodos de las clases (Banco, Cliente, CuentaBancaria)
 * - Controlar el flujo del programa
 *
 * REGLA DE ORO: El main NO debe tener lógica de negocio.
 * Solo debe COORDINAR llamadas a las clases.
 */
public class Main {

    // Scanner global para no crear uno en cada método
    private static Scanner scanner = new Scanner(System.in);

    // El banco principal del sistema
    private static Banco banco = new Banco("Banco POO Colombia");

    // ============================================
    // MÉTODO PRINCIPAL
    // ============================================
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║   BIENVENIDO AL SISTEMA BANCARIO POO                  ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");

        boolean continuar = true;

        while(continuar) {
            mostrarMenuPrincipal();
            int opcion = leerEntero();

            switch(opcion) {
                case 1:
                    menuGestionClientes();
                    break;
                case 2:
                    menuGestionCuentas();
                    break;
                case 3:
                    menuOperaciones();
                    break;
                case 4:
                    menuReportes();
                    break;
                case 5:
                    System.out.println("\n✅ Gracias por usar el sistema. ¡Hasta pronto!");
                    continuar = false;
                    break;
                default:
                    System.out.println("❌ Opción inválida. Intente nuevamente.");
            }
        }

        scanner.close();
    }

    // ============================================
    // MENÚ PRINCIPAL
    // ============================================
    private static void mostrarMenuPrincipal() {
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║              💰 MENÚ PRINCIPAL 💰                      ║");
        System.out.println("╠═══════════════════════════════════════════════════════╣");
        System.out.println("║  1. 👤 Gestión de Clientes                            ║");
        System.out.println("║  2. 🏦 Gestión de Cuentas                             ║");
        System.out.println("║  3. 💵 Operaciones                                     ║");
        System.out.println("║  4. 📊 Reportes                                        ║");
        System.out.println("║  5. 🚪 Salir                                           ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
    }

    // ============================================
    // SUBMENÚ: GESTIÓN DE CLIENTES
    // ============================================
    /**
     * Maneja todo lo relacionado con clientes:
     * - Registrar nuevo cliente
     * - Buscar cliente
     * - Listar todos los clientes
     */
    private static void menuGestionClientes() {
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║          👤 GESTIÓN DE CLIENTES 👤                     ║");
        System.out.println("╠═══════════════════════════════════════════════════════╣");
        System.out.println("║  1. Registrar nuevo cliente                           ║");
        System.out.println("║  2. Buscar cliente por ID                             ║");
        System.out.println("║  3. Listar todos los clientes                         ║");
        System.out.println("║  4. Volver al menú principal                          ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();

        switch(opcion) {
            case 1:
                registrarCliente();
                break;
            case 2:
                buscarClientePorId();
                break;
            case 3:
                banco.listarClientes();
                break;
            case 4:
                return; // Volver al menú principal
            default:
                System.out.println("❌ Opción inválida.");
        }
    }

    // ============================================
    // FUNCIÓN: Registrar cliente
    // ============================================
    /**
     * Captura los datos del usuario y crea un nuevo cliente.
     *
     * VALIDACIONES:
     * - ID no puede estar vacío
     * - Email debe contener @
     * - Teléfono debe tener 10 dígitos
     */
    private static void registrarCliente() {
        System.out.println("\n--- REGISTRAR NUEVO CLIENTE ---");

        System.out.print("ID del cliente: ");
        String id = scanner.next();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();

        System.out.print("Teléfono (10 dígitos): ");
        String telefono = scanner.next();

        // Validación de teléfono
        if(telefono.length() != 10 || !telefono.matches("[0-9]+")) {
            System.out.println("❌ Error: El teléfono debe tener exactamente 10 dígitos.");
            return;
        }

        System.out.print("Email: ");
        String email = scanner.next();

        // Validación de email
        if(!email.contains("@")) {
            System.out.println("❌ Error: Email inválido (debe contener @).");
            return;
        }

        // CREAR el cliente (usa el constructor)
        Cliente nuevoCliente = new Cliente(id, nombre, telefono, email);

        // REGISTRAR el cliente en el banco
        banco.registrarCliente(nuevoCliente);
    }

    // ============================================
    // FUNCIÓN: Buscar cliente
    // ============================================
    private static void buscarClientePorId() {
        System.out.print("\nIngrese ID del cliente: ");
        String id = scanner.next();

        Cliente cliente = banco.buscarCliente(id);

        if(cliente != null) {
            cliente.mostrarInfoCompleta();
        } else {
            System.out.println("❌ No se encontró cliente con ID: " + id);
        }
    }

    // ============================================
    // SUBMENÚ: GESTIÓN DE CUENTAS
    // ============================================
    private static void menuGestionCuentas() {
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║          🏦 GESTIÓN DE CUENTAS 🏦                      ║");
        System.out.println("╠═══════════════════════════════════════════════════════╣");
        System.out.println("║  1. Crear cuenta para un cliente                      ║");
        System.out.println("║  2. Buscar cuenta por número                          ║");
        System.out.println("║  3. Ver cuentas de un cliente                         ║");
        System.out.println("║  4. Volver al menú principal                          ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();

        switch(opcion) {
            case 1:
                crearCuenta();
                break;
            case 2:
                buscarCuentaPorNumero();
                break;
            case 3:
                verCuentasCliente();
                break;
            case 4:
                return;
            default:
                System.out.println("❌ Opción inválida.");
        }
    }

    // ============================================
    // FUNCIÓN: Crear cuenta
    // ============================================
    /**
     * IMPORTANTE: Aquí ves la diferencia entre CREAR y AGREGAR
     *
     * PASO 1: Buscar el cliente (debe existir)
     * PASO 2: CREAR la cuenta (new CuentaBancaria)
     * PASO 3: AGREGAR la cuenta al cliente (cliente.agregarCuenta)
     */
    private static void crearCuenta() {
        System.out.println("\n--- CREAR NUEVA CUENTA ---");

        // PASO 1: Buscar el cliente
        System.out.print("ID del cliente: ");
        String idCliente = scanner.next();

        Cliente cliente = banco.buscarCliente(idCliente);

        if(cliente == null) {
            System.out.println("❌ Error: No existe cliente con ID " + idCliente);
            return;
        }

        // PASO 2: Capturar datos de la cuenta
        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.next();

        // Validar que no exista
        if(banco.existeCuenta(numeroCuenta)) {
            System.out.println("❌ Error: Ya existe una cuenta con ese número.");
            return;
        }

        System.out.print("Tipo de cuenta (Ahorros/Corriente): ");
        String tipo = scanner.next();

        System.out.print("Saldo inicial (mínimo $50,000): ");
        double saldoInicial = leerDouble();

        // Validar saldo mínimo
        if(saldoInicial < 50000) {
            System.out.println("❌ Error: El saldo inicial mínimo es $50,000");
            return;
        }

        // PASO 3: CREAR la cuenta (constructor)
        CuentaBancaria nuevaCuenta = new CuentaBancaria(
                numeroCuenta,
                cliente.getNombre(),
                saldoInicial,
                tipo
        );

        // PASO 4: AGREGAR la cuenta al cliente
        cliente.agregarCuenta(nuevaCuenta);
    }

    // ============================================
    // FUNCIÓN: Buscar cuenta
    // ============================================
    private static void buscarCuentaPorNumero() {
        System.out.print("\nNúmero de cuenta: ");
        String numero = scanner.next();

        CuentaBancaria cuenta = banco.buscarCuentaGlobal(numero);

        if(cuenta != null) {
            cuenta.consultarSaldo();
        } else {
            System.out.println("❌ No se encontró la cuenta " + numero);
        }
    }

    // ============================================
    // FUNCIÓN: Ver cuentas de un cliente
    // ============================================
    private static void verCuentasCliente() {
        System.out.print("\nID del cliente: ");
        String id = scanner.next();

        Cliente cliente = banco.buscarCliente(id);

        if(cliente != null) {
            cliente.listarCuentas();
        } else {
            System.out.println("❌ No se encontró cliente con ID: " + id);
        }
    }

    // ============================================
    // SUBMENÚ: OPERACIONES
    // ============================================
    private static void menuOperaciones() {
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║            💵 OPERACIONES 💵                           ║");
        System.out.println("╠═══════════════════════════════════════════════════════╣");
        System.out.println("║  1. Depositar                                         ║");
        System.out.println("║  2. Retirar                                           ║");
        System.out.println("║  3. Transferir                                        ║");
        System.out.println("║  4. Consultar saldo                                   ║");
        System.out.println("║  5. Volver al menú principal                          ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();

        switch(opcion) {
            case 1:
                depositar();
                break;
            case 2:
                retirar();
                break;
            case 3:
                transferir();
                break;
            case 4:
                consultarSaldo();
                break;
            case 5:
                return;
            default:
                System.out.println("❌ Opción inválida.");
        }
    }

    // ============================================
    // FUNCIÓN: Depositar
    // ============================================
    private static void depositar() {
        System.out.print("\nNúmero de cuenta: ");
        String numero = scanner.next();

        CuentaBancaria cuenta = banco.buscarCuentaGlobal(numero);

        if(cuenta == null) {
            System.out.println("❌ Cuenta no encontrada.");
            return;
        }

        System.out.print("Monto a depositar: $");
        double monto = leerDouble();

        // El método depositar() hace toda la validación
        cuenta.depositar(monto);
    }

    // ============================================
    // FUNCIÓN: Retirar
    // ============================================
    /**
     * NOTA: Aquí ves el uso del while para reintentar.
     * El while está en el MAIN, no en la clase.
     */
    private static void retirar() {
        System.out.print("\nNúmero de cuenta: ");
        String numero = scanner.next();

        CuentaBancaria cuenta = banco.buscarCuentaGlobal(numero);

        if(cuenta == null) {
            System.out.println("❌ Cuenta no encontrada.");
            return;
        }

        // Permitir hasta 3 intentos
        int intentos = 0;
        boolean exito = false;

        while(intentos < 3 && !exito) {
            System.out.print("Monto a retirar: $");
            double monto = leerDouble();

            // El método retirar() retorna true si tuvo éxito
            exito = cuenta.retirar(monto);

            if(!exito) {
                intentos++;
                if(intentos < 3) {
                    System.out.println("Intente nuevamente. Intentos restantes: " + (3 - intentos));
                }
            }
        }

        if(!exito) {
            System.out.println("❌ Máximo de intentos alcanzado.");
        }
    }

    // ============================================
    // FUNCIÓN: Transferir
    // ============================================
    /**
     * NOTA: Aquí ves cómo se usa el método del Banco
     * que busca ambas cuentas y realiza la transferencia.
     */
    private static void transferir() {
        System.out.println("\n--- TRANSFERENCIA ---");

        System.out.print("Cuenta origen: ");
        String origen = scanner.next();

        System.out.print("Cuenta destino: ");
        String destino = scanner.next();

        System.out.print("Monto: $");
        double monto = leerDouble();

        // El banco maneja toda la lógica de la transferencia
        banco.realizarTransferencia(origen, destino, monto);
    }

    // ============================================
    // FUNCIÓN: Consultar saldo
    // ============================================
    private static void consultarSaldo() {
        System.out.print("\nNúmero de cuenta: ");
        String numero = scanner.next();

        CuentaBancaria cuenta = banco.buscarCuentaGlobal(numero);

        if(cuenta != null) {
            cuenta.consultarSaldo();
        } else {
            System.out.println("❌ Cuenta no encontrada.");
        }
    }

    // ============================================
    // SUBMENÚ: REPORTES
    // ============================================
    private static void menuReportes() {
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║            📊 REPORTES 📊                              ║");
        System.out.println("╠═══════════════════════════════════════════════════════╣");
        System.out.println("║  1. Reporte general del banco                         ║");
        System.out.println("║  2. Historial de una cuenta                           ║");
        System.out.println("║  3. Volver al menú principal                          ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();

        switch(opcion) {
            case 1:
                banco.generarReporte();
                break;
            case 2:
                mostrarHistorial();
                break;
            case 3:
                return;
            default:
                System.out.println("❌ Opción inválida.");
        }
    }

    // ============================================
    // FUNCIÓN: Mostrar historial
    // ============================================
    private static void mostrarHistorial() {
        System.out.print("\nNúmero de cuenta: ");
        String numero = scanner.next();

        CuentaBancaria cuenta = banco.buscarCuentaGlobal(numero);

        if(cuenta != null) {
            cuenta.mostrarHistorial();
        } else {
            System.out.println("❌ Cuenta no encontrada.");
        }
    }

    // ============================================
    // FUNCIONES AUXILIARES
    // ============================================
    /**
     * Lee un entero manejando errores.
     */
    private static int leerEntero() {
        while(!scanner.hasNextInt()) {
            System.out.print("❌ Debe ingresar un número entero: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    /**
     * Lee un double manejando errores.
     */
    private static double leerDouble() {
        while(!scanner.hasNextDouble()) {
            System.out.print("❌ Debe ingresar un número válido: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}