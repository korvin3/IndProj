package lab;

/**
 * Created by Roman Kolesnik on 21.05.2017.
 */
public enum View {
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
