package com.bairontapia.projects.cuidamed.mvc.person;

import com.bairontapia.projects.cuidamed.mappings.bloodtype.BloodType;
import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import com.bairontapia.projects.cuidamed.mappings.healthcaresystem.HealthCare;
import com.bairontapia.projects.cuidamed.medicalrecord.allergy.Allergy;
import com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup.RoutineCheckup;
import com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup.RoutineCheckupDAO;
import com.bairontapia.projects.cuidamed.person.Address;
import com.bairontapia.projects.cuidamed.person.AddressDAO;
import com.bairontapia.projects.cuidamed.person.elder.Elder;
import com.bairontapia.projects.cuidamed.person.elder.ElderDAO;
import com.bairontapia.projects.cuidamed.person.responsible.Responsible;
import com.bairontapia.projects.cuidamed.person.responsible.ResponsibleDAO;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class ElderView {

  @FXML
  private TextField rut;
  @FXML
  private TextField name;
  @FXML
  private TextField lastName;
  @FXML
  private TextField secondLastName;
  @FXML
  private DatePicker birthDatePicker;
  @FXML
  private TextField age;
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
  private TextField responsibleRut;
  @FXML
  private TextField responsibleFullName;
  @FXML
  private TextField responsibleBirthDate;
  @FXML
  private TextField responsibleAge;
  @FXML
  private TextField responsibleGender;
  @FXML
  private TextField responsibleMobilePhone;

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
  private TableView<Allergy> allergyTableView;
  @FXML
  private TableColumn<Allergy, String> allergyType;
  @FXML
  private TableColumn<Allergy, String> allergyDetails;
  @FXML
  private TableView<RoutineCheckup> checkupTableView;
  @FXML
  private TableColumn<RoutineCheckup, LocalDate> checkupDate;
  @FXML
  private TableColumn<RoutineCheckup, Double> height;
  @FXML
  private TableColumn<RoutineCheckup, Double> weight;
  @FXML
  private TableColumn<RoutineCheckup, Double> bmi;
  @FXML
  private TableColumn<RoutineCheckup, Integer> heartRate;
  @FXML
  private TableColumn<RoutineCheckup, Double> diastolicPressure;
  @FXML
  private TableColumn<RoutineCheckup, Double> systolicPressure;
  @FXML
  private TableColumn<RoutineCheckup, Double> bodyTemperature;

  public void initialize() throws SQLException, IOException {
    initializeComboBoxes();
    initializeAllergyTable();
    initializeCheckupTable();
    final Elder elder = ElderDAO.getInstance().find("5875397-1").get();
    fillElderFields(elder);
    final var responsibleKey = elder.responsibleRut();
    final var responsible = ResponsibleDAO.getInstance().find(responsibleKey).get();
    fillResponsibleFields(responsible);
    final var address = AddressDAO.getInstance().find(responsibleKey).get();
    fillAddressFields(address);
    final var routineCheckup = RoutineCheckupDAO.getInstance().findAll("5875397-1");
    fillRoutineCheckupTable(routineCheckup);
  }

  private void initializeComboBoxes() {
    genderComboBox.setItems(FXCollections.observableArrayList(Gender.getValues()));
    bloodTypeComboBox.setItems(FXCollections.observableArrayList(BloodType.getValues()));
    healthCareComboBox.setItems(FXCollections.observableArrayList(HealthCare.getValues()));
  }

  private void initializeAllergyTable() {
    allergyType.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().type().toString()));
    allergyDetails.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().details()));
  }

  private void initializeCheckupTable() {
    checkupDate.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().checkupDate()));
    height.setCellValueFactory(e -> new SimpleDoubleProperty(e.getValue().height()).asObject());
    weight.setCellValueFactory(e -> new SimpleDoubleProperty(e.getValue().weight()).asObject());
    bmi.setCellValueFactory(e -> new SimpleDoubleProperty(e.getValue().bmi()).asObject());
    heartRate.setCellValueFactory(
        e -> new SimpleIntegerProperty(e.getValue().heartRate()).asObject());
    diastolicPressure.setCellValueFactory(
        e -> new SimpleDoubleProperty(e.getValue().diastolicPressure()).asObject());
    systolicPressure.setCellValueFactory(
        e -> new SimpleDoubleProperty(e.getValue().systolicPressure()).asObject());
    bodyTemperature.setCellValueFactory(
        e -> new SimpleDoubleProperty(e.getValue().bodyTemperature()).asObject());
  }

  private void fillElderFields(final Elder elder) {
    rut.setText(RutUtils.format(elder.rut()));
    name.setText(elder.firstName());
    lastName.setText(elder.lastName());
    secondLastName.setText(elder.secondLastName());
    birthDatePicker.setValue(elder.birthDate());
    age.setText(elder.age().toString());
    genderComboBox.getSelectionModel().select(elder.gender().ordinal());
    isActiveCheckBox.setSelected(elder.isActive());
    admissionDatePicker.setValue(elder.admissionDate());
  }

  private void fillResponsibleFields(final Responsible responsible) {
    responsibleRut.setText(RutUtils.format(responsible.rut()));
    responsibleFullName.setText(StringUtils
        .joinWith(" ", responsible.firstName(), responsible.lastName(),
            responsible.secondLastName()));
    responsibleBirthDate.setText(responsible.birthDate().toString());
    responsibleAge.setText(responsible.age().toString());
    responsibleGender.setText(responsible.gender().toString());
    responsibleMobilePhone.setText("+56 9 " + responsible.mobilePhone());
  }

  private void fillAddressFields(final Address address) {
    region.setText(address.regionName());
    province.setText(address.provinceName());
    commune.setText(address.communeName());
    street.setText(address.street());
    number.setText(address.number().toString());
  }

  private void fillRoutineCheckupTable(final Collection<RoutineCheckup> routineCheckups) {
    checkupTableView.getItems().addAll(routineCheckups);
  }

}
