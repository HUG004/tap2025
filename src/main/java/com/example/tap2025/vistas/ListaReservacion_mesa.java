package com.example.tap2025.vistas;

import com.example.tap2025.componentes.ButtonCell;
import com.example.tap2025.models.Reservacion_MesaDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListaReservacion_mesa extends Stage{
    private ToolBar tlbMenu;
    private TableView<Reservacion_MesaDAO> tbvReservacion_mesa;
    private VBox vBox;
    private Scene escena;
    private Button btnAgegar;

    public ListaReservacion_mesa(){
        CrearUI();
        this.setTitle("Lista de Reservaciones con sus mesas");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
        tbvReservacion_mesa = new TableView<>();
        btnAgegar = new Button();
        btnAgegar.setOnAction(actionEvent -> new FormReservacion_Mesa(tbvReservacion_mesa, null));
        ImageView img = new ImageView(getClass().getResource("/image/agregar.png").toString());
        img.setFitWidth(30);
        img.setFitHeight(30);
        btnAgegar.setGraphic(img);
        tlbMenu = new ToolBar(btnAgegar);
        CreateTable();
        vBox = new VBox(tlbMenu, tbvReservacion_mesa);
        escena = new Scene(vBox,800,400);
    }
    private void CreateTable(){

        Reservacion_MesaDAO objR = new Reservacion_MesaDAO();
        TableColumn<Reservacion_MesaDAO,String> tbcId_reservacion = new TableColumn<>("id de la reservacion");
        tbcId_reservacion.setCellValueFactory(new PropertyValueFactory<>("id_reservacion"));
        TableColumn<Reservacion_MesaDAO,String> tbcId_mesa = new TableColumn<>("Mesa de la reservacion");
        tbcId_mesa.setCellValueFactory(new PropertyValueFactory<>("id_mesa"));
        TableColumn<Reservacion_MesaDAO,String> tbcComentarios = new TableColumn<>("comentarios de la reservacion");
        tbcComentarios.setCellValueFactory(new PropertyValueFactory<>("comentarios"));

        TableColumn<Reservacion_MesaDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(col -> new ButtonCell<>(
                "Editar",
                (table, reservacion) -> new FormReservacion_Mesa(table, reservacion),
                c -> null,
                c -> Reservacion_MesaDAO.SELECT()
        ));

        TableColumn<Reservacion_MesaDAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(col -> new ButtonCell<>(
                "Eliminar",
                (table, reservacion) -> {},
                reservacion -> {
                    reservacion.DELETE();
                    return null;
                },
                c -> Reservacion_MesaDAO.SELECT()
        ));
        tbvReservacion_mesa.getColumns().addAll(tbcId_reservacion, tbcId_mesa,tbcComentarios,  tbcEditar, tbcEliminar);
        tbvReservacion_mesa.setItems(objR.SELECT());
    }

}
