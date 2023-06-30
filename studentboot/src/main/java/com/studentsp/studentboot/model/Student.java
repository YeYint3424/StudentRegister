package com.studentsp.studentboot.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "st_name", nullable = false)
    private String name;

    @Column(nullable = false)
    private String dob;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String education;

    @Column(columnDefinition = "longblob")
    private String st_photo;

    @Column(length = 14, nullable = false)
    private String phone;

    @Column(name = "user_id", nullable = false)
    private int user_id;

    @Column(name = "status", nullable = true)
    private String status;

    @OneToMany(mappedBy = "student")
    private List<Student_Course> studentCourses;
	
	public Student() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSt_photo() {
		return st_photo;
	}

	public void setSt_photo(String st_photo) {
		this.st_photo = st_photo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}