package com.example.tap2025.componentes;

import com.example.tap2025.models.ClienteDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;

import java.util.Optional;

public class ButtonCell extends TableCell<ClienteDAO, String> {
     private Button btnCell;
     private String str;

    public ButtonCell(String label){

        str = label;
        btnCell = new Button(str);
        btnCell.setOnAction(actionEvent -> {
            ClienteDAO objC = this.getTableView().getItems().get(this.getIndex());
            if (str.equals("Editar")){

            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje del sistema");
                alert.setContentText("¿Deseas eliminar el registro seleccionado?");
                Optional<ButtonType> opcion = alert.showAndWait();
                if (opcion.get() == ButtonType.OK){
                    objC.DELETE();

                }
            }
            this.getTableView().setItems(objC.SELECT());
            this.getTableView().refresh();
        });

    }
    protected void updateItem(String item, boolean empty){
        super.updateItem(item, empty);
        if(!empty){
            this.setGraphic(btnCell);
        }
    }
}
