package com.culinarry.registration.entity;

import java.util.Date;
import java.util.Set;

public class Student {
    private String id;
    private String name;
    private Date registrationDate;
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Program> programs;

    public Student() {
    }

    public Student(String id, String name, Date registrationDate, Set<Program> programs) {
        this.id = id;
        this.name = name;
        this.registrationDate = registrationDate;
        this.programs = programs;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public Set<Program> getPrograms() {
        return programs;
    }



    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setPrograms(Set<Program> programs) {
        this.programs = programs;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", registrationDate=" + registrationDate +
                ", programs=" + programs +
                '}';
    }
}
