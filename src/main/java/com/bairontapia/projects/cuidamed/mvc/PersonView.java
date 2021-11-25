package com.bairontapia.projects.cuidamed.mvc;

import com.bairontapia.projects.cuidamed.person.Person;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

public class PersonView {

  @FXML
  private ComboBox<PersonChoice> personComboBox;
  @FXML
  private TextField rutTextField;
  @FXML
  public Button goToButton;
  @FXML
  public TableColumn<Person, String> rutColumn;
  @FXML
  public TableColumn<Person, String> fullNameColumn;
  @FXML
  public TableColumn<Person, LocalDate> birthDateColumn;
  @FXML
  public TableColumn<Person, Integer> ageColumn;
  @FXML
  public TableColumn<Person, String> genderColumn;
  @FXML
  public TableView<Person> personTableView;

  public void initialize() {
    personComboBox.setItems(FXCollections.observableArrayList(PersonChoice.values()));
    rutColumn
        .setCellValueFactory(e -> new SimpleStringProperty(RutUtils.format(e.getValue().rut())));
    fullNameColumn
        .setCellValueFactory(e -> new SimpleStringProperty(StringUtils.join(" ",
            e.getValue().firstName()), e.getValue().lastName(), e.getValue().secondLastName()));
    birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
    genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
  }

  @FXML
  public void onPersonChosen(ActionEvent event) {
    if (personComboBox.getSelectionModel().isEmpty()) {
      return;
    }
    final var set = new LinkedHashSet<Person>();
    final var choice = personComboBox.getSelectionModel().getSelectedItem();
    switch (choice) {
      case ELDER:
        break;
      case CARER:
        break;
      case RESPONSIBLE:
        break;
    }
  }


  @Getter
  enum PersonChoice {
    ELDER("Adulto mayor"),
    CARER("Cuidador"),
    RESPONSIBLE("Responsable");

    private final String choice;

    PersonChoice(String choice) {
      this.choice = choice;
    }

    @Override
    public String toString() {
      return choice;
    }
  }

}
