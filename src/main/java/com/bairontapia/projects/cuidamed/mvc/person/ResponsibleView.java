package com.bairontapia.projects.cuidamed.mvc.person;


import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import java.awt.Checkbox;
import java.awt.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import lombok.Getter;

@Getter
public class ResponsibleView {

  @FXML
  private TextField rut;
  @FXML
  private TextField name;
  @FXML
  private TextField lastName;
  @FXML
  private TextField secondLastName;
  @FXML
  private DatePicker birthDate;
  @FXML
  private TextField age;
  @FXML
  private ComboBox<Gender> genderComboBox;
  @FXML
  private TextField mobilePhone;

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
  private Button getResponsible;

  @FXML
  private TextField elderRut;
  @FXML
  private TextField elderFullName;
  @FXML
  private TextField elderlastName;
  @FXML
  private TextField elderSecondLastName;
  @FXML
  private TextField elderBirthDate;
  @FXML
  private TextField elderAge;
  @FXML
  private TextField elderGender;
  @FXML
  private Checkbox isActiveCheckBox;
  @FXML
  private TextField admissionDate;
  @FXML
  private TextField healthCare;

}
