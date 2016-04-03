package com.example.springboot.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  public static final String INBOUND_SECURED_ENDPOINTS_ROLE_NAME = "TRUSTED_USER";
  public static final String INBOUND_SECURED_ENDPOINTS_ROLE = "ROLE_" + INBOUND_SECURED_ENDPOINTS_ROLE_NAME;


  @Value("${inbound.secured.endpoints.username}")
  private String inboundSecuredEndpointsUsername;

  @Value("${inbound.secured.endpoints.password}")
  private String inboundSecuredEndpointsPassword;

  @Value("${spring.profiles.active:}")
  private String profile;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers("/**").permitAll().anyRequest().authenticated()
            .and()
            .httpBasic()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().csrf().disable().headers().cacheControl().disable();
  }

  @Autowired
  public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
            .withUser(inboundSecuredEndpointsUsername)
            .password(inboundSecuredEndpointsPassword)
            .roles(INBOUND_SECURED_ENDPOINTS_ROLE_NAME);
  }
}

