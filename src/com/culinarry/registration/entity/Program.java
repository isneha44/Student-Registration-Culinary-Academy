package com.culinarry.registration.entity;

import com.culinarry.registration.entity.Student;

import java.util.Set;

public class Program {

    private String programId;
    private String programName;
    private String duration;
    private double fee;
//    @ManyToMany(mappedBy = "programs")
    private Set<Student> students;
}
