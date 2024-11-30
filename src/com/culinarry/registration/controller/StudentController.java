package com.culinarry.registration.controller;

import com.culinarry.registration.entity.Program;
import com.culinarry.registration.entity.Student;
import com.culinarry.registration.service.ProgramService;
import com.culinarry.registration.service.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentController {

    @FXML
    private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, Long> idColumn;
    @FXML private TableColumn<Student, String> firstNameColumn;
    @FXML private TableColumn<Student, String> lastNameColumn;
    @FXML private TableColumn<Student, String> emailColumn;

    private StudentService studentService;
    private ProgramService programService;

    @FXML
    private void initialize() {
        studentService = new StudentService();
        programService = new ProgramService();

        // Configure table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Load students
        loadStudents();
    }

    private void loadStudents() {
        ObservableList<Student> students = FXCollections.observableArrayList(
                studentService.getAllStudents()
        );
        studentTable.setItems(students);
    }

    @FXML
    private void handleAddStudent() {
        Student student = new Student();
        student.setFirstName(firstNameField.getText());
        student.setLastName(lastNameField.getText());
        student.setEmail(emailField.getText());
        student.setPhoneNumber(phoneField.getText());

        // Demonstrate registration for first available program
        Program firstProgram = programService.getAllPrograms().get(0);

        boolean registered = studentService.registerStudent(student, firstProgram);

        if (registered) {
            loadStudents();
            clearFields();
        } else {
            // Show error dialog
            showErrorDialog("Student Registration Failed",
                    "Please check your input details.");
        }
    }

    @FXML
    private void handleDeleteStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            // Implement delete logic
            // studentService.deleteStudent(selectedStudent.getStudentId());
            loadStudents();
        }
    }

    private void clearFields() {
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        phoneField.clear();
    }

    private void showErrorDialog(String title, String message) {
        // Implement error dialog
    }
}
