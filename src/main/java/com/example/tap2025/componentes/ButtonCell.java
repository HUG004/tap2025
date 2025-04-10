package com.example.tap2025.componentes;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ButtonCell<T> extends TableCell<T, String> {
    private final Button btnCell;
    private final String action;
    private final BiConsumer<TableView<T>, T> onEdit;
    private final Function<T, Void> onDelete;
    private final Function<T, ObservableList<T>> onRefresh;

    public ButtonCell(String action, BiConsumer<TableView<T>, T> onEdit,
                      Function<T, Void> onDelete, Function<T, ObservableList<T>> onRefresh) {
        this.action = action;
        this.onEdit = onEdit;
        this.onDelete = onDelete;
        this.onRefresh = onRefresh;

        btnCell = new Button(action);
        btnCell.setOnAction(event -> {
            T item = getTableView().getItems().get(getIndex());
            if (action.equals("Editar")) {
                onEdit.accept(getTableView(), item);
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmación");
                alert.setContentText("¿Deseas eliminar el registro seleccionado?");
                Optional<ButtonType> opcion = alert.showAndWait();
                if (opcion.isPresent() && opcion.get() == ButtonType.OK) {
                    onDelete.apply(item);
                    getTableView().setItems(onRefresh.apply(item));
                    getTableView().refresh();
                }
            }
        });
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            setGraphic(btnCell);
        } else {
            setGraphic(null);
        }
    }
}
