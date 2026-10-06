package org.egibide.idao;

import org.egibide.models.Doctor;

public interface DoctorDaoImpl_doc {
    Doctor getDoctor(int id);

    boolean update(Doctor doctor);
}
