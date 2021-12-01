package com.bairontapia.projects.cuidamed.mvc.disease;

import com.bairontapia.projects.cuidamed.disease.Disease;
import com.bairontapia.projects.cuidamed.disease.DiseaseDAO;
import com.bairontapia.projects.cuidamed.disease.prescription.Prescription;
import com.bairontapia.projects.cuidamed.disease.prescription.PrescriptionDAO;
import com.bairontapia.projects.cuidamed.disease.sickelderly.SickElderly;
import com.bairontapia.projects.cuidamed.disease.sickelderly.SickElderlyDAO;
import com.bairontapia.projects.cuidamed.person.elder.Elder;
import com.bairontapia.projects.cuidamed.person.elder.ElderDAO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Getter;

@Getter
public class PrescriptionView {

  @FXML
  private ComboBox<String> rut;
  @FXML
  private ComboBox<Disease> diseaseNameComboBox;
  @FXML
  private DatePicker diagnosisDatePicker;
  @FXML
  private DatePicker prescriptionDate;
  @FXML
  private TextArea description;
  @FXML
  private CheckBox isPrescriptionEnabled;

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

    if (rut.getSelectionModel().isEmpty() || diseaseNameComboBox.getSelectionModel().isEmpty()
        || diagnosisDatePicker.getValue() == null) {
      Alert a = new Alert(AlertType.WARNING);
      a.setContentText("¡¡Valores vacíos!!");
      a.show();
    } else {
      SickElderly sickElderly = SickElderly.createInstance(rut.getValue(),
          diseaseNameComboBox.getSelectionModel().getSelectedItem().disease_name(),
          Date.valueOf(diagnosisDatePicker.getValue()));
      SickElderlyDAO.getInstance().save(sickElderly);
      if (isPrescriptionEnabled.isSelected()) {
        if (prescriptionDate.getValue() == null || description.getText().equals("")) {
          Alert a = new Alert(AlertType.WARNING);
          a.setContentText("¡¡Valores vacíos!!");
          a.show();
        } else {
          Prescription prescription = Prescription.createInstance(rut.getValue(),
              diseaseNameComboBox.getSelectionModel().getSelectedItem().disease_name(),
              Date.valueOf(diagnosisDatePicker.getValue()), description.getText());
          PrescriptionDAO.getInstance().save(prescription);
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
