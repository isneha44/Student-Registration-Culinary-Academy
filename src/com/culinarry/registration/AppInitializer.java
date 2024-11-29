package com.culinarry.registration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer  extends Application {
    public static void main(String[] args) {
        launch(args);
//     Student student = new Student();
//     student.setId(student.getId());
//     student.setFirstName("Vihanga");
//     student.setLastName("Samanala");
//     student.setEmail("vihanga@gmail.com");
//     student.setPhoneNumber("+9474");
//
//     Session session = FactoryConfiguration.getInstance().getSession();
//
//     Transaction transaction = session.beginTransaction();

     // every codes and implementation start from here //

//        To Save

//        session.save(student);

//        To Update

//        student.setId(Long.valueOf("12"));
//        session.update(student);

//        To Get One Customer Object

//        Student student1 = session.get(Student.class, Long.valueOf(12));
//        System.out.println(student1); // TO PRINT ONE OBJECT
//        System.out.println(student1.getEmail()); //TO PRINT ONE PROPERTY/ATTRIBUTE OR one thing FROM THE OBJECT
//        System.out.println(student1.getEmail() + " - " + student1.getFirstName()); //TO PRINT MULTIPLE THINGS FROM THE OBJECT whatever THE WAY I WANT

        // To Delete Customer

             //1st way(NOT WORKING FOR ME NOW)

//        Student student2 = new Student();
//        student2.setId(Long.valueOf(11));
//
//        session.delete(student2);


//            //2nd way(WORKING)
//
//        Student student3 = session.get(Student.class , Long.valueOf(12));
//        session.delete(student3);

//     transaction.commit();
//     session.close();

    }

 @Override
 public void start(Stage primaryStage) throws Exception {
     // Load login screen
     Parent root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
     primaryStage.setTitle("Culinary Academy Management System");
     primaryStage.setScene(new Scene(root, 800, 600));
     primaryStage.show();
 }

//    @Override
//    public void stop() {
//        // Shutdown Hibernate session factory when application closes
//        HibernateConfig.shutdown();
//    }

}
