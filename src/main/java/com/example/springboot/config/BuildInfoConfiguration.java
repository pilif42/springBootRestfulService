package com.example.springboot.config;


import com.example.springboot.BuildInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:build.properties")
public class BuildInfoConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public BuildInfo buildInfo() {
        return new BuildInfo(
            env.getProperty("build.info.sha", "UNKNOWN"),
            env.getProperty("build.info.date", "UNKNOWN"),
            env.getProperty("build.info.group", "UNKNOWN"),
            env.getProperty("build.info.name", "UNKNOWN"),
            env.getProperty("build.info.version", "UNKNOWN")
        );
    }
}
