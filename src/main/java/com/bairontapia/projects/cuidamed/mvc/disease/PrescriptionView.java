package com.bairontapia.projects.cuidamed.mvc.disease;

import com.bairontapia.projects.cuidamed.disease.Disease;
import com.bairontapia.projects.cuidamed.disease.DiseaseDAO;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Getter;

@Getter
public class PrescriptionView {

  @FXML
  private TextField rut;
  @FXML
  private ComboBox<Disease> diseaseNameComboBox;
  @FXML
  private DatePicker diagnosisDatePicker;
  @FXML
  private TextField prescriptionDate;
  @FXML
  private TextArea description;
  @FXML
  private CheckBox isPrescriptionEnabled;

  public void initialize() throws SQLException, IOException {
    initializeComboBoxes();
  }

  public void initializeComboBoxes() throws SQLException, IOException {
    diseaseNameComboBox.setItems(
        FXCollections.observableArrayList(DiseaseDAO.getInstance().findAll()));
  }
}
