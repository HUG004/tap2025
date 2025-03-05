package com.example.tap2025.vistas;

import com.example.tap2025.models.ClienteDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListaClientes extends Stage {
    private ToolBar tlbMenu;
    private TableView<ClienteDAO> tbvClientes;
    private VBox vBox;
    private Scene escena;
    private Button btnAgegar;

    public ListaClientes(){
        CrearUI();
        this.setTitle("Listado de Clientes");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
        tbvClientes = new TableView<>();
        btnAgegar = new Button();
        btnAgegar.setOnAction(actionEvent -> new FormCliente(tbvClientes));
        ImageView img = new ImageView(getClass().getResource("/image/agregar.png").toString());
        img.setFitWidth(30);
        img.setFitHeight(30);
        btnAgegar.setGraphic(img);
        tlbMenu = new ToolBar(btnAgegar);
        CreateTable();
        vBox = new VBox(tlbMenu,tbvClientes);
        escena = new Scene(vBox,800,400);
    }
    private void CreateTable(){

        ClienteDAO objC = new ClienteDAO();
        TableColumn<ClienteDAO,String> tbcNombre = new TableColumn<>("Nombre");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nom_cliente"));
        TableColumn<ClienteDAO,String> tbcApellido = new TableColumn<>("Apellido");
        tbcApellido.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
        TableColumn<ClienteDAO,String> tbcDireccion = new TableColumn<>("Dirección");
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        TableColumn<ClienteDAO,String> tbcEmail = new TableColumn<>("Email");
        tbcEmail.setCellValueFactory(new PropertyValueFactory<>("email_cliente"));
        TableColumn<ClienteDAO,String> tbcTelefono = new TableColumn<>("Telefono");
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("tel_cliente"));
        tbvClientes.getColumns().addAll(tbcNombre, tbcApellido, tbcDireccion, tbcEmail, tbcTelefono);
        tbvClientes.setItems(objC.SELECT());
    }
}
