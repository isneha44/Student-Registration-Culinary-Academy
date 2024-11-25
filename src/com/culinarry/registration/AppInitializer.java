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
     student.setPhoneNumber("+94745617997");

     Session session = FactoryConfiguration.getInstance().getSession();

     Transaction transaction = session.beginTransaction();

     session.save(student);

     transaction.commit();
     session.close();

    }

}
