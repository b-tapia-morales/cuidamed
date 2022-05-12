package com.bairontapia.projects.cuidamed;

import com.bairontapia.projects.cuidamed.disease.DiseaseDAO;
import com.bairontapia.projects.cuidamed.disease.prescription.PrescriptionDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.MedicalRecordDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup.RoutineCheckupDAO;
import com.bairontapia.projects.cuidamed.person.elder.ElderDAO;
import com.bairontapia.projects.cuidamed.person.responsible.ResponsibleDAO;
import com.bairontapia.projects.cuidamed.pojo.ElderPOJO;
import com.bairontapia.projects.cuidamed.pojo.MedicalRecordPOJO;
import com.bairontapia.projects.cuidamed.pojo.PrescriptionPOJO;
import com.bairontapia.projects.cuidamed.pojo.ResponsiblePOJO;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class CuidaMedApplication {

    public static void main(String... args) throws SQLException, IOException {
        var pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        try (var mongoClient = new MongoClient("localhost:27017", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build())) {
            var database = mongoClient.getDatabase("admin");
            var collection = database.getCollection("people", ElderPOJO.class);
            collection.drop();
            for (var elder : ElderDAO.getInstance().findAll().stream().limit(2).toList()) {
                var prescriptions = PrescriptionDAO.getInstance().findAll(elder.rut());
                var prescriptionPOJOs = new ArrayList<PrescriptionPOJO>();
                for (var prescription : prescriptions) {
                    var disease = DiseaseDAO.getInstance().find(prescription.diseaseName()).orElseThrow();
                    prescriptionPOJOs.add(new PrescriptionPOJO(prescription, disease));
                }
                var responsible = ResponsibleDAO.getInstance().find(elder.responsibleRut()).orElseThrow();
                var responsiblePOJO = new ResponsiblePOJO(responsible);
                var medicalRecord = MedicalRecordDAO.getInstance().find(elder.rut()).orElseThrow();
                var routineCheckups = RoutineCheckupDAO.getInstance().findAll(medicalRecord.rut()).stream().toList();
                var medicalRecordPOJO = new MedicalRecordPOJO(medicalRecord, routineCheckups);
                var elderPOJO = new ElderPOJO(elder, responsiblePOJO, medicalRecordPOJO, prescriptionPOJOs);
                System.out.println(elderPOJO);
                collection.insertOne(elderPOJO);
            }
        }
    }
}
