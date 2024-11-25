package com.culinarry.registration;

import com.culinarry.registration.entity.Student;

import java.util.Date;

public class AppInitializer {
    public static void main(String[] args) {
     Student student = new Student();
     student.setId("CAP0001");
     student.setName("Shashika Madushan");
//     student.setPrograms(programs);
     student.setRegistrationDate(new Date());

     session.save(student);

    }

}
