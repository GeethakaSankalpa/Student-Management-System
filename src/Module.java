/**
 * Represents a module in the Student Management System which contains marks for three permanent modules.
 */
class Module {
    private double mark1;
    private double mark2;
    private double mark3;

    /**
     * Creates a parameterised constructor
     *
     * @param mark1 marks of the first module
     * @param mark2 marks of the second module
     * @param mark3 marks of the third module
     */
    public Module(double mark1, double mark2, double mark3) {
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
    }


    /**
     * Gets the mark for the first module.
     *
     * @return the mark of the first module.
     */
    public double getMark1() {
        return mark1;
    }

    /**
     * Gets the mark for the second module.
     *
     * @return the mark of the second module.
     */
    public double getMark2() {
        return mark2;
    }

    /**
     * Gets the mark for the third module.
     *
     * @return the mark of the third module.
     */
    public double getMark3() {
        return mark3;
    }

    /**
     * Calculate the Grade based on the average of each three marks for three modules.
     *
     * @param average the average mark calculated based on the user prompted marks.
     * @return the grade (Average >= 80 - Distinction, >=70 - Merit, >= 40 – Pass Else Fail)
     */
    public String Grade(double average){
        String grade;
        if(average >= 80 && average <= 100){
            grade = "Distinction";
        } else if (average >= 70 && average <= 80){
            grade = "Merit";
        } else if (average >= 40 && average <= 70){
            grade = "Pass";
        } else {
            grade = "Fail";
        } return grade;
    }
}