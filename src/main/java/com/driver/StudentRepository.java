package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Component
@Repository
public class StudentRepository {

    HashMap<String, Student> stds = new HashMap<>();
    HashMap<String, Teacher> teachers = new HashMap<>();
    HashMap<String,List<String>> stdTeacher = new HashMap<>();

    public void addStudents(Student student){
        stds.put(student.getName(),student);
    }
    public void addTeachers(Teacher teacher){
        teachers.put(teacher.getName(),teacher);
    }

    // add std and teachers
    public void addStudentTeacher(String stdName, String teacherName){
        if(!stdTeacher.containsKey(teacherName)){
            List<String> newList=stdTeacher.get(teacherName);
            newList.add(stdName);
            stdTeacher.remove(teacherName);
            stdTeacher.put(teacherName,newList);
        }else{
            List<String> stdList=new ArrayList<>();
            stdList.add(stdName);
            stdTeacher.put(teacherName,stdList);
        }

    }
    public Student getStudent(String stdName){
        return stds.get(stdName);
    }

    public Teacher getTeacher(String teacherName){
        return teachers.get(teacherName);
    }
    public List<String> getStudentList(String teacher){

        return stdTeacher.get(teacher);
    }

    public List<String> getAllStudents(){
        List<String> AllStudents=new ArrayList<>();
         for(String s:stds.keySet()){
             AllStudents.add(s);
         }
         return AllStudents;
    }
//    public List<String> getAllTeachers(){
//        List<String> AllTeachers=new ArrayList<>();
//        for(String s:stds.keySet()){
//            AllTeachers.add(s);
//        }
//        return AllTeachers;
//    }

    public void deleteDataTeacher(String teacher){

        if(stdTeacher.containsKey(teacher)){

            List<String> studs=stdTeacher.get(teacher);
//            for(String sts:studs){
//                stds.remove(sts);
//            }
            teachers.remove(teacher);
           // stdTeacher.remove(teacher);
        }
    }
    public void deleteAllTeachers(){

        HashSet<String> delTeacher=new HashSet<>();
        for(String t:teachers.keySet()){
            for(String s:stdTeacher.get(t)){
                delTeacher.add(s);
            }
        }
        for(String s:delTeacher){
            if(stds.containsKey(s)){
                stds.remove(s);
            }
        }
        stdTeacher.clear();
        stds.clear();
        teachers.clear();
    }

}
