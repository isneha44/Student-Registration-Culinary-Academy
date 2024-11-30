package com.culinarry.registration.controller;

import com.culinarry.registration.dao.UserDAO;
import com.culinarry.registration.entity.User;
import com.culinarry.registration.util.PasswordEncryption;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class RegistrationController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private ComboBox<String> roleComboBox;

    private UserDAO userDao;

    @FXML
    public void initialize() {
        // Populate role combo box
        roleComboBox.getItems().addAll("Student", "Coordinator", "Admin");
    }

    @FXML
    private void handleRegister() {
        // Validate input fields
        if (!validateRegistrationInput()) {
            return;
        }

        // Create new user
        User newUser = new User();
        newUser.setUsername(usernameField.getText().trim());
        newUser.setEmail(emailField.getText().trim());

        // Hash and set password
        String hashedPassword = PasswordEncryption.hashPassword(passwordField.getText());
        newUser.setPassword(hashedPassword);

        // Set user role
        newUser.setRole(roleComboBox.getValue());

        // Attempt to create user
        UserDAO userDao = new UserDAO();
        if (userDao.createUser(newUser)) {
            showAlert(Alert.AlertType.INFORMATION, "Registration Successful",
                    "User account created successfully!");
            // Optionally: clear fields or navigate to login
        } else {
            showAlert(Alert.AlertType.ERROR, "Registration Failed",
                    "Unable to create user. Username or email might already exist.");
        }
    }

    private boolean validateRegistrationInput() {
        // Username validation
        String username = usernameField.getText().trim();
        if (username.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Username cannot be empty.");
            return false;
        }
        if (!Pattern.matches("^[a-zA-Z0-9_]{3,20}$", username)) {
            showAlert(Alert.AlertType.WARNING, "Validation Error",
                    "Username must be 3-20 characters long and contain only letters, numbers, and underscores.");
            return false;
        }

        // Email validation
        String email = emailField.getText().trim();
        if (email.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Email cannot be empty.");
            return false;
        }
        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email)) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Invalid email format.");
            return false;
        }

        // Password validation
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        if (password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Password cannot be empty.");
            return false;
        }
        if (password.length() < 8) {
            showAlert(Alert.AlertType.WARNING, "Validation Error",
                    "Password must be at least 8 characters long.");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Passwords do not match.");
            return false;
        }

        // Role validation
        if (roleComboBox.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Please select a user role.");
            return false;
        }

        return true;
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void handleBackToLogin() {
        // Navigate back to login screen
        try {
            // Load login screen
            Parent loginRoot = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(loginRoot));
            stage.setTitle("Culinary Academy - Login");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Could not load login screen.");
        }
    }

    public void handleRegister(ActionEvent actionEvent) {
        // Validate input fields
        if (!validateRegistrationInput()) {
            return;

        }

//        public void handleBackToLogin (ActionEvent actionEvent){
//            return
//        }
    }
}
