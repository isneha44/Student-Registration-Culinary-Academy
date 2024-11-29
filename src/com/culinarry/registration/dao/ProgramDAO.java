package com.culinarry.registration.dao;

import com.culinarry.registration.config.HibernateConfig;
import com.culinarry.registration.entity.Program;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProgramDAO {

    public void saveProgram(Program program) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(program);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Program getProgramById(Long programId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(Program.class, programId);
        }
    }

    public List<Program> getAllPrograms() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("FROM Program", Program.class).getResultList();
        }
    }

    public void updateProgram(Program program) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(program);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteProgram(Long programId) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Program program = session.get(Program.class, programId);
            if (program != null) {
                session.delete(program);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
