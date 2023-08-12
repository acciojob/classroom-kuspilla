package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class service {

    @Autowired
    private StudentRepository repository_ob;

    public void addStudent(Student student){
         repository_ob.addStudent(student);
    }
    public void addTeacher(Teacher teacher){
        repository_ob.addTeacher(teacher);
    }

    public Student getStudentByName(String name){
        Student student = repository_ob.getStudentByName(name);
        return student;
    }
    public Teacher getTeacherByName(String Tname){
        Teacher teacher = repository_ob.getTeacherByName(Tname);
        return teacher;
    }
    public List<String> getAllStudents(){
        List<String> all_students = repository_ob.getAllStudents();
        return all_students;
    }
    public  boolean deleteTeacherByName(String Tname){
        boolean flag = repository_ob.deleteTeacherByName(Tname);
        return flag;
    }
    public void deleteAllTeachers(){
        repository_ob.deleteAllTeachers();
    }
}
