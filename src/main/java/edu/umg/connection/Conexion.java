package edu.umg.connection;

import java.sql.*;
import java.util.Scanner;

public class Conexion {
    private Connection conn = null;

    private void abrirConexion() {
        // Establecer la conexion a la Base de Datos
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/infotarea10",
                    "root", "alsadjklvfjio9938afakfdf938");
            System.out.println("Conexion abierta...");
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void cerrarConexion() throws SQLException {
        if (conn != null) {
            conn.close();
            System.out.println("Conexion cerrada.");
        }
    }

    public void leerDatos() throws SQLException {
        // Leer los datos de la Base de Datos
        abrirConexion();
        String query = "select * from tb_personas";
        PreparedStatement ps;
        ps = conn.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println("Código: " + rs.getString("codigo")
                    + ", Nombre y Apellido: " + rs.getString("nombre_apellido")
                    + ", Fecha de registro: " + rs.getString("fecha_registro")
                    + ", Sueldo base: " + rs.getString("sueldo_base")
                    + ", \n\tSexo: " + rs.getString("sexo")
                    + ", Edad: " + rs.getString("edad")
                    + ", Longitud de Casa: " + rs.getString("casa_long")
                    + ", Latitud de Casa: " + rs.getString("casa_lat")
                    + ", Comentarios: " + rs.getString("comentarios"));
        }
    }

    public void insertarDatos() throws SQLException {
        // Insertar datos en la Base de Datos
        abrirConexion();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa un nombre y apellido:");
        String nombre = sc.nextLine();
        System.out.println("Ingresa la fecha de registro (Formato YYYY-MM-DD):");
        String fechaReg = sc.nextLine();
        System.out.println("Ingresa el sueldo base:");
        double sueldoBase = sc.nextDouble();
        sc.nextLine();
        System.out.println("Ingresa el sexo (M o F):");
        String sexo = sc.nextLine();
        System.out.println("Ingresa la edad:");
        int edad = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingresa la longitud de la casa:");
        double casaLong = sc.nextDouble();
        sc.nextLine();
        System.out.println("Ingresa la latitud de la casa:");
        double casaLat = sc.nextDouble();
        sc.nextLine();
        System.out.println("Ingresa los comentarios:");
        String comentarios = sc.nextLine();

        String query = "insert into tb_personas (nombre_apellido, fecha_registro, " +
                "sueldo_base, sexo, edad, casa_long, casa_lat, comentarios) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps;
        ps = conn.prepareStatement(query);
        ps.setString(1, nombre);
        ps.setDate(2, Date.valueOf(fechaReg));
        ps.setDouble(3, sueldoBase);
        ps.setString(4, sexo);
        ps.setInt(5, edad);
        ps.setDouble(6, casaLong);
        ps.setDouble(7, casaLat);
        ps.setString(8, comentarios);
        ps.executeUpdate();
        System.out.println("Datos insertados correctamente.");
    }

    public void actualizarDatos() throws SQLException {
        // Actualizar datos en la Base de Datos
        abrirConexion();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa un Código para actualizar:");
        int codigo = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingresa un nombre y apellido nuevo para ese Código:");
        String nuevoNombre = sc.nextLine();
        System.out.println("Ingresa la fecha de registro nueva para ese Código (Formato YYYY-MM-DD):");
        String nuevaFechaReg = sc.nextLine();
        System.out.println("Ingresa el sueldo base nuevo para ese Código:");
        double nuevoSueldoBase = sc.nextDouble();
        sc.nextLine();
        System.out.println("Ingresa el sexo nuevo para ese Código (M o F):");
        String nuevoSexo = sc.nextLine();
        System.out.println("Ingresa la edad nueva para ese Código:");
        int nuevaEdad = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingresa la longitud de la casa nueva para ese Código:");
        double nuevaCasaLong = sc.nextDouble();
        sc.nextLine();
        System.out.println("Ingresa la latitud de la casa nueva para ese Código:");
        double nuevaCasaLat = sc.nextDouble();
        sc.nextLine();
        System.out.println("Ingresa los comentarios nuevos para ese Código:");
        String nuevoComentarios = sc.nextLine();

        String query = "update tb_personas set nombre_apellido = ?, fecha_registro = ?, sueldo_base = ?, " +
                "sexo = ?, edad = ?, casa_long = ?, casa_lat = ?, comentarios = ? where codigo = ?";
        PreparedStatement ps;
        ps = conn.prepareStatement(query);
        ps.setString(1, nuevoNombre);
        ps.setDate(2, Date.valueOf(nuevaFechaReg));
        ps.setDouble(3, nuevoSueldoBase);
        ps.setString(4, nuevoSexo);
        ps.setInt(5, nuevaEdad);
        ps.setDouble(6, nuevaCasaLong);
        ps.setDouble(7, nuevaCasaLat);
        ps.setString(8, nuevoComentarios);
        ps.setInt(9, codigo);
        int rowCount = ps.executeUpdate();
        if (rowCount > 0) {
            System.out.println("Datos actualizados correctamente.");
        } else {
            System.out.println("No se encontró ningún registro con el ID proporcionado.");
        }
    }

    public void eliminarDatos() throws SQLException {
        //Eliminar datos en la Base de Datos
        abrirConexion();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa un Código para eliminar:");
        int codigo = sc.nextInt();

        String query = "delete from tb_personas where codigo = ?";
        PreparedStatement ps;
        ps = conn.prepareStatement(query);
        ps.setInt(1, codigo);
        int rowCount = ps.executeUpdate();
        if (rowCount > 0) {
            System.out.println("Datos eliminados correctamente.");
        } else {
            System.out.println("No se encontró ningún registro con el ID proporcionado.");
        }
    }
}
