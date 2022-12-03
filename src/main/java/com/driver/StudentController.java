package com.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("students")
public class StudentController {

    @Autowired
    StudentService studentService;
    //Add a Student: POST /students/add-student Pass the Student object as request body
    // Return success message wrapped in a ResponseEntity object Controller Name - addStudent

    @PostMapping("/add-student")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        studentService.createStd(student);
        return new ResponseEntity<>("New student added successfully", HttpStatus.CREATED);
    }

    //Add a teacher: POST /students/add-teacher Pass the Teacher object as request body
    // Return success message wrapped in a ResponseEntity object Controller Name - addTeacher
    @PostMapping("/add-teacher")
    public ResponseEntity<String> addTeacher(@RequestBody Teacher teacher){
        studentService.createTeacher(teacher);
        return new ResponseEntity<>("New teacher added successfully", HttpStatus.CREATED);
    }

    //Pair an existing student and teacher: PUT /students/add-student-teacher-pair Pass student name and teacher name as request parameters
    //Return success message wrapped in a ResponseEntity object Controller Name - addStudentTeacherPair
    @PutMapping("/add-student-teacher-pair")
    public ResponseEntity<String> addStudentTeacherPair(@RequestParam String studentName, @RequestParam String teacherName){
        studentService.addStudentTeacher(studentName,teacherName);
        return new ResponseEntity<>("New student-teacher pair added successfully", HttpStatus.CREATED);
    }

    //Get Student by student name: GET /students/get-student-by-name/{name} Pass student name as path parameter
    // Return Student object wrapped in a ResponseEntity object Controller Name - getStudentByName
    @GetMapping("/get-student-by-name/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name){
        Student student = null; // Assign student by calling service layer method
        student =studentService.getStudent(name);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    //Get Teacher by teacher name: GET /students/get-teacher-by-name/{name} Pass teacher name as path parameter
    // Return Teacher object wrapped in a ResponseEntity object Controller Name - getTeacherByName
    @GetMapping("/get-teacher-by-name/{name}")
    public ResponseEntity<Teacher> getTeacherByName(@PathVariable String name){
        Teacher teacher = null; // Assign student by calling service layer method
        teacher =studentService.getTeacher(name);
        return new ResponseEntity<>(teacher, HttpStatus.CREATED);
    }

    //Get List of students name for a given teacher name: GET /students/get-students-by-teacher-name/{teacher} Pass teacher name as path parameter
    // Return List of students name(List()) wrapped in a ResponseEntity object Controller Name - getStudentsByTeacherName
    @GetMapping("/get-students-by-teacher-name/{teacher}")
    public ResponseEntity<List<String>> getStudentsByTeacherName(@PathVariable String teacher){
        List<String> students = null; // Assign list of student by calling service layer method
        students=studentService.getStudentList(teacher);
        return new ResponseEntity<>(students, HttpStatus.CREATED);
    }

    //Get List of all students added: GET /students/get-all-students No params or body required
    // Return List of students name(List()) wrapped in a ResponseEntity object Controller Name - getAllStudents
    @GetMapping("/get-all-students")
    public ResponseEntity<List<String>> getAllStudents(){
        List<String> students = null; // Assign list of student by calling service layer method
        students=studentService.getAllStudent();
        return new ResponseEntity<>(students, HttpStatus.CREATED);
    }

    //Delete a teacher and its students from the records: DELETE /students/delete-teacher-by-name Pass teacher’s name as request parameter
    // Return success message wrapped in a ResponseEntity object Controller Name - deleteTeacherByName
    @DeleteMapping("/delete-teacher-by-name")
    public ResponseEntity<String> deleteTeacherByName(@RequestParam String teacher){
        studentService.deleteByTeacher(teacher);
        return new ResponseEntity<>(teacher + " removed successfully", HttpStatus.CREATED);
    }

    //Delete all teachers and all students by them from the records: DELETE /students/delete-all-teachers No params or body required
    // Return success message wrapped in a ResponseEntity object Controller Name - deleteAllTeachers
    @DeleteMapping("/delete-all-teachers")
    public ResponseEntity<String> deleteAllTeachers(){
        studentService.deleteAllTeachers();
        return new ResponseEntity<>("All teachers deleted successfully", HttpStatus.CREATED);
    }
}
