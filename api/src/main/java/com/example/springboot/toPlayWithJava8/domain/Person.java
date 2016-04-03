package com.example.springboot.toPlayWithJava8.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Data
public class Person {

  String name;
  LocalDate birthday;
  Sex gender;
  String emailAddress;

  public int getAge() {
    LocalDate today = LocalDate.now();
    return Period.between(birthday, today).getYears();
  }

  public void printPerson() {
    System.out.println(String.format("%s - %s - %s - %s - %s", name, gender, birthday, getAge(), emailAddress));
  }

  public enum Sex {
    MALE, FEMALE
  }
}
