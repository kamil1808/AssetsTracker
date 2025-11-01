import assets.Asset;

public class PriceProvider {

    public double getActualAssetPrice(Asset asset) {
        return asset.getCurrentAssetPrice();
    }
}
