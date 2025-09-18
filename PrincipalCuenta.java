import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalCuenta {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Cuenta> cuentas = new ArrayList<>();
        int actual = -1; // índice de la cuenta seleccionada

        System.out.println("======================================");
        System.out.println("      CLI de Prueba - Clase Cuenta");
        System.out.println("======================================");

        boolean salir = false;
        while (!salir) {
            System.out.println("\nMenú principal");
            System.out.println("1) Crear cuenta");
            System.out.println("2) Conocer la cantidad de cuentas creadas");
            System.out.println("3) Listar cuentas");
            System.out.println("4) Seleccionar cuenta actual");
            System.out.println("5) Depositar");
            System.out.println("6) Retirar");
            System.out.println("7) Consultar saldo");
            System.out.println("8) Consultar Estado (toString)");
            System.out.println("9) Agregar nombre a cuentas sin nombre");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();

            switch (op) {
                                case "1": { // Crear cuenta
                    Cuenta c;

                    System.out.print("Ingrese el nombre de la cuenta (enter para dejar en blanco): ");
                    String nombre = sc.nextLine().trim();

                    System.out.print("Ingrese el saldo inicial: ");
                    String lineaSaldo = sc.nextLine().trim();
                    double pSaldo;
                    try {
                        pSaldo = Double.parseDouble(lineaSaldo);
                    } catch (NumberFormatException e) {
                        System.out.println("Número inválido, se usará 0.0");
                        pSaldo = 0.0;
                    }

                    if (nombre.isEmpty()) {
                        System.out.print("Se ha creado la cuenta con saldo. ¿Desea indicar el nombre del cuentahabiente ahora? (s/n): ");
                        String respuesta = sc.nextLine().trim();
                        if (respuesta.equalsIgnoreCase("s")) {
                            System.out.print("Ingrese el nombre: ");
                            String nombreHabiente = sc.nextLine().trim();
                            c = new Cuenta(nombreHabiente, pSaldo);
                        } else if (respuesta.equalsIgnoreCase("n")) {
                            System.out.println("No se asignará un nombre. La cuenta se creará sin nombre.");
                            c = new Cuenta(pSaldo);
                        } else {
                            System.out.println("Respuesta inválida. La cuenta no se creará.");
                            break;
                        }
                    } else {
                        c = new Cuenta(nombre, pSaldo);
                    }

                    cuentas.add(c);
                    actual = cuentas.size() - 1;
                    System.out.println("Cuenta creada y seleccionada (índice " + actual + ").");
                    System.out.println(cuentas.get(actual).toString());
                    break;
                }
                case "2": { //Cantidad cuentas creadas
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear una cuenta primero.");
                        break;
                    }
                    System.out.println(cuentas.get(actual).getCantCuentasCreadas());
                    break;
                }
                case "3": { // Listar cuentas
                    if (cuentas.isEmpty()) {
                        System.out.println("No hay cuentas creadas.");
                    } else {
                        System.out.println("Índice | Código cuenta | Saldo ");
                        for (int i = 0; i < cuentas.size(); i++) {
                            Cuenta c = cuentas.get(i);
                            System.out.printf("  %d    | %s | %.2f%n",
                                    i,
                                    c.getCodCuenta(),
                                    c.getSaldo());
                        }
                    }
                    break;
                }
                case "4": { // Seleccionar
                    if (cuentas.isEmpty()) {
                        System.out.println("Cree una cuenta primero.");
                        break;
                    }
                    System.out.print("Índice de la cuenta a seleccionar: ");
                    String idxS = sc.nextLine().trim();
                    try {
                        int idx = Integer.parseInt(idxS);
                        if (idx >= 0 && idx < cuentas.size()) {
                            actual = idx;
                            System.out.println("Cuenta índice " + actual + " seleccionado.");
                        } else {
                            System.out.println("Índice fuera de rango.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Índice inválido.");
                    }
                    break;
                }
                case "5": { // Depositar
                    if (actual == -1) {
                        System.out.println("Error: No hay una cuenta seleccionada.");
                        break;
                    }
                    System.out.print("Ingrese el monto a depositar: ");
                    try {
                        double monto = Double.parseDouble(sc.nextLine());
                        if (monto > 0) {
                            double nuevoSaldo = cuentas.get(actual).depositar(monto);
                            System.out.println("Depósito exitoso. Nuevo saldo: " + nuevoSaldo);
                        } else {
                            System.out.println("Monto inválido. El monto debe ser mayor a 0.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Monto inválido. Intente de nuevo.");
                    }
                    break;
                }
                case "6": { // Retirar
                    if (actual == -1) {
                        System.out.println("Error: No hay una cuenta seleccionada.");
                        break;
                    }
                    System.out.print("Ingrese el monto a retirar: ");
                    try {
                        double monto = Double.parseDouble(sc.nextLine());
                        double saldoInicial = cuentas.get(actual).getSaldo();
                        double nuevoSaldo = cuentas.get(actual).retirar(monto);
                
                        if (nuevoSaldo == saldoInicial) {
                            System.out.println("Retiro fallido. Fondos insuficientes o monto inválido.");
                        } else {
                            System.out.println("Retiro exitoso. Nuevo saldo: " + nuevoSaldo);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Monto inválido. Intente de nuevo.");
                    }
                    break;
                }
                case "7": { // Consultar saldo
                    if (actual == -1) {
                        System.out.println("Error: No hay una cuenta seleccionada.");
                        break;
                    }
                    System.out.println("Saldo actual: " + cuentas.get(actual).getSaldo());
                    break;
                }
                case "8": { // toString
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }
                    System.out.println(cuentas.get(actual).toString());
                    break;
                }
                case "9": { // Agregar nombre
                    if (actual == -1) {
                        System.out.println("Error: No hay una cuenta seleccionada.");
                        break;
                    }
                    Cuenta cuentaSeleccionada = cuentas.get(actual);
                    
                    if (cuentaSeleccionada.getNombreCuentaHabiente() == null || cuentaSeleccionada.getNombreCuentaHabiente().isEmpty()) {
                        System.out.print("Ingrese el nombre para la cuenta: ");
                        String nuevoNombre = sc.nextLine().trim();

                        if (nuevoNombre.isEmpty()) {
                            System.out.println("El nombre no puede estar vacío. Operación cancelada.");
                        } else {
                            cuentaSeleccionada.setNombreCuentaHabiente(nuevoNombre);
                            System.out.println("Nombre de la cuenta agregado exitosamente.");
                        }
                    } else {
                        System.out.println("La cuenta seleccionada ya tiene un nombre. Operación cancelada.");
                    }
                    break;
                }
                case "0": {
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;
                }
                default:
                    System.out.println("Opción inválida.");
            }
        }
        sc.close();
    }
}