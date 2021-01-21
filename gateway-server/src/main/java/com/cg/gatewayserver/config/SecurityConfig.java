/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-19 23:10:56
 * @modify date 2021-01-19 23:10:56
 * @desc Security Configuration
 */
package com.cg.gatewayserver.config;

import java.util.Arrays;

import com.cg.gatewayserver.security.CustomAuthenticationEntryPoint;
import com.cg.gatewayserver.security.JwtAuthorizationFilter;
import com.cg.gatewayserver.security.JwtProvider;
import com.cg.gatewayserver.services.implementation.JwtUserDetailsServiceImpl;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
  
  private JwtUserDetailsServiceImpl userDetailsService;
  private BCryptPasswordEncoder passwordEncoder;
  private JwtProvider jwtProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      // Makes header as default with origin as *
      .cors()
      .configurationSource(request -> {
          CorsConfiguration source = new CorsConfiguration();
          source.applyPermitDefaultValues();
          // .applyPermitDefaultValues(); only allows GET, HEAD, POST
          source.setAllowedMethods(Arrays.asList("GET", "HEAD", "POST", "DELETE", "PUT"));
        return source;
      }).and() // Required for accessing prpotected routes
      .csrf().disable()
      .authorizeRequests().antMatchers("/auth-service/**", "/actuator/**", "/**/h2/**", "/**/swagger*/**", "/**/v2/api-docs").permitAll()
      .antMatchers(HttpMethod.GET, "/product-service/**").permitAll()
      .anyRequest().authenticated()
      .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
    http.addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtProvider, userDetailsService));
    http.headers().frameOptions().disable();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
  }

  public AuthenticationEntryPoint authenticationEntryPoint() {
    return new CustomAuthenticationEntryPoint();
  }
}
