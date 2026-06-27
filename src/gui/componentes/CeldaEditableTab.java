package gui.componentes;

import javafx.application.Platform;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CeldaEditableTab<S>
        extends TextFieldTableCell<S, String> {

    @Override
    public void startEdit() {
        
        super.startEdit();        
        
        Platform.runLater(() -> {

            if (getGraphic() instanceof TextField textField) {

                textField.requestFocus();
                textField.selectAll();

                textField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {

                    switch (event.getCode()) {

                    case ENTER:

                        commitEdit(textField.getText());
                        event.consume();

                        break;

                    case TAB:

                        mover(textField, event.isShiftDown() ? -1 : 1);

                        event.consume();
                        break;

                    case UP:

                        mover(textField, -1);

                        event.consume();
                        break;

                    case DOWN:

                        mover(textField, 1);

                        event.consume();
                        break;

                    default:
                        break;
                    }
                });
            }
        });
    }

    private void mover(TextField textField, int desplazamiento) {

        commitEdit(textField.getText());

        TableView<S> tabla = getTableView();

        TableColumn<S, ?> columna = getTableColumn();

        int nuevaFila = getIndex() + desplazamiento;

        Platform.runLater(() -> {

            if (nuevaFila >= 0 && nuevaFila < tabla.getItems().size()) {

                tabla.getSelectionModel().select(nuevaFila, columna);

                tabla.edit(nuevaFila, columna);
            }
        });
    }
    
}