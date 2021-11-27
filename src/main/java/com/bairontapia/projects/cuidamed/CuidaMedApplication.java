package com.bairontapia.projects.cuidamed;


import com.bairontapia.projects.cuidamed.disease.DiseaseDAO;
import com.bairontapia.projects.cuidamed.disease.medicationadministration.MedicationAdministrationDAO;
import com.bairontapia.projects.cuidamed.disease.medicationprescription.MedicationPrescriptionDAO;
import com.bairontapia.projects.cuidamed.disease.prescription.PrescriptionDAO;
import com.bairontapia.projects.cuidamed.disease.sickelderly.SickElderlyDAO;
import java.io.IOException;
import java.sql.SQLException;

public class CuidaMedApplication {

  public static void main(String... args) throws SQLException, IOException {
    MedicationPrescriptionDAO.getInstance().getAll().forEach(System.out::println);

    JavaFXApplication.main(args);
  }
}
