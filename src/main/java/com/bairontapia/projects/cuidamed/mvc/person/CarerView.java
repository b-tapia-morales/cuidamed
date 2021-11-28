package com.bairontapia.projects.cuidamed.mvc.person;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import lombok.Getter;

@Getter
public class CarerView {

  @FXML public TextField rutTextField;
  @FXML public TextField fullNameTextField;
  @FXML public DatePicker birthDatePicker;
  @FXML public TextField ageTextField;
  @FXML public ComboBox genderComboBox;
  @FXML public TextField mobilePhoneTextField;
  @FXML public DatePicker hireDatePicker;
  @FXML public TextField regionTextField;
  @FXML public TextField ProvinceTextField;
  @FXML public TextField communeTextField;
  @FXML public TextField streetTextField;
  @FXML public TextField numberTextField;

}
