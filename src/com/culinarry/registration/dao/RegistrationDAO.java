package com.culinarry.registration.dao;

import com.culinarry.registration.config.HibernateConfig;
import com.culinarry.registration.entity.Registration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RegistrationDAO {
    public void registerStudentForProgram(Registration registration) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(registration);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Registration> getRegistrationsForStudent(Long studentId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Registration> query = session.createQuery(
                    "FROM Registration WHERE student.id = :studentId", Registration.class);
            query.setParameter("studentId", studentId);
            return query.getResultList();
        }
    }

}
