import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entradaDatos = new Scanner(System.in);
        Cliente nuevoCliente = new Cliente();
        CuentaBancaria crearCuenta = new CuentaBancaria();
        boolean bandera = true;
        while (bandera){
            System.out.println("====================================");
            System.out.println("||  SISTEMA BANCARIO POO          ||");
            System.out.println("====================================");
            System.out.println("|| 1. GESTIÓN CLIENTES            ||");
            System.out.println("|| a. Registrar cliente           ||");
            System.out.println("|| b. Buscar cliente              ||");
            System.out.println("|| c. Mostrar todos los clientes  ||");
            System.out.println("|| =============================  ||");
            System.out.println("|| 2. GESTIÓN CUENTAS             ||");
            System.out.println("|| a. Crear cuenta                ||");
            System.out.println("|| b. Buscar cuenta               ||");
            System.out.println("|| c. Ver cuentas de un cliente   ||");
            System.out.println("|| =============================  ||");
            System.out.println("|| 3. OPERACIOENS                 ||");
            System.out.println("|| a. Depositar                   ||");
            System.out.println("|| b. Retirar                     ||");
            System.out.println("|| c. Transerir                   ||");
            System.out.println("|| d. Consultar Saldo             ||");
            System.out.println("|| =============================  ||");
            System.out.println("|| 4. REPORTES                    ||");
            System.out.println("|| a. Reporte General del banco   ||");
            System.out.println("|| b. Historial de cuenta         ||");
            System.out.println("|| =============================  ||");
            System.out.println("|| 5. SALIR                       ||");
            System.out.println("|| =============================  ||");
            System.out.println("|| =============================  ||");
            System.out.print("|| POR FAVOR SELECCIONE UNA OPCIÓN: ");
            var desicion = entradaDatos.nextInt();

            switch (desicion){
                case 1 :
                    System.out.println("====================================");
                    System.out.println("||       GESTIÓN CLIENTES         ||");
                    System.out.println("====================================");
                    System.out.println("|| 1. Registrar cliente           ||");
                    System.out.println("|| 2. Buscar cliente              ||");
                    System.out.println("|| 3. Mostrar todos los clientes  ||");
                    System.out.println("|| =============================  ||");
                    System.out.print("|| POR FAVOR SELECCIONE UNA OPCIÓN: ");
                    desicion = entradaDatos.nextInt();
                    switch (desicion){
                        case 1:
                            System.out.println("|| =============================  ||");
                            System.out.println("||      REGISTRAR CLIENTE         ||");
                            System.out.println("|| =============================  ||");
                            System.out.print("|| 1. ID: ");
                            var id = entradaDatos.next();
                            System.out.print("|| 2. Nombre: ");
                            var nombre = entradaDatos.next();
                            System.out.print("|| 3. Teléfono: ");
                            var telefono = entradaDatos.next();
                            System.out.print("|| 4. Mail: ");
                            var mail = entradaDatos.next();
                            nuevoCliente.crearCliente(id,nombre,telefono,mail);
                            System.out.println("|| ============================ ||");
                            System.out.println("|| CLIENTE REGISTRADO CON ÉXITO ||");
                            System.out.println("|| ============================ ||");
                            break;

                        case 2:
                            System.out.println("|| =============================  ||");
                            System.out.println("||        BUSCAR CLIENTE          ||");
                            System.out.println("|| =============================  ||");
                            System.out.print("|| Número de cuenta:  ");
                            var numeroCuenta = entradaDatos.next();
                            nuevoCliente.buscarCliente(numeroCuenta);
                            break;

                        case 3:
                            break;

                    }

                case 2 :
                    System.out.println("|| =============================  ||");
                    System.out.println("||       GESTIÓN CUENTAS          ||");
                    System.out.println("|| =============================  ||");
                    System.out.println("|| 1. Crear cuenta                ||");
                    System.out.println("|| 2. Buscar cuenta               ||");
                    System.out.println("|| 3. Ver cuentas de un cliente   ||");
                    System.out.println("|| =============================  ||");
                    System.out.print("|| POR FAVOR SELECCIONE UNA OPCIÓN: ");
                    desicion = entradaDatos.nextInt();
                    switch (desicion){
                        case 1:
                            System.out.println("|| =============================  ||");
                            System.out.println("||         CREAR CUENTA           ||");
                            System.out.println("|| =============================  ||");
                            System.out.print("|| 1. Número cuenta: ");
                            var numeroCuenta = entradaDatos.next();
                            System.out.print("|| 2. Titular: ");
                            var titular = entradaDatos.next();
                            System.out.print("|| 3. Tipo de cuenta: ");
                            var tipoCuenta = entradaDatos.next();
                            crearCuenta.crearCuentaBancaria(numeroCuenta, titular,tipoCuenta);
                            System.out.println("|| ============================ ||");
                            System.out.println("|| CLIENTE REGISTRADO CON ÉXITO ||");
                            System.out.println("|| ============================ ||");

                            break;

                        case 2:
                            System.out.println("|| =============================  ||");
                            System.out.println("||         BUSCAR CUENTA          ||");
                            System.out.println("|| =============================  ||");
                            System.out.print("|| 1. Titular: ");
                            titular = entradaDatos.next();
                            crearCuenta.mostrarCuenta(titular);
                            break;
                    }
                    break;

                case 3 :
                    break;

                case 4  :
                    break;

                case 5 :
                    break;

                default:
                    break;
            }
        }

        System.out.println("|| =============================  ||");
        System.out.println("|| GRACIAS POR USAR EL SISTEMA    ||");
        System.out.println("|| =============================  ||");

    }
}