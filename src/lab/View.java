package lab;

/**
 * Created by Kristina Riadchenko on 21.05.2017.
 */
public enum View {
    Agents {
        @Override
        public String getPath() {
            return "/view/agents.fxml";
        }
    },
    Deliveries {
        @Override
        public String getPath() {
            return "/view/deliveries.fxml";
        }
    },
    NewDelivery {
        @Override
        public String getPath() {
            return "/view/new_delivery.fxml";
        }
    },
    Home {
        @Override
        public String getPath() {
            return "/view/home.fxml";
        }
    },
    Main {
        @Override
        public String getPath() {
            return "/view/main.fxml";
        }
    },
    Goods {
        @Override
        public String getPath() {
            return "/view/goods.fxml";
        }
    };

    public abstract String getPath();
}
