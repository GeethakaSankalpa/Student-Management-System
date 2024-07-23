/**
 * Represents the Student in the Student Management System, which contains Student name, Student ID, and module marks.
 */
public class Student {
    private String name;
    private String id;
    private Module module;

    /**
     * Constructs a new Student object with the given name,ID, and marks.
     * Creates a new module instance.
     *
     * @param name the student's name.
     * @param id the student's id.
     * @param mark1 marks of the first module
     * @param mark2 marks of the second module
     * @param mark3 marks of the third module
     */
    public Student(String name, String id, double mark1, double mark2, double mark3) {
        this.name = name;
        this.id = id;
        // creates a new module instance for the module variable
        this.module = new Module(mark1, mark2, mark3);
    }

    /**
     * Gets the student's name.
     *
     * @return the student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the student's id.
     *
     * @return the student's id.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the student's module marks.
     * Returning the instance of the module created for the student.
     *
     * @return the student's module marks.
     */
    public Module getModule(){
        return module;
    }
}
