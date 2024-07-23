import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Student Management System is a system for managing student activities including their results.
 * It provides features for checking available seats, registering students, deleting students, finding students, storing student details,
 * loading student details, and viewing the list of students.
 */
public class StudentManagementSystem {
    private static int seats = 100;
    private static int studentCount = 0;
    private static Student[] students = new Student[seats];
    private static boolean detailsLoaded = false;
    private static char[] specialChars = {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '{', '}', '+', '=', '-', '/', '.', ',', '?', '|', '\\', '"', '\'', '`', '~', '>', '<', ':', ';'};

    public static void main(String[] args) {
        mainMenu();
    }

    /**
     * Displays the main Menu of the Student Management System.
     * Prompts user to enter an option and navigates to relevant function based on the option.
     * Load student details first to enable other operations.
     */
    public static void mainMenu() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("------------Student Activity Management System----------- \n");
            System.out.println("""      
                    1. Check available seats
                    2. Register student (with ID)
                    3. Delete student
                    4. Find student (with student ID)
                    5. Store student details into a file
                    6. Load student details from the file to the system
                    7. View the list of students based on their names
                    8. Generate Report
                    0. Exit""");
            try {
                System.out.print("\nSelect your choice : ");
                String strChoice = scan.nextLine();
                if (strChoice.trim().isBlank()) {   // checks an empty input
                    System.err.print("\n" + "Input cannot be an empty value");
                    continue;
                }
                int choice = Integer.parseInt(strChoice);    // converts string input to integer input
                switch (choice) {
                    case 1:
                        checkAvailableSeats();
                        break;

                    case 6:
                        if (!detailsLoaded) {     // checks if details are loaded
                            loadDetails();
                            detailsLoaded = true;

                            break;
                        } else {     // executes when details are loaded
                            System.err.println("The Data already exists ");
                            System.out.println('\n');
                        }
                        break;

                    case 0:
                        System.out.println("\nExiting Program.....");
                        System.out.println("Good Bye !!!");
                        return;

                    default:    // handles other options
                        if (!detailsLoaded) {    // checks student details are loaded initially.
                            System.err.println("Load Details First To Perform Other Functions.");
                            System.out.println('\n');
                        } else {
                            switch (choice) {

                                case 2:
                                    registerStudents();
                                    break;

                                case 3:
                                    deleteStudent();
                                    break;

                                case 4:
                                    findStudent();
                                    break;

                                case 5:
                                    storeDetails();
                                    break;

                                case 7:
                                    viewStudentsByName(students);
                                    break;
                                case 8:
                                    generateReport();
                                    break;

                                default:
                                    System.err.println("Input Out of range");
                                    System.out.println();
                            }
                        }
                }
            } catch (Exception e) {
                System.err.println("\n" + "Please provide a valid Input.");
            }
        }
    }

    /**
     * Checks the availability of seats in the system
     * Number of available seats are initially declared to 100.
     */
    public static void checkAvailableSeats() {
        System.out.println("------Check Available Seats--------");
        System.out.println("-----------------------------------");
        if (seats <= studentCount) {
            System.out.println("All seats are Full");
        } else {
            System.out.println("Number of available seats : " + (seats - studentCount) + "\n");
        }
    }

    /**
     * Validates a String input to ensure it does not contain special characters.
     *
     * @param getInput     the input string to validate.
     * @param specialChars the array which contains special characters to check against the input.
     * @return true if the input contains special characters, false otherwise.
     */
    public static boolean validateSpecial(String getInput, char[] specialChars) {
        for (char special : specialChars) {
            if (getInput.indexOf(special) != -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prompts the user to input a student name and validates the input to ensure it does not contain special characters or numbers.
     *
     * @param specialChars the array which contains special characters to check against the input.
     * @return the validated student name.
     */
    public static String inputName(char[] specialChars) {

        Scanner scan = new Scanner(System.in);
        String name;
        while (true) {
            System.out.println("Enter Student Name: ");
            name = scan.nextLine().strip();
            if (name.isBlank()) {      // checks for empty inputs
                System.err.println("\nInput cannot be empty.");
            } else if (validateSpecial(name, specialChars)) {   // checks for special characters in name
                System.err.println("Input Contains Special Characters. \n");
            } else if (name.matches(".*\\d.*")) { // checks for numbers in name
                System.err.println("Input Contains Numbers. ");
            } else {
                break;
            }
        }
        return name;
    }

    /**
     * Prompts the user to input the student ID and validates the input.
     * Student ID should be 8-characters long.
     * Student ID should start with 'w' followed by 7 numbers.
     *
     * @param specialChars the array which contains special characters to check against the input.
     * @return the validated Student ID.
     */
    public static String inputId(char[] specialChars) {

        Scanner scan = new Scanner(System.in);
        String id;

        while (true) {
            System.out.println("Enter Student ID : ");
            id = scan.nextLine().strip();

            if (id.isBlank()) {
                System.err.println("Input cannot be empty.\n");
            } else if (id.length() != 8) {
                System.err.println("Student ID must be 8-character long. \n");
            } else if (validateSpecial(id, specialChars)) {
                System.err.println("Input Contains Special Characters. \n");
            } else if (id.charAt(0) != 'w') {     // checks for first character of ID
                System.err.println("Student ID must start with 'w'. \n");
            } else {
                boolean numberExist = false;
                for (int i = 1; i < id.length(); i++) {
                    if (Character.isDigit(id.charAt(i))) {    // checks if the remaining characters contains numbers.
                        numberExist = true;
                        break;
                    }
                }
                if (!numberExist) {
                    System.err.println("Student ID must contains atleast one number(besides the first letter)\n");
                } else {
                    break;
                }
            }
        }
        return id;
    }

    /**
     * Validates the user provided mark to ensure that it is within the range of 0 to 100.
     *
     * @param mark the user provided mark to validate.
     * @return true if the mark is within the range of 0 to 100, false otherwise.
     */
    public static boolean validateMark(double mark) {
        if (mark >= 0 && mark <= 100) {
            return true;
        }
        return false;
    }

    /**
     * Prompts user to enter student marks for three modules and validates the input.
     *
     * @return an array of validated marks.
     */
    public static double[] getMarks() {
        Scanner scan = new Scanner(System.in);
        double[] marks = new double[3];
        for (int i = 0; i < marks.length; i++) {
            double temp;
            while (true) {
                System.out.print("Enter Mark " + (i + 1) + " :");
                System.out.println();
                try {
                    temp = scan.nextInt();
                    if (validateMark(temp)) {   // checks whether user entered marks within the range
                        marks[i] = temp;
                        break;
                    } else {
                        System.err.println("Mark " + (i + 1) + " is out of range. Please Enter a valid Mark between 0 and 100. ");
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Invalid Input.");
                    scan.next(); // consume invalid input
                }
            }
        }
        return marks;
    }

    /**
     * Takes Student Name with ID.
     * Takes the 3 marks for 3 modules.
     * Registers a new student in the system with Student Name, ID and Marks for 3 modules.
     */
    public static void registerStudents() {
        String name;
        String id;

        if (seats <= studentCount) {
            System.out.println("All Seats are Full. ");
        } else {
            name = inputName(specialChars);
            id = inputId(specialChars);

            double[] marks = getMarks();
            students[studentCount] = new Student(name, id, marks[0], marks[1], marks[2]);
            studentCount++;     // increment number of students
            System.out.println("\nStudent Registered Successfully !");
        }
    }

    /**
     * Deletes a student from the system based on the user entered ID.
     */
    public static void deleteStudent() {

        System.out.println("---------------Delete Student Here-----------\n");
        String deleteId = inputId(specialChars);    // gets user Id
        boolean idFound = false;
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getId().equals(deleteId)) {  // finds student in student array with the given ID.
                students[i] = null;
                idFound = true;
                break;
            }
        }
        System.out.println("\nStudent Deleted\n");
        if (!idFound) {
            System.err.println("Student with the ID : " + deleteId + " is not existing.\n");
        }
    }

    /**
     * Finds a student in the system based on their ID.
     * Displays Student Details correspond to the entered ID.
     */
    public static void findStudent() {

        System.out.println("-----------Find Student With Student ID------------\n");
        String findId = inputId(specialChars);  // gets student ID

        boolean found = false;
        System.out.println("Student Relevant to " + findId + " has been found ! \n");
        for (Student student : students) {
            if (student != null && student.getId().equals(findId)) { // finds student in student array with the given ID.
                System.out.println("----------------------------------------");
                System.out.println("Student Name            : " + student.getName());
                System.out.println("Student ID              : " + student.getId());
                System.out.println("Marks for First Module  : " + student.getModule().getMark1());
                System.out.println("Marks for Second Module : " + student.getModule().getMark2());
                System.out.println("Marks for Third Module  : " + student.getModule().getMark3());
                System.out.println("----------------------------------------");
                found = true;
                break;
            }
        }
        if (!found) {
            System.err.println("Student with the ID : " + findId + " does not exist.");
        }
    }

    /**
     * Stores the student details from the system to a text file.
     * Student details includes Student Name, ID and Marks of 3 modules.
     */
    public static void storeDetails() {
        try {
            File file = new File("Details.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists in the system.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter("Details.txt");
            for (Student student : students) {
                if (student != null) { // Check if the student object is not null
                    writer.write(student.getName() + ", " + student.getId() + ", " + student.getModule().getMark1() +
                            ", " + student.getModule().getMark2() + ", " + student.getModule().getMark3() + "\n");
                }
            }
            writer.close();
            System.out.println("\nStudent Details have been successfully added to the file.\n");
        } catch (IOException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Loads stored student details from the text file into the system.
     * Five student details expected in a specific format : "name, id, mark1, mark2, mark3"
     */
    public static void loadDetails() {
        int studentIndex = 0;
        try {
            File file = new File("Details.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String[] details = reader.nextLine().split(", ");

                if (details.length < 5) {   // skip lines with invalid format
                    System.out.println("Invalid line format: " + Arrays.toString(details));
                    continue;
                }
                String loadName = details[0];
                String loadId = details[1];
                double loadMark1 = Double.parseDouble(details[2]);
                double loadMark2 = Double.parseDouble(details[3]);
                double loadMark3 = Double.parseDouble(details[4]);

                Student loadStudent = new Student(loadName, loadId, loadMark1, loadMark2, loadMark3);

                if (studentIndex < seats - 1) {   // since studentCount starts from zero, seats-1 to get a range from 0-99
                    students[studentIndex] = loadStudent;
                    studentIndex++;
                } else {
                    System.out.println("No more seats available.");
                    break;
                }
            }
            System.out.println("\nStudent Details Loaded to the System Successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading details from file.");
            e.printStackTrace();
        }
        studentCount = studentIndex;
    }

    /**
     * Views the students list in alphabetical order by name.
     *
     * @param students the array of students to view.
     */
    public static void viewStudentsByName(Student[] students) {
        System.out.println("------------------View Students By Name---------------\n");

        for (int i = 0; i < studentCount - 1; i++) { // bubble sort by name
            for (int j = 0; j < studentCount - i - 1; j++) {
                if (students[j].getName().charAt(0) > students[j + 1].getName().charAt(0)) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }

            }
        }
        for (int count = 0; count < students.length; count++) {    // displays sorted array.
            Student student = students[count];
            if (student != null) {
                System.out.println((count + 1) + ". " + student.getName());
            }
        }
    }

    /**
     * Sorts the array of students highest to lowest based on their average marks.
     *
     * @param students the array of students to be sorted.
     */
    public static void sortStudentsByAverage(Student[] students) {
        Student temp;   // holds student during swapping

        for (int a = 0; a < studentCount - 1; a++) { // access every object
            for (int b = 0; b < studentCount - a - 1; b++) {    // compare average

                // calculating total and average for comparison
                double total1 = students[b].getModule().getMark1() +
                        students[b].getModule().getMark2() +
                        students[b].getModule().getMark3();
                double average1 = total1 / 3;

                double total2 = students[b + 1].getModule().getMark1() +
                        students[b + 1].getModule().getMark2() +
                        students[b + 1].getModule().getMark3();
                double average2 = total2 / 3;

                if (average1 < average2) {  // swaps students if the average of next student is higher
                    temp = students[b];
                    students[b] = students[b + 1];
                    students[b + 1] = temp;
                }
            }
        }
    }

    /**
     * Generates a report.
     * option 1 - summary report of student registrations and total number of students who scored more than 40 for all modules
     * option 2 - detailed report including student details, module marks, total marks, average and grade based on average.
     * option 0 - back to main menu
     */
    public static void generateReport() {
        int option;
        Scanner scan = new Scanner(System.in);
        if (seats <= studentCount) {
            System.out.println("All Seats are Full. ");
        } else {
            while (true) {
                System.out.println("\n---------Generate Report---------");
                System.out.println("""
                        1. GENERATE SUMMARY.
                        2. GENERATE COMPLETE REPORT.
                        0. RETURN TO MAIN MENU.""");

                while (true) {
                    System.out.println("\n Choose your option : ");
                    try {
                        option = scan.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.err.println("Invalid Option");
                        scan.next();
                    }
                }
                switch (option) {
                    case 1:     // summary report
                        int gradeCount1 = 0;
                        int gradeCount2 = 0;
                        int gradeCount3 = 0;
                        System.out.println("--------------------------------------Summary-------------------------------------\n");
                        System.out.println("Total Student Registrations : " + studentCount + " registrations.");
                        for (Student student : students) {
                            if (student != null) {
                                if (student.getModule().getMark1() >= 40) {
                                    gradeCount1++;
                                }
                                if (student.getModule().getMark2() >= 40) {
                                    gradeCount2++;
                                }
                                if (student.getModule().getMark3() >= 40) {
                                    gradeCount3++;
                                }

                            }
                        }
                        System.out.println("No of students who scored more than 40 marks in Module 1        : " + gradeCount1 + " students.");
                        System.out.println("No of students who scored more than 40 marks in Module 2        : " + gradeCount2 + " students.");
                        System.out.println("No of students who scored more than 40 marks in Module 3        : " + gradeCount3 + " students.");
                        System.out.println("----------------------------------------------------------------------------------------------");
                        System.out.println("Total No of students who scored more than 40 marks in 3 Modules : " + (gradeCount1 + gradeCount2 + gradeCount3) + " students.");
                        break;

                    case 2:     // detailed report
                        double total;
                        double average;
                        sortStudentsByAverage(students);
                        System.out.println("---------------Complete Student Report---------------\n");
                        for (Student student : students) {
                            if (student != null) {
                                System.out.println("Student ID     : " + student.getId());
                                System.out.println("Student Name   : " + student.getName());
                                System.out.println("Module 1 marks : " + student.getModule().getMark1());
                                System.out.println("Module 2 marks : " + student.getModule().getMark2());
                                System.out.println("Module 3 marks : " + student.getModule().getMark3());
                                total = student.getModule().getMark1() + student.getModule().getMark2() + student.getModule().getMark3();
                                System.out.println("Total          : " + total);
                                average = total / 3;
                                System.out.println("Average        : " + average);
                                System.out.println("Grade          : " + student.getModule().Grade(average));
                                System.out.println("______________________________________________");
                            }
                        }
                        break;
                    case 0:
                        return;
                    default:
                        System.err.println("Input out of range.\n");
                }

            }
        }
    }
}