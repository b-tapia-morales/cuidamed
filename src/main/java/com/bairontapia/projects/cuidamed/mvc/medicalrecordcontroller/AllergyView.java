package com.bairontapia.projects.cuidamed.mvc.medicalrecordcontroller;

import com.bairontapia.projects.cuidamed.mappings.allergytype.AllergyType;
import com.bairontapia.projects.cuidamed.medicalrecord.allergy.Allergy;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
    if (comboBoxType.getSelectionModel().isEmpty() || labelDetails.getText().equals("")) {
      Alert a = new Alert(AlertType.WARNING);
      a.setContentText("¡¡Valores vacios!!");
      a.show();
    } else {
      this.allergy = Allergy
          .createInstance(RutUtils.removeDots(rut.toLowerCase()),
              (short) (comboBoxType.getSelectionModel().getSelectedIndex() + 1),
              labelDetails.getText());
    }
  }


  @FXML
  public void cancelAllergy(ActionEvent actionEvent) {
    Node source = (Node) actionEvent.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
  }

  public void changes(String rut) {
    this.rut = rut;
  }
}
