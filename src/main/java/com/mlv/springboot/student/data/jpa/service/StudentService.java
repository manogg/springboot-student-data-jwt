package com.mlv.springboot.student.data.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.mlv.springboot.student.data.jpa.entity.Student;

public interface StudentService {

	String addStudent(Student student);

	void updateStudent(Student student);

	void deleteStudent(int studentId);

	Optional<Student> getById(int studentId);

	List<Student> getAll();

	List<Student> getByName(String name);

	List<Student> findStudentsWithSorting(String field);

	Page<Student> findStudentsWithPagination(int offset, int pageSize);

	Page<Student> findStudentsWithPaginationAndSorting(int offset, int pageSize, String field);

}
