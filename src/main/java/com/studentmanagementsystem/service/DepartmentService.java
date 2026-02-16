package com.studentmanagementsystem.service;

import com.studentmanagementsystem.model.Department;

import java.util.List;

public interface DepartmentService {
    Department getDepartmentById(Long id);
    List<Department> getAllDepartments();
    Department saveDepartment(Department department);
    void deleteDepartmentById(Long id);
}
