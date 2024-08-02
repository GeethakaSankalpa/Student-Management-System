<h1> Student Management System </h1>
<p> The Student Activity Management System is a robust and CLI application designed to efficiently handle student results for three modules in a Semester. 
This was developed as a university project, this Java console application enables university staff to manage students and their module results seamlessly. </p>

<h2>Features</h2>
    
<h3>Menu-Driven Interface</h3>
<p>The application provides an intuitive menu-driven interface for university staff to interact with, offering the following options:</p>
<ul>
    <li><strong>Check available seats:</strong> Displays the number of available slots for student registration.</li>
    <li><strong>Register student:</strong> Allows for the registration of a student with a unique ID. The ID should contain 8 characters, starting with ‘W’ followed by 7 numeric characters. </li>
    <li><strong>Delete student:</strong> Removes a student from the system.</li>
    <li><strong>Find student:</strong> Retrieves student details using their unique ID.</li>
    <li><strong>Store student details:</strong> Saves all student data to a file for persistence.</li>
    <li><strong>Load student details:</strong> Loads student data from a file back into the system.</li>
    <li><strong>View students list:</strong> Displays the list of students sorted by their names using a custom sorting algorithm.</li>
    <li><strong>Manage student results:</strong> Opens additional options to handle student results, including adding student names and module marks.</li>
</ul>

<h3>Student Management</h3>
<ul>
    <li><strong>Capacity:</strong> Manages up to 100 students per intake.</li>
    <li><strong>Persistence:</strong> Save and load student data to/from files to ensure data persistence between sessions. The text file format is as follows: Student name, ID, and three marks for the respective modules. </li>
    <img src="/images/Text File Format.png"> </img>

</ul>

<h3>Class-Based Design</h3>
<ul>
    <li><strong>Student and Module Classes:</strong> Uses classes to handle student results, including student ID, name, and module marks.</li>
    <li><strong>Grading System:</strong> Calculates grades based on average marks:
        <ul>
            <li><strong>Distinction:</strong> Average >= 80</li>
            <li><strong>Merit:</strong> Average >= 70</li>
            <li><strong>Pass:</strong> Average >= 40</li>
            <li><strong>Fail:</strong> Average < 40</li>
        </ul>
    </li>
</ul>


<h3>Reporting</h3>
<ul>
    <li><strong>Summary Report:</strong> Generates a summary of the system, including total student registrations and the number of students scoring above 40 marks in each module.</li>
    <li><strong>Detailed Report:</strong> Produces a complete report with detailed student information, sorted by average marks from highest to lowest using Bubble Sort.</li>
</ul>

<h3>Testing</h3>
<p>The PDF report Includes comprehensive test cases to ensure the reliability and performance of the application, covering various scenarios and use cases.</p>

## License

This project is licensed under the MIT License. See `LICENSE` for details.

<h3> Getting Started </h3>
<p>Feel free to explore, modify, and enhance the system. Contributions and feedback are welcome!</p>

<p> Thanks for diving into this project! 🌟 I hope you enjoy the experience as much as I enjoyed creating it! 😊 </p>
