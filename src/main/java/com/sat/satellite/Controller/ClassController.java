package com.sat.satellite.Controller;

import com.sat.satellite.Database.Entity.ClassEntity;
import com.sat.satellite.Database.Entity.StudentClass;
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
public class ClassController {

    @Autowired
    ClassRepository classRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentClassRepository studentClassRepository;

    @PostMapping(value = {"/api/class/add"})
    public String add(HttpServletRequest request, Model model) {
        try {

            //Get parameter from form
            String name=request.getParameter("name");
            String major=request.getParameter("major");

            //Validation fields
            Validation.validateString(name);
            Validation.validateString(major);
            if (!classRepository.findByClassName(name).isEmpty()) throw new Exception("Name already exits, try another one");

            //Create new class
            ClassEntity classEntity=new ClassEntity();
            classEntity.setName(name);
            classEntity.setMajor(major);
            classEntity.setDatecreated(null);

            //Save to database
            classRepository.save(new ClassEntity(4,name,major,null));

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("log_AJAX", e.getMessage());
            return "AJAX/failure";
        }
        return "AJAX/success";
    }

    @PostMapping(value = {"/api/class/edit"})
    public String edit(HttpServletRequest request, Model model) {
        try {

            //Get parameter from form
            int id=Integer.parseInt(request.getParameter("id"));
            String name=request.getParameter("name");
            String major=request.getParameter("major");
            //Validation fields
            Validation.validateString(name);
            Validation.validateString(major);
            if (!classRepository.findByClassName(name).isEmpty()) throw new Exception("Name already exits, try another one");

            //Edit class infomation
            ClassEntity classEntity=classRepository.findById(id).orElse(null);
            assert classEntity != null;
            classEntity.setName(name);
            classEntity.setMajor(major);
            classEntity.setDatecreated(null);

            //Save to database
            classRepository.save(classEntity);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("log_AJAX", e.getMessage());
            return "AJAX/failure";
        }
        return "AJAX/success";
    }

    @PostMapping(value = {"/api/class/delete"})
    public String delete(HttpServletRequest request, Model model) {
        try {

            //Get parameter from form
            String id=request.getParameter("id");

            //Delete class
            classRepository.deleteById(Integer.parseInt(id));

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("log_AJAX", e.getMessage());
            return "AJAX/failure";
        }
        return "AJAX/success";
    }

    @PostMapping(value = {"/api/class/addStudent"})
    public String addStudentToClass(HttpServletRequest request, Model model) {
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

    @PostMapping(value = {"/api/class/deleteStudent"})
    public String deleteStudentInClass(HttpServletRequest request, Model model) {
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
