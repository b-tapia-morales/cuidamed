package com.bairontapia.projects.cuidamed.mvc.person;

import com.bairontapia.projects.cuidamed.mappings.bloodtype.BloodType;
import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import com.bairontapia.projects.cuidamed.mappings.healthcaresystem.HealthCare;
import com.bairontapia.projects.cuidamed.person.address.Address;
import com.bairontapia.projects.cuidamed.person.elder.Elder;
import com.bairontapia.projects.cuidamed.person.elder.ElderDAO;
import com.bairontapia.projects.cuidamed.person.responsible.Responsible;
import com.bairontapia.projects.cuidamed.person.responsible.ResponsibleDAO;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import java.awt.Checkbox;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class ResponsibleView {

  @Setter private Responsible responsible;
  @FXML private Button updateButton;
  @FXML private TextField postalCode;
  @FXML private TextField fixedPhone;
  @FXML private TextField region;
  @FXML private TextField province;
  @FXML private TextField commune;
  @FXML private TextField street;
  @FXML private TextField number;
  @FXML private TextField rut;
  @FXML private TextField name;
  @FXML private TextField age;
  @FXML private TextField mobilePhone;
  @FXML private TextField lastName;
  @FXML private TextField secondLastName;
  @FXML private DatePicker birthDatePicker;
  @FXML private ComboBox<Gender> genderComboBox;
  @FXML private TextField elderRut;
  @FXML private TextField elderFirstName;
  @FXML private TextField elderLastName;
  @FXML private TextField elderSecondLastName;
  @FXML private TextField elderBirthDate;
  @FXML private TextField elderAge;
  @FXML private TextField elderGender;
  @FXML private CheckBox isActiveCheckBox;
  @FXML private TextField admissionDate;

  public void initialize() throws SQLException, IOException {
    initializeComboBoxes();
    final Responsible responsible = ResponsibleDAO.getInstance().find("13371799-4").orElseThrow();
    setResponsible(responsible);
    fillResponsibleFields(responsible);
  }

  private void initializeComboBoxes() {
    genderComboBox.setItems(FXCollections.observableArrayList(Gender.getValues()));
  }

  private void fillResponsibleFields(final Responsible responsible) {
    rut.setText(RutUtils.format(responsible.rut()));
    name.setText(responsible.firstName());
    lastName.setText(responsible.lastName());
    secondLastName.setText(responsible.secondLastName());
    birthDatePicker.setValue(responsible.birthDate());
    age.setText(responsible.age().toString());
    genderComboBox.getSelectionModel().select(responsible.gender());
    mobilePhone.setText("+56 9 " + responsible.mobilePhone());
  }

  private void fillAddressFields(final Address address) {}
}
