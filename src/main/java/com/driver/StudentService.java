package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void createStd(Student student){
        studentRepository.addStudents(student);
    }
    public void createTeacher(Teacher teacher){
        studentRepository.addTeachers(teacher);
    }


    public void addStudentTeacher(String stdName,String teacherName){

        studentRepository.addStudentTeacher(stdName,teacherName);
    }

    public Student getStudent(String name){
        return studentRepository.getStudent(name);
    }
    public Teacher getTeacher(String name){
        return studentRepository.getTeacher(name);
    }

    public List<String> getStudentList(String teacher){
        return studentRepository.getStudentList(teacher);
    }
    public List<String> getAllStudent(){
        return studentRepository.getAllStudents();
    }
//    public List<String> getAllTeacher(){
//        return studentRepository.getAllTeachers();
//    }
    public void deleteByTeacher(String teacher){
        studentRepository.deleteDataTeacher(teacher);
    }
    public void deleteAllTeachers(){
        studentRepository.deleteAllTeachers();

}
}
