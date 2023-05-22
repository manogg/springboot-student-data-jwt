package com.mlv.springboot.student.data.jpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Student {

	@Id
	@GeneratedValue(generator = "stu_gen", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "stu_gen", sequenceName = "stu_seq", initialValue = 1, allocationSize = 10)
	private Integer rollNumber;
	private String name;
	private Integer age;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mark_id")
	StudentMarks marks;

	private String role;
	private String password;

	public Student(Integer rollNumber, String name, Integer age, StudentMarks marks, String role, String password) {
		super();
		this.rollNumber = rollNumber;
		this.name = name;
		this.age = age;
		this.marks = marks;
		this.role = role;
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Student() {
		super();
	}

	public Integer getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(Integer rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public StudentMarks getMarks() {
		return marks;
	}

	public void setMarks(StudentMarks marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Student [rollNumber=" + rollNumber + ", name=" + name + ", age=" + age + ", marks=" + marks + ", role="
				+ role + ", password=" + password + "]";
	}

}
