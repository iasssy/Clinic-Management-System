
/**
 * Abstract class Person 
 * @version 1.0
 * @since 2024-04-11
 * @author Iana Setrakova, Mohammad Javad Safdari, Mohamed Amine Mankai, Sesen Msgna Tesfay
 * @return
 */

// import packages
import java.time.LocalDate;
import java.time.Period;


// abstract class Person
abstract class Person {

    // enum object for Gender
    public enum Gender{
        //define each gender and should be capital as they are constant
        MALE,
        FEMALE,
        OTHER;
    }

    // data members for the class Person
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String email;

    //default constructor
    public Person(){
        this.firstName = "";
        this.lastName = "";
        this.dateOfBirth = LocalDate.now();
        this.gender = Gender.MALE;
        this.email = "";
    }
    
    //constructor with parameters to set the Person characteristics
    public Person(String firstName, String lastName, int yearOfBirth,int monthOfBirth, int dayOfBirth, Gender gender, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        //getting the date birthdate as 3 ints to be able to convert it to LocalDate
        this.dateOfBirth = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
        this.gender = gender;
        this.email = email;
    }
    //getter and setter for each field
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public int getYearOfBirth() {
        return this.dateOfBirth.getYear();
    }

    public int getMonthOfBirth() {
        return this.dateOfBirth.getMonthValue();
    }

    public int getDayOfBirth() {
        return this.dateOfBirth.getDayOfMonth();
    }

    public Gender getGender() {
        return gender;
    }
    public String getEmail() {
        return email;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setBirthDate(int yearOfBirth,int monthOfBirth, int dayOfBirth) {
        this.dateOfBirth = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    //calculating the age by knowing the data of birth and current time
    public int getAge(){
        LocalDate today = LocalDate.now();
        //Period class can calculate the difference date with method between
        Period duration = Period.between(dateOfBirth, today);
        return duration.getYears();
    }

    //override the method toString
    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + dateOfBirth +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                '}';
    }
    
}