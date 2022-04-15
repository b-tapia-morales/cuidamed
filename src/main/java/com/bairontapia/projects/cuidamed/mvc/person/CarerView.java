package com.bairontapia.projects.cuidamed.mvc.person;

import com.bairontapia.projects.cuidamed.localization.*;
import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import com.bairontapia.projects.cuidamed.person.address.Address;
import com.bairontapia.projects.cuidamed.person.address.AddressDAO;
import com.bairontapia.projects.cuidamed.person.carer.Carer;
import com.bairontapia.projects.cuidamed.person.carer.CarerDAO;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

@Getter
public class CarerView {

    @Setter
    private Carer carer;
    @Setter
    private Address address;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private ComboBox<Gender> genderComboBox;
    @FXML
    private DatePicker hireDatePicker;
    @FXML
    private Button updateData;
    @FXML
    private TextField rut;
    @FXML
    private TextField name;
    @FXML
    private TextField lastName;
    @FXML
    private TextField secondLastName;
    @FXML
    private TextField age;
    @FXML
    private TextField mobilePhone;
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
    private TextField postalCode;
    @FXML
    private TextField fixedPhone;

    public void initialize() throws SQLException, IOException {
        initializeComboBoxes();
    }

    private void initializeComboBoxes() {
        genderComboBox.setItems(FXCollections.observableArrayList(Gender.getValues()));
    }

    private boolean areFieldsTooShort() {
        final var nameField = StringUtils.trim(name.getText());
        final var lastNameField = StringUtils.trim(lastName.getText());
        final var secondLastNameField = StringUtils.trim(secondLastName.getText());
        return nameField.length() < 4 || lastNameField.length() < 4 || secondLastNameField.length() < 4;
    }

    private boolean areFieldsIncorrect() {
        final var birthDateField = birthDatePicker.getValue();
        final var hireDateField = hireDatePicker.getValue();
        final var now = LocalDate.now();
        final var age = Period.between(birthDateField, now).getYears();
        final var days = Period.between(hireDateField, now).getDays();
        final var years = Period.between(hireDateField, now).getYears();
        return age < 18 || age > 65 || days < 0 || years > 5;
    }

    private boolean areFieldsEmpty() {
        return name.getText().isEmpty()
                || lastName.getText().isEmpty()
                || secondLastName.getText().isEmpty()
                || birthDatePicker.getValue() == null
                || hireDatePicker.getValue() == null
                || genderComboBox.getSelectionModel().isEmpty();
    }

    @FXML
    public void onUpdatedFields() throws SQLException, IOException {
        if (areFieldsEmpty() || areFieldsTooShort() || areFieldsIncorrect()) {
            fillCarerFields(carer);
            return;
        }
        final var rutField = RutUtils.removeDots(rut.getText());
        final var nameField = StringUtils.trim(name.getText());
        final var lastNameField = StringUtils.trim(lastName.getText());
        final var secondLastNameField = StringUtils.trim(secondLastName.getText());
        final var birthDateField = birthDatePicker.getValue();
        final var genderCode = genderComboBox.getSelectionModel().getSelectedItem().getIndex();
        final var mobilePhoneField = mobilePhone.getText();
        final var hireDateField = hireDatePicker.getValue();
        final var carer =
                Carer.createInstance(
                        rutField,
                        nameField,
                        lastNameField,
                        secondLastNameField,
                        Date.valueOf(birthDateField),
                        (short) genderCode,
                        Integer.parseInt(mobilePhoneField),
                        Date.valueOf(hireDateField));
        CarerDAO.getInstance().update(carer);
        setCarer(carer);
    }

    public void recoveryData(Carer carer) throws SQLException, IOException {
        setCarer(carer);
        fillCarerFields(carer);
        final var address = AddressDAO.getInstance().find(carer.rut()).orElseThrow();
        final var communeField = CommuneDAO.getInstance().find(address.communeId()).orElseThrow();
        final var provinceField =
                ProvinceDAO.getInstance().find(communeField.provinceId()).orElseThrow();
        final var regionField = RegionDAO.getInstance().find(provinceField.regionId()).orElseThrow();
        fillAddressFields(address, regionField, provinceField, communeField);
    }

    private void fillCarerFields(final Carer carer) {
        rut.setText(RutUtils.format(carer.rut()));
        name.setText(carer.firstName());
        lastName.setText(carer.lastName());
        secondLastName.setText(carer.secondLastName());
        birthDatePicker.setValue(carer.birthDate());
        age.setText(carer.age().toString());
        genderComboBox.getSelectionModel().select(carer.gender().ordinal());
        // mobilePhone.setText("+56 9 " + carer.mobilePhone().toString());
        mobilePhone.setText(carer.mobilePhone().toString());
        hireDatePicker.setValue(carer.hireDate());
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
