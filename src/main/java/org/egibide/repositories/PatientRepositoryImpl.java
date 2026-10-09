package org.egibide.repositories;

import org.egibide.dao.DoctorDao;
import org.egibide.dao.PatientDao;
import org.egibide.idao.DoctorDaoImpl;
import org.egibide.idao.PatientDaoImpl;
import org.egibide.irepositories.PatientRepository;
import org.egibide.models.Doctor;
import org.egibide.models.Patient;

public class PatientRepositoryImpl implements PatientRepository {
    private final PatientDao patientDao = new PatientDaoImpl();
    private final DoctorDao doctorDao = new DoctorDaoImpl();


    @Override
    public Patient getPatient(int id) {
        Patient patient = patientDao.getPatient(id);

        if (patient == null) {
            return null;
        }

        Doctor doctor =
                doctorDao.getDoctorByPatientId(patient.getId());

        patient.setDoctor(doctor);

        return patient;

    }

    @Override
    public boolean add(Patient patient) {
        return patientDao.add(patient) > 0;
    }

    @Override
    public void update(Patient patient) {
        patientDao.update(patient);
    }

    @Override
    public void remove(Patient patient) {
        patientDao.remove(patient.getId());
    }

    @Override
    public boolean isPatientAttendedByDoctor(int patientId, int doctorId) {
        Patient patient = getPatient(patientId);

        if (patient == null) {
            return false;
        }

        if (patient.getDoctor() == null) {
            return false;
        }

        return patient.getDoctor().getId() == doctorId;
    }
}
