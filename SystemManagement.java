
/**
 * Class SystemManagement (Main execution program) 
 * @version 1.0
 * @since 2024-04-11
 * @author Iana Setrakova, Mohammad Javad Safdari, Mohamed Amine Mankai, Sesen Msgna Tesfay
 * @return
 */

// import packages
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class SystemManagement {
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();
    private static ArrayList<Treatment> treatments = new ArrayList<>();

    private static final String doctorsFileName = "doctors.txt";
    private static final String patientsFileName = "patients.txt";
    private static final String appointmentsFileName = "appointments.txt";
    private static final String treatmentsFileName = "treatments.txt";

    public static void loadData() {
        loadDoctors(doctorsFileName);
        loadPatients(patientsFileName);
        loadAppointments(appointmentsFileName);
        loadTreatments(treatmentsFileName);
    }

    // getting maximum ID from file, so we can increment it
    public static int getMaxId(String fileName) {
        int maxId = 0;
        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    int id = Integer.parseInt(parts[0]);
                    maxId = Math.max(maxId, id);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(fileName + "file not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing data: " + e.getMessage());
        }
        return maxId;
    }

    private static void loadDoctors(String fileName) {
        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim(); // Trim to remove leading and trailing whitespaces
                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");

                // txt file
                // data:"1,John,Doe,1980,1,15,MALE,johndoe@example.com,2022-01-01,Cardiology"
                // constructor public Doctor(String firstName, String lastName, int yearOfBirth,
                // int monthOfBirth, int dayOfBirth, Gender gender,
                // String email, String doctorID, LocalDate dateEmployed, String specialty)

                if (parts.length == 10) {
                    // Extract data from the parts array
                    String doctorID = parts[0];
                    String firstName = parts[1];
                    String lastName = parts[2];
                    int yearOfBirth = Integer.parseInt(parts[3]);
                    int monthOfBirth = Integer.parseInt(parts[4]);
                    int dayOfBirth = Integer.parseInt(parts[5]);
                    Person.Gender gender = Person.Gender.valueOf(parts[6]);
                    String email = parts[7];
                    LocalDate dateEmployed = LocalDate.parse(parts[8]);
                    String specialty = parts[9];

                    // Create a new Doctor object
                    Doctor doctor = new Doctor(firstName, lastName, yearOfBirth, monthOfBirth, dayOfBirth, gender,
                            email,
                            dateEmployed, specialty);
                    doctor.setDoctorID(doctorID); // in a constructor the new ID is generated and we need to replace it
                                                  // with actual one
                    doctors.add(doctor);
                } else {
                    System.out.println("Invalid data format in line: " + line);
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println(fileName + "file not found: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date/time: " + e.getMessage());
        }
    }

    private static void loadPatients(String fileName) {
        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim(); // Trim to remove leading and trailing whitespaces
                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");

                // txt file
                // data:"1,John,Doe,1980,1,15,MALE,johndoe@example.com,2022-01-01,Cardiology"
                // constructor public Doctor(String firstName, String lastName, int yearOfBirth,
                // int monthOfBirth, int dayOfBirth, Gender gender,
                // String email, String doctorID, LocalDate dateEmployed, String specialty)

                if (parts.length == 9) {
                    // Extract data from the parts array
                    String patientID = parts[0];
                    String firstName = parts[1];
                    String lastName = parts[2];
                    int yearOfBirth = Integer.parseInt(parts[3]);
                    int monthOfBirth = Integer.parseInt(parts[4]);
                    int dayOfBirth = Integer.parseInt(parts[5]);
                    Person.Gender gender = Person.Gender.valueOf(parts[6]);
                    String email = parts[7];
                    String insuranceCompany = parts[8];
                    // constructor public Patient(String firstName, String lastName, int
                    // yearOfBirth, int monthOfBirth, int dayOfBirth, Gender gender,
                    // String email, String insuranceCompany)
                    Patient patient = new Patient(firstName, lastName, yearOfBirth, monthOfBirth, dayOfBirth, gender,
                            email,
                            insuranceCompany);
                    patient.setPatientID(patientID); // in a constructor the new ID is generated and we need to replace
                                                     // it
                                                     // with actual one
                    patients.add(patient);
                } else {
                    System.out.println("Invalid data format in line: " + line);
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println(fileName + "file not found: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date/time: " + e.getMessage());
        }
    }

    private static void loadAppointments(String fileName) {
        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim(); // Trim to remove leading and trailing whitespaces
                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");

                if (parts.length == 5) {
                    String appointmentID = parts[0];
                    String doctorId = parts[1];
                    String patientId = parts[2];
                    LocalDate date = LocalDate.parse(parts[3]);
                    LocalTime time = LocalTime.parse(parts[4]);

                    Appointment appointment = new Appointment(doctorId, patientId, date, time);
                    appointment.setAppointmentID(appointmentID);
                    appointments.add(appointment);
                } else {
                    System.out.println("Invalid data format in line: " + line);
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println(fileName + "file not found: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date/time: " + e.getMessage());
        }
    }

    private static void loadTreatments(String fileName) {
        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim(); // Trim to remove leading and trailing whitespaces
                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");

                if (parts.length == 5) {
                    // Extract data from the parts array
                    String treatmentID = parts[0];
                    String doctorId = parts[1];
                    String patientId = parts[2];
                    LocalDate date = LocalDate.parse(parts[3]);
                    String description = parts[4];

                    // Create a new Appointment object
                    Treatment treatment = new Treatment(doctorId, patientId, date, description);
                    treatment.setTreatmentID(treatmentID); // in a constructor the new ID is generated and we need to
                                                           // replace it with actual one
                    treatments.add(treatment);
                } else {
                    System.out.println("Invalid data format in line: " + line);
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println(fileName + "file not found: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date/time: " + e.getMessage());
        }

    }

    public static void scheduleAppointment(String patientId, String doctorId, LocalDate date, LocalTime time) {
        // Create a new appointment object
        Appointment appointment = new Appointment(doctorId, patientId, date, time);

        // Add the appointment to the list of appointments
        appointments.add(appointment);

        // Write appointment details to the appointments.txt file
        try (PrintWriter writer = new PrintWriter(new FileWriter("appointments.txt", true))) {
            // Format the appointment details as a comma-separated string
            String appointmentDetails = String.format("%s,%s,%s,%s,%s%n",
                    appointment.getAppointmentID(),
                    appointment.getDoctorId(),
                    appointment.getPatientId(),
                    appointment.getDate(),
                    appointment.getTime());

            System.out.println(appointment);

            // Write the appointment details to the file
            writer.println(appointmentDetails);

            System.out.println("Appointment scheduled successfully!");
        } catch (IOException e) {
            System.err.println("Error scheduling appointment: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // modify appointment
    public static void rescheduleAppointment(String appointmentId, LocalDate newDate, LocalTime newTime) {
        // find the appointment by ID
        Appointment appointmentToReschedule = null;
        int index = -1; // Index of the appointment to reschedule
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentID().equals(appointmentId)) {
                appointmentToReschedule = appointments.get(i);
                index = i;
                break;
            }
        }
        if (appointmentToReschedule != null) {
            /*
             * TODO print the details of the appointment before modifying, before I enter
             * new modified date, just after I enter appointmentID, theresholud be separate method
             * System.out.println("Appointment details before modification:");
             * System.out.println(appointmentToReschedule);
             */

            // update the appointment's date and time
            appointmentToReschedule.setDate(newDate);
            appointmentToReschedule.setTime(newTime);

            // rewrite the appointments to the file
            try (PrintWriter writer = new PrintWriter(new FileWriter(appointmentsFileName))) {
                for (int i = 0; i < appointments.size(); i++) {
                    if (i == index) {
                        writer.println(String.format("%s,%s,%s,%s,%s",
                                appointmentToReschedule.getAppointmentID(),
                                appointmentToReschedule.getDoctorId(),
                                appointmentToReschedule.getPatientId(),
                                appointmentToReschedule.getDate(),
                                appointmentToReschedule.getTime()));
                    } else {
                        writer.println(String.format("%s,%s,%s,%s,%s",
                                appointments.get(i).getAppointmentID(),
                                appointments.get(i).getDoctorId(),
                                appointments.get(i).getPatientId(),
                                appointments.get(i).getDate(),
                                appointments.get(i).getTime()));
                    }
                }
                System.out.println();
                // Print the modified details of the appointment
                System.out.println("Modified appointment details:");
                System.out.println(appointmentToReschedule);
                System.out.println();

            } catch (IOException e) {
                System.err.println("Error rescheduling appointment: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Appointment not found!");
        }
    }

    public static void cancelAppointment(String appointmentId) {

        // Find and remove the appointment by ID
        Appointment appointmentToRemove = null;
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID().equals(appointmentId)) {
                appointmentToRemove = appointment;
                break;
            }
        }

        if (appointmentToRemove != null) {
            // Remove the appointment from the list
            appointments.remove(appointmentToRemove);

            // Rewrite the remaining appointments to the file
            try (PrintWriter writer = new PrintWriter(new FileWriter(appointmentsFileName))) {
                for (Appointment appointment : appointments) {
                    writer.println(String.format("%s,%s,%s,%s,%s",
                            appointment.getAppointmentID(),
                            appointment.getDoctorId(),
                            appointment.getPatientId(),
                            appointment.getDate(),
                            appointment.getTime()));
                }
                System.out.println("Appointment canceled successfully!");
            } catch (IOException e) {
                System.err.println("Error canceling appointment: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Appointment not found!");
        }
    }

    public static void viewAllAppointments() {
        System.out.println("--------------------------- Appointments list ----------------------------");
        System.out.println();
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
        System.out.println();
        System.out.println("--------------------------------------------------------------------------");
        System.out.println();
    }

    public static void viewAppointmentsForDate(LocalDate date) {
        System.out.println();
        System.out.println("---------------- Appointments for Date " + date + " -----------------");
        System.out.println();
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date)) {
                System.out.println(appointment);
            }
        }
        System.out.println();
        System.out.println("-------------------------------------------------------------------");
        System.out.println();
    }

    public static void viewAppointmentsForPatient(String patientId) {
        System.out.println("---------------- Appointments for Patient " + patientId + " -----------------");
        System.out.println();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId)) {
                System.out.println(appointment);
            }
        }
        System.out.println();
        System.out.println("--------------------------------------------------------------------------");
        System.out.println();
    }
    
    public static void viewAppointmentsForDoctor(String doctorId) {
        System.out.println("---------------- Appointments for Doctor " + doctorId + " -----------------");
        System.out.println();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId().equals(doctorId)) {
                System.out.println(appointment);
            }
        }
        System.out.println();
        System.out.println("--------------------------------------------------------------------------");
        System.out.println();
    }

    public static void registerPatient(String firstName, String lastName, int yearOfBirth, int monthOfBirth,
            int dayOfBirth, Person.Gender gender, String email, String insuranceCompany) {
        // Create a new patient object
        Patient patient = new Patient(firstName, lastName, yearOfBirth, monthOfBirth, dayOfBirth, gender, email,
                insuranceCompany);

        // Add the patient to the list of patients
        patients.add(patient);

        // Write patient details to the patients.txt file
        try (PrintWriter writer = new PrintWriter(new FileWriter("patients.txt", true))) {
            // Format the patient details as a comma-separated string
            String patientDetails = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s%n",
                    patient.getPatientID(),
                    firstName,
                    lastName,
                    yearOfBirth,
                    monthOfBirth,
                    dayOfBirth,
                    gender,
                    email,
                    insuranceCompany);

            // Write the patient details to the file
            writer.println(patientDetails);

            // print a message to confirm
            System.out.println("Patient registered successfully!");
            System.out.println();
            System.out.println("New patient's info:");
            System.out.println(patient);
            System.out.println();

        } catch (IOException e) {
            System.err.println("Error registering patient: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void modifyPatientInfo(String patientId, String newFirstName, String newLastName, int newYearOfBirth,
            int newMonthOfBirth, int newDayOfBirth, Person.Gender newGender, String newEmail,
            String newInsuranceCompany) {
        // Find the patient by ID
        Patient patientToModify = null;
        for (Patient patient : patients) {
            if (patient.getPatientID().equals(patientId)) {
                patientToModify = patient;
                break;
            }
        }

        if (patientToModify != null) {
            // Update the patient's information
            patientToModify.setFirstName(newFirstName);
            patientToModify.setLastName(newLastName);
            patientToModify.setBirthDate(newYearOfBirth, newMonthOfBirth, newDayOfBirth);
            patientToModify.setGender(newGender);
            patientToModify.setEmail(newEmail);
            patientToModify.setInsuranceCompany(newInsuranceCompany);

            // Rewrite the patient's information to the file
            try (PrintWriter writer = new PrintWriter(new FileWriter(patientsFileName))) {
                for (Patient patient : patients) {
                    writer.println(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",
                            patient.getPatientID(),
                            patient.getFirstName(),
                            patient.getLastName(),
                            patient.getYearOfBirth(),
                            patient.getMonthOfBirth(),
                            patient.getDayOfBirth(),
                            patient.getGender(),
                            patient.getEmail(),
                            patient.getInsuranceCompany())); // Include insurance company in the output
                }

                // print a message to confirm
                System.out.println("Patient information modified successfully!");
                System.out.println();
                System.out.println("Patient's info after modification:");
                System.out.println(patientToModify);
                System.out.println();
            } catch (IOException e) {
                System.err.println("Error modifying patient information: " + e.getMessage());
                e.printStackTrace();
            }

        } else {
            System.out.println("Patient not found!");
        }
    }

    

    public static void recordTreatment(String doctorId, String patientId, LocalDate date, String description) {
        // Create a new treatment object
        Treatment treatment = new Treatment(doctorId, patientId, date, description);

        // Add the treatment to the list of treatments
        treatments.add(treatment);

        // Write treatment details to the treatments.txt file
        try (PrintWriter writer = new PrintWriter(new FileWriter(treatmentsFileName, true))) {
            // Format the treatment details as a comma-separated string
            String treatmentDetails = String.format("%s,%s,%s,%s,%s%n",
                    treatment.getTreatmentId(),
                    treatment.getDoctorId(),
                    treatment.getPatientId(),
                    treatment.getDate(),
                    treatment.getDescription());

            // Write the treatment details to the file
            writer.println(treatmentDetails);

            System.out.println("Treatment recorded successfully!");
        } catch (IOException e) {
            System.err.println("Error recording treatment: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void modifyTreatment(String treatmentId, LocalDate newDate, String newDescription) {
        // Find the treatment by ID
        Treatment treatmentToModify = null;
        for (Treatment treatment : treatments) {
            if (treatment.getTreatmentId().equals(treatmentId)) {
                treatmentToModify = treatment;
                break;
            }
        }
        if (treatmentToModify != null) {
            // Update the treatment's date and description
            treatmentToModify.setDate(newDate);
            treatmentToModify.setDescription(newDescription);

            // Rewrite the specific treatment to the file and update treatments list in
            // memory
            try (PrintWriter writer = new PrintWriter(new FileWriter(treatmentsFileName))) {
                for (Treatment treatment : treatments) {
                    writer.println(String.format("%s,%s,%s,%s,%s",
                            treatment.getTreatmentId(),
                            treatment.getDoctorId(),
                            treatment.getPatientId(),
                            treatment.getDate(),
                            treatment.getDescription()));
                }
                System.out.println("Treatment modified successfully!");
            } catch (IOException e) {
                System.err.println("Error modifying treatment: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Treatment not found!");
        }
    }

    public static void addDoctor(String firstName, String lastName, int yearOfBirth, int monthOfBirth, int dayOfBirth,
            Person.Gender gender, String email, LocalDate dateEmployed, String specialty) {
        // Create a new doctor object
        Doctor doctor = new Doctor(firstName, lastName, yearOfBirth, monthOfBirth, dayOfBirth, gender, email,
                dateEmployed,
                specialty);

        // Add the doctor to the list of doctors
        doctors.add(doctor);

        // Write doctor details to the doctors.txt file
        try (PrintWriter writer = new PrintWriter(new FileWriter(doctorsFileName, true))) {
            // Format the doctor details as a comma-separated string
            String doctorDetails = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s%n", doctor.getDoctorID(), firstName,
                    lastName,
                    yearOfBirth, monthOfBirth, dayOfBirth, gender, email, dateEmployed, specialty);

            // Write the doctor details to the file
            writer.println(doctorDetails);

            System.out.println("Doctor added successfully!");
            
            // print a message to confirm registration
            System.out.println("Doctor added successfully!");
            System.out.println();
            System.out.println("Doctor's info:");
            System.out.println(doctor);
            System.out.println();
        } catch (IOException e) {
            System.err.println("Error adding doctor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void modifyDoctorInfo(String doctorID, String firstName, String lastName, int yearOfBirth,
            int monthOfBirth, int dayOfBirth, Person.Gender gender, String email, LocalDate dateEmployed,
            String specialty) {
        // Find the doctor by ID
        Doctor doctorToModify = null;
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorID().equals(doctorID)) {
                doctorToModify = doctor;
                break;
            }
        }

        if (doctorToModify != null) {
            // Update the doctor's information
            doctorToModify.setFirstName(firstName);
            doctorToModify.setLastName(lastName);
            doctorToModify.setBirthDate(yearOfBirth, monthOfBirth, dayOfBirth);
            doctorToModify.setGender(gender);
            doctorToModify.setEmail(email);
            doctorToModify.setDateEmployed(dateEmployed);
            doctorToModify.setSpecialty(specialty);

            // Rewrite the doctor's information to the file
            try (PrintWriter writer = new PrintWriter(new FileWriter(doctorsFileName))) {
                for (Doctor doctor : doctors) {
                    writer.println(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", doctor.getDoctorID(),
                            doctor.getFirstName(), doctor.getLastName(), doctor.getYearOfBirth(),
                            doctor.getMonthOfBirth(),
                            doctor.getDayOfBirth(), doctor.getGender(), doctor.getEmail(), doctor.getDateEmployed(),
                            doctor.getSpecialty()));
                }
                // print a message to confirm 
                System.out.println("Doctor information modified successfully!");
                System.out.println();
                System.out.println("Doctor's info after modification:");
                System.out.println(doctorToModify);
                System.out.println();
            } catch (IOException e) {
                System.err.println("Error modifying doctor information: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Doctor not found!");
        }
    }

    // View patient's appointments
    public static void viewPatientAppointments(String patientId) {
        ArrayList<Appointment> patientAppointments = getPatientAppointments(patientId);
        if (patientAppointments.isEmpty()) {
            System.out.println("No appointments found for the patient.");
        } else {
            System.out.println();
            System.out.println("---------------  Patient's Appointments:  ----------");
            System.out.println();
            for (Appointment appointment : patientAppointments) {
                System.out.println(appointment);
            }
            System.out.println();
            System.out.println("----------------------------------------------------");
            System.out.println();
        }
    }

    // helper method to get patient appointments
    private static ArrayList<Appointment> getPatientAppointments(String patientId) {
        ArrayList<Appointment> patientAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId)) {
                patientAppointments.add(appointment);
            }
        }
        return patientAppointments;
    }



    // view patient treatment history
    public static void viewPatientTreatmentHistory(String patientId) {
        // Retrieve treatment history for the specified patient ID
        ArrayList<Treatment> treatmentHistory = getTreatmentHistoryForPatient(patientId);

        // Display treatment history
        if (treatmentHistory.isEmpty()) {
            System.out.println("No treatment history found for this patient.");
        } else {
            System.out.println("Treatment history for patient " + patientId + ":");
            for (Treatment treatment : treatmentHistory) {
                System.out.println(treatment);
            }
        }
    }

    private static ArrayList<Treatment> getTreatmentHistoryForPatient(String patientId) {
        ArrayList<Treatment> patientTreatmentHistory = new ArrayList<>();
        for (Treatment treatment : treatments) {
            if (treatment.getPatientId().equals(patientId)) {
                patientTreatmentHistory.add(treatment);
            }
        }
        return patientTreatmentHistory;
    }




}
