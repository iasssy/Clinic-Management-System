
/**
 * Define class doctor extending abstract class Person 
 * @version 1.0
 * @since 2024-04-11
 * @author Iana Setrakova, Mohammad Javad Safdari, Mohamed Amine Mankai, Sesen Msgna Tesfay
 * @return
 */

// import packages
import java.time.LocalDate;

class Doctor extends Person {
    private static final String doctorsFileName = "doctors.txt";
    //
    private String doctorID;
    private LocalDate dateEmployed;
    private String specialty;
    // private String officeLocation;

    // Constructor
    // public Person(String firstName, String lastName, int yearOfBirth,int
    // monthOfBirth, int dayOfBirth, Gender gender, String email) {

    public Doctor(String firstName, String lastName, int yearOfBirth, int monthOfBirth, int dayOfBirth, Gender gender,
            String email, LocalDate dateEmployed, String specialty) {
        super(firstName, lastName, yearOfBirth, monthOfBirth, dayOfBirth, gender, email);
        this.doctorID = generateDoctorID();
        this.dateEmployed = dateEmployed;
        this.specialty = specialty;
    }

    public String getDoctorID() {
        return doctorID;
    }

    // Method to generate a unique doctor ID
    private String generateDoctorID() {
        return String.valueOf((SystemManagement.getMaxId(doctorsFileName)) + 1); // Incrementing the counter to generate a unique ID
    }
    
    public void setDoctorID(String doctorID){  // will be needed when reading the file and creating the ArrayList of objects
        this.doctorID = doctorID; 
    }

    public void setDateEmployed(LocalDate aDateEmployed) {
        this.dateEmployed = aDateEmployed;
    }

    // get date whe the doctor was employed
    public LocalDate getDateEmployed() {
        return dateEmployed;
    }

    // set and get doctor's speciality
    public void setSpecialty(String aSpecialty) {
        this.specialty = aSpecialty;
    }

    public String getSpecialty() {
        return specialty;
    }



    // toString() method to represent the appointment information
    @Override
    public String toString() {
        return "Id: " + doctorID +
                ", Name: " + this.getFirstName() +" "+ this.getLastName() +
                ", Date of bith: " + this.getYearOfBirth()+"-"+this.getMonthOfBirth() +"-"+this.getDayOfBirth()+
                ", Gender: " + this.getGender()+", email: " + this.getEmail() + 
                ", Date of employment: " + this.getDateEmployed() + 
                ", Speciality: " + this.getSpecialty();
    }


}