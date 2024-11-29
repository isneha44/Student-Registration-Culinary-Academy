package com.culinarry.registration.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "programs")
public class Program {

    @Id
    private String programId;

    @Column(nullable = false)
    private String programName;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private Double fee;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Registration> registrations = new HashSet<>();

    public Program() {
    }

    public Program(String programId, String programName, String duration, Double fee, Set<Registration> registrations) {
        this.programId = programId;
        this.programName = programName;
        this.duration = duration;
        this.fee = fee;
        this.registrations = registrations;
    }

    public String getProgramId() {
        return programId;
    }

    public String getProgramName() {
        return programName;
    }

    public String getDuration() {
        return duration;
    }

    public Double getFee() {
        return fee;
    }

    public Set<Registration> getRegistrations() {
        return registrations;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public void setRegistrations(Set<Registration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "Program{" +
                "programId='" + programId + '\'' +
                ", programName='" + programName + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                ", registrations=" + registrations +
                '}';
    }
}
