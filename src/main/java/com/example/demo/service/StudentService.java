package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Create
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> saveAllStudent(List<Student> student) {
        return studentRepository.saveAll(student);
    }

    // Read by ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Update
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(studentDetails.getName());
        student.setAge(studentDetails.getAge());
        student.setEmail(studentDetails.getEmail());

        return studentRepository.save(student);
    }

    // Delete
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // Get All Students with Pagination
    public List<Student> getAllStudents() {
        int page = 0; // default page number
        int size = 5; // default page size
         List<Student> result = new ArrayList<>();
        while (true) {
             Pageable pageable = PageRequest.of(page, size);
           List<Student> students = studentRepository.findAll(pageable).getContent();
            if(students.isEmpty()) {
                break;
            }
            page++;
            result.addAll(students);
        }
       return result;
    }
}

