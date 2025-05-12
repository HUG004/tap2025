package com.example.tap2025.vistas;

import com.example.tap2025.componentes.ButtonCell;
import com.example.tap2025.models.ProductoDAO;
import com.example.tap2025.models.ReservacionDAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListaReservacion extends Stage {
    private ToolBar tlbMenu;
    private TableView<ReservacionDAO> tbvReservacion;
    private VBox vBox;
    private Scene escena;
    private Button btnAgegar;

    public ListaReservacion(){
        CrearUI();
        this.setTitle("Lista de Reservaciones");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
        tbvReservacion = new TableView<>();
        btnAgegar = new Button();
        btnAgegar.setOnAction(actionEvent -> new FormReservacion(tbvReservacion, null));
        ImageView img = new ImageView(getClass().getResource("/image/agregar.png").toString());
        img.setFitWidth(30);
        img.setFitHeight(30);
        btnAgegar.setGraphic(img);
        tlbMenu = new ToolBar(btnAgegar);
        CreateTable();
        vBox = new VBox(tlbMenu, tbvReservacion);
        escena = new Scene(vBox,800,400);
    }
    private void CreateTable(){

        ReservacionDAO objR = new ReservacionDAO();
        TableColumn<ReservacionDAO,String> tbcId_Cliente = new TableColumn<>("id del cliente");
        tbcId_Cliente.setCellValueFactory(new PropertyValueFactory<>("id_cliente"));
        TableColumn<ReservacionDAO,String> tbcFecha_reservacion = new TableColumn<>("Fecha de reservacion");
        tbcFecha_reservacion.setCellValueFactory(new PropertyValueFactory<>("fecha_reservacion"));
        TableColumn<ReservacionDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(col -> new ButtonCell<>(
                "Editar",
                (table, reservacion) -> new FormReservacion(table, reservacion),
                c -> null,
                c -> ReservacionDAO.SELECT()
        ));

        TableColumn<ReservacionDAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(col -> new ButtonCell<>(
                "Eliminar",
                (table, reservacion) -> {},
                reservacion -> {
                    Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmacion.setTitle("Confirmar eliminación");
                    confirmacion.setHeaderText("¿Está seguro que desea eliminar esta reservacion?");
                    confirmacion.setContentText("esta reservacion se desactivará, pero no se eliminará de la base de datos.");

                    confirmacion.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            reservacion.DESACTIVAR();
                            tbvReservacion.setItems(ReservacionDAO.SELECT());
                        }
                    });
                    return null;
                },
                c -> ReservacionDAO.SELECT()
        ));
        tbvReservacion.getColumns().addAll(tbcId_Cliente, tbcFecha_reservacion,  tbcEditar, tbcEliminar);
        tbvReservacion.setItems(objR.SELECT());
    }

}
