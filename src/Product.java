import java.io.Serializable;

public class Product implements Serializable {

    public static final int NAME_LENGTH = 35;
    public static final int DESC_LENGTH = 75;
    public static final int ID_LENGTH = 6;

    private String name;
    private String description;
    private String id;
    private double cost;

    public Product(String name, String description, String id, double cost) {
        this.name = pad(name, NAME_LENGTH);
        this.description = pad(description, DESC_LENGTH);
        this.id = pad(id, ID_LENGTH);
        this.cost = cost;
    }

    public static String pad(String value, int length) {
        if (value.length() >= length) return value.substring(0, length);
        return String.format("%-" + length + "s", value);
    }

    public String getFixedRecord() {
        return name + description + id + String.format("%10.2f", cost);
    }

    @Override
    public String toString() {
        return name.trim() + " | " + description.trim() + " | " + id.trim() + " | $" + cost;
    }
}
