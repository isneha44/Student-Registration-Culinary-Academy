package com.culinarry.registration.controller;

import com.culinarry.registration.entity.User;
import com.culinarry.registration.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.naming.AuthenticationException;

import static com.culinarry.registration.entity.UserRole.ADMIN;
import static com.culinarry.registration.entity.UserRole.COORDINATOR;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;

    private UserService userService;

    public LoginController() {
        userService = new UserService();
    }

    @FXML
    private void initialize() {
        loginButton.setOnAction(event -> handleLogin());
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User authenticatedUser = userService.authenticateUser(username, password);

        if (authenticatedUser != null) {
            // Redirect based on user role
            switch (authenticatedUser.getRole()) {
                case "ADMIN":
                    loadAdminDashboard();
                    break;
                case "COORDINATOR":
                    loadCoordinatorDashboard();
                    break;
                default:
                    showAlert("Invalid Role", "Unable to determine user role.");
            }
        } else {
            showAlert("Login Failed", "Invalid username or password.");
        }
    }

    private void loadAdminDashboard() {
        try {
            // Load admin dashboard
            Stage stage = (Stage) loginButton.getScene().getWindow();
            // Use FXMLLoader to load admin dashboard
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCoordinatorDashboard() {
        try {
            // Load coordinator dashboard
            Stage stage = (Stage) loginButton.getScene().getWindow();
            // Use FXMLLoader to load coordinator dashboard
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
