/*
    Manages all inputs given in input.txt file
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HospitalManagementSystem {

    private PatientDAO managePatients;
    private AdmissionDAO manageAdmissions;
    ArrayList<String> toBeWritten = new ArrayList<>();
    public HospitalManagementSystem() {
        this.manageAdmissions = new AdmissionDAO();
        this.managePatients = new PatientDAO();
    }

    public PatientDAO getManagePatients() {
        return managePatients;
    }

    public void setManagePatients(PatientDAO managePatients) {
        this.managePatients = managePatients;
    }

    public AdmissionDAO getManageAdmissions() {
        return manageAdmissions;
    }

    public void setManageAdmissions(AdmissionDAO manageAdmissions) {
        this.manageAdmissions = manageAdmissions;
    }

    // Checks the keywords given as input and calls the right method for operation
    public void manageInput(ArrayList<String> inputs) {
        for (String line : inputs) {
            String[] splittedLine = line.split(" ");
            if (splittedLine[0].equals("AddPatient")) {
                String[] patientArray = line.split(" ", 6);
                addPatient(patientArray);
            }
            if (splittedLine[0].equals("RemovePatient")) {
                removePatient(splittedLine[1]);
            }
            if (splittedLine[0].equals("CreateAdmission")) {
                createAdmission(splittedLine);
            }
            if (splittedLine[0].equals("AddExamination")) {
                addExamination(splittedLine);
            }
            if (splittedLine[0].equals("TotalCost")) {
                totalCost(splittedLine);
            }
            if (splittedLine[0].equals("ListPatients")) {
                listAll();
            }
        }
        
    }
    // Adds a new patient info using Data Access Object Pattern
    public void addPatient(String[] input){
        String address = "Address: " + input[5];
        Patient patient = new Patient(input[1],input[2],input[3],input[4],address);
        managePatients.add(patient);
        String line = "Patient " + patient.getPatientID() + " " + patient.getName() + " added";
        toBeWritten.add(line);
    }
    // Removes patient info using Data Access Object Pattern
    public void removePatient(String input){
        int ID = Integer.parseInt(input);
        Patient deleted = (Patient) managePatients.deleteByID(ID);
        manageAdmissions.deleteByID(ID);
        String line = "Patient " + deleted.getPatientID() + " " +deleted.getName() + " removed";
        toBeWritten.add(line);
    }
    // Adds a new admission info using Data Access Object Pattern
    public void createAdmission(String[] input){
        String  ID = input[1];
        String  patientID = input[2];
        ArrayList<Examination> examinations = new ArrayList<>();
        Admission admission = new Admission(ID, patientID, examinations);
        manageAdmissions.add(admission);
        String line = "Admission " + admission.getID() + " created";
        toBeWritten.add(line);
    }
    // Adds a new examination info to the existing patient
    public void addExamination(String[] input){
        Examination examination = null;
        if (input[2].equals("Inpatient")){
            examination = new Inpatient();
            String line = "Inpatient examination added to admission " + input[1];
            toBeWritten.add(line);
        }
        else{
            examination = new Outpatient();
            String line = "Outpatient examination added to admission " + input[1];
            toBeWritten.add(line);
        }
        for (int j = 3; j < input.length; j++){
            if (input[j].equals("tests"))
                examination = new Test(examination);
            if (input[j].equals("imaging"))
                examination = new Imaging(examination);
            if (input[j].equals("doctorvisit"))
                examination = new DoctorVisit(examination);
            if (input[j].equals("measurements"))
                examination = new Measurement(examination);
        }
        int ID = Integer.parseInt(input[1]);
        Admission admission = (Admission) manageAdmissions.getByID(ID);
        admission.examinations.add(examination);

    }
    // Returns cost value as an integer
    public int totalCost(String[] input){
        int ID = Integer.parseInt(input[1]);
        Admission admission = (Admission)manageAdmissions.getByID(ID);
        int totalCost = 0;
        toBeWritten.add("TotalCost for admission " + ID);
        for (Examination examination : admission.getExaminationsList()){
            toBeWritten.add("\t"+ examination.getExaminations()+ " " + examination.getCost()+ "$");
            totalCost += examination.getCost();
        }
        toBeWritten.add("\tTotal: " + totalCost + "$");
        return totalCost;
    }
    // Lists all patients ordered by name
    public void listAll(){
        int size = managePatients.getAll().size();
        Patient[] toSort = new Patient[size];
        ArrayList<Object> all = managePatients.getAll();
        for (int i = 0; i < all.size(); i++) {
            Object object = all.get(i);
            Patient patient = (Patient) object;
            toSort[i] = patient;
        }
        for (int i = 0; i < toSort.length-1; i++){
            for (int j = 0; j < toSort.length-1-i; j++) {
                if (toSort[j].getName().compareTo(toSort[j + 1].getName()) > 0) {
                    Patient temp = toSort[j];
                    toSort[j] = toSort[j + 1];
                    toSort[j + 1] = temp;
                }
            }
        }
        toBeWritten.add("Patient List:");
        for (Patient patient: toSort){
            toBeWritten.add(patient.getPatientID() + " " + patient.getName() + " " + patient.getSurname()+
                    " " + patient.getPhoneNumber() + " " + patient.getAddress());
        }
    }

    /*
        Creates the output files and writes information about
        patients, admissions and examinations
     */
    public void writeToFile(){
        FileWriter writer = null;
        try{
            writer = new FileWriter("output.txt");

            for(String line: toBeWritten){
                writer.write(line+ "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter patientWriter = null;
        try{
            patientWriter = new FileWriter("patient.txt");
            int size = managePatients.getAll().size();
            Patient[] toSort = new Patient[size];
            ArrayList<Object> all = managePatients.getAll();
            for (int i = 0; i < all.size(); i++) {
                Object object = all.get(i);
                Patient patient = (Patient) object;
                toSort[i] = patient;
            }
            for (int i = 0; i < toSort.length-1; i++){
                for (int j = 0; j < toSort.length-1-i; j++) {
                    if (toSort[j].getPatientID() > toSort[j + 1].getPatientID()) {
                        Patient temp = toSort[j];
                        toSort[j] = toSort[j + 1];
                        toSort[j + 1] = temp;
                    }
                }
            }
            for (Patient patient: toSort){
                String line = patient.getPatientID() + "\t" + patient.getName() + " " + patient.getSurname()+
                        "\t" + patient.getPhoneNumber() + "\t" + patient.getAddress();
                patientWriter.write(line +"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                patientWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter admissionWriter = null;
        try{
            admissionWriter = new FileWriter("admission.txt");

            for (Object object : manageAdmissions.getAll()){
                Admission admission = (Admission) object;
                admissionWriter.write(admission.getID() + "\t" + admission.getPatientID()+ "\n");
                for(Examination examination : admission.getExaminationsList()){
                    admissionWriter.write(examination.getExaminations()+ "\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                admissionWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}