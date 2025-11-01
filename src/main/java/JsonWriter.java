import assets.Asset;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonWriter {

    private static final String OUTPUT_PATH = "assets.json"; // lokalnie w projekcie

    public void saveAssetsToJson(List<Asset> assets) {
        try (FileWriter writer = new FileWriter(OUTPUT_PATH)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(assets, writer);
            System.out.println("Assets saved to " + OUTPUT_PATH);
        } catch (IOException e) {
            System.err.println("Error saving JSON: " + e.getMessage());
        }
    }
}