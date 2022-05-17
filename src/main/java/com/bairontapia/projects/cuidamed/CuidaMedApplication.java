package com.bairontapia.projects.cuidamed;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import com.bairontapia.projects.cuidamed.disease.DiseaseDAO;
import com.bairontapia.projects.cuidamed.disease.prescription.PrescriptionDAO;
import com.bairontapia.projects.cuidamed.mappings.allergytype.AllergyType;
import com.bairontapia.projects.cuidamed.medicalrecord.MedicalRecordDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.allergy.Allergy;
import com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup.RoutineCheckupDAO;
import com.bairontapia.projects.cuidamed.person.elder.ElderDAO;
import com.bairontapia.projects.cuidamed.person.responsible.ResponsibleDAO;
import com.bairontapia.projects.cuidamed.pojo.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import org.bson.codecs.pojo.PojoCodecProvider;


public class CuidaMedApplication {

    public static void main(String... args) throws SQLException, IOException {
        var pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        try (var mongoClient = new MongoClient("localhost:27017", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build())) {
            var database = mongoClient.getDatabase("admin");
            var elderColl = database.getCollection("elder", ElderPOJO.class);
            elderColl.drop();
            var routineCheckupColl = database.getCollection("routine_checkup", RoutineCheckupPOJO.class);
            routineCheckupColl.drop();
            for (var elder : ElderDAO.getInstance().findAll()) {
                var prescriptions = PrescriptionDAO.getInstance().findAll(elder.rut());
                var prescriptionPOJOs = new ArrayList<PrescriptionPOJO>();
                for (var prescription : prescriptions) {
                    var disease = DiseaseDAO.getInstance().find(prescription.diseaseName()).orElseThrow();
                    prescriptionPOJOs.add(new PrescriptionPOJO(prescription, disease));
                }
                var responsible = ResponsibleDAO.getInstance().find(elder.responsibleRut()).orElseThrow();
                var responsiblePOJO = new ResponsiblePOJO(responsible);
                var medicalRecord = MedicalRecordDAO.getInstance().find(elder.rut()).orElseThrow();
                var allergyPOJOS = new ArrayList<AllergyPOJO>();
                for (int i = 0; i < 3; i++) {
                    int randomNum = 1 + (int) (Math.random() * ((5 - 1) + 1));
                    var allergy = Allergy.createInstance(elder.rut(), (short) randomNum, "xddddddddd");
                    allergyPOJOS.add(new AllergyPOJO(allergy));
                }
                var medicalRecordPOJO = new MedicalRecordPOJO(medicalRecord, allergyPOJOS);
                var elderPOJO = new ElderPOJO(elder, responsiblePOJO, medicalRecordPOJO, prescriptionPOJOs);
                elderColl.insertOne(elderPOJO);
                var routineCheckupPOJOS = RoutineCheckupDAO.getInstance().findAll(medicalRecord.rut())
                        .stream().map(e -> new RoutineCheckupPOJO(e, elderPOJO.getId())).toList();
                routineCheckupColl.insertMany(routineCheckupPOJOS);
            }
        }
    }
}
