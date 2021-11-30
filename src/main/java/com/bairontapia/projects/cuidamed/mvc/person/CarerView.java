package com.bairontapia.projects.cuidamed.mvc.person;

import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import com.bairontapia.projects.cuidamed.person.address.Address;
import com.bairontapia.projects.cuidamed.person.address.AddressDAO;
import com.bairontapia.projects.cuidamed.person.carer.Carer;
import com.bairontapia.projects.cuidamed.person.carer.CarerDAO;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
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
    final Carer carer = CarerDAO.getInstance().find("16213821-9").orElseThrow();
    setCarer(carer);
    fillCarerFields(carer);

    final Address address = AddressDAO.getInstance().find("16213821-9").orElseThrow();
    setAddress(address);
    fillAddressFields(address);
  }

  private void initializeComboBoxes() {
    genderComboBox.setItems(FXCollections.observableArrayList(Gender.getValues()));
  }

  public void onUpdatedFields() throws SQLException, IOException {
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

  private void fillAddressFields(final Address address) {
    street.setText(address.street());
    number.setText(address.number().toString());
    postalCode.setText(address.postalCode().toString());
    fixedPhone.setText(address.fixedPhone().toString());
  }
}
