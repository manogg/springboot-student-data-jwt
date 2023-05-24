package com.mlv.springboot.student.data.jpa.entity;

import java.time.Instant;
import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class RefreshToken {

	@Id
	@GeneratedValue(generator = "newtkn_gen", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "newtkn_gen", sequenceName = "newtkn_seq", initialValue = 1, allocationSize = 10)
	private Integer id;
	private String token;
	private Instant expiryDate;

	@OneToOne
	@JoinColumn(name = "student_id", referencedColumnName = "rollNumber")
	private Student student;

}
