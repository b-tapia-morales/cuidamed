package com.bairontapia.projects.cuidamed;

import com.bairontapia.projects.cuidamed.person.CarerDAO;
import com.bairontapia.projects.cuidamed.person.ElderDAO;
import java.io.IOException;
import java.sql.SQLException;

public class CuidaMedApplication {

  public static void main(String... args) throws SQLException, IOException {

    new ElderDAO().getInstance().getAll().forEach(System.out::println);
    JavaFXApplication.main(args);
  }
}
