package com.bairontapia.projects.cuidamed.mvc.medicalrecordcontroller;

import com.bairontapia.projects.cuidamed.mappings.allergytype.AllergyType;
import com.bairontapia.projects.cuidamed.medicalrecord.allergy.Allergy;
import com.bairontapia.projects.cuidamed.mvc.person.ElderView;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;

@Getter
public class AllergyView {

  @FXML
  private ComboBox<AllergyType> comboBoxType;
  @FXML
  private TextField labelDetails;

  private Allergy allergy;

  private String rut;

  public void initialize() {
    comboBoxType.setItems(FXCollections.observableArrayList(AllergyType.getValues()));
    allergy = null;
  }
  @FXML
  public void addedAllergy(ActionEvent actionEvent) throws IOException {
    Node source = (Node) actionEvent.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
    System.out.println(comboBoxType.getSelectionModel().getSelectedIndex());
    this.allergy = Allergy.createInstance(rut,(short) (comboBoxType.getSelectionModel().getSelectedIndex()+1),labelDetails.getText());

  public void addedAllergy(ActionEvent actionEvent) {
  }

  public void cancelAllergy(ActionEvent actionEvent) {
    Node source = (Node) actionEvent.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
  }

  public void pruebaCambios(String rut){
    this.rut = rut;
  }
}
