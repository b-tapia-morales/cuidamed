package com.bairontapia.projects.cuidamed.mvc.person;

import com.bairontapia.projects.cuidamed.localization.Commune;
import com.bairontapia.projects.cuidamed.localization.CommuneDAO;
import com.bairontapia.projects.cuidamed.localization.Province;
import com.bairontapia.projects.cuidamed.localization.ProvinceDAO;
import com.bairontapia.projects.cuidamed.localization.Region;
import com.bairontapia.projects.cuidamed.localization.RegionDAO;
import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import com.bairontapia.projects.cuidamed.person.address.Address;
import com.bairontapia.projects.cuidamed.person.address.AddressDAO;
import com.bairontapia.projects.cuidamed.person.carer.Carer;
import com.bairontapia.projects.cuidamed.person.carer.CarerDAO;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class CarerInsert {

  private final StringBuilder stringBuilder = new StringBuilder();
  @Setter
  private Carer carer;
  @FXML
  private TextField rut;
  @FXML
  private TextField name;
  @FXML
  private TextField lastName;
  @FXML
  private TextField secondLastName;
  @FXML
  private TextField mobilePhone;
  @FXML
  private DatePicker birthDate;
  @FXML
  private ComboBox<Gender> gender;
  @FXML
  private DatePicker hireDate;
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
  private TextField postalCode;
  @FXML
  private TextField fixedPhone;
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
    regionComboBox.setItems(FXCollections.observableArrayList(regions));
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
    if (CarerDAO.getInstance().find(rutField).isPresent()) {
      System.out.println("Cuidador ya está en la base de datos");
      return;
    }
    final var address = createAddress();
    final var carer = createCarer();
    CarerDAO.getInstance().save(carer);
    AddressDAO.getInstance().save(address);
  }

  private Carer createCarer() {
    final var rutField = rut.getText();
    final var nameField = name.getText();
    final var lastNameField = lastName.getText();
    final var secondLastNameField = secondLastName.getText();
    final var birthDateField = birthDate.getValue();
    final var genderCode = gender.getSelectionModel().getSelectedItem().getIndex();
    final var hireDateField = hireDate.getValue();
    final var mobilePhoneField = Integer.parseInt(mobilePhone.getText());
    return Carer.createInstance(rutField, nameField, lastNameField, secondLastNameField,
        Date.valueOf(birthDateField), (short) genderCode, mobilePhoneField,
        Date.valueOf(hireDateField));
  }

  private Address createAddress() {
    final var rutField = rut.getText();
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

  @FXML
  public void onRegionSelection() throws SQLException, IOException {
    if (regionComboBox.getSelectionModel().isEmpty()) {
      return;
    }
    provinceComboBox.getItems().clear();
    communeComboBox.getItems().clear();
    final var region = this.regionComboBox.getSelectionModel().getSelectedItem();
    final var id = region.id();
    final var provinces = ProvinceDAO.getInstance().findAll(id);
    this.provinceComboBox.getItems().addAll(provinces);
  }

  @FXML
  public void onProvinceSelection() throws SQLException, IOException {
    if (provinceComboBox.getSelectionModel().isEmpty()) {
      return;
    }
    communeComboBox.getItems().clear();
    final var province = this.provinceComboBox.getSelectionModel().getSelectedItem();
    final var id = province.id();
    final var communes = CommuneDAO.getInstance().findAll(id);
    this.communeComboBox.getItems().addAll(communes);
  }

  private void trimFields() {
    trimResponsibleFields();
    trimAddressFields();
  }

  private void trimResponsibleFields() {
    rut.setText(StringUtils.replace(StringUtils.trim(rut.getText()), ".", ""));
    name.setText(StringUtils.trim(name.getText()));
    lastName.setText(StringUtils.trim(lastName.getText()));
    secondLastName.setText(StringUtils.trim(secondLastName.getText()));
    mobilePhone.setText(StringUtils.stripStart(StringUtils.trim(mobilePhone.getText()), "0"));
  }

  private void trimAddressFields() {
    street.setText(StringUtils.trim(street.getText()));
    number.setText(StringUtils.stripStart(StringUtils.trim(number.getText()), "0"));
    postalCode.setText(StringUtils.stripStart(StringUtils.trim(postalCode.getText()), "0"));
    fixedPhone.setText(StringUtils.stripStart(StringUtils.trim(fixedPhone.getText()), "0"));
  }

  private void appendEmptyFieldErrors() {
    appendCarerEmptyFieldErrors();
    appendAddressEmptyFieldErrors();
  }

  private void appendFieldTooShortErrors() {
    appendCarerFieldsTooShort();
    appendAddressFieldsTooShort();
  }

  private void appendFieldIncorrectErrors() {
    appendCarerFieldsIncorrect();
    appendAddressFieldsIncorrect();
  }

  private void appendCarerEmptyFieldErrors() {
    if (StringUtils.isBlank(rut.getText()) ||
        StringUtils.isBlank(name.getText()) ||
        StringUtils.isBlank(lastName.getText()) ||
        StringUtils.isBlank(secondLastName.getText()) ||
        birthDate.getValue() == null ||
        gender.getSelectionModel().isEmpty() ||
        StringUtils.isBlank(mobilePhone.getText()) ||
        hireDate.getValue() == null) {
      appendEmptyField(stringBuilder, "Cuidador");
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

  private void appendCarerFieldsTooShort() {
    if (rut.getText().length() < 9 ||
        name.getText().length() < 4 ||
        lastName.getText().length() < 4 ||
        secondLastName.getText().length() < 4) {
      appendFieldTooShort(stringBuilder, "Cuidador");
    }
  }

  private void appendAddressFieldsTooShort() {
    if (street.getText().length() < 4) {
      appendFieldTooShort(stringBuilder, "Dirección");
    }
  }

  private void appendCarerFieldsIncorrect() {
    final var panelName = "Cuidador";
    final var rutField = rut.getText();
    final var birthDateField = birthDate.getValue();
    final var hireDateField = hireDate.getValue();
    final var mobilePhoneField = mobilePhone.getText();
    final var now = LocalDate.now();
    if (!RutUtils.isValid(rutField)) {
      appendFieldIncorrect(stringBuilder, panelName, "Rut", "el formato del Rut es inválido");
    }
    final var age = Period.between(birthDateField, now).getYears();
    if (age < 18 || age > 65) {
      appendFieldIncorrect(stringBuilder, panelName, "Fecha de nacimiento",
          "la edad del responsable debe estar entre 18 y 65 años");
    }
    final var days = Period.between(hireDateField, now).getDays();
    if (days < 0) {
      appendFieldIncorrect(stringBuilder, panelName, "Fecha de contrato",
          "la fecha de contrato no puede ser posterior a la fecha actual");
    }
    final var years = Period.between(hireDateField, now).getYears();
    if (years > 5) {
      appendFieldIncorrect(stringBuilder, panelName, "Fecha de contrato",
          "la cantidad de años desde la que el cuidador fue contratado no puede superar los 5 años");
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
    return areCarerFieldsEmpty() || areAddressFieldsEmpty();
  }

  private boolean anyFieldIsTooShort() {
    return areCarerFieldsTooShort() || areAddressFieldsTooShort();
  }

  private boolean anyFieldIsIncorrect() {
    return areCarerFieldsIncorrect() || areAddressFieldsIncorrect();
  }

  private boolean areCarerFieldsEmpty() {
    return StringUtils.isBlank(rut.getText())
        || StringUtils.isBlank(name.getText())
        || StringUtils.isBlank(lastName.getText())
        || StringUtils.isBlank(secondLastName.getText())
        || birthDate.getValue() == null
        || gender.getSelectionModel().isEmpty()
        || hireDate.getValue() == null;
  }

  private boolean areCarerFieldsTooShort() {
    final var rutField = rut.getText();
    final var nameField = name.getText();
    final var lastNameField = lastName.getText();
    final var secondLastNameField = secondLastName.getText();
    return rutField.length() < 9
        || nameField.length() < 4
        || lastNameField.length() < 4
        || secondLastNameField.length() < 4;
  }

  private boolean areCarerFieldsIncorrect() {
    final var rutField = rut.getText();
    final var birthDateField = birthDate.getValue();
    final var hireDateField = hireDate.getValue();
    final var now = LocalDate.now();
    final var age = Period.between(birthDateField, now).getYears();
    final var days = Period.between(hireDateField, now).getDays();
    final var years = Period.between(hireDateField, now).getYears();
    return !RutUtils.isValid(rutField) || age < 18 || age > 65 || days < 0 || years > 5;
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
