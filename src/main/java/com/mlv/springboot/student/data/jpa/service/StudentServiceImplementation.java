package com.mlv.springboot.student.data.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mlv.springboot.student.data.jpa.dto.StudentUserDetails;
import com.mlv.springboot.student.data.jpa.entity.Student;
import com.mlv.springboot.student.data.jpa.repository.StudentRepository;

@Service
public class StudentServiceImplementation implements UserDetailsService {

	@Autowired
	private StudentRepository studentRepository;

	public void addStudents(Student student) {

		studentRepository.save(student);

	}

	public List<Student> getAllStudent() {

		return studentRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Student> student = studentRepository.findByName(username);

		return student.map(StudentUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("The given user name not found : " + username));

	}

	public List<Student> findStudentsWithSorting(String field) {

		return studentRepository.findAll(Sort.by(Sort.Direction.ASC, field));

	}

	public Page<Student> findStudentsWithPagination(int offset, int pageSize) {

		Page<Student> students = studentRepository.findAll(PageRequest.of(offset, pageSize));
		return students;

	}

	public Page<Student> findStudentsWithPaginationAndSorting(int offset, int pageSize, String field) {

		Page<Student> studentsPaginationWithSorting = studentRepository
				.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.DESC, field)));

		return studentsPaginationWithSorting;
	}

}
