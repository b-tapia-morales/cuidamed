package com.bairontapia.projects.cuidamed.mvc.person;

import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import com.bairontapia.projects.cuidamed.person.Person;
import com.bairontapia.projects.cuidamed.person.carer.CarerDAO;
import com.bairontapia.projects.cuidamed.person.elder.ElderDAO;
import com.bairontapia.projects.cuidamed.person.responsible.ResponsibleDAO;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.Getter;

public class PersonView {

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
  public TableColumn<Person, Gender> genderColumn;
  @FXML
  public TableView<Person> personTableView;
  @FXML
  private ComboBox<PersonChoice> personComboBox;
  @FXML
  private TextField rutTextField;

  public void initialize() {
    personComboBox.setItems(FXCollections.observableArrayList(PersonChoice.values()));
    rutColumn
        .setCellValueFactory(e -> new SimpleStringProperty(RutUtils.format(e.getValue().rut())));
    fullNameColumn
        .setCellValueFactory(e -> new SimpleStringProperty(String.join(" ",
            e.getValue().firstName(), e.getValue().lastName(), e.getValue().secondLastName())));
    birthDateColumn.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().birthDate()));
    ageColumn.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().age()).asObject());
    genderColumn.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().gender()));
  }

  @FXML
  public void onPersonChosen(ActionEvent event) throws SQLException, IOException {
    if (personComboBox.getSelectionModel().isEmpty()) {
      return;
    }

    final var choice = personComboBox.getSelectionModel().getSelectedItem();
    final var set = new LinkedHashSet<Person>();
    switch (choice) {
      case ELDER -> set.addAll(ElderDAO.getInstance().findAll());
      case CARER -> set.addAll(CarerDAO.getInstance().findAll());
      case RESPONSIBLE -> set.addAll(ResponsibleDAO.getInstance().findAll());
    }
    personTableView.getItems().clear();
    personTableView.getItems().addAll(set);
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
