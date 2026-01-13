import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * CLASE CUENTABANCARIA
 *
 * Representa UNA cuenta bancaria individual.
 * Cada cuenta tiene: número, titular, saldo, tipo e historial.
 *
 * RESPONSABILIDADES:
 * - Guardar información de la cuenta
 * - Realizar operaciones (depositar, retirar)
 * - Llevar historial de transacciones
 */
public class CuentaBancaria {
    // ============================================
    // ATRIBUTOS (Datos que guarda cada cuenta)
    // ============================================
    private String numeroCuenta;
    private String titular;
    private double saldo;
    private String tipoCuenta; // "Ahorros" o "Corriente"
    private ArrayList<String> historialTransacciones;

    // ============================================
    // CONSTRUCTOR
    // ============================================
    /**
     * Se ejecuta automáticamente cuando haces: new CuentaBancaria(...)
     *
     * Propósito: Inicializar (dar valores iniciales) a todos los atributos
     *
     * @param numeroCuenta - El número único de la cuenta
     * @param titular - Nombre del dueño
     * @param saldoInicial - Cuánto dinero tiene al inicio
     * @param tipo - "Ahorros" o "Corriente"
     */
    public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial, String tipo) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.tipoCuenta = tipo;
        this.historialTransacciones = new ArrayList<>();

        // Registrar la apertura de cuenta en el historial
        registrarTransaccion("Apertura de cuenta - Saldo inicial: $" + saldoInicial);
    }

    // ============================================
    // GETTERS (Para leer atributos privados)
    // ============================================
    /**
     * ¿Por qué existen?
     * Los atributos son PRIVADOS, entonces desde fuera no puedes hacer:
     * cuenta.numeroCuenta ❌
     *
     * Pero SÍ puedes hacer:
     * cuenta.getNumeroCuenta() ✅
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    // ============================================
    // MÉTODO PRIVADO: Registrar transacción
    // ============================================
    /**
     * Este método es PRIVADO porque solo lo usa esta clase internamente.
     * No queremos que desde afuera alguien agregue transacciones falsas.
     *
     * Formato: "15/01/2025 14:30 - Descripción"
     */
    private void registrarTransaccion(String descripcion) {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fecha = ahora.format(formato);

        String registro = fecha + " - " + descripcion + " - Saldo: $" + String.format("%.2f", saldo);
        historialTransacciones.add(registro);
    }

    // ============================================
    // OPERACIÓN: Depositar
    // ============================================
    /**
     * Depositar dinero en la cuenta.
     *
     * VALIDACIONES:
     * - El monto debe ser positivo
     *
     * RETORNA: void (no devuelve nada, solo imprime mensajes)
     */
    public void depositar(double monto) {
        // Validación
        if(monto <= 0) {
            System.out.println("❌ Error: El monto debe ser mayor a cero.");
            return; // Sale del método sin hacer nada más
        }

        // Si pasa la validación, realiza el depósito
        saldo += monto;
        registrarTransaccion("Depósito: +$" + String.format("%.2f", monto));
        System.out.println("✅ Depósito exitoso. Nuevo saldo: $" + String.format("%.2f", saldo));
    }

    // ============================================
    // OPERACIÓN: Retirar
    // ============================================
    /**
     * Retirar dinero de la cuenta.
     *
     * VALIDACIONES:
     * - El monto debe ser positivo
     * - El monto no puede ser mayor al saldo (no permite quedar en negativo)
     *
     * RETORNA: boolean (true si tuvo éxito, false si falló)
     * ¿Por qué boolean? Porque el main necesita SABER si funcionó o no
     */
    public boolean retirar(double monto) {
        // Validación 1: Monto positivo
        if(monto <= 0) {
            System.out.println("❌ Error: El monto debe ser mayor a cero.");
            return false;
        }

        // Validación 2: Saldo suficiente
        if(monto > saldo) {
            System.out.println("❌ Error: Saldo insuficiente. Saldo actual: $" + String.format("%.2f", saldo));
            return false;
        }

        // Si pasa las validaciones, realiza el retiro
        saldo -= monto;
        registrarTransaccion("Retiro: -$" + String.format("%.2f", monto));
        System.out.println("✅ Retiro exitoso. Nuevo saldo: $" + String.format("%.2f", saldo));
        return true;
    }

    // ============================================
    // OPERACIÓN: Transferir a otra cuenta
    // ============================================
    /**
     * Transferir dinero de ESTA cuenta a OTRA cuenta.
     *
     * @param destino - La cuenta que recibirá el dinero
     * @param monto - Cuánto dinero transferir
     *
     * RETORNA: boolean (éxito o fallo)
     */
    public boolean transferir(CuentaBancaria destino, double monto) {
        // Validación 1: Monto positivo
        if(monto <= 0) {
            System.out.println("❌ Error: El monto debe ser mayor a cero.");
            return false;
        }

        // Validación 2: Saldo suficiente
        if(monto > this.saldo) {
            System.out.println("❌ Error: Saldo insuficiente para transferir.");
            return false;
        }

        // Validación 3: La cuenta destino existe
        if(destino == null) {
            System.out.println("❌ Error: La cuenta destino no existe.");
            return false;
        }

        // Realizar la transferencia
        this.saldo -= monto; // Resta de esta cuenta
        destino.saldo += monto; // Suma a la cuenta destino

        // Registrar en ambas cuentas
        this.registrarTransaccion("Transferencia enviada a " + destino.numeroCuenta + ": -$" + String.format("%.2f", monto));
        destino.registrarTransaccion("Transferencia recibida de " + this.numeroCuenta + ": +$" + String.format("%.2f", monto));

        System.out.println("✅ Transferencia exitosa.");
        return true;
    }

    // ============================================
    // CONSULTAR: Mostrar saldo
    // ============================================
    /**
     * Muestra el saldo actual en pantalla.
     * No retorna nada (void), solo imprime.
     */
    public void consultarSaldo() {
        System.out.println("═══════════════════════════════");
        System.out.println("  💰 CONSULTA DE SALDO");
        System.out.println("═══════════════════════════════");
        System.out.println("Cuenta: " + numeroCuenta);
        System.out.println("Titular: " + titular);
        System.out.println("Tipo: " + tipoCuenta);
        System.out.println("Saldo actual: $" + String.format("%.2f", saldo));
        System.out.println("═══════════════════════════════");
    }

    // ============================================
    // CONSULTAR: Mostrar historial
    // ============================================
    /**
     * Muestra las últimas 10 transacciones (o todas si hay menos de 10).
     */
    public void mostrarHistorial() {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  📜 HISTORIAL DE TRANSACCIONES - Cuenta: " + numeroCuenta);
        System.out.println("═══════════════════════════════════════════════════════════");

        if(historialTransacciones.isEmpty()) {
            System.out.println("No hay transacciones registradas.");
        } else {
            // Mostrar últimas 10 transacciones
            int inicio = Math.max(0, historialTransacciones.size() - 10);

            for(int i = inicio; i < historialTransacciones.size(); i++) {
                System.out.println(historialTransacciones.get(i));
            }
        }

        System.out.println("═══════════════════════════════════════════════════════════");
    }

    // ============================================
    // MÉTODO PARA MOSTRAR INFO COMPLETA
    // ============================================
    /**
     * Muestra toda la información de la cuenta de forma ordenada.
     */
    public void mostrarInfo() {
        System.out.println("─────────────────────────────────");
        System.out.println("  Número: " + numeroCuenta);
        System.out.println("  Titular: " + titular);
        System.out.println("  Tipo: " + tipoCuenta);
        System.out.println("  Saldo: $" + String.format("%.2f", saldo));
        System.out.println("─────────────────────────────────");
    }
}