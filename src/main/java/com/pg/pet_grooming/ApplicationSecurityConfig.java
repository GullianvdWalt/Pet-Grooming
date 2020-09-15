
// Created By Gullian Van Der Walt
        
package com.pg.pet_grooming;

// Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Override 
    protected void configure(HttpSecurity http)throws Exception{
       http
        .csrf().disable()
	.authorizeRequests()
	.antMatchers("/login", "/resources/**", "/css/**", "/fonts/**", "/images/**","/Icons/**","/icons/**").permitAll()
        .antMatchers("/register", "/resources/**", "/css/**", "/fonts/**", "/images/**","/Icons/**","/icons/**","/js/**").permitAll()
        .antMatchers("/users/addNew").permitAll()
	.anyRequest().authenticated()
	.and()
	.formLogin()
	.loginPage("/login").permitAll()
	.and()
	.logout().invalidateHttpSession(true) // set invalidation state when logout
	.clearAuthentication(true)
	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	.logoutSuccessUrl("/login").permitAll()
        .deleteCookies("JSESSIONID")
        .and()
        .exceptionHandling()
        .accessDeniedPage("/403"); 
        
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    
    // Inject UserDetailsService
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean 
    public AuthenticationProvider authenticationProvider(){
        
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        
        provider.setUserDetailsService(userDetailsService);
        
        // Constructor Overloading
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        
        return provider;
    }
}
