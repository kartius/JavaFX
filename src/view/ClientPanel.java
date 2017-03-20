package view;

import container.Book;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class ClientPanel {

    private TableView<Book> table;
    private ObservableList<Book> data;
    private ComboBox<String> groupType;
    private ComboBox<String> taxType;
}
