package com.bairontapia.projects.cuidamed;

import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFXApplication extends Application {

  public static void main(final String... args) {
    launch();
  }

  @Override
  public void start(final Stage stage) {
    stage.setTitle("Cuidamed");
    stage.setResizable(false);
    stage.show();
  }

}
