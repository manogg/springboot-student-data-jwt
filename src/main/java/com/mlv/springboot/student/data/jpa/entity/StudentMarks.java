package com.mlv.springboot.student.data.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class StudentMarks {

	@Id
	@GeneratedValue(generator = "mrk_gen", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "mrk_gen", sequenceName = "mrk_seq", initialValue = 1, allocationSize = 10)
	@JsonIgnore
	private Integer markId;

	private Integer tamil;
	private Integer english;
	private Integer maths;
	private Integer science;
	private Integer socialScience;

	public StudentMarks(Integer markId, Integer tamil, Integer english, Integer maths, Integer science,
			Integer socialScience) {
		super();
		this.markId = markId;
		this.tamil = tamil;
		this.english = english;
		this.maths = maths;
		this.science = science;
		this.socialScience = socialScience;
	}

	public StudentMarks() {
		super();
	}

	public Integer getMarkId() {
		return markId;
	}

	public void setMarkId(Integer markId) {
		this.markId = markId;
	}

	public Integer getTamil() {
		return tamil;
	}

	public void setTamil(Integer tamil) {
		this.tamil = tamil;
	}

	public Integer getEnglish() {
		return english;
	}

	public void setEnglish(Integer english) {
		this.english = english;
	}

	public Integer getMaths() {
		return maths;
	}

	public void setMaths(Integer maths) {
		this.maths = maths;
	}

	public Integer getScience() {
		return science;
	}

	public void setScience(Integer science) {
		this.science = science;
	}

	public Integer getSocialScience() {
		return socialScience;
	}

	public void setSocialScience(Integer socialScience) {
		this.socialScience = socialScience;
	}

	@Override
	public String toString() {
		return "StudentMarks [markId=" + markId + ", tamil=" + tamil + ", english=" + english + ", maths=" + maths
				+ ", science=" + science + ", socialScience=" + socialScience + "]";
	}

}
