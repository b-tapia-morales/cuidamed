package com.bairontapia.projects.cuidamed.mvc.disease;

import com.bairontapia.projects.cuidamed.disease.medication.Medication;
import com.bairontapia.projects.cuidamed.disease.medication.MedicationDAO;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class MedicationPrescriptionView {

  @FXML
  private DatePicker startDatePicker;
  @FXML
  private DatePicker endDatePicker;
  @FXML
  private TextField rut;
  @FXML
  private DatePicker prescriptionDatePicker;
  @FXML
  private ComboBox<Medication> medicationComboBox;

  public void initialize() throws SQLException, IOException {
    initializeComboBoxes();
  }

  public void initializeComboBoxes() throws SQLException, IOException {
    medicationComboBox.setItems(
        FXCollections.observableArrayList(MedicationDAO.getInstance().findAll()));
  }
}
