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
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class ResponsibleView {

  @Setter
  private Responsible responsible;
  @FXML
  private Button updateButton;
  @FXML
  private TextField postalCode;
  @FXML
  private TextField fixedPhone;
  @FXML
  private TextField region;
  @FXML
  private TextField province;
  @FXML
  private TextField commune;
  @FXML
  private TextField street;
  @FXML
  private TextField number;
  @FXML
  private TextField rut;
  @FXML
  private TextField name;
  @FXML
  private TextField age;
  @FXML
  private TextField mobilePhone;
  @FXML
  private TextField lastName;
  @FXML
  private TextField secondLastName;
  @FXML
  private DatePicker birthDatePicker;
  @FXML
  private ComboBox<Gender> genderComboBox;
  @FXML
  private TextField elderRut;
  @FXML
  private TextField elderFirstName;
  @FXML
  private TextField elderLastName;
  @FXML
  private TextField elderSecondLastName;
  @FXML
  private TextField elderBirthDate;
  @FXML
  private TextField elderAge;
  @FXML
  private TextField elderGender;
  @FXML
  private CheckBox isActiveCheckBox;
  @FXML
  private TextField admissionDate;

  public void initialize() {
    initializeComboBoxes();
    /*
        final Responsible responsible = ResponsibleDAO.getInstance().find("13371799-4").orElseThrow();
        setResponsible(responsible);
        fillResponsibleFields(responsible);
        final var address = AddressDAO.getInstance().find(responsible.rut()).orElseThrow();
        final var communeField = CommuneDAO.getInstance().find(address.communeId()).orElseThrow();
        final var provinceField = ProvinceDAO.getInstance().find(communeField.provinceId())
            .orElseThrow();
        final var regionField = RegionDAO.getInstance().find(provinceField.regionId()).orElseThrow();
        fillAddressFields(address, regionField, provinceField, communeField);
    */
  }

  @FXML
  public void recoveryData(Responsible responsible) throws SQLException, IOException {
    setResponsible(responsible);
    fillResponsibleFields(responsible);
    final var address = AddressDAO.getInstance().find(responsible.rut()).orElseThrow();
    final var communeField = CommuneDAO.getInstance().find(address.communeId()).orElseThrow();
    final var provinceField =
        ProvinceDAO.getInstance().find(communeField.provinceId()).orElseThrow();
    final var regionField = RegionDAO.getInstance().find(provinceField.regionId()).orElseThrow();
    fillAddressFields(address, regionField, provinceField, communeField);
  }

  private void initializeComboBoxes() {
    genderComboBox.setItems(FXCollections.observableArrayList(Gender.getValues()));
  }

  @FXML
  public void onUpdatedFields() throws SQLException, IOException {
    if (areFieldsEmpty() || areFieldsTooShort() || areFieldsIncorrect()) {
      fillResponsibleFields(responsible);
    }
    final var rutField = RutUtils.removeDots(rut.getText());
    final var nameField = StringUtils.trim(name.getText());
    final var lastNameField = StringUtils.trim(lastName.getText());
    final var secondLastNameField = StringUtils.trim(secondLastName.getText());
    final var birthDateField = birthDatePicker.getValue();
    final var genderCode = genderComboBox.getSelectionModel().getSelectedItem().getIndex();
    final var mobilePhoneField = Integer.parseInt(mobilePhone.getText());
    final var responsible =
        Responsible.createInstance(
            rutField,
            nameField,
            lastNameField,
            secondLastNameField,
            Date.valueOf(birthDateField),
            (short) genderCode,
            mobilePhoneField);
    ResponsibleDAO.getInstance().update(responsible);
    setResponsible(responsible);
  }

  private boolean areFieldsTooShort() {
    final var nameField = StringUtils.trim(name.getText());
    final var lastNameField = StringUtils.trim(lastName.getText());
    final var secondLastNameField = StringUtils.trim(secondLastName.getText());
    return nameField.length() < 4 || lastNameField.length() < 4 || secondLastNameField.length() < 4;
  }

  private boolean areFieldsIncorrect() {
    final var birthDateField = birthDatePicker.getValue();
    final var now = LocalDate.now();
    final var age = Period.between(birthDateField, now).getYears();
    return age < 18 || age > 65;
  }

  private boolean areFieldsEmpty() {
    return name.getText().isEmpty()
        || lastName.getText().isEmpty()
        || secondLastName.getText().isEmpty()
        || birthDatePicker.getValue() == null
        || genderComboBox.getSelectionModel().isEmpty();
  }

  private void fillResponsibleFields(final Responsible responsible) {
    rut.setText(RutUtils.format(responsible.rut()));
    name.setText(responsible.firstName());
    lastName.setText(responsible.lastName());
    secondLastName.setText(responsible.secondLastName());
    birthDatePicker.setValue(responsible.birthDate());
    age.setText(responsible.age().toString());
    genderComboBox.getSelectionModel().select(responsible.gender().ordinal());
    // mobilePhone.setText("+56 9 " + responsible.mobilePhone());
    mobilePhone.setText(responsible.mobilePhone().toString());
  }

  private void fillAddressFields(
      final Address address,
      final Region regionField,
      final Province provinceField,
      final Commune communeField) {
    region.setText(regionField.toString());
    province.setText(provinceField.toString());
    commune.setText(communeField.toString());
    street.setText(address.street());
    number.setText(address.number().toString());
    postalCode.setText(address.postalCode().toString());
    fixedPhone.setText(address.fixedPhone().toString());
  }
}
