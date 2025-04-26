package com.example.tap2025.vistas;

import com.example.tap2025.componentes.ButtonCell;
import com.example.tap2025.models.Detalle_ProductoDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListaDetalle_Producto extends Stage {
    private ToolBar tlbMenu;
    private TableView<Detalle_ProductoDAO> tbvDetalleProducto;
    private VBox vBox;
    private Scene escena;
    private Button btnAgegar;

    public ListaDetalle_Producto(){
        CrearUI();
        this.setTitle("Lista de productos y sus insumos");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
        tbvDetalleProducto = new TableView<>();
        btnAgegar = new Button();
        btnAgegar.setOnAction(actionEvent -> new FormDetalle_Producto(tbvDetalleProducto, null));
        ImageView img = new ImageView(getClass().getResource("/image/agregar.png").toString());
        img.setFitWidth(30);
        img.setFitHeight(30);
        btnAgegar.setGraphic(img);
        tlbMenu = new ToolBar(btnAgegar);
        CreateTable();
        vBox = new VBox(tlbMenu, tbvDetalleProducto);
        escena = new Scene(vBox,800,400);
    }
    private void CreateTable(){

        Detalle_ProductoDAO objDP = new Detalle_ProductoDAO();
        TableColumn<Detalle_ProductoDAO,String> tbcId_producto = new TableColumn<>("id del producto");
        tbcId_producto.setCellValueFactory(new PropertyValueFactory<>("id_producto"));
        TableColumn<Detalle_ProductoDAO,String> tbcId_insumo = new TableColumn<>("insumo del producto");
        tbcId_insumo.setCellValueFactory(new PropertyValueFactory<>("id_insumo"));
        TableColumn<Detalle_ProductoDAO,String> tbcCantidad = new TableColumn<>("cantidad");
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        TableColumn<Detalle_ProductoDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(col -> new ButtonCell<>(
                "Editar",
                (table, detalle) -> new FormDetalle_Producto(table, detalle),
                c -> null,
                c -> Detalle_ProductoDAO.SELECT()
        ));

        TableColumn<Detalle_ProductoDAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(col -> new ButtonCell<>(
                "Eliminar",
                (table, detalle) -> {}, // No se usa en eliminar
                detalle -> {
                    detalle.DELETE();
                    return null;
                },
                c -> Detalle_ProductoDAO.SELECT()
        ));
        tbvDetalleProducto.getColumns().addAll(tbcId_producto, tbcId_insumo, tbcCantidad,  tbcEditar, tbcEliminar);
        tbvDetalleProducto.setItems(objDP.SELECT());
    }

}
