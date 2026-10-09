package org.egibide.irepositories;

import org.egibide.models.Patient;

public interface PatientRepository {
    Patient getPatient(int id);

    boolean add(Patient patient);

    void update(Patient patient);

    void remove(Patient patient);

    boolean isPatientAttendedByDoctor(int i, int i1);
}
