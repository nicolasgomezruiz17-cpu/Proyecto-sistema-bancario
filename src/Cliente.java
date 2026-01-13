import java.util.ArrayList;

/**
 * CLASE CLIENTE
 *
 * Representa UN cliente del banco.
 * Un cliente puede tener MÚLTIPLES cuentas bancarias.
 *
 * RESPONSABILIDADES:
 * - Guardar información personal del cliente
 * - Administrar las cuentas que tiene
 * - Buscar cuentas específicas
 * - Calcular total de dinero en todas sus cuentas
 */
public class Cliente {
    // ============================================
    // ATRIBUTOS
    // ============================================
    private String id;
    private String nombre;
    private String telefono;
    private String email;
    private ArrayList<CuentaBancaria> cuentas; // Lista de cuentas del cliente

    // ============================================
    // CONSTRUCTOR
    // ============================================
    /**
     * Se ejecuta cuando haces: new Cliente(...)
     *
     * IMPORTANTE: Cuando se crea un cliente, NO tiene cuentas todavía.
     * El ArrayList se crea vacío.
     *
     * Las cuentas se agregan DESPUÉS con agregarCuenta()
     */
    public Cliente(String id, String nombre, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.cuentas = new ArrayList<>(); // Inicializa vacío
    }

    // ============================================
    // GETTERS
    // ============================================
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    // ============================================
    // SETTERS (Solo para datos que pueden cambiar)
    // ============================================
    /**
     * El ID NO tiene setter porque NO debe cambiar.
     * El nombre tampoco (aunque podría tenerlo si cambia legalmente).
     *
     * Teléfono y email SÍ pueden cambiar.
     */
    public void setTelefono(String telefono) {
        // Validación: 10 dígitos
        if(telefono.length() == 10 && telefono.matches("[0-9]+")) {
            this.telefono = telefono;
            System.out.println("✅ Teléfono actualizado exitosamente.");
        } else {
            System.out.println("❌ Error: El teléfono debe tener 10 dígitos numéricos.");
        }
    }

    public void setEmail(String email) {
        // Validación: Debe contener @
        if(email.contains("@")) {
            this.email = email;
            System.out.println("✅ Email actualizado exitosamente.");
        } else {
            System.out.println("❌ Error: Email inválido (debe contener @).");
        }
    }

    // ============================================
    // MÉTODO: Agregar cuenta
    // ============================================
    /**
     * Agrega una cuenta bancaria al ArrayList de este cliente.
     *
     * IMPORTANTE: La cuenta ya debe estar CREADA antes de llamar este método.
     *
     * Ejemplo de uso:
     * CuentaBancaria cuenta = new CuentaBancaria("001", "Juan", 50000, "Ahorros");
     * cliente.agregarCuenta(cuenta);
     *
     * VALIDACIONES:
     * - La cuenta no puede ser null
     * - No puede haber duplicados (mismo número de cuenta)
     */
    public void agregarCuenta(CuentaBancaria cuenta) {
        // Validación 1: La cuenta existe
        if(cuenta == null) {
            System.out.println("❌ Error: No se puede agregar una cuenta nula.");
            return;
        }

        // Validación 2: No hay duplicados
        for(CuentaBancaria c : cuentas) {
            if(c.getNumeroCuenta().equals(cuenta.getNumeroCuenta())) {
                System.out.println("❌ Error: Esta cuenta ya está asociada al cliente.");
                return;
            }
        }

        // Si pasa las validaciones, agrega la cuenta
        cuentas.add(cuenta);
        System.out.println("✅ Cuenta " + cuenta.getNumeroCuenta() + " agregada exitosamente a " + nombre);
    }

    // ============================================
    // MÉTODO: Listar cuentas
    // ============================================
    /**
     * Muestra TODAS las cuentas que tiene este cliente.
     *
     * No retorna nada (void), solo imprime en pantalla.
     */
    public void listarCuentas() {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  👤 CUENTAS DEL CLIENTE: " + nombre);
        System.out.println("═══════════════════════════════════════════════════════");

        if(cuentas.isEmpty()) {
            System.out.println("Este cliente no tiene cuentas registradas.");
        } else {
            int contador = 1;
            for(CuentaBancaria cuenta : cuentas) {
                System.out.println("\n--- Cuenta #" + contador + " ---");
                cuenta.mostrarInfo();
                contador++;
            }
        }

        System.out.println("═══════════════════════════════════════════════════════");
    }

    // ============================================
    // MÉTODO: Buscar cuenta por número
    // ============================================
    /**
     * Busca una cuenta específica dentro del ArrayList de este cliente.
     *
     * @param numeroCuenta - El número de cuenta a buscar
     * @return La cuenta si la encuentra, null si no existe
     *
     * RETORNA CuentaBancaria porque el que llama este método necesita
     * la cuenta para hacer operaciones con ella.
     */
    public CuentaBancaria buscarCuenta(String numeroCuenta) {
        // Recorre TODAS las cuentas del cliente
        for(CuentaBancaria cuenta : cuentas) {
            // Compara el número de cada cuenta
            if(cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta; // ¡Encontrada! Retorna la cuenta
            }
        }

        // Si llegó aquí, no la encontró
        return null;
    }

    // ============================================
    // MÉTODO: Total de saldos
    // ============================================
    /**
     * Calcula la SUMA de todos los saldos de todas las cuentas del cliente.
     *
     * RETORNA double porque el que llama necesita el número para mostrarlo.
     *
     * Ejemplo:
     * Cuenta 1: $100,000
     * Cuenta 2: $50,000
     * Total: $150,000
     */
    public double totalSaldos() {
        double total = 0;

        // Suma el saldo de cada cuenta
        for(CuentaBancaria cuenta : cuentas) {
            total += cuenta.getSaldo();
        }

        return total;
    }

    // ============================================
    // MÉTODO: Mostrar información completa
    // ============================================
    /**
     * Muestra toda la info del cliente incluyendo sus cuentas.
     */
    public void mostrarInfoCompleta() {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  📋 INFORMACIÓN DEL CLIENTE");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Email: " + email);
        System.out.println("Cantidad de cuentas: " + cuentas.size());
        System.out.println("Total en todas las cuentas: $" + String.format("%.2f", totalSaldos()));
        System.out.println("═══════════════════════════════════════════════════════");
    }
}