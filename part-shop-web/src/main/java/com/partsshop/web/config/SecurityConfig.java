package com.partsshop.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.partsshop.web.security.CustomUserDetailService;
import com.partsshop.web.security.JwtAuthenticationEntryPoint;
import com.partsshop.web.security.JwtAuthenticationFilter;
import com.partsshop.web.security.RestAuthenticationProvider;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired 
	private CustomUserDetailService customUserDetailService ; 
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandelr; 
	
	
//	@Bean
//	public JwtAuthenticationFilter jwtAuthenticationFilter() {
//		return new JwtAuthenticationFilter() ; 
//	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	     //System.out.println(customUserDetailService);
		RestAuthenticationProvider provider = new RestAuthenticationProvider() ; 
		provider.setUserDetailsService(customUserDetailService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		 authenticationManagerBuilder.
		 authenticationProvider(provider) ; 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable()
		 .exceptionHandling()
	      	.authenticationEntryPoint(unauthorizedHandelr)
	     .and()
         .authorizeRequests()
             .antMatchers("/users/login", "/users/signup", "/users/logout", "/assets/**").permitAll()
             .anyRequest().authenticated();
		
		

	}

}