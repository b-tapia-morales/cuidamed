package com.bairontapia.projects.cuidamed.mvc.graphics;



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


  public void initialize() {
    typeGraphicsCombo.setItems(FXCollections.observableArrayList(TypeGraphic.values()));
    loadDataBloodType();
    loadDataHealthySystem();
    loadDataDisease();
    loadDataMedication();
    healthySystem.setTitle("");
    disease.setTitle("");
    medication.setTitle("");
  }

  @FXML
  public void onGraphicChoice(javafx.event.ActionEvent actionEvent) {
    if(typeGraphicsCombo.getSelectionModel().isSelected(0)){
      bloodType.setVisible(true);
      healthySystem.setVisible(false);
      disease.setVisible(false);
      medication.setVisible(false);
    }else{
      if(typeGraphicsCombo.getSelectionModel().isSelected(1)){
        healthySystem.setVisible(true);
        bloodType.setVisible(false);
        disease.setVisible(false);
        medication.setVisible(false);
      }else {
        if(typeGraphicsCombo.getSelectionModel().isSelected(2)){
          disease.setVisible(true);
          bloodType.setVisible(false);
          healthySystem.setVisible(false);
          medication.setVisible(false);
        }else{
          if(typeGraphicsCombo.getSelectionModel().isSelected(3)){
            medication.setVisible(true);
            disease.setVisible(false);
            healthySystem.setVisible(false);
            bloodType.setVisible(false);
          }
        }
      }
    }

  }

  private void loadDataBloodType(){
    bloodType.getData().add(new PieChart.Data("Iphone 5S", 13));
    bloodType.getData().add(new PieChart.Data("Samsung Grand", 25));
    bloodType.getData().add(new PieChart.Data("MOTO G", 10));
    bloodType.getData().add(new PieChart.Data("Nokia Lumia", 22));
    bloodType.setVisible(false);
    bloodType.setTitle("");
  }

  private void loadDataHealthySystem(){
    healthySystem.getData().add(new PieChart.Data("perros", 4));
    healthySystem.getData().add(new PieChart.Data("gatos",6));
    healthySystem.getData().add(new PieChart.Data("tortuga", 5));
    healthySystem.setVisible(false);
    healthySystem.setTitle("");
  }

  private void loadDataDisease(){
    disease.getData().add(new PieChart.Data("grandes",3));
    disease.getData().add(new PieChart.Data("pequeños",4));
    disease.setVisible(false);
    disease.setTitle("");
  }
  private void loadDataMedication(){
    medication.getData().add(new PieChart.Data("blancos",5));
    medication.getData().add(new PieChart.Data("negros",5));
    medication.getData().add(new PieChart.Data("verdes", 9));
    medication.setVisible(false);
    medication.setTitle("");
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
