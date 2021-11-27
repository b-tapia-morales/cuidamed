package com.bairontapia.projects.cuidamed.mvc.person;

import com.bairontapia.projects.cuidamed.mappings.bloodtype.BloodType;
import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import com.bairontapia.projects.cuidamed.mappings.healthcaresystem.HealthCare;
import com.bairontapia.projects.cuidamed.person.Elder;
import com.bairontapia.projects.cuidamed.person.ElderDAO;
import com.bairontapia.projects.cuidamed.person.Responsible;
import com.bairontapia.projects.cuidamed.person.ResponsibleDAO;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

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
  private TextField ageField;
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
  @FXML
  private TextField responsibleRutField;
  @FXML
  private TextField responsibleFullNameField;
  @FXML
  private TextField responsibleBirthDateField;
  @FXML
  private TextField responsibleAgeField;
  @FXML
  private TextField responsibleSexField;
  @FXML
  private TextField responsibleMobilePhoneField;

  public void initialize() throws SQLException, IOException {
    genderComboBox.setItems(FXCollections.observableArrayList(Gender.getValues()));
    bloodTypeComboBox.setItems(FXCollections.observableArrayList(BloodType.getValues()));
    healthCareComboBox.setItems(FXCollections.observableArrayList(HealthCare.getValues()));
    final var elder = ElderDAO.getInstance().get("5875397-1").get();
    fillElderFields(elder);
    final var responsibleRut = elder.responsibleRut();
    final var responsible = ResponsibleDAO.getInstance().get(responsibleRut).get();
    fillResponsibleFields(responsible);
  }

  private void fillElderFields(final Elder elder) {
    rutField.setText(RutUtils.format(elder.rut()));
    nameField.setText(elder.firstName());
    lastNameField.setText(elder.lastName());
    secondLastNameField.setText(elder.secondLastName());
    birthDatePicker.setValue(elder.birthDate());
    ageField.setText(elder.age().toString());
    genderComboBox.getSelectionModel().select(elder.gender().ordinal());
    isActiveCheckBox.setSelected(elder.isActive());
    admissionDatePicker.setValue(elder.admissionDate());
  }

  private void fillResponsibleFields(final Responsible responsible) {
    responsibleRutField.setText(RutUtils.format(responsible.rut()));
    responsibleFullNameField.setText(StringUtils
        .joinWith(" ", responsible.firstName(), responsible.lastName(),
            responsible.secondLastName()));
    responsibleBirthDateField.setText(responsible.birthDate().toString());
    responsibleAgeField.setText(responsible.age().toString());
    responsibleSexField.setText(responsible.gender().toString());
    responsibleMobilePhoneField.setText("+56 9 " + responsible.mobilePhone());
  }


}
