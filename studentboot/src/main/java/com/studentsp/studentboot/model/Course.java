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
@Table(name = "course")
public class Course {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    @Column(name = "course_name", nullable = false)
    private String course_name;

    @Column(name = "course_info", nullable = false)
    private String course_info;

    @Column(name = "course_photo", columnDefinition = "longblob", nullable = false)
    private String course_photo;

    @OneToMany(mappedBy = "course")
    private List<Student_Course> studentCourses;

	public String getCourse_photo() {
		return course_photo;
	}

	public void setCourse_photo(String course_photo) {
		this.course_photo = course_photo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getCourse_info() {
		return course_info;
	}

	public void setCourse_info(String course_info) {
		this.course_info = course_info;
	}
	
	
}
