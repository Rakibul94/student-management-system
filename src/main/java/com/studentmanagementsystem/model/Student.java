package com.studentmanagementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Required by JPA
@AllArgsConstructor // Generates all-args constructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double cgpa;
    private String email;
    private String program;

    @ManyToOne
    @JoinColumn(name = "department_id", foreignKey = @ForeignKey(name = "fk_student_department"))
    private Department department;
}