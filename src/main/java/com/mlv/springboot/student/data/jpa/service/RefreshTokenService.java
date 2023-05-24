package com.mlv.springboot.student.data.jpa.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlv.springboot.student.data.jpa.entity.RefreshToken;
import com.mlv.springboot.student.data.jpa.repository.RefreshTokenRepository;
import com.mlv.springboot.student.data.jpa.repository.StudentRepository;

@Service
public class RefreshTokenService {

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private StudentRepository studentRepository;

	public RefreshToken createrefReshToken(String name) {

		RefreshToken refreshToken = RefreshToken.builder().token(UUID.randomUUID().toString())
				.student(studentRepository.findByName(name).get()).expiryDate(Instant.now().plusMillis(600000)).build();

		return refreshTokenRepository.save(refreshToken);
	}

	public Optional<RefreshToken> findByToken(String token) {

		return refreshTokenRepository.findByToken(token);
	}

	public RefreshToken verifyExpiration(RefreshToken token) {

		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {

			refreshTokenRepository.delete(token);

			throw new RuntimeException(token.getToken() + " : " + "New Token got expired, Please relogin again");
		}
		return token;
	}
}
