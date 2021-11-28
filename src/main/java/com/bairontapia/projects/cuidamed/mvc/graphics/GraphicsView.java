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
  private ComboBox<DataSelection> dataComboBox;
  @FXML
  private PieChart bloodTypes;
  @FXML
  private PieChart healthSystems;
  @FXML
  private PieChart diseases;
  @FXML
  private PieChart medications;


  public void initialize() throws SQLException, IOException {
    dataComboBox.setItems(FXCollections.observableArrayList(DataSelection.values()));
    initializeBloodTypesChart();
    initializeHealthSystemsChart();
    initializeDiseasesChart();
    initializeMedicationsChart();
  }

  @FXML
  public void onGraphicChoice() {
    if (dataComboBox.getSelectionModel().isSelected(0)) {
      setChartVisibility(bloodTypes, healthSystems, diseases, medications);
      return;
    }
    if (dataComboBox.getSelectionModel().isSelected(1)) {
      setChartVisibility(healthSystems, bloodTypes, diseases, medications);
      return;
    }
    if (dataComboBox.getSelectionModel().isSelected(2)) {
      setChartVisibility(diseases, bloodTypes, healthSystems, medications);
      return;
    }
    setChartVisibility(medications, bloodTypes, healthSystems, diseases);
  }

  private static void setChartVisibility(final PieChart firstChart, final PieChart secondChart,
      final PieChart thirdChart, final PieChart fourthChart) {
    firstChart.setVisible(true);
    secondChart.setVisible(false);
    thirdChart.setVisible(false);
    fourthChart.setVisible(false);
  }

  private void initializeBloodTypesChart() throws SQLException, IOException {
    final var data = BloodTypeStatsDAO.getInstance().findAll();
    data.forEach(e -> bloodTypes.getData().add(new Data(e.bloodType().getName(), e.frequency())));
    bloodTypes.setVisible(false);
    bloodTypes.setTitle("Tipos de Sangre");
  }

  private void initializeHealthSystemsChart() throws SQLException, IOException {
    final var data = HealthSystemStatsDAO.getInstance().findAll();
    data.forEach(
        e -> healthSystems.getData().add(new Data(e.healthCare().getName(), e.frequency())));
    healthSystems.setVisible(false);
    healthSystems.setTitle("Sistemas de Salud");
  }

  private void initializeDiseasesChart() throws SQLException, IOException {
    final var data = DiseaseStatsDAO.getInstance().findAll();
    data.forEach(e -> diseases.getData().add(new Data(e.diseaseName(), e.frequency())));
    diseases.setVisible(false);
    diseases.setTitle("Enfermedades");
  }

  private void initializeMedicationsChart() throws SQLException, IOException {
    final var data = MedicationStatsDAO.getInstance().findAll();
    data.forEach(e -> medications.getData().add(new Data(e.name(), e.frequency())));
    medications.setVisible(false);
    medications.setTitle("Medicamentos");
  }

  @Getter
  private enum DataSelection {
    BLOOD_TYPE("Tipo de sangre"),
    HEALTH_SYSTEM("Sistema de Salud"),
    DISEASE("Enfermedades"),
    MEDICATION("Medicamentos");

    private final String name;

    DataSelection(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return name;
    }
  }
}
