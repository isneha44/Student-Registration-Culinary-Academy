package com.culinarry.registration.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "registrations")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;

    @Column(nullable = false)
    private LocalDate registrationDate;

    @Column(nullable = false)
    private Double paymentAmount;

    public Registration() {
    }

    public Registration(Long id, Student student, Program program, LocalDate registrationDate, Double paymentAmount) {
        this.id = id;
        this.student = student;
        this.program = program;
        this.registrationDate = registrationDate;
        this.paymentAmount = paymentAmount;
    }

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Program getProgram() {
        return program;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", student=" + student +
                ", program=" + program +
                ", registrationDate=" + registrationDate +
                ", paymentAmount=" + paymentAmount +
                '}';
    }
}
