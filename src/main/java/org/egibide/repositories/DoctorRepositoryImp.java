package org.egibide.repositories;

import org.egibide.dao.DoctorDao;
import org.egibide.dao.PatientDao;
import org.egibide.idao.DoctorDaoImpl;
import org.egibide.idao.PatientDaoImpl;
import org.egibide.irepositories.DoctorRepository;
import org.egibide.models.Doctor;
import org.egibide.models.Patient;

import java.util.List;

public class DoctorRepositoryImp implements DoctorRepository {
    private final DoctorDao doctorDao = new DoctorDaoImpl();

    private final PatientDao patientDao = new PatientDaoImpl();


    @Override
    public Doctor getDoctor(int doctorId) {

        Doctor doctor = doctorDao.getDoctor(doctorId);

        if (doctor == null) {
            return null;
        }

        List<Patient> patients =
                patientDao.getPatientsByDoctorId(doctorId);

        doctor.setAttendedPatients(patients);

        return doctor;
    }
}
