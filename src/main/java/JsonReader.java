import assets.Asset;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonReader {
    private static final String MAIN_JSON = "/assets.json";

    public List<Asset> loadAssetsFromJson() {
        try (InputStream is = getClass().getResourceAsStream(MAIN_JSON)) {
            if (is == null) {
                System.err.println("File " + MAIN_JSON + " not found in /resources");
                return List.of();
            }
            Reader reader = new InputStreamReader(is);
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Asset>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (Exception e) {
            System.err.println("Excepction while loading json " + MAIN_JSON + " " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }
}
