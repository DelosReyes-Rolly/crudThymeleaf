package com.crud.thymleaf.controller;

import com.crud.thymleaf.model.User;
import com.crud.thymleaf.repository.UserRepository;
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
public class UserController {
   private final UserRepository userRepository;

   @Autowired
   public UserController(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @GetMapping({"/"})
   public String getUsers(HttpServletRequest request, Model model) {
      List<User> users = this.userRepository.findAll();
      if (users != null && !users.isEmpty()) {
         int page = 0;
         int size = 6;
         if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
         }

         if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("page")) - 1;
         }

         model.addAttribute("users", this.userRepository.findAll(PageRequest.of(page, size)));
         return "index";
      } else {
         model.addAttribute("users", (Object)null);
         return "index";
      }
   }

   @GetMapping({"/newstudent"})
   public String newStudent(User user) {
      return "addStudent";
   }

   @PostMapping({"/addStudent"})
   public String addStudent(@Valid User user, BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "addStudent";
      } else {
         this.userRepository.save(user);
         return "redirect:/";
      }
   }

   @GetMapping({"/view/{id}"})
   public String viewStudent(@PathVariable("id") Long id, Model model) {
      User user = (User)this.userRepository.findById(id).orElseThrow(() -> {
         return new IllegalArgumentException("Invalid user Id: " + String.valueOf(id));
      });
      model.addAttribute("user", user);
      return "viewStudent";
   }

   @GetMapping({"/edit/{id}"})
   public String editStudent(@PathVariable("id") Long id, Model model) {
      User user = (User)this.userRepository.findById(id).orElseThrow(() -> {
         return new IllegalArgumentException("Invalid user Id: " + String.valueOf(id));
      });
      model.addAttribute("user", user);
      return "editStudent";
   }

   @PostMapping({"/update/{id}"})
   public String updateStudent(@PathVariable("id") Long id, @Valid User user, BindingResult result, Model model) {
      if (result.hasErrors()) {
         user.setId(id);
         return "editStudent";
      } else {
         this.userRepository.save(user);
         return "redirect:/";
      }
   }

   @GetMapping({"/deletepage/{id}"})
   public String deleteStudentPage(@PathVariable("id") Long id, Model model) {
      User user = (User)this.userRepository.findById(id).orElseThrow(() -> {
         return new IllegalArgumentException("Invalid user Id: " + String.valueOf(id));
      });
      model.addAttribute("user", user);
      return "deleteStudent";
   }

   @GetMapping({"/delete/{id}"})
   public String deleteStudent(@PathVariable("id") Long id, Model model) {
      User user = (User)this.userRepository.findById(id).orElseThrow(() -> {
         return new IllegalArgumentException("Invalid user Id: " + String.valueOf(id));
      });
      this.userRepository.delete(user);
      return "redirect:/";
   }
}
