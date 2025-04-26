package com.example.tap2025.vistas;

import com.example.tap2025.models.ReservacionDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;

public class FormReservacion extends Stage {
    private Button btnGuardar;
    private TextField txtid_cliente;
    private DatePicker dpFechaReservacion;
    private VBox vBox;
    private Scene escena;
    private ReservacionDAO objR;
    private TableView<ReservacionDAO> tbvReservacion;

    public FormReservacion(TableView<ReservacionDAO> tbvReservacion, ReservacionDAO obj){
        this.tbvReservacion = tbvReservacion;
        CrearUI();
        if( obj == null ){
            objR = new ReservacionDAO();
        }else{
            objR = obj;
            txtid_cliente.setText(String.valueOf(objR.getId_cliente()));
            dpFechaReservacion.setValue(objR.getFecha_reservacion());
        }
        this.setTitle("Registrar la reservacion");
        this.setScene(escena);
        this.show();
    }
    public void CrearUI(){
        txtid_cliente = new TextField();
        txtid_cliente.setPromptText("id del cliente");

        dpFechaReservacion = new DatePicker();
        dpFechaReservacion.setPromptText("Fecha de reservación");

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(actionEvent -> {
            objR.setId_cliente(Integer.parseInt(txtid_cliente.getText()));
            objR.setFecha_reservacion(dpFechaReservacion.getValue());
            if( objR.getId_reservacion() > 0 )
                objR.UPDATE();
            else
                objR.INSERT();
            tbvReservacion.setItems(objR.SELECT());
            tbvReservacion.refresh();
            this.close();
        });

        vBox = new VBox(txtid_cliente, dpFechaReservacion, btnGuardar);
        escena = new Scene(vBox, 320, 150);
    }
}
