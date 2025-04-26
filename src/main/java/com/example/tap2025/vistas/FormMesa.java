package com.example.tap2025.vistas;

import com.example.tap2025.models.MesaDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.function.UnaryOperator;

public class FormMesa extends Stage {
    private Button btnGuardar;
    private TextField txtCapacidad;
    private VBox vBox;
    private Scene escena;
    private MesaDAO objM;
    private TableView<MesaDAO> tbvMesa;

    public FormMesa(TableView<MesaDAO> tbvMesa, MesaDAO obj){
        this.tbvMesa = tbvMesa;
        CrearUI();
        if( obj == null ){
            objM = new MesaDAO();
        }else{
            objM = obj;
            txtCapacidad.setText(String.valueOf(obj.getCapacidad()));

        }
        this.setTitle("Registrar Mesa");
        this.setScene(escena);
        this.show();
    }
    public void CrearUI(){
        txtCapacidad = new TextField();
        txtCapacidad.setPromptText("Capacidad de la  mesa");
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> formatter = new TextFormatter<>(filter);
        txtCapacidad.setTextFormatter(formatter);

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(actionEvent -> {
            try {
                int capacidad = Integer.parseInt(txtCapacidad.getText());
                objM.setCapacidad(capacidad);
                if (objM.getId_mesa() > 0)
                    objM.UPDATE();
                else
                    objM.INSERT();
                tbvMesa.setItems(objM.SELECT());
                tbvMesa.refresh();
                this.close();
            } catch (NumberFormatException e) {
                txtCapacidad.setStyle("-fx-border-color: red;");
                txtCapacidad.setPromptText("Solo números válidos");
                txtCapacidad.clear();
            }
        });

        vBox = new VBox(txtCapacidad, btnGuardar);
        escena = new Scene(vBox, 320, 150);
    }
}

