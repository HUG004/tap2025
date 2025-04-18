package com.example.tap2025.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class viewAdmin extends Stage {
    private Scene escena;
    private VBox vbox;
    private HBox hbox;
    private Label lbl_admin;
    private GridPane grid;
    private Button btn_Clt, btn_producto, btn_categoria, btn_tipo_categoria,btn_empleado, btn_proveedor;
    private Button btnCerrarSesion;

    public viewAdmin(){
        CrearUI();
        this.setTitle("Administrador");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI() {

        ImageView logo = new ImageView(getClass().getResource("/image/logo_lince.png").toExternalForm());
        logo.setFitWidth(100);
        logo.setFitHeight(100);
        logo.setPreserveRatio(true);

        HBox logoContainer = new HBox(logo);
        logoContainer.setAlignment(Pos.TOP_LEFT);
        logoContainer.setPadding(new Insets(10));

        btnCerrarSesion = new Button("Cerrar Sesión");
        btnCerrarSesion.setOnAction(event -> cerrarSesion());
        btnCerrarSesion.getStyleClass().add("btn-danger");

        btn_Clt = new Button("Cliente");
        btn_Clt.setOnAction(actionEvent -> new ListaClientes());
        btn_Clt.getStyleClass().add("btn-primary");

        btn_empleado = new Button("Empleado");
        btn_empleado.setOnAction(actionEvent -> new ListaEmpleados());
        btn_empleado.getStyleClass().add("btn-primary");

        btn_tipo_categoria = new Button("Tipo Categoria");
        btn_tipo_categoria.setOnAction(actionEvent -> new ListaTipo_Categoria());
        btn_tipo_categoria.getStyleClass().add("btn-primary");

        btn_categoria = new Button("Categoria");
        btn_categoria.setOnAction(actionEvent -> new ListaCategoria());
        btn_categoria.getStyleClass().add("btn-primary");

        btn_producto = new Button("Producto");
        btn_producto.setOnAction(actionEvent -> new ListaProductos());
        btn_producto.getStyleClass().add("btn-primary");

        btn_proveedor = new Button("Proveedor");
        btn_proveedor.setOnAction(actionEvent -> new ListaProveedores());
        btn_proveedor.getStyleClass().add("btn-primary");

/*        btnRepTotal = new Button("Reporte total de artistas y canciones");
        btnRepTotal.setOnAction(actionEvent -> {
            ReportePDF reportePDF = new ReportePDF();
            reportePDF.generarReporte();
        });
        btnRepTotal.getStyleClass().add("btn-success");
*/
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(15));
        grid.setAlignment(Pos.CENTER);

        grid.add(btn_Clt, 0, 0);
        grid.add(btn_empleado, 0, 1);
        grid.add(btn_tipo_categoria, 0, 2);
        grid.add(btn_categoria, 1, 0);
        grid.add(btn_producto, 1, 1);
        grid.add(btn_proveedor,1,2);
        lbl_admin = new Label("Administrador");
        lbl_admin.getStyleClass().add("title");

        HBox header = new HBox(10, logoContainer, lbl_admin);
        header.setAlignment(Pos.CENTER_LEFT);

        hbox = new HBox(20);
        hbox.getChildren().addAll(grid);
        hbox.setAlignment(Pos.CENTER);

        vbox = new VBox(20);
        vbox.getChildren().addAll(header, hbox, btnCerrarSesion);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getStyleClass().add("background-black");

        escena = new Scene(vbox, 1150, 400);
        escena.getStylesheets().add(getClass().getResource("/styles/Listas.css").toExternalForm());
    }


    private void cerrarSesion() {
        //new Login();
        this.close();
    }
}
