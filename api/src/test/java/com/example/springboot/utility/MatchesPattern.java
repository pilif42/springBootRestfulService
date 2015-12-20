package com.example.springboot.utility;

import java.util.regex.Pattern;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class MatchesPattern extends TypeSafeMatcher<String> {

    private final Pattern pattern;

    public MatchesPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    protected boolean matchesSafely(String item) {
        return pattern.matcher(item).matches();
    }

    @Override
    protected void describeMismatchSafely(String item, Description mismatchDescription) {
        // TODO Auto-generated method stub
        super.describeMismatchSafely(item, mismatchDescription);
    }

    @Override
    public void describeTo(Description description) {
        // TODO Auto-generated method stub
    }

    @Factory
    public static <T> Matcher<String> isADate() {
        // TODO define regex for 2015-12-18T21:13:29
        Pattern datePattern = Pattern.compile(".");
        return new MatchesPattern(datePattern);
    }

}
