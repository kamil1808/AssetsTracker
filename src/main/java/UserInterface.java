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
    PriceProvider priceProvider = new PriceProvider();
    private TableView<String[]> table;
    private Label summaryValueLabel;

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
        addButton.setOnAction(click -> addButtonAction());

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(click -> deleteButtonAction());

        Button saveButton = new Button("Save");
        saveButton.setOnAction(click -> saveButtonAction());

        Button loadButton = new Button("Refresh");
        loadButton.setOnAction(click -> refreshButtonAction());

        HBox buttonBox = new HBox(10, addButton, deleteButton, saveButton, loadButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setStyle("-fx-alignment: center; -fx-padding: 10;");
        return buttonBox;
    }

    private void addButtonAction() {
        System.out.println("Add button clicked");
    }

    private void deleteButtonAction() {
        System.out.println("Delete button clicked");
    }

    private void saveButtonAction() {
        System.out.println("Save button clicked");
    }

    private void refreshButtonAction() {
        System.out.println("Refresh button clicked");
        loadTableData();
        updateSummaryValueLabel();
    }

    private TableView<String[]> getTable() {
        table = new TableView<>();
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        for (int i = 1; i <= Columns.values().length; i++) {
            final int colIndex = i;
            TableColumn<String[], String> col = new TableColumn<>(Columns.getNameById(i));
            col.setCellValueFactory(data ->
                    new SimpleStringProperty(data.getValue()[colIndex - 1]));
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
            row[3] = String.valueOf(asset.getAmount());
            row[4] = asset.getUnit();

            double actualPrice = priceProvider.getActualAssetPrice(asset);
            row[5] = String.format("%.3f", actualPrice);

            double actualValue = actualPrice * asset.getAmount();
            row[6] = String.format("%.3f", actualValue);
            row[7] = String.format("%.3f", asset.getLatestValue());
            double change = actualValue - asset.getLatestValue();
            row[8] = (change > 0 ? "+" : "") + String.format("%.3f", change);
            table.getItems().add(row);

            asset.setLatestValue(actualPrice * asset.getAmount());
            newData.add(asset);
        }
        jsonWriter.saveAssetsToJson(newData);
    }

    private HBox getSummaryLabelsBox() {
        summaryValueLabel = new Label();
        updateSummaryValueLabel();

        HBox summaryBox = new HBox(20, summaryValueLabel);
        summaryBox.setStyle("-fx-alignment: center; -fx-padding: 10; -fx-font-size: 16px;");
        return summaryBox;
    }

    private void updateSummaryValueLabel() {
        double summary = calculateSummaryValue();
        summaryValueLabel.setText("Summary value: " + String.format("%.3f", summary));
    }

    private double calculateSummaryValue() {
        double summaryValue = 0.0;
        for (String[] row : table.getItems()) {
            try {
                summaryValue += Double.parseDouble(row[6].replace(",", "."));
            } catch (NumberFormatException ignored) {}
        }
        return summaryValue;
    }
}

