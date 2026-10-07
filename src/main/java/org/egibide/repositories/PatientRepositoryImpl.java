package org.egibide.repositories;

import org.egibide.dao.DoctorDao;
import org.egibide.dao.PatientDao;
import org.egibide.idao.DoctorDaoImpl;
import org.egibide.idao.PatientDaoImpl;
import org.egibide.irepositories.PatientRepository;
import org.egibide.models.Doctor;
import org.egibide.models.Patient;
import org.egibide.utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    public boolean add(Patient patient) {
        if (patientExists(patient.getId())) {

            String query = "insert into patients values id=?, name=?, lastname=?, dni=?, age=?, phone=?, disease=?";

            PreparedStatement ps;

            int rs = 0;

            try {
                ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
                ps.setInt(1, patient.getId());
                ps.setString(2, patient.getName());
                ps.setString(3, patient.getLastname());
                ps.setString(4, patient.getDni());
                ps.setInt(5, patient.getAge());
                ps.setString(6, patient.getPhone());
                ps.setString(7, patient.getDisease());

                rs = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return (rs > 0);
        }
        return false;
    }

    private boolean patientExists(int id) {
        if(getPatient(id) == null){
            System.out.println("El paciente no existe");
        }
        return true;

    }

    @Override
    public void update(Patient patient) {

    }

    @Override
    public void remove(Patient patient) {

    }
}
