package com.mlv.springboot.student.data.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mlv.springboot.student.data.jpa.dto.APIResponce;
import com.mlv.springboot.student.data.jpa.entity.Student;
import com.mlv.springboot.student.data.jpa.entity.StudentMarks;
import com.mlv.springboot.student.data.jpa.service.StudentServiceImplementation;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentServiceImplementation studentServiceImplementation;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/*
	 * @PostConstruct public void intiDB() {
	 * 
	 * StudentMarks m1 = new StudentMarks(1, 100, 100, 100, 100, 100);
	 * 
	 * Student mano = new Student(1, "Mano", 25, m1, "ADMIN",
	 * passwordEncoder.encode("Mano"));
	 * 
	 * StudentMarks m2 = new StudentMarks(1, 60, 50, 70, 80, 80);
	 * 
	 * Student elango = new Student(2, "Elangovan", 25, m2, "USER",
	 * passwordEncoder.encode("Elangovan"));
	 * 
	 * studentServiceImplementation.addStudent(mano);
	 * 
	 * studentServiceImplementation.addStudent(elango);
	 * 
	 * }
	 */

	@GetMapping("/get")
	@PreAuthorize("hasAuthority('USER')")
	public List<Student> get() {

		return studentServiceImplementation.getAll();
	}

	@GetMapping("/getAll")
	@PreAuthorize("hasAuthority('ADMIN')")
	private APIResponce<List<APIResponce>> getAll() {

		List<Student> allStudents = studentServiceImplementation.getAll();

		return new APIResponce(allStudents.size(), allStudents);
	}

	@GetMapping("/sort/getAll/{field}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public APIResponce<List<APIResponce>> findStudentsWithSorting(@PathVariable("field") String field) {

		List<Student> allStudents2 = studentServiceImplementation.findStudentsWithSorting(field);

		return new APIResponce(allStudents2.size(), allStudents2);

	}

	@GetMapping("/pagination/getAll/{offset}/{pageSize}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public APIResponce<Page<Student>> findStudentsWithPagination(@PathVariable("offset") int offset,
			@PathVariable("pageSize") int pageSize) {

		Page<Student> students = studentServiceImplementation.findStudentsWithPagination(offset, pageSize);

		return new APIResponce(students.getSize(), students);
	}

	@GetMapping("/pagination/sort/getAll/{offset}/{pageSize}/{field}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public APIResponce<Page<Student>> findStudentsWithPaginationAndSorting(@PathVariable("offset") int offset,
			@PathVariable("pageSize") int pageSize, @PathVariable("field") String field) {

		Page<Student> students = studentServiceImplementation.findStudentsWithPaginationAndSorting(offset, pageSize,
				field);

		return new APIResponce(students.getSize(), students);
	}
}
