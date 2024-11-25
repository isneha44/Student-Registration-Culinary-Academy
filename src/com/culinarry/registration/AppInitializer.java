package com.culinarry.registration;

import com.culinarry.registration.entity.Student;

import java.util.Date;

public class AppInitializer {
    public static void main(String[] args) {
     Student student = new Student();
     student.setFirstName("Shashika");
     student.setLastName("Madushan");
     student.setEmail("shashi@gmail.com");
     student.setPhoneNumber("+947134789456");


     session.save(student);

    }

}
