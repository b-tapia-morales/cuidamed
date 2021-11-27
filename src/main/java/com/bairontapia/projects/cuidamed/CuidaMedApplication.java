package com.bairontapia.projects.cuidamed;

import com.bairontapia.projects.cuidamed.person.elder.ElderDAO;
import com.bairontapia.projects.cuidamed.person.responsible.ResponsibleDAO;
import java.io.IOException;
import java.sql.SQLException;

public class CuidaMedApplication {

  public static void main(String... args) throws SQLException, IOException {
    ElderDAO.getInstance().findAll().forEach(System.out::println);
    ResponsibleDAO.getInstance().findAll().forEach(System.out::println);
    JavaFXApplication.main(args);
  }
}
