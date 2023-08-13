package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

    public HashMap<String, Student> studentDB = new HashMap<>();
    public HashMap<String,Teacher> teacherDB = new HashMap<>();
    public HashMap<String, List<String>> teacher_and_studentDB = new HashMap<>();


    public void addStudent(Student student){
        String St_name = student.getName();
        studentDB.put(St_name, student);
    }


    public void addTeacher(Teacher teacher){
        String t_name = teacher.getName();
        teacherDB.put(t_name, teacher);
    }
    public void addStudentTeacherPair(String student, String teacher){

            if(teacher_and_studentDB.containsKey(teacher)){
                teacher_and_studentDB.get(teacher).add(student);
            }
            else{
                List<String> l = new ArrayList<>();
                l.add(student);
                teacher_and_studentDB.put(teacher,l);
            }

    }
    public Student getStudentByName(String name){
        Student student = studentDB.get(name) ;
         return student;
    }
    public Teacher getTeacherByName(String Tname){
        Teacher teacher = teacherDB.get(Tname);
        return teacher;
    }
    public List<String> getStudentsByTeacherName(String Teacher_name){
        if( teacher_and_studentDB.containsKey(Teacher_name) == false){
            return new ArrayList<>();
        }
        return teacher_and_studentDB.get(Teacher_name);
    }
    public List<String> getAllStudents(){

        return new ArrayList<>(studentDB.keySet());
    }
    public void deleteTeacherByName(String Tname){
        List<String> l = new ArrayList<>(teacher_and_studentDB.get(Tname));
        teacher_and_studentDB.remove(Tname);
        teacherDB.remove(Tname);
        for(String s : l){
            studentDB.remove(s);
        }

    }
    public void deleteAllTeachers(){
       List<String> l = new ArrayList<>();
       for(String s : teacher_and_studentDB.keySet()){
           l.addAll(teacher_and_studentDB.get(s));
       }
       teacher_and_studentDB.clear();
       teacherDB.clear();
       for(String t : l){
           studentDB.remove(t);
       }
    }
}
