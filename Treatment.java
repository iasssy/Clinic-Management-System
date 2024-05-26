
/**
 * DEfine class Treatment
 * @version 1.0
 * @since 2024-04-11
 * @author Iana Setrakova, Mohammad Javad Safdari, Mohamed Amine Mankai, Sesen Msgna Tesfay
 * @return
 */

import java.time.LocalDate;

// Treatment interface representing medical treatments provided by doctors to patients
public class Treatment {    
    private static final String treatmentsFileName = "treatments.txt";

    private String treatmentId;
    private String description;
    private LocalDate date;
    private String doctorId;
    private String patientId;

    // Constructor
    public Treatment(String aPatientID, String aDoctorID, LocalDate aDate, String aDescription) {
        this.treatmentId = generateTreatmentID();
        this.patientId = aPatientID;
        this.doctorId = aDoctorID;
        this.date = aDate;
        this.description = aDescription;
    }

    // Method to generate a unique treatment ID as String
    private String generateTreatmentID() {
        return String.valueOf((SystemManagement.getMaxId(treatmentsFileName)) + 1);
    }

    public void setTreatmentID(String theTreatmentID){  // will be needed when reading the file and creating the ArrayList of objects
        this.treatmentId = theTreatmentID;
    }

    // Getter methods
    public String getTreatmentId() {
        return treatmentId;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    // Setter methods
    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    // toString() method to represent the appointment information
    @Override
    public String toString() {
        return "Id: " + this.getTreatmentId() +
                ", Date: " + date +
                ", DoctorId: " + doctorId +
                ", PatientId: " + patientId+
                ", Description: " + this.getDescription();
    }

    
}