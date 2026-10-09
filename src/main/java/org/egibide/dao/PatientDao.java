package org.egibide.dao;

import org.egibide.models.Patient;

import java.util.List;

public interface PatientDao {
    Patient getPatient(int id);

    List<Patient> getPatients();

    int add(Patient patient);

    void remove(int id);

    boolean update(Patient patient);

    List<Patient> getPatientsByDoctorId(int doctorId);
}
