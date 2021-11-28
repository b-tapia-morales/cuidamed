package com.bairontapia.projects.cuidamed.mvc.graphics;


import com.bairontapia.projects.cuidamed.medicalrecord.stats.BloodTypeStatsDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.stats.DiseaseStatsDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.stats.HealthSystemStatsDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.stats.MedicationStatsDAO;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.ComboBox;
import lombok.Getter;

@Getter
public class GraphicsView {

  @FXML
  private ComboBox<GraphicSelection> typeGraphicsCombo;
  @FXML
  private PieChart bloodTypes;
  @FXML
  private PieChart healthSystems;
  @FXML
  private PieChart diseases;
  @FXML
  private PieChart medications;


  public void initialize() throws SQLException, IOException {
    typeGraphicsCombo.setItems(FXCollections.observableArrayList(GraphicSelection.values()));
    loadDataBloodType();
    loadDataHealthySystem();
    loadDataDisease();
    loadDataMedication();
  }

  @FXML
  public void onGraphicChoice() {
    if (typeGraphicsCombo.getSelectionModel().isSelected(0)) {
      bloodTypes.setVisible(true);
      healthSystems.setVisible(false);
      diseases.setVisible(false);
      medications.setVisible(false);
    } else {
      if (typeGraphicsCombo.getSelectionModel().isSelected(1)) {
        healthSystems.setVisible(true);
        bloodTypes.setVisible(false);
        diseases.setVisible(false);
        medications.setVisible(false);
      } else {
        if (typeGraphicsCombo.getSelectionModel().isSelected(2)) {
          diseases.setVisible(true);
          bloodTypes.setVisible(false);
          healthSystems.setVisible(false);
          medications.setVisible(false);
        } else {
          if (typeGraphicsCombo.getSelectionModel().isSelected(3)) {
            medications.setVisible(true);
            diseases.setVisible(false);
            healthSystems.setVisible(false);
            bloodTypes.setVisible(false);
          }
        }
      }
    }

  }

  private void loadDataBloodType() throws SQLException, IOException {
    final var data = BloodTypeStatsDAO.getInstance().findAll();
    data.forEach(e -> bloodTypes.getData().add(new Data(e.bloodType().getName(), e.frequency())));
    bloodTypes.setVisible(false);
    bloodTypes.setTitle("Tipos de Sangre");
  }

  private void loadDataHealthySystem() throws SQLException, IOException {
    final var data = HealthSystemStatsDAO.getInstance().findAll();
    data.forEach(
        e -> healthSystems.getData().add(new Data(e.healthCare().getName(), e.frequency())));
    healthSystems.setVisible(false);
    healthSystems.setTitle("Sistemas de Salud");
  }

  private void loadDataDisease() throws SQLException, IOException {
    final var data = DiseaseStatsDAO.getInstance().findAll();
    data.forEach(e -> diseases.getData().add(new Data(e.diseaseName(), e.frequency())));
    diseases.setVisible(false);
    diseases.setTitle("Enfermedades");
  }

  private void loadDataMedication() throws SQLException, IOException {
    final var data = MedicationStatsDAO.getInstance().findAll();
    data.forEach(e -> medications.getData().add(new Data(e.name(), e.frequency())));
    medications.setVisible(false);
    medications.setTitle("Medicamentos");
  }

  @Getter
  enum GraphicSelection {
    BLOOD_TYPE("Tipo de sangre"),
    HEALTH_SYSTEM("Sistema de Salud"),
    DISEASE("Enfermedades"),
    MEDICATION("Medicamentos");

    private final String name;

    GraphicSelection(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return name;
    }
  }
}
