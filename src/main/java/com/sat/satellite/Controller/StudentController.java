package com.sat.satellite.Controller;

import com.sat.satellite.Database.Entity.StudentClass;
import com.sat.satellite.Database.Entity.StudentEntity;
import com.sat.satellite.Database.Repository.ClassRepository;
import com.sat.satellite.Database.Repository.StudentClassRepository;
import com.sat.satellite.Database.Repository.StudentRepository;
import com.sat.satellite.Validator.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassRepository classRepository;

    @Autowired
    StudentClassRepository studentClassRepository;

    public StudentController(StudentRepository studentRepository, ClassRepository classRepository, StudentClassRepository studentClassRepository) {
        this.studentRepository = studentRepository;
        this.classRepository = classRepository;
        this.studentClassRepository = studentClassRepository;
    }

    @PostMapping(value = {"/api/student/add"})
    public String add(HttpServletRequest request, Model model) {
        try {

            //Get parameter from form
            String name=request.getParameter("name");
            String phone=request.getParameter("phone");
            String email=request.getParameter("email");
            String game=request.getParameter("game");

            //Validation fields
            Validation.validateString(name);
            Validation.validateString(phone);
            Validation.validateString(email);
            Validation.validateString(game);
            if (!studentRepository.findByStudentName(name).isEmpty()) throw new Exception("Name already exits, try another one");

            //Create new student
            StudentEntity studentEntity=new StudentEntity();
            studentEntity.setName(name);
            studentEntity.setPhone(phone);
            studentEntity.setEmail(email);
            studentEntity.setGame(game);

            //Save to database
            studentRepository.save(studentEntity);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("log_AJAX", e.getMessage());
            return "AJAX/failure";
        }
        return "AJAX/success";
    }

    @PostMapping(value = {"/api/student/edit"})
    public String edit(HttpServletRequest request, Model model) {
        try {
            //Get parameter from form
            Integer id=Integer.parseInt(request.getParameter("id"));
            String name=request.getParameter("name");
            String phone=request.getParameter("phone");
            String email=request.getParameter("email");
            String game=request.getParameter("game");

            //Validation fields
            Validation.validateString(name);
            Validation.validateString(phone);
            Validation.validateString(email);
            Validation.validateString(game);
            if (!studentRepository.findByStudentName(name).isEmpty()) throw new Exception("Name already exits, try another one");

            //Edit student
            StudentEntity studentEntity=studentRepository.findById(id).orElse(null);
            assert studentEntity != null;
            studentEntity.setName(name);
            studentEntity.setPhone(phone);
            studentEntity.setEmail(email);
            studentEntity.setGame(game);

            //Save to database
            studentRepository.save(studentEntity);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("log_AJAX", e.getMessage());
            return "AJAX/failure";
        }
        return "AJAX/success";
    }

    @PostMapping(value = {"/api/student/delete"})
    public String delete(HttpServletRequest request, Model model) {
        try {

            //Get parameter from form
            String id=request.getParameter("id");

            //Delete student
            studentRepository.deleteById(Integer.parseInt(id));

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("log_AJAX", e.getMessage());
            return "AJAX/failure";
        }
        return "AJAX/success";
    }

    @PostMapping(value = {"/api/learning/add"})
    public String addClassForStudent(HttpServletRequest request, Model model) {
        try {

            //Get parameter from form
            int studentId=Integer.parseInt(request.getParameter("studentId"));
            int classId=Integer.parseInt(request.getParameter("classId"));

            //Validation fields
            if (!studentClassRepository.findByStudentIDAndClassID(studentId,classId).isEmpty()) throw new Exception("Student had joined this classes already !");

            //Add class to student learnings
            StudentClass studentClass=new StudentClass();
            studentClass.setDemo1StudentByStudentid(studentRepository.findById(studentId).orElse(null));
            studentClass.setDemo1ClassByClassid(classRepository.findById(classId).orElse(null));

            //Save to database
            studentClassRepository.save(studentClass);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("log_AJAX", e.getMessage());
            return "AJAX/failure";
        }
        return "AJAX/success";
    }

    @PostMapping(value = {"/api/learning/delete"})
    public String deleteLearning(HttpServletRequest request, Model model) {
        try {

            //Get parameter from form
            String id=request.getParameter("id");

            //Delete student
            studentClassRepository.deleteById(Integer.parseInt(id));

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("log_AJAX", e.getMessage());
            return "AJAX/failure";
        }
        return "AJAX/success";
    }
}
