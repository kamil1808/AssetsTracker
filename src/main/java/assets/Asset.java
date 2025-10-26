package assets;

public class Asset {
    protected String category;
    protected String name;
    protected String code;
    protected double latestValue;
    protected double actualValue;

    Asset(String category, String name, String code, double latestValue, double actualValue) {
        this.category = category;
        this.name = name;
        this.code = code;
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

    public double getLatestValue() {
        return latestValue;
    }

    public double getActualValue() {
        return actualValue;
    }
}

