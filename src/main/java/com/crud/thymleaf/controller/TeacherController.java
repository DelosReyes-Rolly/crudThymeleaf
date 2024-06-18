package com.crud.thymleaf.controller;

import com.crud.thymleaf.model.Teacher;
import com.crud.thymleaf.repository.TeacherRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeacherController {
   private final TeacherRepository teacherRepository;

   @Autowired
   public TeacherController(TeacherRepository teacherRepository) {
      this.teacherRepository = teacherRepository;
   }

   @GetMapping({"/teachers"})
   public String getTeachers(HttpServletRequest request, Model model) {
      List<Teacher> teachers = this.teacherRepository.findAll();
      if (teachers != null && !teachers.isEmpty()) {
         int page = 0;
         int size = 6;
         if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
         }

         if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("page")) - 1;
         }

         model.addAttribute("teachers", this.teacherRepository.findAll(PageRequest.of(page, size)));
         return "teacher/teacherIndex";
      } else {
         model.addAttribute("teachers", (Object)null);
         return "teacher/teacherIndex";
      }
   }

   @GetMapping({"/newteacher"})
   public String newTeacher(Teacher teacher) {
      return "teacher/addTeacher";
   }

   @PostMapping({"/addTeacher"})
   public String addTeacher(@Valid Teacher teacher, BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "teacher/addTeacher";
      } else {
         this.teacherRepository.save(teacher);
         return "redirect:/teachers";
      }
   }

   @GetMapping({"/viewteacher/{id}"})
   public String viewTeacher(@PathVariable("id") Long id, Model model) {
      Teacher teacher = (Teacher)this.teacherRepository.findById(id).orElseThrow(() -> {
         return new IllegalArgumentException("Invalid user Id: " + String.valueOf(id));
      });
      model.addAttribute("teacher", teacher);
      return "teacher/viewTeacher";
   }

   @GetMapping({"/editteacher/{id}"})
   public String editTeacher(@PathVariable("id") Long id, Model model) {
      Teacher teacher = (Teacher)this.teacherRepository.findById(id).orElseThrow(() -> {
         return new IllegalArgumentException("Invalid user Id: " + String.valueOf(id));
      });
      model.addAttribute("teacher", teacher);
      return "teacher/editTeacher";
   }

   @PostMapping({"/updateteacher/{id}"})
   public String updateTeacher(@PathVariable("id") Long id, @Valid Teacher teacher, BindingResult result, Model model) {
      if (result.hasErrors()) {
         teacher.setId(id);
         return "teacher/editTeacher";
      } else {
         this.teacherRepository.save(teacher);
         return "redirect:/teachers";
      }
   }

   @GetMapping({"/deletepageteacher/{id}"})
   public String deleteTeacherPage(@PathVariable("id") Long id, Model model) {
      Teacher teacher = (Teacher)this.teacherRepository.findById(id).orElseThrow(() -> {
         return new IllegalArgumentException("Invalid user Id: " + String.valueOf(id));
      });
      model.addAttribute("teacher", teacher);
      return "teacher/deleteTeacher";
   }

   @GetMapping({"/deleteteacher/{id}"})
   public String deleteTeacher(@PathVariable("id") Long id, Model model) {
      Teacher teacher = (Teacher)this.teacherRepository.findById(id).orElseThrow(() -> {
         return new IllegalArgumentException("Invalid user Id: " + String.valueOf(id));
      });
      this.teacherRepository.delete(teacher);
      return "redirect:/teachers";
   }
}
