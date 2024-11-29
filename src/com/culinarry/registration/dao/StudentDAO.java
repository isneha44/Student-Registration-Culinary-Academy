package com.culinarry.registration.dao;

import com.culinarry.registration.config.HibernateConfig;
import com.culinarry.registration.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDAO {

    public void saveStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Student getStudentById(Long studentId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(Student.class, studentId);
        }
    }

    public List<Student> getAllStudents() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("FROM Student", Student.class).getResultList();
        }
    }

    public void updateStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteStudent(Long studentId) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            if (student != null) {
                session.delete(student);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Advanced query to find students registered in all programs
    public List<Student> findStudentsRegisteredInAllPrograms() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            String hql = "SELECT s FROM Student s " +
                    "WHERE SIZE(s.registrations) = (SELECT COUNT(p) FROM Program p)";
            return session.createQuery(hql, Student.class).getResultList();
        }
    }
}
