package assets;

public class Asset {
    protected String category;
    protected String name;
    protected String code;
    protected double amount;
    protected String unit;
    protected double latestValue;
    protected double actualValue;

    Asset(String category, String name, String code, double amount, String unit, double latestValue, double actualValue) {
        this.category = category;
        this.name = name;
        this.code = code;
        this.amount = amount;
        this.unit = unit;
        this.latestValue = latestValue;
        this.actualValue = actualValue;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public double getLatestValue() {
        return latestValue;
    }

    public void setLatestValue(double actualValue) {
        latestValue = actualValue;
    }

    public double getActualValue() {
        return actualValue;
    }

    public double getCurrentAssetPrice() {
        return Math.random() * 100;
    }
}

