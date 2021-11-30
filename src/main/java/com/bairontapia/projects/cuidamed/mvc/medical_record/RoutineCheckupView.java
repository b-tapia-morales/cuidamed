package com.bairontapia.projects.cuidamed.mvc.medical_record;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lombok.Getter;

@Getter
public class RoutineCheckupView {

  @FXML private DatePicker checkupDatePicker;
  @FXML private TextField height;
  @FXML private TextField weight;
  @FXML private TextField bmi;
  @FXML private TextField heartRate;
  @FXML private TextField diastolicPressure;
  @FXML private TextField systolicPressure;
  @FXML private TextField bodyTemperature;
  @FXML private Button add;
  @FXML private Button cancel;
}
