package com.studentmanagementsystem.service;

import java.util.List;

import com.studentmanagementsystem.model.Student;

public interface StudentService {
    Student saveStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    void deleteStudentById(Long id);
}
