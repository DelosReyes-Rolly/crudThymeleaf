package com.crud.thymleaf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(
   name = "User"
)
public class User {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @Column(
      name = "student_number",
      nullable = false,
      length = 15,
      unique = true
   )
   @NotBlank(
      message = "Student number is required!"
   )
   private String student_number;
   @Column(
      name = "first_name",
      nullable = false,
      length = 225
   )
   @NotBlank(
      message = "First name is required!"
   )
   private String first_name;
   @Column(
      name = "middle_name",
      nullable = true,
      length = 225
   )
   private String middle_name;
   @Column(
      name = "last_name",
      nullable = false,
      length = 225
   )
   @NotBlank(
      message = "Last name is required!"
   )
   private String last_name;
   @Column(
      name = "email",
      nullable = false,
      length = 225
   )
   @NotBlank(
      message = "Email is required!"
   )
   private String email;

   public User() {
   }

   public User(@NotBlank(message = "Student number is required!") String student_number, @NotBlank(message = "First name is required!") String first_name, @NotBlank(message = "Last name is required!") String last_name, @NotBlank(message = "Email is required!") String email) {
      this.student_number = student_number;
      this.first_name = first_name;
      this.last_name = last_name;
      this.email = email;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getStudent_number() {
      return this.student_number;
   }

   public void setStudent_number(String student_number) {
      this.student_number = student_number;
   }

   public String getFirst_name() {
      return this.first_name;
   }

   public void setFirst_name(String first_name) {
      this.first_name = first_name;
   }

   public String getMiddle_name() {
      return this.middle_name;
   }

   public void setMiddle_name(String middle_name) {
      this.middle_name = middle_name;
   }

   public String getLast_name() {
      return this.last_name;
   }

   public void setLast_name(String last_name) {
      this.last_name = last_name;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
}
