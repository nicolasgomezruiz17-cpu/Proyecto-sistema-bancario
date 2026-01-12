import java.util.ArrayList;

public class Cliente {
    private String id;
    private String nombre;
    String telefono;
    String mail;
    double total =0;
    ArrayList<String> clientes = new ArrayList<>();

    ArrayList<CuentaBancaria> cuentas = new ArrayList<>();


    public void crearCliente(String id, String nombre, String telefono, String mail){
        clientes.add("Id: " + id+ "\n" + "Nombre: " + nombre + "\n" + "Teléfono: " + telefono + "\n" + "Mail: " + mail);
    }

    public void agregarCuenta(CuentaBancaria cuenta){
        cuentas.add(cuenta);
    }

    public void mostrarCuentas(){
            if(cuentas.isEmpty()){
                System.out.println("Este cliente no tiene cuentas registradas.");
                return;
            }

            System.out.println("=== Cuentas de " + nombre + " ===");
            for (int i = 0; i < cuentas.size(); i++) {
                CuentaBancaria cuenta = cuentas.get(i);
                System.out.println("Cuenta #" + (i+1));
                System.out.println("  Número: " + cuenta.numeroCuenta);
                System.out.println("  Saldo: $" + cuenta.saldo);
                System.out.println("  Tipo: " + cuenta.tipoCuenta);
                System.out.println("-------------------");
            }
        }

    public void buscarCliente(String numeroCuenta){
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.contains(numeroCuenta)){
                CuentaBancaria cuenta = cuentas.get(i);
                System.out.println("Cuenta #" + (i+1));
                System.out.println("  Número: " + cuenta.numeroCuenta);
                System.out.println("  Saldo: $" + cuenta.saldo);
                System.out.println("  Tipo: " + cuenta.tipoCuenta);
                System.out.println("-------------------");
            }
        }
    }

    public Double totalSaldos(String numeroCuenta){
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.contains(numeroCuenta)){
                CuentaBancaria cuenta = cuentas.get(i);
                total += cuenta.saldo;

            }
        }
        return total;
    }

}
