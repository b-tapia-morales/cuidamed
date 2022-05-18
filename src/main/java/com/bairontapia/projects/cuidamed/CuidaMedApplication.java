package com.bairontapia.projects.cuidamed;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import com.bairontapia.projects.cuidamed.disease.DiseaseDAO;
import com.bairontapia.projects.cuidamed.disease.medication.MedicationDAO;
import com.bairontapia.projects.cuidamed.disease.medicationadministration.MedicationAdministrationDAO;
import com.bairontapia.projects.cuidamed.disease.medicationprescription.MedicationPrescriptionDAO;
import com.bairontapia.projects.cuidamed.disease.prescription.DiagnosticDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.MedicalRecordDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup.RoutineCheckupDAO;
import com.bairontapia.projects.cuidamed.person.elder.ElderDAO;
import com.bairontapia.projects.cuidamed.person.responsible.ResponsibleDAO;
import com.bairontapia.projects.cuidamed.pojo.DiagnosticPOJO;
import com.bairontapia.projects.cuidamed.pojo.ElderPOJO;
import com.bairontapia.projects.cuidamed.pojo.MedicalRecordPOJO;
import com.bairontapia.projects.cuidamed.pojo.MedicationAdministrationPOJO;
import com.bairontapia.projects.cuidamed.pojo.MedicationPrescriptionPOJO;
import com.bairontapia.projects.cuidamed.pojo.ResponsiblePOJO;
import com.bairontapia.projects.cuidamed.pojo.RoutineCheckupPOJO;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.model.Indexes;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

public class CuidaMedApplication {

  public static void main(String... args) throws SQLException, IOException {
    var mongoLogger = Logger.getLogger("org.mongodb.driver");
    mongoLogger.setLevel(Level.WARNING);
    var pojoCodecRegistry =
        fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    try (var mongoClient =
        new MongoClient("localhost:27017",
            MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build())) {
      var database = mongoClient.getDatabase("admin");
      var elderColl = database.getCollection("elder", ElderPOJO.class);
      elderColl.drop();
      var routineCheckupColl = database.getCollection("routine_checkup", RoutineCheckupPOJO.class);
      routineCheckupColl.drop();
      var administrationColl = database.getCollection("medication_administration",
          MedicationAdministrationPOJO.class);
      administrationColl.drop();
      for (var elder : ElderDAO.getInstance().findAll()) {
        var elderId = new ObjectId();
        var diagnostics = DiagnosticDAO.getInstance().findAll(elder.rut());
        var diagnosticPOJOS = new ArrayList<DiagnosticPOJO>();
        for (var diagnostic : diagnostics) {
          var disease = DiseaseDAO.getInstance().find(diagnostic.diseaseName()).orElseThrow();
          var prescriptions =
              MedicationPrescriptionDAO.getInstance().findByRutAndDiseaseName(elder.rut(),
                  disease.name());
          var prescriptionPOJOS = new ArrayList<MedicationPrescriptionPOJO>();
          for (var prescription : prescriptions) {
            var medication =
                MedicationDAO.getInstance().find(prescription.medicationName()).orElseThrow();
            var administrations =
                MedicationAdministrationDAO.getInstance().findByRutAndMedicationName(elder.rut(),
                    medication.name());
            var administrationPOJOS = administrations.stream()
                .map(MedicationAdministrationPOJO::new).toList();
            administrationColl.insertMany(administrationPOJOS);
            var administrationIds =
                administrationPOJOS.stream().map(MedicationAdministrationPOJO::getId).toList();
            prescriptionPOJOS.add(
                new MedicationPrescriptionPOJO(prescription, medication, administrationIds));
          }
          diagnosticPOJOS.add(new DiagnosticPOJO(diagnostic, disease, prescriptionPOJOS));
        }
        var responsible = ResponsibleDAO.getInstance().find(elder.responsibleRut()).orElseThrow();
        var responsiblePOJO = new ResponsiblePOJO(responsible);
        var medicalRecord = MedicalRecordDAO.getInstance().find(elder.rut()).orElseThrow();
        var medicalRecordPOJO = new MedicalRecordPOJO(medicalRecord);
        var elderPOJO = new ElderPOJO(elder, responsiblePOJO, medicalRecordPOJO, diagnosticPOJOS);
        elderColl.insertOne(elderPOJO);
        var routineCheckupPOJOS =
            RoutineCheckupDAO.getInstance().findAll(medicalRecord.rut()).stream()
                .map(e -> new RoutineCheckupPOJO(e, elderId)).toList();
        routineCheckupColl.insertMany(routineCheckupPOJOS);
        routineCheckupColl.createIndex(Indexes.hashed("elderId"));
        elderColl.createIndex(Indexes.hashed("{diagnostics.medicationPrescriptions"
            + ".administrationIds:1}"));
      }
    }
  }
}
