package com.culinarry.registration;

import com.culinarry.registration.entity.Student;
import com.culinarry.registration.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class AppInitializer {
    public static void main(String[] args) {
     Student student = new Student();
     student.setId(student.getId());
     student.setFirstName("Vihanga");
     student.setLastName("Samanala");
     student.setEmail("vihanga@gmail.com");
     student.setPhoneNumber("+9474");

     Session session = FactoryConfiguration.getInstance().getSession();

     Transaction transaction = session.beginTransaction();

     // every codes and implementation start from here //

//        To Save
//        session.save(student);

//        To Update
//        student.setId(Long.valueOf("12"));
//        session.update(student);

//        To Get One Customer Object
        Student student1 = session.get(Student.class, Long.valueOf(12));
        System.out.println(student1);

     transaction.commit();
     session.close();

    }

}
