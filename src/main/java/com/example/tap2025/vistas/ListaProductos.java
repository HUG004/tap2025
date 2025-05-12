package com.example.tap2025.vistas;

import com.example.tap2025.componentes.ButtonCell;
import com.example.tap2025.models.ProductoDAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;  // IMPORTANTE

public class ListaProductos extends Stage {
    private ToolBar tlbMenu;
    private TableView<ProductoDAO> tbvProducto;
    private VBox vBox;
    private Scene escena;
    private Button btnAgegar;

    public ListaProductos(){
        CrearUI();
        this.setTitle("Listado de Productos");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI(){
        tbvProducto = new TableView<>();
        btnAgegar = new Button();
        btnAgegar.setOnAction(actionEvent -> new FormProducto(tbvProducto, null));
        ImageView img = new ImageView(getClass().getResource("/image/agregar.png").toString());
        img.setFitWidth(30);
        img.setFitHeight(30);
        btnAgegar.setGraphic(img);
        tlbMenu = new ToolBar(btnAgegar);
        CreateTable();
        vBox = new VBox(tlbMenu,tbvProducto);
        escena = new Scene(vBox,800,400);
    }

    private void CreateTable(){
        ProductoDAO objP = new ProductoDAO();

        TableColumn<ProductoDAO,String> tbcId = new TableColumn<>("ID del producto");
        tbcId.setCellValueFactory(new PropertyValueFactory<>("id_producto"));
        TableColumn<ProductoDAO,String> tbcNombre = new TableColumn<>("Nombre del producto");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("producto"));

        TableColumn<ProductoDAO,String> tbcPrecio = new TableColumn<>("Precio");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<ProductoDAO,String> tbcCategoria = new TableColumn<>("Categoria");
        tbcCategoria.setCellValueFactory(new PropertyValueFactory<>("id_categoria"));

        TableColumn<ProductoDAO,String> tbcActivo = new TableColumn<>("Activo");
        tbcActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));


        TableColumn<ProductoDAO, String> tbcImagen = new TableColumn<>("Imagen");
        tbcImagen.setCellValueFactory(new PropertyValueFactory<>("imagen"));
        tbcImagen.setCellFactory(col -> new TableCell<ProductoDAO, String>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String ruta, boolean empty) {
                super.updateItem(ruta, empty);
                if (empty || ruta == null || ruta.isEmpty()) {
                    setGraphic(null);
                } else {
                    try {
                        if (!ruta.startsWith("/")) {
                            ruta = "/" + ruta;
                        }
                        URL url = getClass().getResource(ruta);
                        System.out.println("Buscando imagen en ruta: " + ruta + " -> " + url);
                        if (url == null) {
                            setGraphic(null);
                        } else {
                            imageView.setImage(new javafx.scene.image.Image(url.toExternalForm()));
                            imageView.setFitHeight(60);
                            imageView.setFitWidth(60);
                            setGraphic(imageView);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        setGraphic(null);
                    }
                }
            }
        });

        TableColumn<ProductoDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(col -> new ButtonCell<>(
                "Editar",
                (table, producto) -> new FormProducto(table, producto),
                c -> null,
                c -> ProductoDAO.SELECT()
        ));

        TableColumn<ProductoDAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(col -> new ButtonCell<>(
                "Eliminar",
                (table, producto) -> {}, // no se usa
                producto -> {
                    Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmacion.setTitle("Confirmar eliminación");
                    confirmacion.setHeaderText("¿Está seguro que desea eliminar este producto?");
                    confirmacion.setContentText("Este producto se desactivará, pero no se eliminará de la base de datos.");

                    confirmacion.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            producto.DESACTIVAR();
                            tbvProducto.setItems(ProductoDAO.SELECT());
                        }
                    });
                    return null;
                },
                c -> ProductoDAO.SELECT()
        ));

        tbvProducto.getColumns().addAll(tbcId,tbcNombre, tbcPrecio, tbcCategoria, tbcActivo, tbcImagen ,tbcEditar, tbcEliminar);
        tbvProducto.setItems(objP.SELECT());
    }
}
