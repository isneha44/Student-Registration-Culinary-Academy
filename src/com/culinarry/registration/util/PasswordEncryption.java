package com.culinarry.registration.util;

public class PasswordEncryption {
    public static boolean checkPassword(String password, String password1) {
        return true;
    }

    public static String hashPassword(String newPassword) {
        return null;
    }

//    public static String hashPassword(String plainTextPassword) {
//        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(12));
//    }
//
//    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
//        return BCrypt.checkpw(plainTextPassword, hashedPassword);
//    }
}
