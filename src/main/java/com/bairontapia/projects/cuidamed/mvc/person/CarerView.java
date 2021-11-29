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
import com.bairontapia.projects.cuidamed.person.carer.Carer;
import com.bairontapia.projects.cuidamed.person.carer.CarerDAO;
import com.bairontapia.projects.cuidamed.person.elder.Elder;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CarerView {

  @FXML public TextField rutTextField;
  @FXML public TextField fullNameTextField;
  @FXML public DatePicker birthDatePicker;
  @FXML public TextField ageTextField;
  @FXML public ComboBox<Gender> genderComboBox;
  @FXML public TextField mobilePhoneTextField;
  @FXML public DatePicker hireDatePicker;
  @FXML public TextField streetTextField;
  @FXML public TextField numberTextField;
  @FXML public TextField lastNameTextField;
  @FXML public TextField secondLastNameTextField;
  @FXML public ComboBox<Province> provinceComboBox;
  @FXML public ComboBox<Commune> communeComboBox;
  @FXML public TextField regionTextField;
  @Setter private Carer carer;
  @Setter private Address address;
  @Setter private Commune commune;
  @Setter private Province province;
  @Setter private Region region;

  public void initialize() throws SQLException, IOException {
    initializeComboBoxes();
    final Carer carer = CarerDAO.getInstance().find("16213821-9").orElseThrow();
    setCarer(carer);
    fillCarerFields(carer);

    final Address address = AddressDAO.getInstance().find("16213821-9").orElseThrow();
    setAddress(address);
    final Region region = RegionDAO.getInstance().find((short) 5).orElseThrow(); // coquimbo
    setRegion(region);
    fillAddressFields(region, address);
    initializeProvinceComboBox(region.id());
    initializeCommuneComboBox((short) 11); // Elqui
  }



  private void initializeProvinceComboBox(short idRegion) throws SQLException, IOException {
    provinceComboBox.setItems(
        FXCollections.observableArrayList(ProvinceDAO.getInstance().findAll(idRegion)));
  }

  private void initializeCommuneComboBox(short idProvince) throws SQLException, IOException {
    communeComboBox.setItems(
        FXCollections.observableArrayList(CommuneDAO.getInstance().findAll(idProvince)));
  }

  private void initializeComboBoxes() throws SQLException, IOException {
    genderComboBox.setItems(FXCollections.observableArrayList(Gender.getValues()));
  }

  public void onUpdatedFields() {

  }

  private void fillCarerFields(final Carer carer) {
    rutTextField.setText(RutUtils.format(carer.rut()));
    fullNameTextField.setText(carer.firstName());
    lastNameTextField.setText(carer.lastName());
    secondLastNameTextField.setText(carer.secondLastName());
    birthDatePicker.setValue(carer.birthDate());
    ageTextField.setText(carer.age().toString());
    genderComboBox.getSelectionModel().select(carer.gender().ordinal());
    mobilePhoneTextField.setText("+56 9 " + carer.mobilePhone().toString());
    hireDatePicker.setValue(carer.hireDate());
  }

  private void fillAddressFields(final Region region, final Address address) {
    regionTextField.setText(region.name());
    streetTextField.setText(address.street());
    numberTextField.setText(address.number().toString());
  }
}
