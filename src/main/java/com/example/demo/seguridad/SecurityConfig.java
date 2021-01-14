package com.example.demo.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserDetailsService userDetailsService;
	
	//Configuración inicial para que los no conectados solo accedan a registro y login
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()
	        .antMatchers(
                    "/","/index",
                    "/js/**",
                    "/css/**",
                    "/vendor/**",
                    "/images/**",
                    "/registro",
                    "/login",
                    "/signup",
                    "/fragments/**",
                    "/webjars/**").permitAll()
	        	.anyRequest().authenticated()
	            .and()
	     .formLogin()
           .loginPage("/login")
           .loginProcessingUrl("/user/logueado")
           .usernameParameter("nombre")
          .successHandler((AuthenticationSuccessHandler) myAuthenticationSuccessHandler())
          .permitAll()
      .and()
      .logout()
          .invalidateHttpSession(true)
           .deleteCookies("JSESSIONID")
           .clearAuthentication(true)
          .logoutUrl("/logout")
          .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
          .logoutSuccessUrl("/login?logout")
          .permitAll();
}
	
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
 
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
    }
    
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
    @Bean
    public AuthenticationSuccessHandlerImpl myAuthenticationSuccessHandler(){
        return new AuthenticationSuccessHandlerImpl();
    }
}
