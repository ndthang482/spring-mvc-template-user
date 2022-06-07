package com.sat.satellite.Controller;

import com.sat.satellite.Database.Entity.ClassEntity;
import com.sat.satellite.Database.Entity.StudentEntity;
import com.sat.satellite.Database.Repository.ClassRepository;
import com.sat.satellite.Database.Repository.StudentClassRepository;
import com.sat.satellite.Database.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController implements ErrorController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentClassRepository studentClassRepository;
    @Autowired
    ClassRepository classRepository;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        try {
            model.addAttribute("StudentList",studentRepository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("InternalLog", e.toString());
            return "Common/error_internal";
        }
        return "home";
    }

    @GetMapping(value = {"/classes"})
    public String classes(Model model) {
        try {
            model.addAttribute("ClassesList",classRepository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("InternalLog", e.toString());
            return "Common/error_internal";
        }
        return "classes";
    }

    @RequestMapping(value = "/student/{student_name}")
    public String getClassesStudentJoined(@PathVariable("student_name") String student_name,HttpServletRequest request, Model model) {
        try {
            StudentEntity student=studentRepository.findByStudentName(student_name).get(0);
            model.addAttribute("StudentSelectedClassList",studentClassRepository.findByStudentID(student.getId()));
            model.addAttribute("StudentSelected",student);
            model.addAttribute("ClassList",classRepository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return "Common/error_404";
        }
        return "learning";
    }

    @GetMapping(value = "/classes/{class_name}")
    public String getStudentsInClasses(@PathVariable("class_name") String class_name, Model model) {
        try {
            ClassEntity classEntity=classRepository.findByClassName(class_name).get(0);
            model.addAttribute("ClassSelectedStudentList",studentClassRepository.findByClassID(classEntity.getId()));
            model.addAttribute("ClassSelected",classEntity);
            model.addAttribute("StudentList",studentRepository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return "Common/error_404";
        }
        return "room";
    }

    @RequestMapping(value = {"/about"})
    public String about() {
        return "about";
    }

    @RequestMapping("/error")
    public String handleError() {
        return "Common/error_404";
    }

    @RequestMapping("/error/internal")
    public String handleInternalError(Model model) {
        model.addAttribute("InternalLog", "satellite.not_bug.go_to_error_page.error");
        return "Common/error_internal";
    }

}
