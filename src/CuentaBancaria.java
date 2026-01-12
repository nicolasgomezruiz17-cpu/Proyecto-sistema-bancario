import java.util.ArrayList;

public class CuentaBancaria {
    String numeroCuenta;
    String titular;
    double saldo = 0;
    String tipoCuenta;
    ArrayList<String> historialTransacciones = new ArrayList<>();
    ArrayList<String> datosCuenta = new ArrayList<>();
    int contadorDepositos = 1;
    int contadorRetiros =1;
    public void crearCuentaBancaria(String numeroCuenta, String titular, String tipoCuenta){
        datosCuenta.add("Número de cuenta: " + numeroCuenta + "\n" + "Titular: " + titular + "\n" + "Tipo de cuenta: " + tipoCuenta);
    }

    public void depositar(double montoDepositado){
        saldo += montoDepositado;
        historialTransacciones.add("# " +contadorDepositos+ "Haz hecho un deposito de "+ montoDepositado);
        contadorDepositos++;

    }

    public void mostrarCuenta(String titular) {
        if (datosCuenta.isEmpty()) {
            System.out.println("Este cliente no tiene cuentas registradas.");
            return;
        }

        System.out.println("=== Cuentas de " + titular + " ===");
        boolean encontro = false;

        for (int i = 0; i < datosCuenta.size(); i++) {
            // Obtenemos el texto de la cuenta actual
            String infoCuenta = datosCuenta.get(i);

            // Verificamos si el nombre del titular está dentro de ese texto
            if (infoCuenta.contains(titular)) {
                System.out.println(infoCuenta);
                encontro = true;
            }
        }

        if (!encontro) {
            System.out.println("No se encontró ninguna cuenta para el titular: " + titular);
        }
    }
    public void retirar(double monto){
        if (monto < saldo){
            System.out.println("No puedes retirar saldo insuficiente");
        }else{
            saldo -= monto;
            historialTransacciones.add("# " +contadorRetiros +" Haz hecho un Retiro de "+ monto);
            contadorRetiros++;
        }
    }
    public void consultarSaldo(){
        System.out.println("*Tu saldo es: "+ saldo+" *");
    }

    public void historial(){
        System.out.println("===============================");
        System.out.println("|| Este ha sido tu historial ||");
        System.out.println("===============================");
        for (int i =0; i<historialTransacciones.size(); i++){
            System.out.println(historialTransacciones.get(i)+"\n");
        }

    }
    public void transferir(String titular, String numeroCuenta, double montoTransferir){


    }
}
