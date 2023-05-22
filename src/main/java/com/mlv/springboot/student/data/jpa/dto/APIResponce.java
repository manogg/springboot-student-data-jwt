package com.mlv.springboot.student.data.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponce<T> {

	int recordCount;
	T responses;

}
