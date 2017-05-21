package lab;

/**
 * Created by Roman Kolesnik on 21.05.2017.
 */
public enum DeliveryStatus {
    Delivered {
        @Override
        public String value() {
            return "D";
        }

        @Override
        public String toString() {
            return "Доставлено";
        }
    },
    Sent {
        @Override
        public String value() {
            return "S";
        }

        @Override
        public String toString() {
            return "В пути";
        }
    },
    Canceled {
        @Override
        public String value() {
            return "C";
        }

        @Override
        public String toString() {
            return "Отказ";
        }
    };

    public abstract String value();

    public static DeliveryStatus fromValue(String value) {
        if (Delivered.value().equals(value)) {
            return Delivered;
        } else if (Sent.value().equals(value)) {
            return Sent;
        } else if (Canceled.value().equals(value)) {
            return Canceled;
        } else throw new IllegalArgumentException("value " + value + " is unsupported");
    }
}
