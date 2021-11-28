package com.bairontapia.projects.cuidamed.mvc.graphics;


import com.bairontapia.projects.cuidamed.medicalrecord.stats.BloodTypeStats;
import com.bairontapia.projects.cuidamed.medicalrecord.stats.BloodTypeStatsDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.stats.DiseaseStats;
import com.bairontapia.projects.cuidamed.medicalrecord.stats.DiseaseStatsDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.stats.HealthySystemTypeStats;
import com.bairontapia.projects.cuidamed.medicalrecord.stats.HealthySystemTypeStatsDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.stats.MedicationStats;
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
  private ComboBox<TypeGraphic> typeGraphicsCombo;
  @FXML
  private PieChart bloodType;
  @FXML
  private PieChart healthySystem;
  @FXML
  private PieChart disease;
  @FXML
  private PieChart medication;


  public void initialize() throws SQLException, IOException {
    typeGraphicsCombo.setItems(FXCollections.observableArrayList(TypeGraphic.values()));
    loadDataBloodType();
    loadDataHealthySystem();
    loadDataDisease();
    loadDataMedication();
  }

  @FXML
  public void onGraphicChoice(javafx.event.ActionEvent actionEvent) {
    if (typeGraphicsCombo.getSelectionModel().isSelected(0)) {
      bloodType.setVisible(true);
      healthySystem.setVisible(false);
      disease.setVisible(false);
      medication.setVisible(false);
    } else {
      if (typeGraphicsCombo.getSelectionModel().isSelected(1)) {
        healthySystem.setVisible(true);
        bloodType.setVisible(false);
        disease.setVisible(false);
        medication.setVisible(false);
      } else {
        if (typeGraphicsCombo.getSelectionModel().isSelected(2)) {
          disease.setVisible(true);
          bloodType.setVisible(false);
          healthySystem.setVisible(false);
          medication.setVisible(false);
        } else {
          if (typeGraphicsCombo.getSelectionModel().isSelected(3)) {
            medication.setVisible(true);
            disease.setVisible(false);
            healthySystem.setVisible(false);
            bloodType.setVisible(false);
          }
        }
      }
    }

  }

  private void loadDataBloodType() throws SQLException, IOException {
    final var data = BloodTypeStatsDAO.getInstance().findAll();
    for (BloodTypeStats blood : data) {
      bloodType.getData().add(new Data(blood.bloodType().getName(), blood.frequency()));
    }
    bloodType.setVisible(false);
    bloodType.setTitle("Tipos de Sangre");
  }

  private void loadDataHealthySystem() throws SQLException, IOException {
    final var data = HealthySystemTypeStatsDAO.getInstance().findAll();
    for (HealthySystemTypeStats healthy : data) {
      healthySystem.getData().add(new Data(healthy.healthCare().getName(), healthy.frequency()));
    }
    healthySystem.setVisible(false);
    healthySystem.setTitle("Sistemas de Salud");
  }

  private void loadDataDisease() throws SQLException, IOException {
    final var data = DiseaseStatsDAO.getInstance().findAll();
    for (DiseaseStats diseaseStats : data) {
      disease.getData().add(new Data(diseaseStats.diseaseName(), diseaseStats.frequency()));
    }
    disease.setVisible(false);
    disease.setTitle("Enfermedades");
  }

  private void loadDataMedication() throws SQLException, IOException {
    final var data = MedicationStatsDAO.getInstance().findAll();
    for (MedicationStats medicationStats : data) {
      medication.getData().add(new Data(medicationStats.name(), medicationStats.frequency()));
    }
    medication.setVisible(false);
    medication.setTitle("Medicamentos");
  }

  @Getter
  enum TypeGraphic {
    BLOODY_TYPE("Tipo de sangre"),
    HEALTHY_SYSTEM("Sistema de Salud"),
    DISEASE("Enfermedades"),
    MEDICATION("Medicamentos");

    private final String name;

    TypeGraphic(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return name;
    }
  }
}
