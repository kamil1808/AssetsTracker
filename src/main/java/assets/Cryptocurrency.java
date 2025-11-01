package assets;

public class Cryptocurrency extends Asset {
    Cryptocurrency(String category, String name, String code, double amount, String unit, double latestValue, double actualValue) {
        super(category, name, code, amount, unit, latestValue, actualValue);
    }
}
