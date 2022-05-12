package com.bairontapia.projects.cuidamed.mvc.medical_record;

import com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup.RoutineCheckup;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;

import java.sql.Date;

@Getter
public class RoutineCheckupView {

    @FXML
    private Button add;
    @FXML
    private Button cancel;
    @FXML
    private DatePicker checkupDatePicker;
    @FXML
    private TextField height;
    @FXML
    private TextField weight;
    @FXML
    private TextField bmi;
    @FXML
    private TextField heartRate;
    @FXML
    private TextField diastolicPressure;
    @FXML
    private TextField systolicPressure;
    @FXML
    private TextField bodyTemperature;

    private RoutineCheckup routineCheckup;
    private String rut;


    public void initialize() {
        routineCheckup = null;
    }

    public void changes(String rut) {
        this.rut = rut;
    }

    public void addRoutineCheckup(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        this.routineCheckup = RoutineCheckup
                .createInstance(RutUtils.removeDots(rut.toLowerCase()),
                        Date.valueOf(checkupDatePicker.getValue()), Double.parseDouble(height.getText()),
                        Double.parseDouble(weight.getText()), Double.parseDouble(bmi.getText()),
                        Integer.parseInt(height.getText()), Integer.parseInt(diastolicPressure.getText()),
                        Integer.parseInt(systolicPressure.getText()),
                        Double.parseDouble(bodyTemperature.getText()));
    }

    public void cancelRoutineCheckup(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
