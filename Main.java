
/**
 * Main execution program
 * 
 * @version 1.0
 * @since 2024-04-11
 * @author Iana Setrakova, Mohammad Javad Safdari, Mohamed Amine Mankai, Sesen
 *         Msgna Tesfay
 * @return
 */

// import classes
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SystemManagement.loadData();

        // Prompt the user to choose between options
        int choice = 0;
        while (choice != 4) {

            System.out.println();
            System.out.println("-----  MAIN MENU -----");
            System.out.println();
            System.out.println("Choose an option:");
            System.out.println("1. Receptionist"); // receptionist also can add doctor as HR
            System.out.println("2. Doctor");
            System.out.println("3. Patient");
            System.out.println("4. Exit");
            System.out.println();
            System.out.println("Enter your choice:");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    receptionistMenu(scanner);
                    break;
                case 2:
                    doctorMenu(scanner);
                    break;
                case 3:
                    patientMenu(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }

        scanner.close();
    } // end main

    private static void receptionistMenu(Scanner scanner) {

        boolean stayAtSameMenu = true;

        while (stayAtSameMenu) {
            System.out.println();
            System.out.println("-----  Receptionist menu -----");
            System.out.println();
            System.out.println("Choose an option:");
            System.out.println("1. Create a new appointment"); // scheduleAppointment(int, int, Date, Time): Appointment
            System.out.println("2. Modify the appointment"); // reschedulAppointment(String, LocalDate, Time) : void
            System.out.println("3. Cancel the appointment"); // cancelAppointment(String): void
            System.out.println("4. View all appointments for specific date");
            System.out.println("5. View all appointments for specific patient");
            System.out.println("6. View all appointments for specific doctor");
            System.out.println("7. Register a patient"); // - registerPatient(String,Date,String): Patient
            System.out.println("8. Modify the patient's info"); // modifyPatientInfo(Patient)
            System.out.println("9. Add a new doctor"); // addDoctor(Doctor): void
            System.out.println("10. Modify a doctor's info"); // modifyDoctorInfo(Doctor): void
            System.out.println("11. Return to main menu");

            int choice = 0;
            System.out.println();
            System.out.println("Enter your choice:");
            choice = scanner.nextInt();
            while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6 && choice != 7
                    && choice != 8 && choice != 9 && choice != 10 && choice != 11) {
                System.out.println("Invalid choice, please try again.");
                choice = scanner.nextInt();
            }
            switch (choice) {
                case 1:
                    System.out.println();
                    System.out.println("-----  Create a new appointment -----");
                    System.out.println();
                    // scheduleAppointment(String patientId, String doctorId, LocalDate date,
                    // LocalTime time)
                    System.out.println("Enter patientID");
                    String patientID = scanner.next();
                    System.out.println("Enter doctorID");
                    String doctorID = scanner.next();
                    System.out.println("Enter date in the format YYYY-MM-DD");
                    LocalDate date = LocalDate.parse(scanner.next());
                    System.out.println("Enter time in the format HH:MM");
                    LocalTime time = LocalTime.parse(scanner.next());
                    SystemManagement.scheduleAppointment(patientID, doctorID, date, time);

                    break;
                case 2:
                    // rescheduleAppointment
                    System.out.println();
                    System.out.println("-----  Modify the appointment -----");
                    System.out.println();
                    System.out.println("Enter appointment ID:");
                    String appointmentID = scanner.next();
                    System.out.println("Enter new date in the format YYYY-MM-DD");
                    LocalDate appointmentNewDate = LocalDate.parse(scanner.next());
                    System.out.println("Enter new time in the format HH:MM");
                    LocalTime appointmentNewTime = LocalTime.parse(scanner.next());
                    SystemManagement.rescheduleAppointment(appointmentID, appointmentNewDate, appointmentNewTime);
                    break;
                case 3:
                    // cancelAppointment
                    System.out.println();
                    System.out.println("-----  Cancel the appointment -----");
                    System.out.println();
                    System.out.println("Enter appointment ID:");
                    String cancelAppointmentID = scanner.next();
                    SystemManagement.cancelAppointment(cancelAppointmentID);
                    break;
                case 4:
                    System.out.println();
                    System.out.println("Enter a date of the appointment(s):");
                    LocalDate dateForAppointment = LocalDate.parse(scanner.next());
                    SystemManagement.viewAppointmentsForDate(dateForAppointment);
                    break;
                case 5:
                    System.out.println();
                    System.out.println("Enter patient ID:");
                    String patientIDForAppointment = scanner.next();
                    SystemManagement.viewAppointmentsForPatient(patientIDForAppointment);
                    break;
                case 6:
                    System.out.println();
                    System.out.println("Enter doctor ID:");
                    String doctorIDForAppointment = scanner.next();
                    SystemManagement.viewAppointmentsForDoctor(doctorIDForAppointment);
                    break;
                case 7:
                    // registerPatient
                    System.out.println();
                    System.out.println("-----  Register a new patient -----");
                    System.out.println();
                    System.out.println("Enter patient's first name:");
                    String firstNamePatient = scanner.next();
                    System.out.println("Enter patient's last name:");
                    String lastNamePatient = scanner.next();
                    System.out.println("Enter patient's date of birth (YYYY-MM-DD):");
                    LocalDate dateOfBirthPatient = LocalDate.parse(scanner.next());
                    System.out.println("Enter patient's gender (MALE/FEMALE/OTHER):");
                    Person.Gender genderPatient = Person.Gender.valueOf(scanner.next().toUpperCase());
                    System.out.println("Enter patient's email:");
                    String emailPatient = scanner.next();
                    System.out.println("Enter patient's insurance company:");
                    String insuranceCompany = scanner.next();
                    SystemManagement.registerPatient(firstNamePatient, lastNamePatient, dateOfBirthPatient.getYear(),
                            dateOfBirthPatient.getMonthValue(), dateOfBirthPatient.getDayOfMonth(), genderPatient,
                            emailPatient, insuranceCompany);
                    break;
                case 8:
                    // modifyPatientInfo
                    // Modify patient's info, it can later submenu, you choose ID and then choose
                    // what exactly to modify, not everything
                    System.out.println();
                    System.out.println("----- Modify Patient's Info -----");
                    System.out.println();
                    System.out.println("Enter patient ID:");
                    String modifyPatientId = scanner.next();
                    System.out.println("Enter new first name:");
                    String newFirstName = scanner.next();
                    System.out.println("Enter new last name:");
                    String newLastName = scanner.next();
                    System.out.println("Enter patient's new date of birth (YYYY-MM-DD):");
                    LocalDate newdateOfBirthPatient = LocalDate.parse(scanner.next());
                    System.out.println("Enter new gender (MALE/FEMALE/OTHER):");
                    Person.Gender newGender = Person.Gender.valueOf(scanner.next().toUpperCase());
                    System.out.println("Enter new email:");
                    String newEmail = scanner.next();
                    System.out.println("Enter new insurance company:");
                    String newInsuranceCompany = scanner.next();
                    SystemManagement.modifyPatientInfo(modifyPatientId, newFirstName, newLastName,
                            newdateOfBirthPatient.getYear(), newdateOfBirthPatient.getMonthValue(),
                            newdateOfBirthPatient.getDayOfMonth(), newGender,
                            newEmail, newInsuranceCompany);
                    break;

                case 9:
                    // addDoctor
                    System.out.println();
                    System.out.println("----- Add a new doctor -----");
                    System.out.println();
                    System.out.println("Enter doctor's first name:");
                    String firstNameDoctor = scanner.next();
                    System.out.println("Enter doctor's last name:");
                    String lastNameDoctor = scanner.next();
                    System.out.println("Enter doctor's date of birth (YYYY-MM-DD):");
                    LocalDate dateOfBirthDoctor = LocalDate.parse(scanner.next());
                    System.out.println("Enter doctor's gender (MALE/FEMALE/OTHER):");
                    Person.Gender genderDoctor = Person.Gender.valueOf(scanner.next().toUpperCase());
                    System.out.println("Enter doctor's email:");
                    String emailDoctor = scanner.next();
                    System.out.println("Enter doctor's date employed (YYYY-MM-DD):");
                    LocalDate dateEmployed = LocalDate.parse(scanner.next());
                    System.out.println("Enter doctor's specialty:");
                    String specialty = scanner.next();
                    // Call the addDoctor method with the provided parameters
                    SystemManagement.addDoctor(firstNameDoctor, lastNameDoctor, dateOfBirthDoctor.getYear(),
                            dateOfBirthDoctor.getMonthValue(), dateOfBirthDoctor.getDayOfMonth(), genderDoctor,
                            emailDoctor,
                            dateEmployed, specialty);
                    break;
                case 10:
                    // modifyDoctorInfo
                    System.out.println();
                    System.out.println("----- Modify a doctor's info -----");
                    System.out.println();
                    System.out.println("Enter doctor's ID:");
                    String doctorIDToChange = scanner.next();
                    System.out.println("Enter doctor's first name:");
                    String newFirstNameDoctor = scanner.next();
                    System.out.println("Enter doctor's last name:");
                    String newLastNameDoctor = scanner.next();
                    System.out.println("Enter doctor's date of birth (YYYY-MM-DD):");
                    LocalDate newDateOfBirthDoctor = LocalDate.parse(scanner.next());
                    System.out.println("Enter doctor's gender (MALE/FEMALE/OTHER):");
                    Person.Gender newGenderDoctor = Person.Gender.valueOf(scanner.next().toUpperCase());
                    System.out.println("Enter doctor's email:");
                    String newEmailDoctor = scanner.next();
                    System.out.println("Enter doctor's date employed (YYYY-MM-DD):");
                    LocalDate newDateEmployed = LocalDate.parse(scanner.next());
                    System.out.println("Enter doctor's specialty:");
                    String newSpecialty = scanner.next();
                    // Call the modifyDoctorInfo method with the provided parameters
                    SystemManagement.modifyDoctorInfo(doctorIDToChange, newFirstNameDoctor, newLastNameDoctor,
                            newDateOfBirthDoctor.getYear(), newDateOfBirthDoctor.getMonthValue(),
                            newDateOfBirthDoctor.getDayOfMonth(), newGenderDoctor,
                            newEmailDoctor, newDateEmployed, newSpecialty);
                    break;
                case 11:
                    // Return to main menu
                    System.out.println("Returning to the main menu...");
                    stayAtSameMenu = false;
                    return;
            }
        }

    }

    private static void doctorMenu(Scanner scanner) {

        boolean stayAtSameMenu = true;

        while (stayAtSameMenu) {
            System.out.println();
            System.out.println("-----  Doctor menu -----");
            System.out.println();
            System.out.println("Choose an option:");
            System.out.println("1. Record a new treatment");
            System.out.println("2. Modify the treatment");
            System.out.println("3. Return to main menu");
            System.out.println();
            int choice = 0;
            System.out.println();
            System.out.println("Enter your choice:");
            choice = scanner.nextInt();

            while (choice != 1 && choice != 2 && choice != 3) {
                System.out.println("Invalid choice, please try again.");
                choice = scanner.nextInt();
            }
            switch (choice) {
                case 1:
                    // recordTreatment
                    System.out.println();
                    System.out.println("----- Record a new treatment -----");
                    System.out.println();
                    System.out.println("Enter doctor ID:");
                    String doctorId = scanner.next();
                    System.out.println("Enter patient ID:");
                    String patientId = scanner.next();
                    System.out.println("Enter treatment date in the format YYYY-MM-DD:");
                    LocalDate treatmentDate = LocalDate.parse(scanner.next());
                    scanner.nextLine();
                    System.out.println("Enter treatment description:");
                    String description = scanner.nextLine();
                    SystemManagement.recordTreatment(doctorId, patientId, treatmentDate, description);
                    break;
                case 2:
                    // modifyTreatment
                    System.out.println();
                    System.out.println("----- Modify the treatment -----");
                    System.out.println();
                    System.out.println("Enter treatment ID:");
                    String treatmentID = scanner.next();
                    System.out.println("Enter new date in the format YYYY-MM-DD:");
                    LocalDate newTreatmentDate = LocalDate.parse(scanner.next());
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter new description:");
                    String newDescription = scanner.nextLine();
                    SystemManagement.modifyTreatment(treatmentID, newTreatmentDate, newDescription);
                    break;
                case 3:
                    // Return to main menu
                    System.out.println("Returning to the main menu...");
                    stayAtSameMenu = false;
                    return;
            }
        }
    }

    private static void patientMenu(Scanner scanner) {

        boolean stayAtSameMenu = true;

        while (stayAtSameMenu) {
            System.out.println();
            System.out.println("-----  Patient menu -----");
            System.out.println();
            System.out.println("Choose an option:");
            System.out.println("1. View my appointments");
            System.out.println("2. View my treatment history");
            System.out.println("3. Return to main menu");
            System.out.println();

            int choice = 0;
            System.out.println();
            System.out.println("Enter your choice:");
            choice = scanner.nextInt();
            while (choice != 1 && choice != 2 && choice != 3) {
                System.out.println("Invalid choice, please try again.");
                choice = scanner.nextInt();
            }
            switch (choice) {
                case 1:
                    // View my appointments
                    System.out.println();
                    System.out.println("----- View my appointments -----");
                    System.out.println();
                    System.out.println("Enter your patient ID:");
                    String patientIdForAppointments = scanner.next();
                    SystemManagement.viewPatientAppointments(patientIdForAppointments);
                    break;
                case 2:
                    // View my treatment history
                    System.out.println();
                    System.out.println("----- View my treatment history -----");
                    System.out.println();
                    System.out.println("Enter your patient ID:");
                    String patientIdTreatment = scanner.next();
                    SystemManagement.viewPatientTreatmentHistory(patientIdTreatment);
                    break;
                case 3:
                    // Return to main menu
                    System.out.println("Returning to the main menu...");
                    stayAtSameMenu = false;
                    return;

            }
        }
    }

} // end class class Main
