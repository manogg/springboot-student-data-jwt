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

import com.mlv.springboot.student.data.jpa.dto.StudentDTO;
import com.mlv.springboot.student.data.jpa.entity.Student;
import com.mlv.springboot.student.data.jpa.repository.StudentRepository;

@Service
public class StudentServiceImplementation implements StudentService, UserDetailsService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public String addStudent(Student student) {

		studentRepository.save(student);

		return "The given student added";
	}

	@Override
	public void updateStudent(Student student) {

		studentRepository.save(student);

	}

	@Override
	public void deleteStudent(int studentId) {

		studentRepository.deleteById(studentId);

	}

	@Override
	public Optional<Student> getById(int studentId) {

		return studentRepository.findById(studentId);

	}

	@Override
	public List<Student> getAll() {

		return studentRepository.findAll();
	}

	@Override
	public List<Student> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findStudentsWithSorting(String field) {

		return studentRepository.findAll(Sort.by(Sort.Direction.ASC, field));

	}

	@Override
	public Page<Student> findStudentsWithPagination(int offset, int pageSize) {

		Page<Student> students = studentRepository.findAll(PageRequest.of(offset, pageSize));
		return students;

	}

	@Override
	public Page<Student> findStudentsWithPaginationAndSorting(int offset, int pageSize, String field) {

		Page<Student> studentsPaginationWithSorting = studentRepository
				.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.DESC, field)));

		return studentsPaginationWithSorting;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Student> studentRole = studentRepository.findByName(username);

		return studentRole.map(StudentDTO::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found" + username));

	}

}
