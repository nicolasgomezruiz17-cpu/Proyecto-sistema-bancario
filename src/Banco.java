import java.util.ArrayList;

/**
 * CLASE BANCO
 *
 * Representa el banco completo con TODOS sus clientes.
 *
 * RESPONSABILIDADES:
 * - Administrar todos los clientes del banco
 * - Buscar clientes específicos
 * - Realizar transferencias entre cuentas de diferentes clientes
 * - Generar reportes generales
 * - Validar números de cuenta únicos
 */
public class Banco {
    // ============================================
    // ATRIBUTOS
    // ============================================
    private String nombre;
    private ArrayList<Cliente> clientes;

    // ============================================
    // CONSTRUCTOR
    // ============================================
    /**
     * Crea un banco vacío (sin clientes al inicio).
     *
     * Los clientes se agregan después con registrarCliente()
     */
    public Banco(String nombre) {
        this.nombre = nombre;
        this.clientes = new ArrayList<>();
    }

    // ============================================
    // GETTER
    // ============================================
    public String getNombre() {
        return nombre;
    }

    // ============================================
    // MÉTODO: Registrar cliente
    // ============================================
    /**
     * Agrega un nuevo cliente al banco.
     *
     * VALIDACIONES:
     * - El cliente no puede ser null
     * - No puede haber duplicados (mismo ID)
     */
    public void registrarCliente(Cliente cliente) {
        // Validación 1: Cliente existe
        if(cliente == null) {
            System.out.println("❌ Error: No se puede registrar un cliente nulo.");
            return;
        }

        // Validación 2: No hay duplicados
        for(Cliente c : clientes) {
            if(c.getId().equals(cliente.getId())) {
                System.out.println("❌ Error: Ya existe un cliente con ID " + cliente.getId());
                return;
            }
        }

        // Si pasa las validaciones, registra el cliente
        clientes.add(cliente);
        System.out.println("✅ Cliente " + cliente.getNombre() + " registrado exitosamente.");
    }

    // ============================================
    // MÉTODO: Buscar cliente por ID
    // ============================================
    /**
     * Busca un cliente específico dentro del ArrayList del banco.
     *
     * @param id - El ID del cliente a buscar
     * @return El cliente si lo encuentra, null si no existe
     *
     * RETORNA Cliente porque quien llama necesita el objeto
     * para trabajar con él.
     */
    public Cliente buscarCliente(String id) {
        for(Cliente cliente : clientes) {
            if(cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }

    // ============================================
    // MÉTODO: Buscar cuenta en TODO el banco
    // ============================================
    /**
     * Busca una cuenta por su número en TODOS los clientes del banco.
     *
     * Esto es útil para transferencias entre clientes diferentes.
     *
     * @param numeroCuenta - El número de cuenta a buscar
     * @return La cuenta si la encuentra, null si no existe
     */
    public CuentaBancaria buscarCuentaGlobal(String numeroCuenta) {
        // Recorre TODOS los clientes
        for(Cliente cliente : clientes) {
            // Busca la cuenta en cada cliente
            CuentaBancaria cuenta = cliente.buscarCuenta(numeroCuenta);

            if(cuenta != null) {
                return cuenta; // ¡Encontrada!
            }
        }

        return null; // No existe en ningún cliente
    }

    // ============================================
    // MÉTODO: Validar número de cuenta único
    // ============================================
    /**
     * Verifica si un número de cuenta ya existe en el banco.
     *
     * Útil antes de crear una cuenta nueva.
     *
     * @param numeroCuenta - El número a validar
     * @return true si ya existe, false si está disponible
     */
    public boolean existeCuenta(String numeroCuenta) {
        return buscarCuentaGlobal(numeroCuenta) != null;
    }

    // ============================================
    // MÉTODO: Realizar transferencia global
    // ============================================
    /**
     * Realiza una transferencia entre DOS cuentas cualesquiera del banco.
     *
     * Estas cuentas pueden pertenecer a clientes diferentes.
     *
     * @param numeroCuentaOrigen - Número de la cuenta que envía dinero
     * @param numeroCuentaDestino - Número de la cuenta que recibe dinero
     * @param monto - Cantidad a transferir
     * @return true si tuvo éxito, false si falló
     */
    public boolean realizarTransferencia(String numeroCuentaOrigen, String numeroCuentaDestino, double monto) {
        // Buscar ambas cuentas en TODO el banco
        CuentaBancaria cuentaOrigen = buscarCuentaGlobal(numeroCuentaOrigen);
        CuentaBancaria cuentaDestino = buscarCuentaGlobal(numeroCuentaDestino);

        // Validación 1: Ambas cuentas existen
        if(cuentaOrigen == null) {
            System.out.println("❌ Error: La cuenta origen " + numeroCuentaOrigen + " no existe.");
            return false;
        }

        if(cuentaDestino == null) {
            System.out.println("❌ Error: La cuenta destino " + numeroCuentaDestino + " no existe.");
            return false;
        }

        // Validación 2: No son la misma cuenta
        if(numeroCuentaOrigen.equals(numeroCuentaDestino)) {
            System.out.println("❌ Error: No puedes transferir a la misma cuenta.");
            return false;
        }

        // Realizar la transferencia (el método de CuentaBancaria hace las demás validaciones)
        return cuentaOrigen.transferir(cuentaDestino, monto);
    }

    // ============================================
    // MÉTODO: Listar todos los clientes
    // ============================================
    /**
     * Muestra un resumen de TODOS los clientes del banco.
     */
    public void listarClientes() {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  🏦 CLIENTES DEL BANCO: " + nombre);
        System.out.println("═══════════════════════════════════════════════════════");

        if(clientes.isEmpty()) {
            System.out.println("El banco no tiene clientes registrados.");
        } else {
            int contador = 1;
            for(Cliente cliente : clientes) {
                System.out.println("\n--- Cliente #" + contador + " ---");
                System.out.println("ID: " + cliente.getId());
                System.out.println("Nombre: " + cliente.getNombre());
                System.out.println("Email: " + cliente.getEmail());
                System.out.println("Teléfono: " + cliente.getTelefono());
                contador++;
            }
        }

        System.out.println("\nTotal de clientes: " + clientes.size());
        System.out.println("═══════════════════════════════════════════════════════");
    }

    // ============================================
    // MÉTODO: Generar reporte general
    // ============================================
    /**
     * Genera un reporte completo con estadísticas del banco.
     */
    public void generarReporte() {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  📊 REPORTE GENERAL DEL BANCO");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("Nombre del banco: " + nombre);
        System.out.println("Total de clientes: " + clientes.size());

        // Calcular estadísticas
        int totalCuentas = 0;
        double totalDinero = 0;

        for(Cliente cliente : clientes) {
            totalCuentas += cliente.buscarCuenta("") != null ? 1 : 0; // Contar cuentas
            totalDinero += cliente.totalSaldos();
        }

        System.out.println("Total de dinero en el banco: $" + String.format("%.2f", totalDinero));
        System.out.println("\n--- DETALLE POR CLIENTE ---");

        for(Cliente cliente : clientes) {
            System.out.println("\n" + cliente.getNombre() + " (ID: " + cliente.getId() + ")");
            System.out.println("  Total en cuentas: $" + String.format("%.2f", cliente.totalSaldos()));
        }

        System.out.println("═══════════════════════════════════════════════════════");
    }
}