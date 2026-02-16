package com.studentmanagementsystem.mapper;

import com.studentmanagementsystem.data.DepartmentData;
import com.studentmanagementsystem.data.StudentData;
import com.studentmanagementsystem.model.Department;
import com.studentmanagementsystem.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    /* ---------- Entity → DTO ---------- */

    public StudentData toData(Student student) {
        if (student == null) return null;

        StudentData studentData = new StudentData();
        studentData.setId(student.getId());
        studentData.setName(student.getName());
        studentData.setEmail(student.getEmail());
        studentData.setCgpa(student.getCgpa());
        studentData.setProgram(student.getProgram());

        if (student.getDepartment() != null) {
            DepartmentData departmentData = new DepartmentData();
            departmentData.setId(student.getDepartment().getId());
            departmentData.setName(student.getDepartment().getName());
            studentData.setDepartmentData(departmentData);
        }
        return studentData;
    }

    /* ---------- DTO → Entity ---------- */

    public Student toEntity(StudentData studentData) {
        if (studentData == null) return null;
        //No id is set as JPA generates id

        Student student = new Student();
        if (studentData.getId() != null ) {
            student.setId(studentData.getId());
        }
        student.setName(studentData.getName());
        student.setEmail(studentData.getEmail());
        student.setCgpa(studentData.getCgpa());
        student.setProgram(studentData.getProgram());

        if (studentData.getDepartmentData() != null) {
            Department department = new Department();
            department.setId(studentData.getDepartmentData().getId());
            department.setName(studentData.getDepartmentData().getName());
            student.setDepartment(department);
        }
        return student;
    }

    /* ---------- Update existing entity ---------- */

    public void updateEntity(Student student,
                             StudentData studentData) {

        student.setName(studentData.getName());
        student.setEmail(studentData.getEmail());
        student.setCgpa(studentData.getCgpa());
        student.setProgram(studentData.getProgram());

        if (studentData.getDepartmentData() != null) {
            Department department = new Department();
            department.setId(studentData.getDepartmentData().getId());
            department.setName(studentData.getDepartmentData().getName());
            student.setDepartment(department);
        }
    }


}