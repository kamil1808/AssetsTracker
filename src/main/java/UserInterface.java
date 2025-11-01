import assets.Asset;
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

import java.util.ArrayList;
import java.util.List;

public class UserInterface {

    JsonReader jsonReader = new JsonReader();
    JsonWriter jsonWriter = new JsonWriter();
    ActualValueProvider actualValueProvider = new ActualValueProvider();
    private TableView<String[]> table;

    public void displayUI(Stage stage) {
        VBox layout = new VBox(10, getTopButtonsBox(), getTable(), getSummaryLabelsBox());
        layout.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(layout, 900, 600);
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

        Button loadButton = new Button("Refresh");
        loadButton.setOnAction(click -> refreshValues());

        HBox buttonBox = new HBox(10, addButton, deleteButton, saveButton, loadButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setStyle("-fx-alignment: center; -fx-padding: 10;");
        return buttonBox;
    }

    private void refreshValues() {
        System.out.println("Refresh button clicked");
        loadTableData();
    }

    private TableView<String[]> getTable() {
        table = new TableView<>();
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        for (int i = 1; i <= Columns.values().length; i++) {
            final int colIndex = i;
            TableColumn<String[], String> col = new TableColumn<>(Columns.getNameById(i));
            col.setCellValueFactory(data ->
                    new SimpleStringProperty(data.getValue()[colIndex - 1])
            );
            table.getColumns().add(col);
        }

        loadTableData();

        table.setPlaceholder(new Label("No data to display"));
        table.setFixedCellSize(40);

        return table;
    }

    private void loadTableData() {
        table.getItems().clear();
        List<Asset> dataFromJson = jsonReader.loadAssetsFromJson();
        ArrayList<Asset> newData = new ArrayList<>();

        for (Asset asset : dataFromJson) {
            String[] row = new String[Columns.values().length];
            row[0] = asset.getCategory();
            row[1] = asset.getName();
            row[2] = asset.getCode();
            row[3] = String.valueOf(asset.getLatestValue());
            double actualValue = actualValueProvider.getActualAssetValue(asset);
            row[4] = String.valueOf(actualValue);
            asset.setLatestValue(actualValue);
            newData.add(asset);
            double change = asset.getActualValue() - asset.getLatestValue();
            row[5] = (change > 0 ? "+" : "") + change;
            table.getItems().add(row);
        }

        jsonWriter.saveAssetsToJson(newData);
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

