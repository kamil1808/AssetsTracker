import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;

public class UserInterface {

    int testValue = 0;

    public void displayUI(Stage stage) {
        VBox layout = new VBox(10, getTopButtonsBox(), getTable(), getSummaryLabelsBox());
        layout.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(layout, 600, 400);
        stage.setTitle("AssetsTracker");
        stage.setScene(scene);
        stage.show();
    }

    private HBox getTopButtonsBox() {
        Button addButton = new Button("Add");
        addButton.setOnAction(click -> System.out.println("add"));

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(click -> System.out.println("delete"));

        Button saveButton = new Button("Save");
        saveButton.setOnAction(click -> System.out.println("save"));

        Button loadButton = new Button("Load");
        loadButton.setOnAction(click -> System.out.println("load"));

        HBox buttonBox = new HBox(10, addButton, deleteButton, saveButton, loadButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setStyle("-fx-alignment: center; -fx-padding: 10;");
        return buttonBox;
    }

    private TableView<String[]> getTable() {
        TableView<String[]> table = new TableView<>();
        table.setPrefHeight(200);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        for (int i = 1; i <= Columns.values().length; i++) {
            final int colIndex = i;
            TableColumn<String[], String> col = new TableColumn<>(Columns.getNameById(i));
            col.setCellValueFactory(data ->
                    new SimpleStringProperty(data.getValue()[colIndex - 1])
            );
            table.getColumns().add(col);
        }

        for (int i = 0; i < 4; i++) {
            String[] row = new String[6];
            Arrays.fill(row, i == 2 ? "a" : "b");
            table.getItems().add(row);
        }

        return table;
    }

    private HBox getSummaryLabelsBox() {
        Label label1 = new Label("Summary value: ");
        Label label2 = new Label("Info 1:");
        Label label3 = new Label("Info 2:");

        HBox summaryBox = new HBox(20, label1, label2, label3);
        summaryBox.setStyle("-fx-alignment: center; -fx-padding: 10; -fx-font-size: 16px;");
        return summaryBox;
    }
}

