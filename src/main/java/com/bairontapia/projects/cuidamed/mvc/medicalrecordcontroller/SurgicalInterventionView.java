package com.bairontapia.projects.cuidamed.mvc.medicalrecordcontroller;

import com.bairontapia.projects.cuidamed.mappings.severity.Severity;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SurgicalInterventionView {

  @FXML
  private DatePicker datePicker;
  @FXML
  private TextField textHospital;
  @FXML
  private ComboBox<Severity> severityComboBox;
  @FXML
  private TextArea textDescription;

  public void initialize() {
    severityComboBox.setItems(FXCollections.observableArrayList(Severity.getValues()));

  }

}
