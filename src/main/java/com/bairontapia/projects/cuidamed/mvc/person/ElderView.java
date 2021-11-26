package com.bairontapia.projects.cuidamed.mvc.person;

import com.bairontapia.projects.cuidamed.mappings.bloodtype.BloodType;
import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import com.bairontapia.projects.cuidamed.mappings.healthcaresystem.HealthCare;
import com.bairontapia.projects.cuidamed.medicalrecord.MedicalRecordDAO;
import com.bairontapia.projects.cuidamed.person.ElderDAO;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lombok.Getter;

@Getter
public class ElderView {

  @FXML
  private TextField rutField;
  @FXML
  private TextField nameField;
  @FXML
  private TextField lastNameField;
  @FXML
  private TextField secondLastNameField;
  @FXML
  private DatePicker birthDatePicker;
  @FXML
  private ComboBox<Gender> genderComboBox;
  @FXML
  private CheckBox isActiveCheckBox;
  @FXML
  private DatePicker admissionDatePicker;
  @FXML
  private ComboBox<BloodType> bloodTypeComboBox;
  @FXML
  private ComboBox<HealthCare> healthCareComboBox;

  public void initialize() throws SQLException, IOException {
    genderComboBox.setItems(FXCollections.observableArrayList(Gender.getValues()));
    bloodTypeComboBox.setItems(FXCollections.observableArrayList(BloodType.getValues()));
    healthCareComboBox.setItems(FXCollections.observableArrayList(HealthCare.getValues()));
    final var elder = ElderDAO.getInstance().get("5875397-1").get();
    genderComboBox.getSelectionModel().select(elder.gender().ordinal());
    rutField.setText(elder.rut());
    nameField.setText(elder.firstName());
    lastNameField.setText(elder.lastName());
    secondLastNameField.setText(elder.secondLastName());
    birthDatePicker.setValue(elder.birthDate());
    if (elder.isActive()) isActiveCheckBox.setSelected(true);
    admissionDatePicker.setValue(elder.admissionDate());
  }




}
