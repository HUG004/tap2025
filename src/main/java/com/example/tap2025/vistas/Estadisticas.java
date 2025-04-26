package com.example.tap2025.vistas;

import com.example.tap2025.models.Detalle_OrdenDAO;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Estadisticas extends Stage {
    private VBox vBox;
    private Scene escena;
    private Detalle_OrdenDAO detalleOrden;

    public Estadisticas(){
        Detalle_OrdenDAO detalleOrden = new Detalle_OrdenDAO();
        CrearUI();
        this.setTitle("Estadisticas de ventas");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI(){
        VBox vBox = new VBox();
        PieChart chartProductos = new PieChart(Detalle_OrdenDAO.obtenerProductosConMasVentas());
        chartProductos.setTitle("Productos con más ventas");
        chartProductos.setPrefHeight(300);

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> charFechas = new BarChart<>(xAxis, yAxis);
        charFechas.setTitle("Dias con más ventas");
        xAxis.setLabel("Fecha");
        yAxis.setLabel("Ventas");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setData(Detalle_OrdenDAO.obtenerFechasConMasVentas());
        charFechas.getData().add(series);
        charFechas.setPrefHeight(300);

        CategoryAxis x = new CategoryAxis();
        NumberAxis y = new NumberAxis();
        BarChart<String, Number> chartEmpleado = new BarChart<>(x, y);
        chartEmpleado.setTitle("Empleados con más ventas");
        x.setLabel("Empleado");
        y.setLabel("Ventas");
        XYChart.Series<String, Number> s = new XYChart.Series<>();
        s.setData(Detalle_OrdenDAO.obtenerEmpleadosConMasVentas());
        chartEmpleado.getData().add(s);
        chartEmpleado.setPrefHeight(80);
        vBox.getChildren().addAll(chartProductos, charFechas,chartEmpleado);
        escena = new Scene(vBox,900,700);
    }
}
