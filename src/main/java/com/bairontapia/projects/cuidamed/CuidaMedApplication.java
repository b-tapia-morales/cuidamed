package com.bairontapia.projects.cuidamed;

import com.bairontapia.projects.cuidamed.disease.DiseaseDAO;
import com.bairontapia.projects.cuidamed.disease.prescription.Prescription;
import com.bairontapia.projects.cuidamed.disease.prescription.PrescriptionDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.MedicalRecordDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup.RoutineCheckupDAO;
import com.bairontapia.projects.cuidamed.person.elder.ElderDAO;
import com.bairontapia.projects.cuidamed.person.responsible.ResponsibleDAO;
import com.bairontapia.projects.cuidamed.pojo.*;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.bson.Document;

public class CuidaMedApplication {

  public static void main(String... args) throws SQLException, IOException {
    MongoClient mongo = new MongoClient("localhost", 27017);

    System.out.println("Connected to the database successfully");
    // Accessing the database
    MongoDatabase database = mongo.getDatabase("myDb");

    // Creating a collection
    MongoCollection<Document> collection = database.getCollection("sampleCollection");
    System.out.println("Collection created successfully");

    for (var elder : ElderDAO.getInstance().findAll().stream().limit(70).toList()) {
      var prescriptions = PrescriptionDAO.getInstance().findAll(elder.rut());
      var prescriptionPOJOs = new ArrayList<PrescriptionPOJO>();
      for (var prescription : prescriptions) {
        var disease = DiseaseDAO.getInstance().find(prescription.diseaseName()).orElseThrow();
        prescriptionPOJOs.add(new PrescriptionPOJO(prescription, disease));
      }
      var responsible = ResponsibleDAO.getInstance().find(elder.responsibleRut()).orElseThrow();
      var responsiblePOJO = new ResponsiblePOJO(responsible);
      var medicalRecord = MedicalRecordDAO.getInstance().find(elder.rut()).orElseThrow();
      var routineCheckups =
          RoutineCheckupDAO.getInstance().findAll(medicalRecord.rut()).stream().toList();
      var medicalRecordPOJO = new MedicalRecordPOJO(medicalRecord, routineCheckups);
      var elderPOJO = new ElderPOJO(elder, responsiblePOJO, medicalRecordPOJO, prescriptionPOJOs);
      System.out.println(elderPOJO);
      Document user = new Document();
      user.append("rut", elderPOJO.getRut())
          .append("nombre", elderPOJO.getFirstName())
          .append("primerApellido", elderPOJO.getLastName())
          .append("segundoApellido", elderPOJO.getSecondLastName())
          .append("fechaNacimiento", elderPOJO.getBirthDate())
          .append("fechaAdmision", elderPOJO.getAdmissionDate())
          .append("edad", elderPOJO.getAge())
          .append("gender", elderPOJO.getGender().toString())
          .append("esActivo", elderPOJO.getIsActive());
      collection.insertOne(user);
    }
  }
}
