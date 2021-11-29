package com.bairontapia.projects.cuidamed.mvc.medicalrecordcontroller;

import com.bairontapia.projects.cuidamed.mappings.allergytype.AllergyType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AllergyView {

  @FXML
  private ComboBox<AllergyType> comboBoxType;
  @FXML
  private TextField labelDetails;


  public void initialize() {
    comboBoxType.setItems(FXCollections.observableArrayList(AllergyType.getValues()));
  }

  public void addedAllergy(ActionEvent actionEvent) {
  }

  public void cancelAllergy(ActionEvent actionEvent) {
  }
}
