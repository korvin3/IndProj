package lab.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import lab.Main;
import lab.View;
import lab.controller.FXController;

import java.io.IOException;

/**
 * Created by Kristina Riadchenko on 21.05.2017.
 */
public class FXUtils {
    /**
     * Warning! Use this method with cautious.
     */
    public static void setCurrentView(View view) {
        Main.getMainStage().getScene().setRoot(loadView(view));
    }

    /**
     * Main stage will be used by default.
     * See {@link Main#getMainStage()}
     */
    public static Parent loadView(View view) {
        return loadView(view.getPath(), Main.getMainStage());
    }

    /**
     * Main stage will be used by default.
     * See {@link Main#getMainStage()}
     */
    public static Parent loadView(String viewUrl) {
        return loadView(viewUrl, Main.getMainStage());
    }

    public static Parent loadView(View view, Stage stage) {
        return loadView(view.getPath(), stage);
    }

    public static Parent loadView(String viewUrl, Stage stage) {
        FXMLLoader loader = new FXMLLoader(FXUtils.class.getResource(viewUrl));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (stage == null) {
            return root;
        }
        Object controller = loader.getController();
        if (controller instanceof FXController) {
            ((FXController) controller).setStage(stage);
        }
        return root;
    }
}