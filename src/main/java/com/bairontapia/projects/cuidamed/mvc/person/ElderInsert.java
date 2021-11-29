package com.bairontapia.projects.cuidamed.mvc.person;

import com.bairontapia.projects.cuidamed.localization.Commune;
import com.bairontapia.projects.cuidamed.localization.CommuneDAO;
import com.bairontapia.projects.cuidamed.localization.Province;
import com.bairontapia.projects.cuidamed.localization.ProvinceDAO;
import com.bairontapia.projects.cuidamed.localization.Region;
import com.bairontapia.projects.cuidamed.localization.RegionDAO;
import com.bairontapia.projects.cuidamed.mappings.bloodtype.BloodType;
import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import com.bairontapia.projects.cuidamed.mappings.healthcaresystem.HealthCare;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class ElderInsert {

  @FXML
  private TextField rut;
  @FXML
  private TextField name;
  @FXML
  private TextField lastName;
  @FXML
  private TextField secondLastName;
  @FXML
  private DatePicker birthDate;
  @FXML
  private ComboBox<Gender> gender;
  @FXML
  private CheckBox isActive;
  @FXML
  private DatePicker admissionDate;
  @FXML
  private ComboBox<BloodType> bloodType;
  @FXML
  private ComboBox<HealthCare> healthCare;

  @FXML
  private TextField responsibleRut;
  @FXML
  private TextField responsibleName;
  @FXML
  private TextField responsibleLastName;
  @FXML
  private TextField responsibleSecondLastName;
  @FXML
  private DatePicker responsibleBirthDate;
  @FXML
  private ComboBox<Gender> responsibleGender;
  @FXML
  private TextField responsibleMobilePhone;

  @FXML
  private ComboBox<Region> regionComboBox;
  @FXML
  private ComboBox<Province> provinceComboBox;
  @FXML
  private ComboBox<Commune> communeComboBox;
  @FXML
  private TextField street;
  @FXML
  private TextField number;
  @FXML
  private TextField fixedPhone;
  @FXML
  private TextField postalCode;

  @FXML
  private Button addData;

  public void initialize() throws SQLException, IOException {
    initializeComboBoxes();
  }

  private void initializeComboBoxes() throws SQLException, IOException {
    final var regions = RegionDAO.getInstance().findAll();
    gender.setItems(FXCollections.observableArrayList(Gender.getValues()));
    bloodType.setItems(FXCollections.observableArrayList(BloodType.getValues()));
    healthCare.setItems(FXCollections.observableArrayList(HealthCare.getValues()));
    responsibleGender.setItems(FXCollections.observableArrayList(Gender.getValues()));
    regionComboBox.setItems(FXCollections.observableArrayList(regions));
  }

  @FXML
  public void onRegionSelection() throws SQLException, IOException {
    if (regionComboBox.getSelectionModel().isEmpty()) {
      return;
    }
    provinceComboBox.getItems().clear();
    communeComboBox.getItems().clear();
    final var region = regionComboBox.getSelectionModel().getSelectedItem();
    final var id = region.id();
    final var provinces = ProvinceDAO.getInstance().findAll(id);
    provinceComboBox.getItems().addAll(provinces);
  }

  @FXML
  public void onProvinceSelection() throws SQLException, IOException {
    if (provinceComboBox.getSelectionModel().isEmpty()) {
      return;
    }
    communeComboBox.getItems().clear();
    final var province = provinceComboBox.getSelectionModel().getSelectedItem();
    final var id = province.id();
    final var communes = CommuneDAO.getInstance().findAll(id);
    communeComboBox.getItems().addAll(communes);
  }

  @FXML
  public void onButtonPressed() {
    if (areElderFieldsEmpty()) {
      System.out.println("MALO: Datos del adulto mayor aún sin rellenar");
      return;
    }
    if (areElderFieldsTooShort()) {
      System.out.println("MALO: Datos del adulto mayor son muy cortos");
      return;
    }
    if (areElderFieldsIncorrect()) {
      System.out.println("MALO: Datos del adulto mayor son incorrectos");
    }
  }

  private void trimElderFields() {
    rut.setText(StringUtils.replace(StringUtils.trim(rut.getText()), ".", ""));
    name.setText(StringUtils.trim(name.getText()));
    lastName.setText(StringUtils.trim(lastName.getText()));
    secondLastName.setText(StringUtils.trim(secondLastName.getText()));
  }

  private void trimResponsibleFields() {
    responsibleRut
        .setText(StringUtils.replace(StringUtils.trim(responsibleRut.getText()), ".", ""));
    responsibleName.setText(StringUtils.trim(responsibleName.getText()));
    responsibleLastName.setText(StringUtils.trim(responsibleLastName.getText()));
    responsibleSecondLastName.setText(StringUtils.trim(responsibleSecondLastName.getText()));
    responsibleMobilePhone
        .setText(StringUtils.stripStart(StringUtils.trim(responsibleMobilePhone.getText()), "0"));
  }

  private void trimAddressFields() {
    street.setText(StringUtils.trim(street.getText()));
    number.setText(StringUtils.stripStart(StringUtils.trim(number.getText()), "0"));
    postalCode.setText(StringUtils.stripStart(StringUtils.trim(postalCode.getText()), "0"));
    fixedPhone.setText(StringUtils.stripStart(StringUtils.trim(fixedPhone.getText()), "0"));
  }

  private boolean areElderFieldsEmpty() {
    return StringUtils.isBlank(rut.getText()) || StringUtils.isBlank(name.getText()) ||
        StringUtils.isBlank(lastName.getText()) || StringUtils.isBlank(secondLastName.getText()) ||
        birthDate.getValue() == null || gender.getSelectionModel().isEmpty() ||
        admissionDate.getValue() == null;
  }

  private boolean areElderFieldsTooShort() {
    final var rutField = rut.getText();
    final var nameField = name.getText();
    final var lastNameField = lastName.getText();
    final var secondLastNameField = secondLastName.getText();
    return rutField.length() < 9 || nameField.length() < 4 || lastNameField.length() < 4 ||
        secondLastNameField.length() < 4;
  }

  private boolean areElderFieldsIncorrect() {
    final var rutField = rut.getText();
    final var birthDateField = birthDate.getValue();
    final var admissionDateField = admissionDate.getValue();
    final var now = LocalDate.now();
    final var age = Period.between(birthDateField, now).getYears();
    final var days = Period.between(admissionDateField, now).getDays();
    final var years = Period.between(admissionDateField, now).getYears();
    return !RutUtils.isValid(rutField) || age < 65 || age > 120 || days < 0 || years > 5;
  }

  private boolean areResponsibleFieldsEmpty() {
    return StringUtils.isBlank(responsibleRut.getText()) ||
        StringUtils.isBlank(responsibleName.getText()) ||
        StringUtils.isBlank(responsibleLastName.getText()) ||
        StringUtils.isBlank(responsibleSecondLastName.getText()) ||
        responsibleBirthDate.getValue() == null || gender.getSelectionModel().isEmpty() ||
        StringUtils.isBlank(responsibleMobilePhone.getText());
  }

  private boolean areResponsibleFieldsTooShort() {
    final var rutField = responsibleRut.getText();
    final var nameField = responsibleName.getText();
    final var lastNameField = responsibleLastName.getText();
    final var secondLastNameField = responsibleSecondLastName.getText();
    return rutField.length() < 9 || nameField.length() < 4 || lastNameField.length() < 4
        || secondLastNameField.length() < 4;
  }

  private boolean areResponsibleFieldsIncorrect() {
    final var rutField = responsibleRut.getText();
    final var birthDateField = responsibleBirthDate.getValue();
    final var mobilePhoneField = responsibleMobilePhone.getText();
    final var now = LocalDate.now();
    final var age = Period.between(birthDateField, now).getYears();
    return !RutUtils.isValid(rutField) || age < 18 || age > 65 ||
        !mobilePhoneField.matches("[1-9][0-9]{7}");
  }

  private boolean areAddressFieldsEmpty() {
    return StringUtils.isBlank(street.getText()) || StringUtils.isBlank(number.getText());
  }

  private boolean areAddressFieldsTooShort() {
    return street.getText().length() < 4;
  }

  private boolean areAddressFieldsIncorrect() {
    final var numberField = number.getText();
    final var postalCodeField = postalCode.getText();
    final var fixedPhoneField = fixedPhone.getText();
    return !numberField.matches("[1-9][0-9]?") ||
        (!StringUtils.isBlank(postalCodeField) && !postalCodeField.matches("[1-9][0-9]{6}")) ||
        (!StringUtils.isBlank(fixedPhoneField) && !fixedPhoneField.matches("[1-9][0-9]{5}"));
  }

}
