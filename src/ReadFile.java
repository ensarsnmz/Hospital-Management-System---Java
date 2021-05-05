/*
    Reads patient.txt , admission.txt and
    input file which is given as argument.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public Admission addAdmission(ArrayList<String> fileLines){
        ArrayList<Examination> examinations = new ArrayList<>();
        String ID = fileLines.get(0).split("\t")[0];
        String patientID = fileLines.get(0).split("\t")[1];
        for (int i = 1; i < fileLines.size(); i++){
            String[] operation = fileLines.get(i).split("\t")[1].split(" ");
            Examination examination = null;
            if (operation[0].equals("Inpatient")){
                examination = new Inpatient();
            }
            else{
                examination = new Outpatient();
            }
            for (int j = 0; j < operation.length; j++){
                if (operation[j].equals("tests"))
                    examination = new Test(examination);
                if (operation[j].equals("imaging"))
                    examination = new Imaging(examination);
                if (operation[j].equals("doctorvisit"))
                    examination = new DoctorVisit(examination);
                if (operation[j].equals("measurements"))
                    examination = new Measurement(examination);
            }
            examinations.add(examination);
        }
        return new Admission(ID, patientID, examinations);
    }
    public void readFile(String fileName) {
        FileInputStream readPatient = null;
        HospitalManagementSystem hms = new HospitalManagementSystem();
        try {
            readPatient = new FileInputStream("patient.txt");
            Scanner readFile = new Scanner(readPatient);
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                String[] patientInfo = line.split("\t");
                Patient patient = new Patient(patientInfo[0], patientInfo[1].split(" ")[0],
                        patientInfo[1].split(" ")[1], patientInfo[2], patientInfo[3]);
                hms.getManagePatients().add(patient);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't read file.");
        } finally {
            try {
                readPatient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileInputStream readAdmission = null;
        try {
            readAdmission = new FileInputStream("admission.txt");
            Scanner readFile = new Scanner(readAdmission);
            ArrayList<String> fileLines = new ArrayList<>();
            String ID;
            String patientID;
            ArrayList<Examination> examinations;
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                String[] admissionInfo = line.split("\t");
                if(isNumeric(admissionInfo[0]) && fileLines.isEmpty()){
                    fileLines.add(line);
                }
                else if(isNumeric(admissionInfo[0]) && !(fileLines.isEmpty())){
                    Admission admission = addAdmission(fileLines);
                    hms.getManageAdmissions().add(admission);
                    fileLines.clear();
                    fileLines.add(line);
                }
                else{
                    fileLines.add(line);
                    if (!readFile.hasNextLine()){
                        Admission admission = addAdmission(fileLines);
                        hms.getManageAdmissions().add(admission);
                        fileLines.clear();
                    }

                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't read file.");
        } finally {
            try {
                readPatient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileInputStream readInput = null;
        try {
            readInput = new FileInputStream(fileName);
            Scanner readFile = new Scanner(readInput);
            ArrayList<String> lines = new ArrayList<>();
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                lines.add(line);
            }
            hms.manageInput(lines);
            hms.writeToFile();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't read file.");
        } finally {
            try {
                readPatient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}