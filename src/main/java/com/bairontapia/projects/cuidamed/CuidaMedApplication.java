package com.bairontapia.projects.cuidamed;

import com.bairontapia.projects.cuidamed.medicalrecord.MedicalRecordDAO;
import com.bairontapia.projects.cuidamed.medicalrecord.routinecheckup.RoutineCheckupDAO;
import com.bairontapia.projects.cuidamed.person.elder.ElderDAO;
import com.bairontapia.projects.cuidamed.person.responsible.ResponsibleDAO;
import com.bairontapia.projects.cuidamed.pojo.ElderPOJO;
import com.bairontapia.projects.cuidamed.pojo.MedicalRecordPOJO;
import com.bairontapia.projects.cuidamed.pojo.ResponsiblePOJO;

import java.io.IOException;
import java.sql.SQLException;

public class CuidaMedApplication {

    public static void main(String... args) throws SQLException, IOException {
        for (var elder : ElderDAO.getInstance().findAll()) {
            var responsible = ResponsibleDAO.getInstance().find(elder.responsibleRut()).orElseThrow();
            var responsiblePOJO = new ResponsiblePOJO(responsible);
            var medicalRecord = MedicalRecordDAO.getInstance().find(elder.rut()).orElseThrow();
            var routineCheckups = RoutineCheckupDAO.getInstance().findAll(medicalRecord.rut()).stream().toList();
            var medicalRecordPOJO = new MedicalRecordPOJO(medicalRecord, routineCheckups);
            var elderPOJO = new ElderPOJO(elder, responsiblePOJO, medicalRecordPOJO);
            System.out.println(elderPOJO.getRut());
            System.out.println(elderPOJO.getResponsible().getRut());
            System.out.println(elderPOJO.getMedicalRecord().getBloodType());
            System.out.println(elderPOJO.getMedicalRecord().getHealthCare());
            System.out.println(elderPOJO.getMedicalRecord().getRoutineCheckupPOJOS().get(0).getCheckupDate());
        }
    }
}
