package com.advpro.profiling.tutorial.service;

import com.advpro.profiling.tutorial.model.Student;
import com.advpro.profiling.tutorial.model.StudentCourse;
import com.advpro.profiling.tutorial.repository.StudentCourseRepository;
import com.advpro.profiling.tutorial.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author muhammad.khadafi
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public List<StudentCourse> getAllStudentsWithCourses() {
//        List<Student> students = studentRepository.findAll();
//        List<StudentCourse> studentCourses = new ArrayList<>();
//        for (Student student : students) {
//            List<StudentCourse> studentCoursesByStudent = studentCourseRepository.findByStudentId(student.getId());
//            for (StudentCourse studentCourseByStudent : studentCoursesByStudent) {
//                StudentCourse studentCourse = new StudentCourse();
//                studentCourse.setStudent(student);
//                studentCourse.setCourse(studentCourseByStudent.getCourse());
//                studentCourses.add(studentCourse);
//            }
//        }
        // "List<Student> students = studentRepository.findAll();"
        // if the line above can get the list of all Student object in studentRepository in one line
        // what if we can get the list of all StudentCourse object in studentCourseRepository with the same way?
        List<StudentCourse> studentCourses = studentCourseRepository.findAll();
        return studentCourses;
    }

    public Optional<Student> findStudentWithHighestGpa() {
//        List<Student> students = studentRepository.findAll();
//        Student highestGpaStudent = null;
//        double highestGpa = 0.0;
//        for (Student student : students) {
//            if (student.getGpa() > highestGpa) {
//                highestGpa = student.getGpa();
//                highestGpaStudent = student;
//            }
//        }
//        return Optional.ofNullable(highestGpaStudent);
        // The previous code call in the data from the database and iterate it here
        // But accessing the database takes time, and the previous code can't be optimised further
        // because the number of comparison will always be O(N), therefore why not cut the middle men
        // and do the calculation inside the database
        return studentRepository.findStudentWithHighestGpa();
    }

    public String joinStudentNames() {
//        List<Student> students = studentRepository.findAll();
//        String result = "";
//        for (Student student : students) {
//            result += student.getName() + ", ";
//        }
//        return result.substring(0, result.length() - 2);
        // As every String concatenation copies the whole string,
        // usually it is preferable to replace it with explicit
        // calls to StringBuilder.append() or StringBuffer.append().
        // - IntelliJ Ultimate
        // From what I understand, by using concatenation, the code is copying
        // and making new string with added string every time the code add a new student
        // name, making the operation very heavy on copying existing data
        // which is inefficient, instead of copying the immutable String object
        // we can instead use the "mutable" StringBuilder object
        List<Student> students = studentRepository.findAll();
        StringBuilder result = new StringBuilder();
        for (Student student : students) {
            result.append(student.getName()).append(", ");
        }
        return result.substring(0, result.length() - 2);
    }
}

