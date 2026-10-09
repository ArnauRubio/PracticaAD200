package org.egibide.examples;

import org.egibide.irepositories.PatientRepository;
import org.egibide.irepositories.DoctorRepository;
import org.egibide.models.Doctor;
import org.egibide.models.Patient;
import org.egibide.repositories.DoctorRepositoryImp;
import org.egibide.repositories.PatientRepositoryImpl;

public class App {
    public static void main(String[] args) {

        System.out.println("TAREA 1:  ");

        DoctorRepository doctorRepository = new DoctorRepositoryImp();

        // Buscamos el doctor con id 1
        Doctor doctor =
                doctorRepository.getDoctor(1);


        // Comprobamos que existe
        if (doctor != null) {

            System.out.println("Doctor encontrado:");

            System.out.println(doctor);


            System.out.println();
            System.out.println("Pacientes atendidos:");

            for (Patient paciente : doctor.getAttendedPatients()) {

                System.out.println(paciente);
            }

        } else {

            System.out.println("No existe el doctor");
        }

        System.out.println("TAREA 2:  ");

        PatientRepository patientRepository = new PatientRepositoryImpl();


        // El paciente 1 pertenece al doctor 1
        boolean prueba1 = patientRepository.isPatientAttendedByDoctor(1, 1);

        System.out.println(
                "Paciente 1 - Doctor 1: " + prueba1
        );


        // El paciente 1 NO pertenece al doctor 999
        boolean prueba2 =
                patientRepository
                        .isPatientAttendedByDoctor(1, 999);

        System.out.println(
                "Paciente 1 - Doctor 999: " + prueba2
        );
    }
}
