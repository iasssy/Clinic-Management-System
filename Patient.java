

/**
 * Define class Patient extending abstract class Person 
 * @version 1.0
 * @since 2024-04-11
 * @author Iana Setrakova, Mohammad Javad Safdari, Mohamed Amine Mankai, Sesen Msgna Tesfay
 * @return
 */


// Patient class extending abstract class Person
class Patient extends Person{    
    private static final String patientsFileName = "patients.txt";
    
    private String insuranceCompany;
    private String patientID;

    // Constructor
    public Patient(String firstName, String lastName, int yearOfBirth, int monthOfBirth, int dayOfBirth, Gender gender,
            String email, String insuranceCompany) {
        super(firstName, lastName, yearOfBirth, monthOfBirth, dayOfBirth, gender, email);
        this.insuranceCompany = insuranceCompany;
        this.patientID = generatePatientID();
    }


    // Method to generate a unique patient ID
    private String generatePatientID() {
        return String.valueOf((SystemManagement.getMaxId(patientsFileName)) + 1);
    }
    
    public void setPatientID(String thePatientID){ // will be needed when reading the file and creating the ArrayList of objects
        this.patientID = thePatientID;
    }


    // Getter methods
    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public String getPatientID() {
        return patientID;
    }

    // Setter methods
    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    // toString() method to represent the appointment information
    @Override
    public String toString() {
        return "Id: " + patientID +
                ", Name: " + this.getFirstName() +" "+ this.getLastName() +
                ", Date of bith: " + this.getYearOfBirth()+"-"+this.getMonthOfBirth() +"-"+this.getDayOfBirth()+
                ", gender: " + this.getGender()+", email: " + this.getEmail()+
                ", insurance company: " + this.getInsuranceCompany();
    }

}
