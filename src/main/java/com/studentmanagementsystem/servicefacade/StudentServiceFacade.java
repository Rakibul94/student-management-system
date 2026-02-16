package com.studentmanagementsystem.servicefacade;

import com.studentmanagementsystem.data.StudentData;

import java.util.List;

public interface StudentServiceFacade {
    List<StudentData> getAllStudents();
    StudentData getStudentById(Long id);
    void createStudent(StudentData studentData);
    void updateStudent(StudentData studentData);
    void deleteStudentById(Long id);
}