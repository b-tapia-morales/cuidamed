package com.bairontapia.projects.cuidamed.mvc.disease;

import com.bairontapia.projects.cuidamed.disease.Disease;
import com.bairontapia.projects.cuidamed.disease.DiseaseDAO;
import com.bairontapia.projects.cuidamed.disease.prescription.Diagnostic;
import com.bairontapia.projects.cuidamed.disease.prescription.DiagnosticDAO;
import com.bairontapia.projects.cuidamed.disease.sickelderly.SickElderly;
import com.bairontapia.projects.cuidamed.disease.sickelderly.SickElderlyDAO;
import com.bairontapia.projects.cuidamed.person.elder.Elder;
import com.bairontapia.projects.cuidamed.person.elder.ElderDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedHashSet;

@Getter
public class PrescriptionView {

  @FXML private ComboBox<String> rut;
  @FXML private ComboBox<Disease> diseaseNameComboBox;
  @FXML private DatePicker diagnosisDatePicker;
  @FXML private DatePicker prescriptionDate;
  @FXML private TextArea description;
  @FXML private CheckBox isPrescriptionEnabled;

  public void initialize() throws SQLException, IOException {
    initializeComboBoxes();
  }

  public void initializeComboBoxes() throws SQLException, IOException {
    description.setDisable(true);
    prescriptionDate.setDisable(true);
    diseaseNameComboBox.setItems(
        FXCollections.observableArrayList(DiseaseDAO.getInstance().findAll()));
    final var set = ElderDAO.getInstance().findAll();
    final var setNew = new LinkedHashSet<String>();
    for (Elder e : set) {
      setNew.add(e.rut());
    }
    rut.setItems(FXCollections.observableArrayList(setNew));
  }

  public void addedPrescription(ActionEvent actionEvent) throws SQLException, IOException {
    Node source = (Node) actionEvent.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();

    if (rut.getSelectionModel().isEmpty()
        || diseaseNameComboBox.getSelectionModel().isEmpty()
        || diagnosisDatePicker.getValue() == null) {
      Alert a = new Alert(AlertType.WARNING);
      a.setContentText("¡¡Valores vacíos!!");
      a.show();
    } else {
      SickElderly sickElderly =
          SickElderly.createInstance(
              rut.getValue(),
              diseaseNameComboBox.getSelectionModel().getSelectedItem().name(),
              Date.valueOf(diagnosisDatePicker.getValue()));
      SickElderlyDAO.getInstance().save(sickElderly);
      if (isPrescriptionEnabled.isSelected()) {
        if (prescriptionDate.getValue() == null || description.getText().equals("")) {
          Alert a = new Alert(AlertType.WARNING);
          a.setContentText("¡¡Valores vacíos!!");
          a.show();
        } else {
          Diagnostic diagnostic =
              Diagnostic.createInstance(
                  rut.getValue(),
                  diseaseNameComboBox.getSelectionModel().getSelectedItem().name(),
                  Date.valueOf(diagnosisDatePicker.getValue()),
                  description.getText());
          DiagnosticDAO.getInstance().save(diagnostic);
        }
      }
    }
  }

  public void onClick(MouseEvent mouseEvent) {
    if (isPrescriptionEnabled.isSelected()) {
      description.setDisable(false);
      prescriptionDate.setDisable(false);
    } else {
      description.setDisable(true);
      prescriptionDate.setDisable(true);
    }
  }
}
