package Assets;

public abstract class Asset {
    double price;
    String code;

    protected double getValue() {
        return price;
    }
}
