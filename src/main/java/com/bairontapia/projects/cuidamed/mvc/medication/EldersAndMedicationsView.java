package com.bairontapia.projects.cuidamed.mvc.medication;

import com.bairontapia.projects.cuidamed.disease.medicationprescription.MedicationPrescription;
import com.bairontapia.projects.cuidamed.disease.medicationprescription.MedicationPrescriptionDAO;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class EldersAndMedicationsView {

  @FXML private TableView<MedicationPrescription> medicationPrescriptionTableView;
  @FXML private TableColumn<MedicationPrescription, String> rutColumn;
  @FXML private TableColumn<MedicationPrescription, String> nameDiseaseColumn;
  @FXML private TableColumn<MedicationPrescription, String> fullMedicationName;
  @FXML private TableColumn<MedicationPrescription, LocalDate> dateStart;
  @FXML private TableColumn<MedicationPrescription, LocalDate> dateEnd;
  private TableColumn<MedicationPrescription, Short> quantity;

  public void initialize() throws SQLException, IOException {
    rutColumn.setCellValueFactory(
        e -> new SimpleStringProperty(RutUtils.format(e.getValue().rut())));
    nameDiseaseColumn.setCellValueFactory(
        e -> new SimpleStringProperty(e.getValue().diseaseName()));
    fullMedicationName.setCellValueFactory(
        e -> new SimpleStringProperty(e.getValue().medicationName()));
    dateStart.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().startDate()));
    dateEnd.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().endDate()));
    quantity.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().quantity()));
    final var set = MedicationPrescriptionDAO.getInstance().findAll();
    medicationPrescriptionTableView.getItems().addAll(set);
  }

  public void addedPrescription() throws IOException {
    FXMLLoader fxml = new FXMLLoader();
    Scene scene;
    Stage stage = new Stage();
    fxml.setLocation(getClass().getResource("/fxml/window_prescription.fxml"));
    scene = new Scene(fxml.load());
    stage.setScene(scene);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.showAndWait();
  }

  public void addedPrescriptionMedication() throws IOException {
    FXMLLoader fxml = new FXMLLoader();
    Scene scene;
    Stage stage = new Stage();
    fxml.setLocation(getClass().getResource("/fxml/medication_prescription.fxml"));
    scene = new Scene(fxml.load());
    stage.setScene(scene);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.showAndWait();
  }
}
