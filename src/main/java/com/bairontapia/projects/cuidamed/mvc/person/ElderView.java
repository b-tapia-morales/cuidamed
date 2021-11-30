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
import com.bairontapia.projects.cuidamed.medicalrecord.MedicalRecord;
import com.bairontapia.projects.cuidamed.medicalrecord.MedicalRecordDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.allergy.Allergy;
import com.bairontapia.projects.cuidamed.medicalrecord.allergy.AllergyDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup.RoutineCheckup;
import com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup.RoutineCheckupDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.surgicalintervention.SurgicalIntervention;
import com.bairontapia.projects.cuidamed.medicalrecord.surgicalintervention.SurgicalInterventionDAO;
import com.bairontapia.projects.cuidamed.mvc.medical_record.RoutineCheckupView;
import com.bairontapia.projects.cuidamed.mvc.medicalrecordcontroller.AllergyView;
import com.bairontapia.projects.cuidamed.mvc.medicalrecordcontroller.SurgicalInterventionView;
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
import java.util.Collection;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class ElderView {


  @FXML
  private AnchorPane anchor;
  @Setter
  private Elder elder;
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
  private Button updateData;

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
  private TabPane tabPane;

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

  @FXML
  private TableView<SurgicalIntervention> surgicalInterventionTable;
  @FXML
  private TableColumn<SurgicalIntervention, LocalDate> interventionDate;
  @FXML
  private TableColumn<SurgicalIntervention, String> hospital;
  @FXML
  private TableColumn<SurgicalIntervention, String> severity;
  @FXML
  private TableColumn<SurgicalIntervention, String> description;

  @FXML
  private Button addData;

  private Allergy allergy;
  private SurgicalIntervention surgicalIntervention;
  private RoutineCheckup routineCheckup;

  public void initialize() throws SQLException, IOException {
    initializeComboBoxes();
    initializeAllergyTable();
    initializeCheckupTable();
    initializeSurgicalInterventionTable();
    allergy = null;
    surgicalIntervention = null;
    routineCheckup = null;
    /*
    final Elder elder = ElderDAO.getInstance().find("5902831-6").orElseThrow();
    setElder(elder);
    fillElderFields(elder);
    final var responsibleKey = elder.responsibleRut();
    final var responsible = ResponsibleDAO.getInstance().find(responsibleKey).orElseThrow();
    fillResponsibleFields(responsible);
    final var address = AddressDAO.getInstance().find(responsibleKey).orElseThrow();
    fillAddressFields(address);
    final var allergies = AllergyDAO.getInstance().findAll("5902831-6");
    fillAllergyTable(allergies);
    final var routineCheckups = RoutineCheckupDAO.getInstance().findAll("5902831-6");
    fillRoutineCheckupTable(routineCheckups);
    final var surgicalInterventions = SurgicalInterventionDAO.getInstance().findAll("5902831-6");
    fillSurgicalInterventionTable(surgicalInterventions);

     */
  }

  @FXML
  public void recoveryData(Elder elder) throws SQLException, IOException {
    setElder(elder);
    onUpdatedFields();
    fillElderFields(elder);
    final var responsibleKey = elder.responsibleRut();
    final var responsible = ResponsibleDAO.getInstance().find(responsibleKey).orElseThrow();
    fillResponsibleFields(responsible);
    final var address = AddressDAO.getInstance().find(responsibleKey).orElseThrow();
    final var communeField = CommuneDAO.getInstance().find(address.communeId()).orElseThrow();
    final var provinceField = ProvinceDAO.getInstance().find(communeField.provinceId())
        .orElseThrow();
    final var regionField = RegionDAO.getInstance().find(provinceField.regionId()).orElseThrow();
    fillAddressFields(address, regionField, provinceField, communeField);
    final var allergies = AllergyDAO.getInstance().findAll(elder.rut());
    fillAllergyTable(allergies);
    final var routineCheckups = RoutineCheckupDAO.getInstance().findAll(elder.rut());
    fillRoutineCheckupTable(routineCheckups);
    final var surgicalInterventions = SurgicalInterventionDAO.getInstance().findAll(elder.rut());
    fillSurgicalInterventionTable(surgicalInterventions);
    final var medicalRecord = MedicalRecordDAO.getInstance().find(elder.rut()).orElseThrow();
    fillMedicalRecord(medicalRecord);
  }

  @FXML
  public void onUpdatedFields() throws SQLException, IOException {
    if (areFieldsEmpty() || areFieldsTooShort() || areFieldsIncorrect()) {
      fillElderFields(elder);
    }
    final var rutField = RutUtils.removeDots(rut.getText());
    final var nameField = StringUtils.trim(name.getText());
    final var lastNameField = StringUtils.trim(lastName.getText());
    final var secondLastNameField = StringUtils.trim(secondLastName.getText());
    final var birthDateField = birthDatePicker.getValue();
    final var genderCode = genderComboBox.getSelectionModel().getSelectedItem().getIndex();
    final var isActiveField = isActiveCheckBox.isSelected();
    final var admissionDateField = admissionDatePicker.getValue();
    final var responsibleRutField = RutUtils.removeDots(responsibleRut.getText());
    final var elder = Elder.createInstance(rutField, nameField, lastNameField,
        secondLastNameField, Date.valueOf(birthDateField), (short) genderCode, isActiveField,
        Date.valueOf(admissionDateField), responsibleRutField);
    ElderDAO.getInstance().update(elder);
    setElder(elder);
  }

  @FXML
  public void addColumn() throws IOException, SQLException {
    if (tabPane.getSelectionModel().isEmpty()) {
      return;
    }

    FXMLLoader fxml = new FXMLLoader();
    Scene scene;
    Stage stage = new Stage();

    final var tabSelectionIndex = tabPane.getSelectionModel().getSelectedIndex();
    if (tabSelectionIndex == 1) {
      fxml.setLocation(getClass().getResource("/fxml/routine_checkup.fxml"));
      scene = new Scene(fxml.load());
      RoutineCheckupView routineCheckupView = fxml.getController();
      routineCheckupView.changes(rut.getText());
    } else {
      if (tabSelectionIndex == 2) {
        fxml.setLocation(getClass().getResource("/fxml/surgical_intervention_dialog.fxml"));
        scene = new Scene(fxml.load());
        SurgicalInterventionView surgicalInterventionView = fxml.getController();
        surgicalInterventionView.changes(rut.getText());
      } else {
        fxml.setLocation(getClass().getResource("/fxml/allergy_dialog.fxml"));
        scene = new Scene(fxml.load());
        AllergyView allergyView = (AllergyView) fxml.getController();
        allergyView.changes(rut.getText());
      }
    }
    stage.setScene(scene);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.showAndWait();
    tabSelectsetObject(fxml);
  }

  private void tabSelectsetObject(FXMLLoader fxml) throws SQLException, IOException {
    final var tabSelectionIndex = tabPane.getSelectionModel().getSelectedIndex();
    if (tabSelectionIndex == 1) {
      setObjectRoutine(fxml);
    } else {
      if (tabSelectionIndex == 2) {
        setObjectSurgical(fxml);
      } else {
        setObjectAllergy(fxml);
      }
    }
  }

  private void setObjectAllergy(FXMLLoader fxml) throws SQLException, IOException {
    AllergyView allergyView = fxml.getController();
    this.allergy = allergyView.getAllergy();
    addToAllergyTable();
  }

  private void setObjectSurgical(FXMLLoader fxml) throws SQLException, IOException {
    SurgicalInterventionView surgicalInterventionView = fxml.getController();
    this.surgicalIntervention = surgicalInterventionView.getSurgicalIntervention();
    addToSurgicalInterventionTable();
  }

  private void setObjectRoutine(FXMLLoader fxml) throws SQLException, IOException {
    RoutineCheckupView routineCheckupView = fxml.getController();
    this.routineCheckup = routineCheckupView.getRoutineCheckup();
    addToRoutineCheckupTable();
  }

  @FXML
  public void addToAllergyTable() throws SQLException, IOException {
    if (this.allergy != null) {
      final var daoAllergy = AllergyDAO.getInstance();
      daoAllergy.save(this.allergy);
      fillAllergyTable(daoAllergy.findAll(this.elder.rut()));
    }
  }

  public void addToSurgicalInterventionTable() throws SQLException, IOException {
    if (this.surgicalIntervention != null) {
      final var daoSurgicalIntervention = SurgicalInterventionDAO.getInstance();
      daoSurgicalIntervention.save(this.surgicalIntervention);
      fillSurgicalInterventionTable(daoSurgicalIntervention.findAll(this.elder.rut()));
    }
  }

  public void addToRoutineCheckupTable() throws SQLException, IOException {
    if (this.routineCheckup != null) {
      final var daoRoutineCheckup = RoutineCheckupDAO.getInstance();
      daoRoutineCheckup.save(this.routineCheckup);
      fillRoutineCheckupTable(daoRoutineCheckup.findAll(this.elder.rut()));
    }
  }

  private boolean areFieldsEmpty() {
    return name.getText().isEmpty() || lastName.getText().isEmpty() ||
        secondLastName.getText().isEmpty() || birthDatePicker.getValue() == null ||
        admissionDatePicker.getValue() == null || genderComboBox.getSelectionModel().isEmpty();
  }

  private boolean areFieldsTooShort() {
    final var nameField = StringUtils.trim(name.getText());
    final var lastNameField = StringUtils.trim(lastName.getText());
    final var secondLastNameField = StringUtils.trim(secondLastName.getText());
    return nameField.length() < 4 || lastNameField.length() < 4 || secondLastNameField.length() < 4;
  }

  private boolean areFieldsIncorrect() {
    final var birthDateField = birthDatePicker.getValue();
    final var admissionDateField = admissionDatePicker.getValue();
    final var now = LocalDate.now();
    final var age = Period.between(birthDateField, now).getYears();
    final var days = Period.between(admissionDateField, now).getDays();
    final var years = Period.between(admissionDateField, now).getYears();
    return age < 65 || age > 120 || days < 0 || years > 5;
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

  private void initializeSurgicalInterventionTable() {
    interventionDate
        .setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().interventionDate()));
    hospital.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().hospital()));
    severity.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().severity().toString()));
    description.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().description()));
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

  private void fillAddressFields(final Address address, final Region regionField,
      final Province provinceField, final Commune communeField) {
    region.setText(regionField.toString());
    province.setText(provinceField.toString());
    commune.setText(communeField.toString());
    street.setText(address.street());
    number.setText(address.number().toString());
  }

  private void fillAllergyTable(final Collection<Allergy> allergies) {
    allergyTableView.getItems().clear();
    allergyTableView.getItems().addAll(allergies);
  }

  private void fillRoutineCheckupTable(final Collection<RoutineCheckup> routineCheckups) {
    checkupTableView.getItems().clear();
    checkupTableView.getItems().addAll(routineCheckups);
  }

  private void fillSurgicalInterventionTable(
      final Collection<SurgicalIntervention> surgicalInterventions) {
    surgicalInterventionTable.getItems().clear();
    surgicalInterventionTable.getItems().addAll(surgicalInterventions);
  }

  private void fillMedicalRecord(final MedicalRecord medicalRecord) {
    bloodTypeComboBox.getSelectionModel().select(medicalRecord.bloodType().ordinal());
    healthCareComboBox.getSelectionModel().select(medicalRecord.healthCare().ordinal());
  }
}
