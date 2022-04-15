package com.bairontapia.projects.cuidamed;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class JavaFXApplication extends Application {

    public static void main(final String... args) {
        launch();
    }

    @Override
    public void start(final Stage stage) throws IOException {
        final Parent root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource("/fxml/main_window.fxml")));
        final var scene = new Scene(root);
        stage.setTitle("Cuidamed");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
