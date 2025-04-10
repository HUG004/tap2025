package com.example.tap2025.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ReservacionDAO {
    private int id_reservacion;
    private int id_cliente;
    private LocalDate fecha_reservacion;

    public int getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public LocalDate getFecha_reservacion() {
        return fecha_reservacion;
    }

    public void setFecha_reservacion(LocalDate fecha_reservacion) {
        this.fecha_reservacion = fecha_reservacion;
    }

    public void INSERT() {
        String query = "INSERT INTO reservacion(id_cliente, fecha_reservacion) VALUES ('"+id_cliente+"', '"+fecha_reservacion+"')";
        try {
            PreparedStatement stmt = conexion.connection.prepareStatement(query);
            stmt.setInt(1, id_cliente);

            // Convertir LocalDate a Timestamp (agregamos hora 00:00:00)
            LocalDateTime dateTime = fecha_reservacion.atStartOfDay();
            stmt.setTimestamp(2, Timestamp.valueOf(dateTime));

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UPDATE() {
        String query = "UPDATE reservacion SET id_cliente = '"+id_cliente+"', fecha_reservacion = '"+fecha_reservacion+"' WHERE id_reservacion = " +id_reservacion;
        try {
            PreparedStatement stmt = conexion.connection.prepareStatement(query);
            stmt.setInt(1, id_cliente);

            // Convertir LocalDate a Timestamp (hora 00:00:00)
            LocalDateTime dateTime = fecha_reservacion.atStartOfDay();
            stmt.setTimestamp(2, Timestamp.valueOf(dateTime));

            stmt.setInt(3, id_reservacion);

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DELETE() {
        String query = "DELETE FROM reservacion WHERE id_reservacion = " +id_reservacion;
        try {
            PreparedStatement stmt = conexion.connection.prepareStatement(query);
            stmt.setInt(1, id_reservacion);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ReservacionDAO> SELECT() {
        String query = "SELECT * FROM reservacion";
        ObservableList<ReservacionDAO> listaR = FXCollections.observableArrayList();
        ReservacionDAO objR;
        try {
            Statement stmt = conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objR = new ReservacionDAO();
                objR.setId_reservacion(res.getInt("id_reservacion"));
                objR.setId_cliente(res.getInt("id_cliente"));

                // Convertir Timestamp a LocalDate
                Timestamp ts = res.getTimestamp("fecha_reservacion");
                if (ts != null) {
                    objR.setFecha_reservacion(ts.toLocalDateTime().toLocalDate());
                }

                listaR.add(objR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaR;
    }
}
