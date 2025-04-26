package com.example.tap2025.models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Detalle_OrdenDAO {
    private int id_producto;
    private int id_orden;
    private int cantidad;
    private ProductoDAO producto;

    public ProductoDAO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDAO producto) {
        this.producto = producto;
    }
    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void INSERT(){
        String query = "INSERT INTO detalle_orden(id_producto, id_orden, cantidad)"
                + "values('"+id_producto+"','"+id_orden+"','"+cantidad+"') ";
        try{
            Statement stmt =  conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void UPDATE(){
        String query = "UPDATE detalle_orden SET id_producto = '"+id_producto+"', id_orden = '"+id_orden+"', cantidad = '"+cantidad+"' WHERE id_producto= "+id_producto + " and id_orden = "+id_orden;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void DELETE(){
        String query = "DELETE FROM detalle_orden WHERE id_producto = " +id_producto + " and id_orden = " +id_orden;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public ObservableList<Detalle_OrdenDAO> SELECT(){
        String query = "SELECT * FROM detalle_orden";
        ObservableList<Detalle_OrdenDAO> listaDO = FXCollections.observableArrayList();
        Detalle_OrdenDAO objDO;
        try{
            Statement stmt = conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objDO = new Detalle_OrdenDAO();
                objDO.setId_producto(res.getInt("id_producto"));
                objDO.setId_orden(res.getInt("id_orden"));
                objDO.setCantidad(res.getInt("cantidad"));
                listaDO.add(objDO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaDO;
    }
    public Detalle_OrdenDAO(int id_orden, ProductoDAO producto, int cantidad) {
        this.id_orden = id_orden;
        this.id_producto = producto.getId_producto(); // extrae el id del producto
        this.producto = producto;
        this.cantidad = cantidad;
    }
    public Detalle_OrdenDAO() {
    }
    public static ObservableList<PieChart.Data> obtenerProductosConMasVentas(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        String query = """
        SELECT p.producto AS producto, SUM(do.cantidad) AS total_venta
        FROM detalle_orden do
        JOIN producto p ON do.id_producto = p.id_producto
        GROUP BY p.producto
        ORDER BY total_venta DESC
    """;

        try (Statement stmt = conexion.connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String producto = rs.getString("producto");
                int cantidad = rs.getInt("total_venta");
                pieChartData.add(new PieChart.Data(producto, cantidad));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pieChartData;
    }
    public static ObservableList<XYChart.Data<String, Number>> obtenerFechasConMasVentas() {
        ObservableList<XYChart.Data<String, Number>> barChartData = FXCollections.observableArrayList();

        String query = """
        SELECT COUNT(do.id_orden) AS total_ventas, o.fecha AS fecha
        FROM orden o
        JOIN detalle_orden do ON o.id_orden = do.id_orden
        GROUP BY o.fecha
        ORDER BY total_ventas DESC
    """;

        try (Statement stmt = conexion.connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String fecha = rs.getString("fecha");
                int totalVentas = rs.getInt("total_ventas");
                barChartData.add(new XYChart.Data<>(fecha, totalVentas));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return barChartData;
    }
    public static ObservableList<XYChart.Data<String, Number>> obtenerEmpleadosConMasVentas() {
        ObservableList<XYChart.Data<String, Number>> barChartData = FXCollections.observableArrayList();

        String query = """
    SELECT e.nombre AS empleado, COUNT(do.id_orden) AS total_ventas
    FROM empleado e
    JOIN orden o ON e.id_empleado = o.id_empleado
    JOIN detalle_orden do ON o.id_orden = do.id_orden
    GROUP BY e.nombre
    ORDER BY total_ventas DESC;
    """;

            try (Statement stmt = conexion.connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    String Empleado = rs.getString("Empleado");
                    int totalVentas = rs.getInt("total_ventas");
                    barChartData.add(new XYChart.Data<>(Empleado, totalVentas));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return barChartData;
        }
}
