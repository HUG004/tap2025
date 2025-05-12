package com.example.tap2025.vistas;

import com.example.tap2025.componentes.ButtonCell;
import com.example.tap2025.models.MesaDAO;
import com.example.tap2025.models.ProductoDAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListaMesa extends Stage {
    private ToolBar tlbMenu;
    private TableView<MesaDAO> tbvMesa;
    private VBox vBox;
    private Scene escena;
    private Button btnAgegar;

    public ListaMesa(){
        CrearUI();
        this.setTitle("Listado de Mesas");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
        tbvMesa = new TableView<>();
        btnAgegar = new Button();
        btnAgegar.setOnAction(actionEvent -> new FormMesa(tbvMesa, null));
        ImageView img = new ImageView(getClass().getResource("/image/agregar.png").toString());
        img.setFitWidth(30);
        img.setFitHeight(30);
        btnAgegar.setGraphic(img);
        tlbMenu = new ToolBar(btnAgegar);
        CreateTable();
        vBox = new VBox(tlbMenu, tbvMesa);
        escena = new Scene(vBox,800,400);
    }
    private void CreateTable(){

        MesaDAO objM = new MesaDAO();
        TableColumn<MesaDAO,String> tbcCapacidad = new TableColumn<>("Capacidad de la mesa");
        tbcCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        TableColumn<MesaDAO,String> tbcActivo = new TableColumn<>("Activo");
        tbcActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));
        TableColumn<MesaDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(col -> new ButtonCell<>(
                "Editar",
                (table, mesa) -> new FormMesa(table, mesa),
                c -> null,
                c -> MesaDAO.SELECT()
        ));

        TableColumn<MesaDAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(col -> new ButtonCell<>(
                "Eliminar",
                (table, mesa) -> {},
                mesa -> {
                    Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmacion.setTitle("Confirmar eliminación");
                    confirmacion.setHeaderText("¿Está seguro que desea eliminar esta mesa?");
                    confirmacion.setContentText("Esta mesa se desactivará, pero no se eliminará de la base de datos.");

                    confirmacion.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            mesa.DESACTIVAR();
                            tbvMesa.setItems(MesaDAO.SELECT());
                        }
                    });
                    return null;
                },

                c -> MesaDAO.SELECT()
        ));
        tbvMesa.getColumns().addAll(tbcCapacidad, tbcActivo, tbcEditar, tbcEliminar);
        tbvMesa.setItems(objM.SELECT());
    }

}
