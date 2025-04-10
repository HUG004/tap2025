package com.example.tap2025.vistas;

import com.example.tap2025.models.EmpleadoDAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormEmpleados extends Stage {
    private Button btnGuardar;
    private TextField txtNombre, txtApellido, txtCurp, txtRfc, txtNss, txtSueldo,
            txtPuesto, txtTelefono, txtHorario;
    private VBox vBox;
    private Scene escena;
    private EmpleadoDAO objE;
    private TableView<EmpleadoDAO> tbvEmpleados;

    public FormEmpleados(TableView<EmpleadoDAO> tbvEmpleados, EmpleadoDAO obj) {
        this.tbvEmpleados = tbvEmpleados;
        CrearUI();

        if (obj == null) {
            objE = new EmpleadoDAO();
        } else {
            objE = obj;
            txtNombre.setText(objE.getNombre());
            txtApellido.setText(objE.getApellido());
            txtCurp.setText(objE.getCurp());
            txtRfc.setText(objE.getRfc());
            txtNss.setText(objE.getNss());
            txtSueldo.setText(String.valueOf(objE.getSueldo()));
            txtPuesto.setText(objE.getPuesto());
            txtTelefono.setText(objE.getTel_empleado());
            txtHorario.setText(objE.getHorario());
        }

        this.setTitle("Formulario Empleado");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");

        txtApellido = new TextField();
        txtApellido.setPromptText("Apellido");

        txtCurp = new TextField();
        txtCurp.setPromptText("CURP");

        txtRfc = new TextField();
        txtRfc.setPromptText("RFC");

        txtNss = new TextField();
        txtNss.setPromptText("NSS");

        txtSueldo = new TextField();
        txtSueldo.setPromptText("Sueldo");

        txtPuesto = new TextField();
        txtPuesto.setPromptText("Puesto");

        txtTelefono = new TextField();
        txtTelefono.setPromptText("Teléfono");

        txtHorario = new TextField();
        txtHorario.setPromptText("Horario");

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> {
            objE.setNombre(txtNombre.getText());
            objE.setApellido(txtApellido.getText());
            objE.setCurp(txtCurp.getText());
            objE.setRfc(txtRfc.getText());
            objE.setNss(txtNss.getText());
            try {
                objE.setSueldo(new BigDecimal(txtSueldo.getText()));
            } catch (NumberFormatException e) {
                showError("Error", "El sueldo debe ser un número válido.");
                return;
            }
            objE.setPuesto(txtPuesto.getText());
            objE.setTel_empleado(txtTelefono.getText());
            objE.setHorario(txtHorario.getText());

            if (objE.getId_empleado() > 0)
                objE.UPDATE();
            else
                objE.INSERT();

            tbvEmpleados.setItems(objE.SELECT());
            tbvEmpleados.refresh();
            this.close();
        });

        vBox = new VBox(
                txtNombre, txtApellido, txtCurp, txtRfc, txtNss,
                txtSueldo, txtPuesto, txtTelefono, txtHorario,
                btnGuardar
        );
        vBox.setSpacing(5);
        escena = new Scene(vBox, 400, 500);
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

