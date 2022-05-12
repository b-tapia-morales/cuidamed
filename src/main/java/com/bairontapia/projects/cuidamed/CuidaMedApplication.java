package com.bairontapia.projects.cuidamed;

import com.bairontapia.projects.cuidamed.disease.DiseaseDAO;
import com.bairontapia.projects.cuidamed.disease.prescription.Prescription;
import com.bairontapia.projects.cuidamed.disease.prescription.PrescriptionDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.MedicalRecordDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup.RoutineCheckupDAO;
import com.bairontapia.projects.cuidamed.person.elder.ElderDAO;
import com.bairontapia.projects.cuidamed.person.responsible.ResponsibleDAO;
import com.bairontapia.projects.cuidamed.pojo.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CuidaMedApplication {

    public static void main(String... args) throws SQLException, IOException {
        for (var elder : ElderDAO.getInstance().findAll().stream().limit(2).toList()) {
            var prescriptions = PrescriptionDAO.getInstance().findAll(elder.rut());
            var prescriptionPOJOs = new ArrayList<PrescriptionPOJO>();
            for (var prescription: prescriptions) {
                var disease = DiseaseDAO.getInstance().find(prescription.diseaseName()).orElseThrow();
                prescriptionPOJOs.add(new PrescriptionPOJO(prescription, disease));
            }
            var responsible = ResponsibleDAO.getInstance().find(elder.responsibleRut()).orElseThrow();
            var responsiblePOJO = new ResponsiblePOJO(responsible);
            var medicalRecord = MedicalRecordDAO.getInstance().find(elder.rut()).orElseThrow();
            var routineCheckups = RoutineCheckupDAO.getInstance().findAll(medicalRecord.rut()).stream().toList();
            var medicalRecordPOJO = new MedicalRecordPOJO(medicalRecord, routineCheckups);
            var elderPOJO = new ElderPOJO(elder, responsiblePOJO, medicalRecordPOJO, prescriptionPOJOs);
            System.out.println(elderPOJO.getRut());
            System.out.println(elderPOJO.getResponsible().getRut());
            System.out.println(elderPOJO.getMedicalRecord().getBloodType());
            System.out.println(elderPOJO.getMedicalRecord().getHealthCare());
            System.out.println(elderPOJO.getPrescriptions());
        }
    }
}
