import assets.Asset;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class JsonReader {

    private static final String JSON_PATH = "assets.json";

    public List<Asset> loadAssetsFromJson() {
        try (Reader reader = new FileReader(JSON_PATH)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Asset>>() {}.getType();
            List<Asset> assets = gson.fromJson(reader, listType);

            return assets != null ? assets : Collections.emptyList();

        } catch (Exception e) {
            System.err.println("JSON not found or invalid. Returning empty list.");
            return Collections.emptyList();
        }
    }
}
