package lab.datalayer;

/**
 * Created by Roman Kolesnik on 21.05.2017.
 */
public class Warehouse {
    private String id;
    private String name;
    private String town;

    public Warehouse(String id, String name, String town) {
        this.id = id;
        this.name = name;
        this.town = town;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTown() {
        return town;
    }

    @Override
    public String toString() {
        return getName();
    }
}