package com.bairontapia.projects.cuidamed.mvc.controller;

import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller {

  @FXML
  private AnchorPane layout;

  private AnchorPane dateElder;
  private AnchorPane allerElder;
  private AnchorPane surgicalIntervention;
  private AnchorPane responsible;
  private AnchorPane graphic;
  private AnchorPane addedElder;
  private AnchorPane datePerson;

  public void initialize() throws IOException {
    allerElder =
        FXMLLoader.load(
            Objects.requireNonNull(getClass().getResource("/fxml/allergy_dialog.fxml")));
    dateElder = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/elder.fxml")));
    surgicalIntervention =
        FXMLLoader.load(
            Objects.requireNonNull(
                getClass().getResource("/fxml/surgical_intervention_dialog.fxml")));
    responsible =
        FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/responsible.fxml")));
    graphic =
        FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/graphics.fxml")));
    addedElder =
        FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/elder_insert.fxml")));
    datePerson =
        FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/person.fxml")));

    layout.getChildren().add(allerElder);
    layout.getChildren().add(dateElder);
    layout.getChildren().add(surgicalIntervention);
    layout.getChildren().add(responsible);
    layout.getChildren().add(graphic);
    layout.getChildren().add(addedElder);
    layout.getChildren().add(datePerson);

    layout.getChildren().get(0).setVisible(false);
    layout.getChildren().get(1).setVisible(false);
    layout.getChildren().get(2).setVisible(false);
    layout.getChildren().get(3).setVisible(false);
    layout.getChildren().get(4).setVisible(false);
    layout.getChildren().get(5).setVisible(false);
    layout.getChildren().get(6).setVisible(false);
  }

  @FXML
  public void iniAllergyElder(MouseEvent mouseEvent) throws IOException {
    layout.getChildren().get(0).setVisible(true);
    layout.getChildren().get(1).setVisible(false);
    layout.getChildren().get(2).setVisible(false);
    layout.getChildren().get(3).setVisible(false);
    layout.getChildren().get(4).setVisible(false);
    layout.getChildren().get(5).setVisible(false);
    layout.getChildren().get(6).setVisible(false);
  }

  @FXML
  public void iniDateElder(MouseEvent mouseEvent) throws IOException {
    layout.getChildren().get(0).setVisible(false);
    layout.getChildren().get(1).setVisible(true);
    layout.getChildren().get(2).setVisible(false);
    layout.getChildren().get(3).setVisible(false);
    layout.getChildren().get(4).setVisible(false);
    layout.getChildren().get(5).setVisible(false);
    layout.getChildren().get(6).setVisible(false);
  }

  @FXML
  public void iniSurgicalInterventionElder(MouseEvent mouseEvent) throws IOException {
    layout.getChildren().get(0).setVisible(false);
    layout.getChildren().get(1).setVisible(false);
    layout.getChildren().get(2).setVisible(true);
    layout.getChildren().get(3).setVisible(false);
    layout.getChildren().get(4).setVisible(false);
    layout.getChildren().get(5).setVisible(false);
    layout.getChildren().get(6).setVisible(false);
  }

  @FXML
  public void iniResponsible(MouseEvent mouseEvent) throws IOException {
    layout.getChildren().get(0).setVisible(false);
    layout.getChildren().get(1).setVisible(false);
    layout.getChildren().get(2).setVisible(false);
    layout.getChildren().get(3).setVisible(true);
    layout.getChildren().get(4).setVisible(false);
    layout.getChildren().get(5).setVisible(false);
    layout.getChildren().get(6).setVisible(false);
  }

  @FXML
  public void iniGraphic(MouseEvent mouseEvent) throws IOException {
    layout.getChildren().get(0).setVisible(false);
    layout.getChildren().get(1).setVisible(false);
    layout.getChildren().get(2).setVisible(false);
    layout.getChildren().get(3).setVisible(false);
    layout.getChildren().get(4).setVisible(true);
    layout.getChildren().get(5).setVisible(false);
    layout.getChildren().get(6).setVisible(false);
  }

  @FXML
  public void iniAddedElder(MouseEvent mouseEvent) throws IOException {
    layout.getChildren().get(0).setVisible(false);
    layout.getChildren().get(1).setVisible(false);
    layout.getChildren().get(2).setVisible(false);
    layout.getChildren().get(3).setVisible(false);
    layout.getChildren().get(4).setVisible(false);
    layout.getChildren().get(5).setVisible(true);
    layout.getChildren().get(6).setVisible(false);
  }

  public void iniDatePerson(MouseEvent mouseEvent) {
    layout.getChildren().get(0).setVisible(false);
    layout.getChildren().get(1).setVisible(false);
    layout.getChildren().get(2).setVisible(false);
    layout.getChildren().get(3).setVisible(false);
    layout.getChildren().get(4).setVisible(false);
    layout.getChildren().get(5).setVisible(false);
    layout.getChildren().get(6).setVisible(true);
  }
}
