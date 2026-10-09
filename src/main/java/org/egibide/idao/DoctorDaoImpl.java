package org.egibide.idao;

import org.egibide.dao.DoctorDao;
import org.egibide.models.Doctor;
import org.egibide.utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {

    @Override
    public boolean add(Doctor doctor) {
        if (doctorExists(doctor.getId())) {
            return false;
        }

        String query = "INSERT INTO doctors (id, name, lastname, dni, salary, speciality) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = DatabaseConnection.getInstance()
                .getConnection().prepareStatement(query)) {
            ps.setInt(1, doctor.getId());
            ps.setString(2, doctor.getName());
            ps.setString(3, doctor.getLastname());
            ps.setString(4, doctor.getDni());
            ps.setDouble(5, doctor.getSalary());
            ps.setString(6, doctor.getSpeciality());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void delete(int id) {
        String query =
                "DELETE FROM doctors WHERE id=?";

        try {

            PreparedStatement ps = DatabaseConnection.getInstance()
                    .getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public Doctor getDoctor(int id) {

        String query = "select * from doctors where id=?";

        Doctor doctor = null;

        try (PreparedStatement ps = DatabaseConnection.getInstance()
                .getConnection().prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    doctor = new Doctor(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("lastname"),
                            rs.getString("dni"),
                            rs.getDouble("salary"),
                            rs.getString("speciality"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctor;


    }

    @Override
    public List<Doctor> getDoctors() {
        ArrayList<Doctor> doctors =
                new ArrayList<>();

        String query =
                "SELECT * FROM doctors";

        try (PreparedStatement ps = DatabaseConnection.getInstance()
                .getConnection().prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                doctors.add(new Doctor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("dni"),
                        rs.getDouble("salary"),
                        rs.getString("speciality")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }


    @Override

    public boolean update(Doctor doctor) {

        if (!doctorExists(doctor.getId())) {
            return false;
        }
        String query = "UPDATE doctors SET name=?, lastname=?, dni=?, salary=?, speciality=? WHERE id=?";
        try (PreparedStatement ps = DatabaseConnection.getInstance()
                .getConnection().prepareStatement(query)) {
            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getLastname());
            ps.setString(3, doctor.getDni());
            ps.setDouble(4, doctor.getSalary());
            ps.setString(5, doctor.getSpeciality());
            ps.setInt(6, doctor.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Doctor getDoctorByPatientId(int patient_id) {
        String query =
                "SELECT d.* " + "FROM doctors d " + "JOIN patients p ON d.id = p.doctor_id " + "WHERE p.id = ?";

        Doctor doctor = null;

        try (PreparedStatement ps = DatabaseConnection.getInstance()
                .getConnection().prepareStatement(query)) {
            ps.setInt(1, patient_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    doctor = new Doctor(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("lastname"),
                            rs.getString("dni"),
                            rs.getDouble("salary"),
                            rs.getString("speciality"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctor;
    }

    private boolean doctorExists(int id) {
        return getDoctor(id) != null;
    }
}
