package com.example.intelGraphicsApi.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {
    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeHttpRequests().
                requestMatchers("", "")
                .authenticated().and().
                authorizeHttpRequests().
                anyRequest().permitAll()
                .and().exceptionHandling().authenticationEntryPoint((request, response, authException) ->
                        response.sendError(HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.getReasonPhrase()));
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(tokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
     PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    FilterConfiguration tokenFilter(){
        return new FilterConfiguration();
    }
}
