package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class repository {

    public HashMap<String, Student> studentDB = new HashMap<>();
    public HashMap<String,Teacher> teacherDB = new HashMap<>();
    public HashMap<String, Pair> student_and_teacherDB = new HashMap<>();
    class Pair{
        Student student;
        Teacher teacher;
        Pair(Student student, Teacher teacher){
            this.student = student;
            this.teacher = teacher;
        }

    }

    public void addStudent(Student student){
        String St_name = student.getName();
        studentDB.put(St_name, student);
    }


    public void addTeacher(Teacher teacher){
        String t_name = teacher.getName();
        teacherDB.put(t_name, teacher);
    }

    public Student getStudentByName(String name){
        Student student = studentDB.get(name) ;
         return student;
    }
    public Teacher getTeacherByName(String Tname){
        Teacher teacher = teacherDB.get(Tname);
        return teacher;
    }
    public List<String> getAllStudents(){
        List<String> all_students = new ArrayList<>();
        for( Student student : studentDB.values()){
            all_students.add( student.getName());
        }
        return all_students;
    }
    public boolean deleteTeacherByName(String Tname){
        if(teacherDB.containsKey(Tname) == false){
            return false;
        }
        teacherDB.remove(Tname);
        return true;
    }
    public void deleteAllTeachers(){
        teacherDB.clear();
    }
}
