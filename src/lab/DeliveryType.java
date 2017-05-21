package lab;

/**
 * Created by Roman Kolesnik on 21.05.2017.
 */
public enum DeliveryType {
    ToWarehouse {
        @Override
        public String value() {
            return "A";
        }

        @Override
        public String toString() {
            return "На склад";
        }
    },
    FromWarehouse {
        @Override
        public String value() {
            return "R";
        }

        @Override
        public String toString() {
            return "Со склада";
        }
    };

    public abstract String value();

    public static DeliveryType fromValue(String value) {
        if (ToWarehouse.value().equals(value)) {
            return ToWarehouse;
        } else if (FromWarehouse.value().equals(value)) {
            return FromWarehouse;
        } else throw new IllegalArgumentException("value " + value + " is unsupported");
    }
}
