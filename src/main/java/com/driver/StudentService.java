package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {


    private StudentRepository repository_ob = new StudentRepository();

    public void addStudent(Student student){
         repository_ob.addStudent(student);
    }
    public void addTeacher(Teacher teacher){
        repository_ob.addTeacher(teacher);
    }
    public void addStudentTeacherPair(String student, String teacher){
        repository_ob.addStudentTeacherPair(student,teacher);
    }

    public Student getStudentByName(String name){
        Student student = repository_ob.getStudentByName(name);
        return student;
    }
    public Teacher getTeacherByName(String Tname){
        Teacher teacher = repository_ob.getTeacherByName(Tname);
        return teacher;
    }
    public List<String> getStudentsByTeacherName(String Teacher_Name){
        return repository_ob.getStudentsByTeacherName(Teacher_Name);
    }
    public List<String> getAllStudents(){
        List<String> all_students = repository_ob.getAllStudents();
        return all_students;
    }
    public void deleteTeacherByName(String Tname){
         repository_ob.deleteTeacherByName(Tname);

    }
    public void deleteAllTeachers(){
        repository_ob.deleteAllTeachers();
    }
}
