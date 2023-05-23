package com.mlv.springboot.student.data.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mlv.springboot.student.data.jpa.entity.AuthRequest;
import com.mlv.springboot.student.data.jpa.entity.Student;
import com.mlv.springboot.student.data.jpa.entity.StudentMarks;
import com.mlv.springboot.student.data.jpa.service.JWTService;
import com.mlv.springboot.student.data.jpa.service.StudentServiceImplementation;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentServiceImplementation studentServiceImplementation;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JWTService jWTService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostConstruct
	public void intiDB() {

		StudentMarks m1 = new StudentMarks(1, 100, 100, 100, 100, 100);

		Student mano = new Student(1, "mano", 25, m1, "ROLE_ADMIN", passwordEncoder.encode("mano"));

		StudentMarks m3 = new StudentMarks(2, 60, 50, 70, 80, 80);

		Student elango = new Student(2, "elango", 25, m3, "ROLE_USER", passwordEncoder.encode("elango"));

		studentServiceImplementation.addStudents(mano);

		studentServiceImplementation.addStudents(elango);

	}

	@GetMapping("/welcome")
	// @PreAuthorize("hasAuthority('ROLE_USER')")
	public String welcomeMessage() {

		return "Hi Welcome to spring security";
	}

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<Student> getAllStudents() {

		return studentServiceImplementation.getAllStudent();
	}

	@GetMapping("/sort/{field}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Student> findStudentsWithSorting(@PathVariable("field") String field) {

		return studentServiceImplementation.findStudentsWithSorting(field);
	}

	@GetMapping("/pagination/{offset}/{pageSize}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Page<Student> findStudentsWithPagination(@PathVariable("offset") int offset,
			@PathVariable("pageSize") int pageSize) {

		return studentServiceImplementation.findStudentsWithPagination(offset, pageSize);
	}

	@GetMapping("/pagination/{offset}/{pageSize}/{field}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Page<Student> findStudentsWithPaginationAndSorting(@PathVariable("offset") int offset,
			@PathVariable("pageSize") int pageSize, @PathVariable("field") String field) {

		return studentServiceImplementation.findStudentsWithPaginationAndSorting(offset, pageSize, field);
	}

	@PostMapping("/authentication")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			return jWTService.generateToken(authRequest.getName());
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}

	}

}
