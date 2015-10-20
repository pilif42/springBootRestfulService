package com.example.springboot.toPlayWithJava8.utility;

import com.example.springboot.toPlayWithJava8.domain.Person;

public class CheckPersonEligibleForSelectiveService implements CheckPerson {
    @Override
    public boolean test(Person person) {
        return person.getGender() == Person.Sex.MALE &&
            person.getAge() >= 30 &&
            person.getAge() <= 50;
    }
}
