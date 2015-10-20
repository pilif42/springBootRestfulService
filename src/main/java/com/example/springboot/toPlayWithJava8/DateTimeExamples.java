package com.example.springboot.toPlayWithJava8;

import java.time.Instant;
import java.time.LocalDate;

public class DateTimeExamples {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("Now is " + today);

        Instant maintenant = Instant.now();
        System.out.println("maintenant is " + maintenant);

        long currentTime = System.currentTimeMillis();
        System.out.println("currentTime is " + currentTime);
    }
}
