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
        // Format is yyyy-MM-dd'T'HH:mm:ss
        String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}";
        Pattern datePattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        return new MatchesPattern(datePattern);
    }

}
