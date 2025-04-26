package com.example.tap2025.vistas;

import com.example.tap2025.models.Detalle_ProductoDAO;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormDetalle_Producto extends Stage {
    private Button btnGuardar;
    private boolean esNuevo;
    private TextField txtid_insumo,txtid_producto, txtcantidad;
    private VBox vBox;
    private Scene escena;
    private Detalle_ProductoDAO objDP;
    private TableView<Detalle_ProductoDAO> tbvDetalleProducto;

    public FormDetalle_Producto(TableView<Detalle_ProductoDAO> tbvDetalleProducto, Detalle_ProductoDAO obj) {
        this.tbvDetalleProducto = tbvDetalleProducto;
        this.esNuevo = (obj == null);
        this.objDP = esNuevo ? new Detalle_ProductoDAO() : obj;
        CrearUI();

        if (obj != null) {
            txtid_producto.setText(String.valueOf(objDP.getId_producto()));
            txtid_insumo.setText(String.valueOf(objDP.getId_insumo()));
            txtcantidad.setText(String.valueOf(objDP.getCantidad()));
        }

        this.setTitle("Registrar el insumo y el producto");
        this.setScene(escena);
        this.show();
    }

    public void CrearUI(){
        txtid_producto = new TextField();
        txtid_producto.setPromptText("id del producto");
        txtid_insumo = new TextField();
        txtid_insumo.setPromptText("id del insumo");
        txtcantidad = new TextField();
        txtcantidad.setPromptText("cantidad");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> guardarRelacion());

        vBox = new VBox(txtid_producto, txtid_insumo,txtcantidad, btnGuardar);
        escena = new Scene(vBox, 320, 150);
    }
    private void guardarRelacion() {
        try {
            objDP.setId_producto(Integer.parseInt(txtid_producto.getText()));
            objDP.setId_insumo(Integer.parseInt(txtid_insumo.getText()));
            objDP.setCantidad(Integer.parseInt(txtcantidad.getText()));

            String mensaje;
            if (esNuevo) {
                if (objDP.INSERT() > 0) {
                    mensaje = "Relación agregada correctamente.";
                } else {
                    mensaje = "Error al agregar la relación.";
                }
            } else {
                objDP.UPDATE();
                mensaje = "Relación actualizada correctamente.";
            }

            mostrarAlerta(Alert.AlertType.INFORMATION, mensaje);
            tbvDetalleProducto.setItems(Detalle_ProductoDAO.SELECT());
            tbvDetalleProducto.refresh();
            this.close(); // Cierra la ventana después de guardar

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "ID del producto y del insumo deben ser números válidos.");
        }
    }

    private void mostrarAlerta(Alert.AlertType type, String mensaje) {
        Alert alert = new Alert(type);
        alert.setTitle("Mensaje del sistema");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
