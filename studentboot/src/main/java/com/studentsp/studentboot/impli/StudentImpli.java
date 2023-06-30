package com.studentsp.studentboot.impli;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentsp.studentboot.model.Student;
import com.studentsp.studentboot.repo.StudentRepo;
import com.studentsp.studentboot.services.StudentServices;

@Service
public class StudentImpli implements StudentServices {

	@Autowired
	private StudentRepo studentRepo;

	@Override
	public Student saveStudent(Student student) {
		return studentRepo.save(student);
	}

//	@Override
//	public List<Student> getAllStudent() {
//		return studentRepo.findAll();
//	}

	@Override
	public Student getStudentByStudentId(int id) {
		return studentRepo.findById(id).orElse(null);
	}

	@Override
	public Student updateStudent(Student student, int id) {
		Student existingStudent = studentRepo.findById(id).orElse(null);
		if (existingStudent != null) {
			existingStudent.setName(student.getName());
			existingStudent.setDob(student.getDob());
			existingStudent.setGender(student.getGender());
			existingStudent.setEducation(student.getEducation());
			existingStudent.setSt_photo(student.getSt_photo());
			existingStudent.setPhone(student.getPhone());
			existingStudent.setUser_id(student.getUser_id());
			return studentRepo.save(existingStudent);
		}
		return null;
	}

	@Override
	public Student statusChange(Student student, int id) {
		Student existingStudent = studentRepo.findById(id).orElse(null);
		if (existingStudent != null) {
			existingStudent.setName(student.getName());
			existingStudent.setDob(student.getDob());
			existingStudent.setGender(student.getGender());
			existingStudent.setEducation(student.getEducation());
			existingStudent.setSt_photo(student.getSt_photo());
			existingStudent.setPhone(student.getPhone());
			existingStudent.setUser_id(student.getUser_id());
			existingStudent.setStatus(student.getStatus());
			return studentRepo.save(existingStudent);
		}
		return null;
	}

	@Override
	public Student getStudentByName(String name) {
		return studentRepo.findByName(name);
	}

	@Override
	public List<Student> getStudentByStatus1(String status) {
		// TODO Auto-generated method stub
		return studentRepo.findByStatus(status);
	}
}
