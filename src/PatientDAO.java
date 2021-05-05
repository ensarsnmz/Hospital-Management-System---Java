// Makes the operations for patients

import java.util.ArrayList;

public class PatientDAO implements DataAccessObject {
    ArrayList<Object> patients;

    public PatientDAO() {
        this.patients = new ArrayList<>();
    }

    @Override
    public Object getByID(int ID) {
        Patient found = null;
        for(Object object : patients){
            Patient patient = (Patient)object;
            if(patient.getPatientID() == ID){
                found = patient;
                System.out.println(patient.getName());
                break;
            }
        }
        return found;
    }

    @Override
    public Object deleteByID(int ID) {
        Patient toBeDeleted = null;
        for (Object object : patients) {
            Patient patient = (Patient)object;
            if (patient.getPatientID() == ID) {
                toBeDeleted = patient;
                patients.remove(patient);
                //System.out.println(toBeDeleted.getName() + " " + toBeDeleted.getSurname() + " deleted");
                break;

            }
        }
        return toBeDeleted;
    }

    @Override
    public void add(Object object) {
        patients.add((Patient)object);
    }

    @Override
    public ArrayList<Object> getAll() {
        return patients;
    }
}