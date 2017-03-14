package sample;

import container.Book;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javafx.beans.value.ChangeListener;

import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private TableView<Book> table;
    private ObservableList<Book> data;
    private Text actionStatus;
    private ComboBox<String> groupType;
    private ComboBox<String> taxType;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Low&Finance");
        // Books label
        Label label = new Label("Clients");
        label.setTextFill(Color.DARKBLUE);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().add(label);
//        hb.getChildren().add(label1);
        actionStatus = new Text();
        actionStatus.setFill(Color.FIREBRICK);
        // Table view, data, columns and properties
        table = new TableView();
        groupType = new ComboBox<>();
        taxType = new ComboBox<>();
        data = getInitialTableData();
        table.setItems(data);
        table.setEditable(true);
        TableColumn titleCol = new TableColumn("Name");
        titleCol.setCellValueFactory(new PropertyValueFactory("title"));
        titleCol.setCellFactory(TextFieldTableCell.forTableColumn());
        titleCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent t) {
                ((Book) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setTitle((String) t.getNewValue());
            }
        });
        TableColumn authorCol = new TableColumn("Company");
        authorCol.setCellValueFactory(new PropertyValueFactory("author"));
        TableColumn accountCol = new TableColumn("Account");
        accountCol.setCellValueFactory(new PropertyValueFactory("account"));
        table.getColumns().setAll(titleCol, authorCol, accountCol);
        table.setPrefWidth(450);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getSelectionModel().selectedIndexProperty().addListener(((observable, oldValue, newValue) -> {
            int ix = newValue.intValue();
            if ((ix == data.size())) {
                return; // invalid data
            }
            Book book = (Book) data.get(ix);
//            actionStatus.setText(book.toString());
        }));
        // Add and delete buttons
        Button addbtn = new Button("Add");
        addbtn.setOnAction(new AddButtonListener());
        Button delbtn = new Button("Delete");
        delbtn.setOnAction(new DeleteButtonListener());
        HBox buttonHb = new HBox(10);
        buttonHb.setAlignment(Pos.CENTER);
        buttonHb.getChildren().addAll(addbtn, delbtn);


        // Status message text

        // Vbox
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        ;
        vbox.getChildren().addAll(hb, taxType, groupType, table, buttonHb, actionStatus);
        // Scene
        Scene scene = new Scene(vbox, 500, 475); // w x h
        primaryStage.setScene(scene);
        primaryStage.show();
        // Select the first row
        table.getSelectionModel().select(0);
        Book book = (Book) table.getSelectionModel().getSelectedItem();
//        actionStatus.setText(book.toString());
    }


    private ObservableList getInitialTableData() {
        List list = new ArrayList();
        list.add(new Book("The Thief", "Fuminori Nakamura", "sdfsdfsdf"));
        list.add(new Book("Of Human Bondage", "Somerset Maugham", ""));
        list.add(new Book("The Bluest Eye", "Toni Morrison", ""));
        list.add(new Book("I Am Ok You Are Ok", "Thomas Harris", ""));
        list.add(new Book("Magnificent Obsession", "Lloyd C Douglas", ""));
        list.add(new Book("100 Years of Solitude", "Gabriel Garcia Marquez", ""));
        list.add(new Book("What the Dog Saw", "Malcolm Gladwell", ""));
        list.add(new Book("The Fakir", "Ruzbeh Bharucha", ""));
        list.add(new Book("The Hobbit", "J.R.R. Tolkien", ""));
        list.add(new Book("Strange Life of Ivan Osokin", "P.D. Ouspensky", ""));
        list.add(new Book("The Hunt for Red October", "Tom Clancy", ""));
        list.add(new Book("Coma", "Robin Cook", ""));
        ObservableList data = FXCollections.observableList(list);
        return data;
    }


    private class AddButtonListener implements EventHandler {
        @Override
        public void handle(Event e) {
            // Create a new row after last row
            Book book = new Book("...", "...", "...");
            data.add(book);
            int row = data.size() - 1;
            // Select the new row
            table.requestFocus();
            table.getSelectionModel().select(row);
            table.getFocusModel().focus(row);
            actionStatus.setText("New book: Enter title and author. Press .");
        }
    }

    private class DeleteButtonListener implements EventHandler {
        @Override
        public void handle(Event e) {
            // Get selected row and delete
            int ix = table.getSelectionModel().getSelectedIndex();
            Book book = (Book) table.getSelectionModel().getSelectedItem();
            data.remove(ix);
            actionStatus.setText("Deleted: " + book.toString());
            // Select a row
            if (table.getItems().size() == 0) {
                actionStatus.setText("No data in table !");
                return;
            }
            if (ix != 0) {
                ix = ix - 1;
            }
            table.requestFocus();
            table.getSelectionModel().select(ix);
            table.getFocusModel().focus(ix);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
