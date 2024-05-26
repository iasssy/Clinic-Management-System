/**
 * Define class Appointment 
 * @version 1.0
 * @since 2024-04-11
 * @author Iana Setrakova, Mohammad Javad Safdari, Mohamed Amine Mankai, Sesen Msgna Tesfay
 * @return
 */

// import java packages
import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private static final String appointmentsFileName = "appointments.txt";

    private String appointmentId;
    private String doctorId;
    private String patientId;
    private LocalDate date;
    private LocalTime time;

    public Appointment(String doctorId, String patientId, LocalDate date, LocalTime time) {
        this.appointmentId = generateAppointmentID();
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
        this.time = time;
    }

    // Method to generate a unique patient ID
    private String generateAppointmentID() {       
        return String.valueOf((SystemManagement.getMaxId(appointmentsFileName)) + 1);
    }

    public String getAppointmentID() {
        return this.appointmentId;
    }

    public void setAppointmentID(String theAppointmentID) { // will be needed when reading the file and creating the
                                                            // ArrayList of objects
        this.appointmentId = theAppointmentID;
    }

    // Getters and setters for all fields

    public String getAppointmentId() {
        return appointmentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    // toString() method to represent the appointment information
    @Override
    public String toString() {
        return "Id: " + appointmentId +
                ", Date: " + date +
                ", Time: " + time +
                ", DoctorId: " + doctorId +
                ", PatientId: " + patientId;
    }
}
