package com.example.tap2025.vistas;

import com.example.tap2025.models.MesaDAO;
import com.example.tap2025.models.ReservacionDAO;
import com.example.tap2025.models.Reservacion_MesaDAO;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormReservacion_Mesa extends Stage {
    private Button btnGuardar;
    private boolean esNuevo;
    private TextField txtid_reservacion,txtid_mesa, txtcomentarios;
    private VBox vBox;
    private Scene escena;
    private Reservacion_MesaDAO objRM;
    private TableView<Reservacion_MesaDAO> tbvReservacion_mesa;

    public FormReservacion_Mesa(TableView<Reservacion_MesaDAO> tbvReservacion_mesa, Reservacion_MesaDAO obj) {
        this.tbvReservacion_mesa = tbvReservacion_mesa;
        this.esNuevo = (obj == null);
        this.objRM = esNuevo ? new Reservacion_MesaDAO() : obj;
        CrearUI();

        if (obj != null) {
            txtid_reservacion.setText(String.valueOf(objRM.getId_reservacion()));
            txtid_mesa.setText(String.valueOf(objRM.getId_mesa()));
            txtcomentarios.setText(objRM.getComentarios());
        }

        this.setTitle("Registrar la reservación con la mesa");
        this.setScene(escena);
        this.show();
    }

    public void CrearUI(){
        txtid_reservacion = new TextField();
        txtid_reservacion.setPromptText("id de la reservacion");
        txtid_mesa = new TextField();
        txtid_mesa.setPromptText("id de la mesa");
        txtcomentarios = new TextField();
        txtcomentarios.setPromptText("comentarios");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> guardarRelacion());

        vBox = new VBox(txtid_reservacion, txtid_mesa,txtcomentarios, btnGuardar);
        escena = new Scene(vBox, 320, 150);
    }
    private void guardarRelacion() {
        try {
            objRM.setId_reservacion(Integer.parseInt(txtid_reservacion.getText()));
            objRM.setId_mesa(Integer.parseInt(txtid_mesa.getText()));
            objRM.setComentarios(txtcomentarios.getText());

            String mensaje;
            if (esNuevo) {
                if (objRM.INSERT() > 0) {
                    mensaje = "Relación agregada correctamente.";
                } else {
                    mensaje = "Error al agregar la relación.";
                }
            } else {
                objRM.UPDATE();
                mensaje = "Relación actualizada correctamente.";
            }

            mostrarAlerta(Alert.AlertType.INFORMATION, mensaje);
            tbvReservacion_mesa.setItems(Reservacion_MesaDAO.SELECT());
            tbvReservacion_mesa.refresh();
            this.close(); // Cierra la ventana después de guardar

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "ID de reservación y de mesa deben ser números válidos.");
        }
    }

    private void mostrarAlerta(Alert.AlertType type, String mensaje) {
        Alert alert = new Alert(type);
        alert.setTitle("Mensaje del sistema");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}


