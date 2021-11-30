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
import com.bairontapia.projects.cuidamed.person.address.Address;
import com.bairontapia.projects.cuidamed.person.address.AddressDAO;
import com.bairontapia.projects.cuidamed.person.elder.Elder;
import com.bairontapia.projects.cuidamed.person.elder.ElderDAO;
import com.bairontapia.projects.cuidamed.person.responsible.Responsible;
import com.bairontapia.projects.cuidamed.person.responsible.ResponsibleDAO;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import java.io.IOException;
import java.sql.Date;
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

  private final StringBuilder stringBuilder = new StringBuilder();

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

  private static void appendEmptyField(final StringBuilder stringBuilder, String panelName) {
    stringBuilder.append("Hay campos vacíos o sin selección en panel de ")
        .append(panelName).append(".\n");
  }

  private static void appendFieldTooShort(final StringBuilder stringBuilder,
      String panelName) {
    stringBuilder
        .append("Hay campos de texto cuyos valores ingresados no son de largo suficiente ")
        .append("(largo mínimo: 3 caracteres) en panel de ").append(panelName).append(".\n");
  }

  private static void appendFieldIncorrect(final StringBuilder stringBuilder,
      String panelName, String fieldName, String motive) {
    stringBuilder
        .append("El valor ingresado en el campo ").append(fieldName).append(" en panel de ")
        .append(panelName).append(" está incorrecto. La razón: ").append(motive).append("\n");
  }

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
  public void onButtonPressed() throws SQLException, IOException {
    stringBuilder.setLength(0);
    trimFields();
    appendEmptyFieldErrors();
    if (anyFieldIsEmpty()) {
      System.out.println(stringBuilder);
      return;
    }
    appendFieldTooShortErrors();
    if (anyFieldIsTooShort()) {
      System.out.println(stringBuilder);
      return;
    }
    appendFieldIncorrectErrors();
    if (anyFieldIsIncorrect()) {
      System.out.println(stringBuilder);
      return;
    }
    final var rutField = rut.getText();
    if (ElderDAO.getInstance().find(rutField).isPresent()) {
      System.out.println("Adulto mayor ya está en la base de datos");
      return;
    }
    final var responsibleRutField = responsibleRut.getText();
    if (ResponsibleDAO.getInstance().find(responsibleRutField).isPresent()) {
      System.out.println("Responsable ya está en la base de datos");
      return;
    }
    final var elder = createElder();
    final var responsible = createResponsible();
    final var address = createAddress();
    ResponsibleDAO.getInstance().save(responsible);
    AddressDAO.getInstance().save(address);
    ElderDAO.getInstance().save(elder);
    System.out.println(stringBuilder);
  }

  private Elder createElder() {
    final var rutField = rut.getText();
    final var nameField = name.getText();
    final var lastNameField = lastName.getText();
    final var secondLastNameField = secondLastName.getText();
    final var birthDateField = birthDate.getValue();
    final var genderCode = gender.getSelectionModel().getSelectedItem().getIndex();
    final var isActiveField = isActive.isSelected();
    final var admissionDateField = admissionDate.getValue();
    final var responsibleRutField = responsibleRut.getText();
    return Elder.createInstance(rutField, nameField, lastNameField, secondLastNameField,
        Date.valueOf(birthDateField), (short) genderCode, isActiveField,
        Date.valueOf(admissionDateField), responsibleRutField);
  }

  private Responsible createResponsible() {
    final var rutField = responsibleRut.getText();
    final var nameField = responsibleName.getText();
    final var lastNameField = responsibleLastName.getText();
    final var secondLastNameField = responsibleSecondLastName.getText();
    final var birthDateField = responsibleBirthDate.getValue();
    final var genderCode = responsibleGender.getSelectionModel().getSelectedItem().getIndex();
    final var mobilePhoneField = Integer.parseInt(responsibleMobilePhone.getText());
    return Responsible.createInstance(rutField, nameField, lastNameField, secondLastNameField,
        Date.valueOf(birthDateField), (short) genderCode, mobilePhoneField);
  }

  private Address createAddress() {
    final var rutField = responsibleRut.getText();
    final var communeField = communeComboBox.getSelectionModel().getSelectedItem().id();
    final var streetField = street.getText();
    final var numberField = (short) Integer.parseInt(number.getText());
    final var postalCodeField =
        postalCode.getText().isEmpty() ? null : Integer.parseInt(postalCode.getText());
    final var fixedPhoneField =
        fixedPhone.getText().isEmpty() ? null : Integer.parseInt(fixedPhone.getText());
    return Address.createInstance(communeField, streetField, numberField, postalCodeField,
        fixedPhoneField, rutField);
  }

  private void trimFields() {
    trimElderFields();
    trimResponsibleFields();
    trimAddressFields();
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

  private void appendEmptyFieldErrors() {
    appendElderEmptyFieldErrors();
    appendResponsibleEmptyFieldErrors();
    appendAddressEmptyFieldErrors();
  }

  private void appendFieldTooShortErrors() {
    appendElderFieldsTooShort();
    appendResponsibleFieldsTooShort();
    appendAddressFieldsTooShort();
  }

  private void appendFieldIncorrectErrors() {
    appendElderFieldsIncorrect();
    appendResponsibleFieldsIncorrect();
    appendAddressFieldsIncorrect();
  }

  private void appendElderEmptyFieldErrors() {
    if (StringUtils.isBlank(rut.getText()) ||
        StringUtils.isBlank(name.getText()) ||
        StringUtils.isBlank(lastName.getText()) ||
        StringUtils.isBlank(secondLastName.getText()) ||
        birthDate.getValue() == null ||
        gender.getSelectionModel().isEmpty() ||
        admissionDate.getValue() == null) {
      appendEmptyField(stringBuilder, "Adulto mayor");
    }
  }

  private void appendResponsibleEmptyFieldErrors() {
    if (StringUtils.isBlank(responsibleRut.getText()) ||
        StringUtils.isBlank(responsibleName.getText()) ||
        StringUtils.isBlank(responsibleLastName.getText()) ||
        StringUtils.isBlank(responsibleSecondLastName.getText()) ||
        responsibleBirthDate.getValue() == null ||
        responsibleGender.getSelectionModel().isEmpty() ||
        StringUtils.isBlank(responsibleMobilePhone.getText())) {
      appendEmptyField(stringBuilder, "Responsable");
    }
  }

  private void appendAddressEmptyFieldErrors() {
    if (regionComboBox.getSelectionModel().isEmpty() ||
        provinceComboBox.getSelectionModel().isEmpty() ||
        communeComboBox.getSelectionModel().isEmpty() ||
        StringUtils.isBlank(street.getText()) ||
        StringUtils.isBlank(number.getText())) {
      appendEmptyField(stringBuilder, "Dirección");
    }
  }

  private void appendElderFieldsTooShort() {
    if (rut.getText().length() < 9 ||
        name.getText().length() < 4 ||
        lastName.getText().length() < 4 ||
        secondLastName.getText().length() < 4) {
      appendFieldTooShort(stringBuilder, "Adulto mayor");
    }
  }

  private void appendResponsibleFieldsTooShort() {
    if (responsibleRut.getText().length() < 9 ||
        responsibleName.getText().length() < 4 ||
        responsibleLastName.getText().length() < 4 ||
        responsibleSecondLastName.getText().length() < 4) {
      appendFieldTooShort(stringBuilder, "Responsable");
    }
  }

  private void appendAddressFieldsTooShort() {
    if (street.getText().length() < 4) {
      appendFieldTooShort(stringBuilder, "Dirección");
    }
  }

  private void appendElderFieldsIncorrect() {
    final var panelName = "Adulto mayor";
    final var rutField = rut.getText();
    final var birthDateField = birthDate.getValue();
    final var admissionDateField = admissionDate.getValue();
    if (!RutUtils.isValid(rutField)) {
      appendFieldIncorrect(stringBuilder, panelName, "Rut", "el formato del Rut es inválido.");
    }
    final var now = LocalDate.now();
    final var age = Period.between(birthDateField, now).getYears();
    if (age < 65) {
      appendFieldIncorrect(stringBuilder, panelName, "Fecha de nacimiento",
          "la edad del adulto mayor debe ser de 65 años o más.");
    }
    final var years = Period.between(admissionDateField, now).getYears();
    if (years > 5) {
      appendFieldIncorrect(stringBuilder, panelName, "Fecha de admisión",
          "la fecha de admisión no puede superar los 5 años de antigüedad.");
    }
  }

  private void appendResponsibleFieldsIncorrect() {
    final var panelName = "Responsable";
    final var rutField = responsibleRut.getText();
    final var birthDateField = responsibleBirthDate.getValue();
    final var mobilePhoneField = responsibleMobilePhone.getText();
    final var now = LocalDate.now();
    if (!RutUtils.isValid(rutField)) {
      appendFieldIncorrect(stringBuilder, panelName, "Rut", "el formato del Rut es inválido");
    }
    final var age = Period.between(birthDateField, now).getYears();
    if (age < 18 || age > 65) {
      appendFieldIncorrect(stringBuilder, panelName, "Fecha de nacimiento",
          "la edad del responsable debe estar entre 18 y 65 años");
    }
    if (!mobilePhoneField.matches("[1-9][0-9]{7}")) {
      appendFieldIncorrect(stringBuilder, panelName, "Teléfono móvil",
          "el teléfono móvil debe tener 8 dígitos");
    }
  }

  private void appendAddressFieldsIncorrect() {
    final var panelName = "Dirección";
    final var numberField = number.getText();
    final var postalCodeField = postalCode.getText();
    final var fixedPhoneField = fixedPhone.getText();
    if (!numberField.matches("[1-9][0-9]{0,4}")) {
      appendFieldIncorrect(stringBuilder, panelName, "Número",
          "El número de residencia debe tener a lo menos 1 dígito.");
    }
    if ((!StringUtils.isBlank(postalCodeField) && !postalCodeField.matches("[1-9][0-9]{6}"))) {
      appendFieldIncorrect(stringBuilder, panelName, "Código postal",
          "El código postal debe tener 9 dígitos.");
    }
    if (!StringUtils.isBlank(fixedPhoneField) && !fixedPhoneField.matches("[1-9][0-9]{5}")) {
      appendFieldIncorrect(stringBuilder, panelName, "Teléfono fijo",
          "El teléfono fijo debe tener 6 dígitos.");
    }
  }

  private boolean anyFieldIsEmpty() {
    return areElderFieldsEmpty() || areResponsibleFieldsEmpty() || areAddressFieldsEmpty();
  }

  private boolean anyFieldIsTooShort() {
    return areElderFieldsTooShort() || areResponsibleFieldsTooShort()
        || areAddressFieldsTooShort();
  }

  private boolean anyFieldIsIncorrect() {
    return areElderFieldsIncorrect() || areResponsibleFieldsIncorrect() ||
        areAddressFieldsIncorrect();
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
    return rutField.length() < 9 || nameField.length() < 4 || lastNameField.length() < 4 ||
        secondLastNameField.length() < 4;
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
    return regionComboBox.getSelectionModel().isEmpty() ||
        provinceComboBox.getSelectionModel().isEmpty() ||
        communeComboBox.getSelectionModel().isEmpty() ||
        StringUtils.isBlank(street.getText()) || StringUtils.isBlank(number.getText());
  }

  private boolean areAddressFieldsTooShort() {
    return street.getText().length() < 4;
  }

  private boolean areAddressFieldsIncorrect() {
    final var numberField = number.getText();
    final var postalCodeField = postalCode.getText();
    final var fixedPhoneField = fixedPhone.getText();
    return !numberField.matches("[1-9][0-9]{0,4}") ||
        (!StringUtils.isBlank(postalCodeField) && !postalCodeField.matches("[1-9][0-9]{6}")) ||
        (!StringUtils.isBlank(fixedPhoneField) && !fixedPhoneField.matches("[1-9][0-9]{5}"));
  }

}
