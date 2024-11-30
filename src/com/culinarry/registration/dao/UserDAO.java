package com.culinarry.registration.dao;

import com.culinarry.registration.config.HibernateConfig;
import com.culinarry.registration.entity.User;
import com.culinarry.registration.util.PasswordEncryption;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.regex.Pattern;

public class UserDAO {
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public User authenticateUser(String username, String password) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery(
                    "FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);
            User user = query.uniqueResult();

            if (user != null && PasswordEncryption.checkPassword(password, user.getPassword())) {
                return user;
            }
            return null;
        }
    }

    public void updateUserPassword(Long userId, String newPassword) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, userId);
            if (user != null) {
                user.setPassword(PasswordEncryption.hashPassword(newPassword));
                session.update(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public boolean createUser(User newUser) {
        Transaction transaction = null;

        // Validate input before processing
        if (!validateUserInput(newUser)) {
            return false;
        }

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // Begin transaction
            transaction = session.beginTransaction();

            // Check if username or email already exists
            if (isUserExists(session, newUser.getUsername(), newUser.getEmail())) {
                return false;
            }

            // Encrypt password before saving
            newUser.setPassword(PasswordEncryption.hashPassword(newUser.getPassword()));

            // Set creation timestamp
            newUser.setCreatedAt(LocalDateTime.now());

            // Save the user
            session.save(newUser);

            // Commit transaction
            transaction.commit();

            return true;
        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) {
                transaction.rollback();
            }

            // Log the error (consider using a logging framework)
            System.err.println("Error creating user: " + e.getMessage());
            e.printStackTrace();

            return false;
        }
    }

    private boolean isUserExists(Session session, String username, String email) {
        // Check if username or email already exists in the database
        Query<Long> usernameQuery = session.createQuery(
                "SELECT COUNT(*) FROM User WHERE username = :username", Long.class);
        usernameQuery.setParameter("username", username);

        Query<Long> emailQuery = session.createQuery(
                "SELECT COUNT(*) FROM User WHERE email = :email", Long.class);
        emailQuery.setParameter("email", email);

        return usernameQuery.uniqueResult() > 0 || emailQuery.uniqueResult() > 0;
    }

    private boolean validateUserInput(User newUser) {
        // Username validation
        if (newUser.getUsername() == null ||
                !Pattern.matches("^[a-zA-Z0-9_]{3,20}$", newUser.getUsername())) {
            return false;
        }

        // Email validation
        if (newUser.getEmail() == null ||
                !Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", newUser.getEmail())) {
            return false;
        }

        // Password validation
        if (newUser.getPassword() == null || newUser.getPassword().length() < 8) {
            return false;
        }

        // Role validation
        if (newUser.getRole() == null ||
                !Arrays.asList("Student", "Coordinator", "Admin").contains(newUser.getRole())) {
            return false;
        }

        return true;
    }
}
