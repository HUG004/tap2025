package com.example.tap2025.vistas;

import com.example.tap2025.componentes.ButtonCell;
import com.example.tap2025.models.ProveedorDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListaProveedores extends Stage {
    private ToolBar tlbMenu;
    private TableView<ProveedorDAO> tbvProveedor;
    private VBox vBox;
    private Scene escena;
    private Button btnAgegar;

    public ListaProveedores(){
        CrearUI();
        this.setTitle("Listado de Proveedores");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
        tbvProveedor = new TableView<>();
        btnAgegar = new Button();
        btnAgegar.setOnAction(actionEvent -> new FormProveedor(tbvProveedor, null));
        ImageView img = new ImageView(getClass().getResource("/image/agregar.png").toString());
        img.setFitWidth(30);
        img.setFitHeight(30);
        btnAgegar.setGraphic(img);
        tlbMenu = new ToolBar(btnAgegar);
        CreateTable();
        vBox = new VBox(tlbMenu, tbvProveedor);
        escena = new Scene(vBox,800,400);
    }
    private void CreateTable(){

        ProveedorDAO objc = new ProveedorDAO();
        TableColumn<ProveedorDAO,String> tbcProveedor = new TableColumn<>("nombre del proveedor");
        tbcProveedor.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<ProveedorDAO,String> tbcTelefono = new TableColumn<>("Telefono del proveedor");
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        TableColumn<ProveedorDAO,String> tbcdireccion = new TableColumn<>("direccion del proveedor");
        tbcdireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        TableColumn<ProveedorDAO,String> tbcemail = new TableColumn<>("email del proveedor");
        tbcemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<ProveedorDAO,String> tbcnota = new TableColumn<>("nota del proveedor");
        tbcnota.setCellValueFactory(new PropertyValueFactory<>("nota"));
        TableColumn<ProveedorDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(col -> new ButtonCell<>(
                "Editar",
                (table, proveedor) -> new FormProveedor(table, proveedor),
                pr -> null,
                pr -> ProveedorDAO.SELECT()
        ));

        TableColumn<ProveedorDAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(col -> new ButtonCell<>(
                "Eliminar",
                (table, proveedor) -> {}, // No se usa en eliminar
                proveedor -> {
                    proveedor.DELETE();
                    return null;
                },
                c -> ProveedorDAO.SELECT()
        ));
        tbvProveedor.getColumns().addAll(tbcProveedor,tbcTelefono,tbcdireccion,tbcemail,tbcnota,  tbcEditar, tbcEliminar);
        tbvProveedor.setItems(objc.SELECT());
    }

}
