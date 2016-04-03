package com.example.springboot.toPlayWithJava8.domain;

import com.example.springboot.toPlayWithJava8.utility.CheckPerson;
import com.example.springboot.toPlayWithJava8.utility.CheckPersonEligibleForSelectiveService;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class LambdaExpressionsExamples {

  public static void main(String[] args) {
    Person personA = new Person();
    personA.setGender(Person.Sex.MALE);
    LocalDate birthday = LocalDate.of(1975, Month.OCTOBER, 7);
    personA.setBirthday(birthday);
    personA.setName("JoeBlog");
    personA.setEmailAddress("jblog@gmail.com");

    Person personB = new Person();
    personB.setGender(Person.Sex.MALE);
    birthday = LocalDate.of(2000, Month.OCTOBER, 7);
    personB.setBirthday(birthday);
    personB.setName("JuniorBlog");
    personB.setEmailAddress("juniorblog@gmail.com");

    List<Person> myPersonsList = new ArrayList<>();
    myPersonsList.add(personA);
    myPersonsList.add(personB);

        /*
        * Case where you want to treat functionality as method argument
         */
    System.out.println("Result with traditional method: ");
    printPersons(myPersonsList, new CheckPersonEligibleForSelectiveService());

    System.out.println("Result with lambda expression: ");
    printPersons(
            myPersonsList,
            (Person p) -> p.getGender() == Person.Sex.MALE
                    && p.getAge() >= 30
                    && p.getAge() <= 50
    );
  }

  public static void printPersons(List<Person> roster, CheckPerson tester) {
    for (Person p : roster) {
      if (tester.test(p)) {
        p.printPerson();
      }
    }
  }
}
