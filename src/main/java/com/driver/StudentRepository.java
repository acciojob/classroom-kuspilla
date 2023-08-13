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
        if(!teacherDB.containsKey(teacher)) return ;
        if( !teacher_and_studentDB.containsKey(teacher)){

            Teacher teacherOB = teacherDB.get(teacher);
            int number_of_students = teacherOB.getNumberOfStudents();
            teacherOB.setNumberOfStudents( number_of_students +1);
            teacherDB.put(teacher, teacherOB);

            teacher_and_studentDB.put(teacher, new ArrayList<>());
            teacher_and_studentDB.get(teacher).add(student);
            return ;
        }
        /*
        first get teacher object and increase number of students and add agin updated teacher object
        teacher_and_studentDB also add teacher key and student list add student
         */
        Teacher teacherOB = teacherDB.get(teacher);
        int number_of_students = teacherOB.getNumberOfStudents();
        teacherOB.setNumberOfStudents( number_of_students +1);
        teacherDB.put(teacher, teacherOB);
        teacher_and_studentDB.get(teacher).add(student);

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
        teacher_and_studentDB.values();
    }
}
