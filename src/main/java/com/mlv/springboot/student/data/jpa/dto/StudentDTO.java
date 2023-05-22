package com.mlv.springboot.student.data.jpa.dto;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mlv.springboot.student.data.jpa.entity.Student;

public class StudentDTO implements UserDetails {

	String name;
	String password;
	private List<GrantedAuthority> grantedAuthority;

	public StudentDTO(Student student) {

		name = student.getName();
		password = student.getPassword();
		grantedAuthority = Arrays.stream(student.getRole().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return grantedAuthority;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return name;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
