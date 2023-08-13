package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StudentService {


    private StudentRepository repository_ob = new StudentRepository();

    public void addStudent(Student student){
        List<Student> l = repository_ob.getStudentList();
        l.add(student);

    }
    public void addTeacher(Teacher teacher){
        List<Teacher> l  = repository_ob.getTeacherList();
        l.add(teacher);
    }
    public void addStudentTeacherPair(String student, String teacher){
        List<Student> studentList = repository_ob.getStudentList();
        List<Teacher> teacherList = repository_ob.getTeacherList();
        Student stu = null;
        Teacher tec = null;
        for(Student s: studentList){
            if(s.getName().equals(student)){
                stu = s;
                break;
            }
        }
        for(Teacher t: teacherList){
            if(t.getName().equals(teacher)){
                tec = t;
                break;
            }
        }
        if(stu == null || tec == null) return;

        HashMap<Teacher, ArrayList<Student>> STMap = repository_ob.getStudentTeacherMap();
        ArrayList<Student> studentListUnderTeacher = STMap.getOrDefault(tec, new ArrayList<>());
        studentListUnderTeacher.add(stu);
        STMap.put(tec, studentListUnderTeacher);
        System.out.println(studentListUnderTeacher);
        tec.setNumberOfStudents(tec.getNumberOfStudents()+1);
    }

    public Student getStudentByName(String name){
        List<Student> list = repository_ob.getStudentList();
        for(Student stu : list){
            if(stu.getName().equals(name)) return stu;
        }
        return null;
    }
    public Teacher getTeacherByName(String Tname){
        List<Teacher> list = repository_ob.getTeacherList() ;
        for(Teacher tech : list){
            if( tech.getName().equals(Tname)) return tech;
        }
        return null;
    }
    public List<String> getStudentsByTeacherName(String teacherName){

        List<Teacher> teacherList = repository_ob.getTeacherList();
        Teacher teacher = null;
        for(Teacher t: teacherList){
            if(t.getName().equals(teacherName)){
                teacher = t;
                break;
            }
        }
        HashMap<Teacher, ArrayList<Student>> STMap = repository_ob.getStudentTeacherMap();
        List<Student> studentListUnderTeacher = STMap.getOrDefault(teacher, new ArrayList<>());
        List<String> res = new ArrayList<>();
        for(Student s: studentListUnderTeacher){
            res.add(s.getName());
        }
        return res;
    }
    public List<String> getAllStudents(){
        List<String> all_students = new ArrayList<>();
        List<Student> stu = repository_ob.getStudentList();
        for(Student student : stu){
            all_students.add(student.getName());
        }
        return all_students;
    }
    public void deleteTeacherByName(String teacherName){
        List<Student> studentList = repository_ob.getStudentList();
        List<Teacher> teacherList = repository_ob.getTeacherList();
        Teacher teacher = null;
        for(Teacher t: teacherList){
            if(t.getName().equals(teacherName)){
                teacher = t;
                break;
            }
        }
        HashMap<Teacher, ArrayList<Student>> STMap = repository_ob.getStudentTeacherMap();
        List<Student> studentListUnderTeacher = STMap.getOrDefault(teacher, new ArrayList<>());

        for(Student s: studentListUnderTeacher){
            studentList.remove(s);
        }
        teacherList.remove(teacher);
        repository_ob.removeTeacher(teacher);

    }
    public void deleteAllTeachers(){
        List<Teacher> teacherList = repository_ob.getTeacherList();
        List<Teacher> teacherListCopy = new ArrayList<>();
        teacherListCopy.addAll(teacherList);
        for(Teacher t: teacherListCopy){
            deleteTeacherByName(t.getName());
        }

    }
}
