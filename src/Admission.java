import java.util.ArrayList;

public class Admission {

    private int ID;
    private int patientID;
    ArrayList<Examination> examinations;

    public Admission(String ID, String patientID, ArrayList<Examination> examinations) {
        this.ID = Integer.parseInt(ID);
        this.patientID = Integer.parseInt(patientID);
        this.examinations = examinations;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public ArrayList<Examination> getExaminationsList() {
        return examinations;
    }

    public void setExamination(Examination examination) {

    }
}