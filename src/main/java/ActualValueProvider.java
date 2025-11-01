import assets.Asset;

public class ActualValueProvider {

    public double getActualAssetValue(Asset asset) {
        return asset.getCurrentAssetPrice();
    }
}
