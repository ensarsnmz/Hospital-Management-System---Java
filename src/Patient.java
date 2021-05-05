import java.util.ArrayList;

public class Patient {
    private int patientID;
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;

    public Patient(String patientID, String name, String surname, String phoneNumber, String address) {
        this.patientID = Integer.parseInt(patientID);
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getPatientID() {
        return patientID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}