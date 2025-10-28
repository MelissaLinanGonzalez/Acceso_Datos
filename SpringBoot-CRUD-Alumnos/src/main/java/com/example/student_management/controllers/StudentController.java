package com.example.student_management.controllers;


import com.example.student_management.domain.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/alunmos")
public class StudentController {

    private List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1, "María Rodríguez", "mRodri@email.com", 20, "2ºDAM"),
            new Student(2, "Gerardo Bretón", "geri@email.com", 21, "1ºDAM"),
            new Student(3, "Miguel Unamuno", "miguelito@email.com", 22, "1ªASIR"),
            new Student(4, "Nadia Fernández", "nadiaF@email.com", 23, "2ºASIR")
    ));

    // Mostrar todos los alumnos
    @GetMapping
    public List<Student> getStudents(){
        return students;
    }

    //Consultar un alumno por su email
    @GetMapping("/{email}")
    public Student getStudent(@PathVariable String email){
        for (Student e : students){
            if (e.getEmail().equalsIgnoreCase(email)){
                return e;
            }
        }
        return null;
    }

    // Crear un nuevo Alumno
    @PostMapping
    public Student postStudent(@RequestBody Student student){
        students.add(student);
        return student;
    }

    // Modificación total de un alumno
    @PutMapping
    public Student putStudent(@RequestBody Student student){
        for (Student e : students){
            if (e.getID() == student.getID()){
                e.setName(student.getName());
                e.setEmail(student.getEmail());
                e.setAge(student.getAge());
                e.setCourse(student.getCourse());
                return e;
            }
        }
        return null;
    }

    // Borrar un alumno por su id
    @DeleteMapping("{id}")
    public Student deleteStudent(@PathVariable int id){
        for (Student e : students){
            if (e.getID() == id){
                students.remove(e);

                return e;
            }
        }
        return null;
    }

    @PatchMapping
    public Student patchSudent(@RequestBody Student student){
        for (Student e : students){
            if (e.getID() == student.getID()){
                if (student.getName() != null){
                    e.setName(student.getName());
                }
                if (student.getEmail() != null){
                    e.setEmail(student.getEmail());
                }
                if (student.getAge() != 0){
                    e.setAge(student.getAge());
                }
                if (student.getCourse() != null){
                    e.setCourse(student.getCourse());
                }
                return e;
            }
        }
        return null;
    }

}
