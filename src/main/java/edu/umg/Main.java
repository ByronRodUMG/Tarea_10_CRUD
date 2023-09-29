package edu.umg;

import edu.umg.connection.Conexion;

import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Conexion cone = new Conexion();
        Scanner sc = new Scanner(System.in);

        while (true) { // Loop infinito
            System.out.println("###      Menú Principal:     ###");
            System.out.println("# 1. Leer datos de la DB       #");
            System.out.println("# 2. Insertar datos en la DB   #");
            System.out.println("# 3. Actualizar datos en la DB #");
            System.out.println("# 4. Eliminar datos de la DB   #");
            System.out.println("# 5. Salir del programa        #");
            System.out.print("\nSelecciona una opción: ");

            try {
                int seleccion = sc.nextInt();
                sc.nextLine();

                switch (seleccion) {
                    case 1:
                        cone.leerDatos();
                        cone.cerrarConexion();
                        esperarEnter(sc);
                        break;
                    case 2:
                        cone.insertarDatos();
                        cone.leerDatos();
                        cone.cerrarConexion();
                        esperarEnter(sc);
                        break;
                    case 3:
                        cone.leerDatos();
                        cone.actualizarDatos();
                        cone.leerDatos();
                        cone.cerrarConexion();
                        esperarEnter(sc);
                        break;
                    case 4:
                        cone.leerDatos();
                        cone.eliminarDatos();
                        cone.leerDatos();
                        cone.cerrarConexion();
                        esperarEnter(sc);
                        break;
                    case 5:
                        System.out.println("Cerrando el programa.");
                        System.exit(0); // Termina el programa
                    default:
                        System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingresa un número válido.");
                sc.nextLine();
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void esperarEnter(Scanner scanner) {
        System.out.println("Presiona Enter para continuar...");
        scanner.nextLine();
    }
}
