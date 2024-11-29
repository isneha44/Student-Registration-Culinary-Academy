package com.culinarry.registration.service;

import com.culinarry.registration.dao.UserDAO;
import com.culinarry.registration.entity.User;
import com.culinarry.registration.util.PasswordEncryption;
import com.culinarry.registration.util.ValidationUtil;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public boolean registerUser(User user) {
        // Validate user details
        if (!ValidationUtil.validateEmail(user.getEmail()) ||
                !ValidationUtil.validatePassword(user.getPassword())) {
            return false;
        }

        // Hash password
        user.setPassword(PasswordEncryption.hashPassword(user.getPassword()));

        // Save user
        userDAO.saveUser(user);
        return true;
    }


    public User authenticateUser(String username, String password) {
        return userDAO.authenticateUser(username, password);
    }

    public boolean changePassword(Long userId, String newPassword) {
        if (!ValidationUtil.validatePassword(newPassword)) {
            return false;
        }
        userDAO.updateUserPassword(userId, newPassword);
        return true;
    }
}
