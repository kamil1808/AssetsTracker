public enum Columns {
    CATEGORY(1, "Category"),
    NAME(2, "Name"),
    CODE(3, "Code"),
    AMOUNT(4, "Amount"),
    UNIT(5, "Unit"),
    PRICE(6, "Price"),
    ACTUAL_VALUE(7, "Actual value"),
    LAST_VALUE(8, "Latest value"),
    CHANGE(9, "Change");

    final private int id;
    final private String name;

    Columns(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static String getNameById(int searchId) {
        for (Columns col : Columns.values()) {
            if (col.getId() == searchId) {
                return col.getName();
            }
        }
        return "Not found column with id " + searchId;
    }
}
