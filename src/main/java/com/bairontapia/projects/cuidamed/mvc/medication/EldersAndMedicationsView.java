package com.bairontapia.projects.cuidamed.mvc.medication;


import com.bairontapia.projects.cuidamed.disease.medicationprescription.MedicationPrescription;
import com.bairontapia.projects.cuidamed.disease.medicationprescription.MedicationPrescriptionDAO;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javax.swing.text.TabableView;

public class EldersAndMedicationsView {
  @FXML
  private TableView<MedicationPrescription> medicationPrescriptionTableView;
  @FXML
  private TableColumn<MedicationPrescription, String> rutColumn;
  @FXML
  private TableColumn<MedicationPrescription, String> fullnameColumn;
  @FXML
  private TableColumn<MedicationPrescription, String> nameDiseaseColumn;
  @FXML
  private TableColumn<MedicationPrescription, String> fullMedicationName;
  @FXML
  private TableColumn<MedicationPrescription, LocalDate> dateStart;
  @FXML
  private TableColumn<MedicationPrescription, LocalDate> dateEnd;

  public void initialize() throws SQLException, IOException {
    rutColumn.setCellValueFactory(e -> new SimpleStringProperty(RutUtils.format(e.getValue().rut())));
    fullnameColumn.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().fullName()));
    nameDiseaseColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().diseaseName()));
    fullMedicationName.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().medicationName()));
    dateStart.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().startDate()));
    dateEnd.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().endDate()));
    final var set = MedicationPrescriptionDAO.getInstance().findAll();
    medicationPrescriptionTableView.getItems().addAll(set);
  }

  public void inEvento(ActionEvent actionEvent) {
  }

  public void inEvento2(ActionEvent actionEvent) {
  }
}
