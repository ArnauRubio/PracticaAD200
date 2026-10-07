package org.egibide.repositories;

import org.egibide.dao.DoctorDao;
import org.egibide.dao.PatientDao;
import org.egibide.idao.DoctorDaoImpl;
import org.egibide.idao.PatientDaoImpl;
import org.egibide.irepositories.PatientRepository;
import org.egibide.models.Doctor;
import org.egibide.models.Patient;

public class PatientRepositoryImpl implements PatientRepository {
    private PatientDao patientDao = new PatientDaoImpl();
    private DoctorDao doctorDao = new DoctorDaoImpl();


    @Override
    public Patient getPatient(int id) {
        Patient patient = patientDao.getPatient(id);
        Doctor doctor = doctorDao.getDoctorByPatientId(patient.getId());
        patient.setDoctor(doctor);
        return patient;

    }

    @Override
    public void add(Patient patient) {

    }

    @Override
    public void update(Patient patient) {

    }

    @Override
    public void remove(Patient patient) {

    }
}
