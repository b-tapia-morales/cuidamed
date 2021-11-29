package com.bairontapia.projects.cuidamed.mvc.person;

import com.bairontapia.projects.cuidamed.localization.Commune;
import com.bairontapia.projects.cuidamed.localization.CommuneDAO;
import com.bairontapia.projects.cuidamed.localization.Province;
import com.bairontapia.projects.cuidamed.localization.ProvinceDAO;
import com.bairontapia.projects.cuidamed.localization.Region;
import com.bairontapia.projects.cuidamed.localization.RegionDAO;
import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import com.bairontapia.projects.cuidamed.person.carer.Carer;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import java.io.IOException;
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

  @Setter private Carer carer;
  @FXML private ComboBox<Region> regionComboBox;
  @FXML private ComboBox<Province> provinceComboBox;
  @FXML private ComboBox<Commune> communeComboBox;
  @FXML private ComboBox<Gender> genderComboBox;
  @FXML private DatePicker birthDatePicker;
  @FXML private TextField rut;
  @FXML private TextField name;
  @FXML private TextField lastName;
  @FXML private TextField secondLastName;
  @FXML private TextField mobilePhone;
  @FXML private DatePicker hireDatePicker;
  @FXML private TextField street;
  @FXML private TextField number;
  @FXML private Button addData;

  public void initialize() throws SQLException, IOException {
    initializeComboBoxes();
  }

  private void initializeComboBoxes() throws SQLException, IOException {
    final var regions = RegionDAO.getInstance().findAll();
    genderComboBox.setItems(FXCollections.observableArrayList(Gender.getValues()));
    regionComboBox.setItems(FXCollections.observableArrayList(regions));
  }

  private boolean areCarerFieldsEmpty() {
    return StringUtils.isBlank(rut.getText())
        || StringUtils.isBlank(name.getText())
        || StringUtils.isBlank(lastName.getText())
        || StringUtils.isBlank(secondLastName.getText())
        || birthDatePicker.getValue() == null
        || genderComboBox.getSelectionModel().isEmpty()
        || hireDatePicker.getValue() == null;
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
    final var birthDateField = birthDatePicker.getValue();
    final var hireDateField = hireDatePicker.getValue();
    final var now = LocalDate.now();
    final var age = Period.between(birthDateField, now).getYears();
    final var days = Period.between(hireDateField, now).getDays();
    final var years = Period.between(hireDateField, now).getYears();
    return !RutUtils.isValid(rutField) || age < 18 || age > 65 || days < 0 || years > 5;
  }

  @FXML
  public void onButtonPressed() {
    if (areCarerFieldsEmpty()) {
      System.out.println("MALO: Datos del cuidador aún sin rellenar");
      return;
    }
    if (areCarerFieldsTooShort()) {
      System.out.println("MALO: Datos del cuidador son muy cortos");
      return;
    }
    if (areCarerFieldsIncorrect()) {
      System.out.println("MALO: Datos del cuidador son incorrectos");
    }
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
}
