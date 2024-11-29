package com.culinarry.registration.service;

import com.culinarry.registration.dao.RegistrationDAO;
import com.culinarry.registration.dao.StudentDAO;
import com.culinarry.registration.entity.Program;
import com.culinarry.registration.entity.Registration;
import com.culinarry.registration.entity.Student;
import com.culinarry.registration.util.ValidationUtil;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class StudentService {

    private StudentDAO studentDAO;
    private RegistrationDAO registrationDAO;

    public StudentService() {
        this.studentDAO = new StudentDAO();
        this.registrationDAO = new RegistrationDAO();
    }

    public boolean registerStudent(Student student, Program program) {
        // Validate student details
        if (!ValidationUtil.validateEmail(student.getEmail()) ||
                !ValidationUtil.validatePhoneNumber(student.getPhoneNumber())) {
            return false;
        }

        // Set registration date
        student.setRegistrationDate(new Date());

        // Save student
        studentDAO.saveStudent(student);

        // Create registration
        Registration registration = new Registration();
        registration.setStudent(student);
        registration.setProgram(program);
        registration.setRegistrationDate(new Date());
        registration.setPaymentStatus("PENDING");

        registrationDAO.registerStudentForProgram(registration);

        return true;
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    public Student getStudentById(Long studentId) {
        return studentDAO.getStudentById(studentId);
    }
}
