// Makes the operations for admissions

import java.util.ArrayList;

public class AdmissionDAO implements DataAccessObject {
    ArrayList<Object> admissions;
    public AdmissionDAO() {
        this.admissions = new ArrayList<>();
    }

    @Override
    public Object getByID(int ID) {
        Admission found = null;
        for(Object object : admissions){
            Admission admission = (Admission) object;
            if(ID == admission.getID()){
                found = admission;
                break;
            }
        }
        return found;
    }

    @Override
    public Object deleteByID(int ID) {
        Admission toBeDeleted = null;
        for (Object object : admissions) {
            Admission admission = (Admission) object;
            if (admission.getPatientID() == ID) {
                toBeDeleted = admission;
                admissions.remove(admission);
                break;

            }
        }
        return toBeDeleted;
    }

    @Override
    public void add(Object object) {
        admissions.add((Admission)object);
    }

    @Override
    public ArrayList<Object> getAll() {
        return admissions;
    }
}