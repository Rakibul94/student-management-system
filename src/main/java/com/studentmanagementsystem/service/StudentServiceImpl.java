package com.studentmanagementsystem.service;

import com.studentmanagementsystem.exceptions.StudentNotFoundException;
import org.springframework.stereotype.Service;
import com.studentmanagementsystem.model.Student;
import com.studentmanagementsystem.repository.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {

        return studentRepository.findById(id).orElseThrow(() ->
                new StudentNotFoundException("Student not found"));
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

}
