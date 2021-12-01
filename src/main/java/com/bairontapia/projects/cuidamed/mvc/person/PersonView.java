package com.bairontapia.projects.cuidamed.mvc.person;

import com.bairontapia.projects.cuidamed.mappings.gender.Gender;
import com.bairontapia.projects.cuidamed.person.Person;
import com.bairontapia.projects.cuidamed.person.carer.Carer;
import com.bairontapia.projects.cuidamed.person.carer.CarerDAO;
import com.bairontapia.projects.cuidamed.person.elder.Elder;
import com.bairontapia.projects.cuidamed.person.elder.ElderDAO;
import com.bairontapia.projects.cuidamed.person.responsible.Responsible;
import com.bairontapia.projects.cuidamed.person.responsible.ResponsibleDAO;
import com.bairontapia.projects.cuidamed.utils.validation.RutUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;

public class PersonView {

  @FXML
  public AnchorPane anchor;
  @FXML
  public Button addElderButton;
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
    personTableView.getItems().clear();
    final var choice = personComboBox.getSelectionModel().getSelectedItem();
    final var set = new LinkedHashSet<Person>();
    switch (choice) {
      case ELDER -> set.addAll(ElderDAO.getInstance().findAll());
      case CARER -> set.addAll(CarerDAO.getInstance().findAll());
      case RESPONSIBLE -> set.addAll(ResponsibleDAO.getInstance().findAll());
    }
    personTableView.getItems().addAll(set);
  }

  @FXML
  public void loadPanel(MouseEvent event) throws IOException, SQLException {
    if (!event.getButton().equals(MouseButton.PRIMARY) || event.getClickCount() != 2
        || personComboBox.getSelectionModel().isEmpty()) {
      return;
    }
    FXMLLoader fxml = new FXMLLoader();
    Scene scene;
    Stage stage = new Stage();
    if (personComboBox.getSelectionModel().getSelectedIndex() == 0) {
      fxml.setLocation(getClass().getResource("/fxml/elder.fxml"));
      scene = new Scene(fxml.load());
      Elder elder = (Elder) personTableView.getSelectionModel().getSelectedItem();
      ElderView elderView = (ElderView) fxml.getController();
      elderView.recoveryData(elder);
    } else {
      if (personComboBox.getSelectionModel().getSelectedIndex() == 1) {
        fxml.setLocation(getClass().getResource("/fxml/carer.fxml"));
        scene = new Scene(fxml.load());
        Carer carer = (Carer) personTableView.getSelectionModel().getSelectedItem();
        CarerView carerView = (CarerView) fxml.getController();
        carerView.recoveryData(carer);
      } else {
        fxml.setLocation(getClass().getResource("/fxml/responsible.fxml"));
        scene = new Scene(fxml.load());
        Responsible responsible = (Responsible) personTableView.getSelectionModel()
            .getSelectedItem();
        ResponsibleView responsibleView = (ResponsibleView) fxml.getController();
        responsibleView.recoveryData(responsible);
      }
    }
    stage.setScene(scene);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.showAndWait();
  }

  @FXML
  public void onElderButtonClicked() throws IOException {
    final var stage = new Stage();
    final Parent root = FXMLLoader
        .load(Objects.requireNonNull(getClass().getResource("/fxml/elder_insert.fxml")));
    final Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
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
