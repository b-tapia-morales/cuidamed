package com.bairontapia.projects.cuidamed.mvc.medicalrecordcontroller;

import com.bairontapia.projects.cuidamed.mappings.severity.Severity;
import com.bairontapia.projects.cuidamed.medicalrecord.surgicalintervention.SurgicalIntervention;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;

@Getter
public class SurgicalInterventionView {

  @FXML
  private DatePicker datePicker;
  @FXML
  private TextField textHospital;
  @FXML
  private ComboBox<Severity> severityComboBox;
  @FXML
  private TextArea textDescription;

  private SurgicalIntervention surgicalIntervention;

  private String rut;

  public void initialize() {
    severityComboBox.setItems(FXCollections.observableArrayList(Severity.getValues()));

  }

  public void changes(String rut) {
    this.rut = rut;
  }

  @FXML
  public void addSurgicalIntervention(ActionEvent actionEvent) {
    Node source = (Node) actionEvent.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
    if (datePicker.getValue() == null || textHospital.getText().equals("") || severityComboBox
        .getSelectionModel().isEmpty() || textDescription.getText().equals("")) {
      Alert a = new Alert(AlertType.WARNING);
      a.setContentText("¡¡Valores vacios!!");
      a.show();
    } else {
      this.surgicalIntervention = SurgicalIntervention
          .createInstance(RutUtils.removeDots(rut.toLowerCase()),
              Date.valueOf(datePicker.getValue()),
              textHospital.getText(),
              (short) (severityComboBox.getSelectionModel().getSelectedIndex() + 1),
              textDescription.getText());
    }
  }

  @FXML
  public void cancelSurgicalIntervention(ActionEvent actionEvent) {
    Node source = (Node) actionEvent.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
  }
}
