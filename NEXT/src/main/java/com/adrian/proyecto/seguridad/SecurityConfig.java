package com.adrian.proyecto.seguridad;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.adrian.proyecto.beans.PasswordEncoderBuilder;
import com.adrian.proyecto.services.Autenticacion;


@Configuration
@EnableWebSecurity(debug=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
    @Autowired
    private Autenticacion autenticacion;
    
    @Autowired
	private PasswordEncoderBuilder passwordEncoder;
    
    
    
    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
   
    
    
    
    /**
     * Bean que nos permite hacer las subidas de archivos al server
     * 
     * @return
     * @throws IOException
     */
    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize((1024 * 1024) * 1);
        return multipartResolver;
    }
    
    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();	//creamos un proveedor de autenticacion para encriptar las contraseñas
        provider.setPasswordEncoder(passwordEncoder.passwordEncoder());		//le decimos el sistema de codificacion de las contraseñas
        provider.setUserDetailsService(autenticacion);		//le decimos el servicio que autentica
    	
    	auth.authenticationProvider(provider);		
    }
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    	.cors().and()
        .authorizeRequests()
        	.antMatchers("/").permitAll()
        	.antMatchers("/opinion/**").authenticated()
        	.antMatchers("/admin/**").hasAuthority("ADMIN")
        	.antMatchers("/usuario/**").authenticated()
        	.antMatchers("/favoritos/**").authenticated()
	        .and()    	
        .formLogin()
            .loginPage("/login").permitAll()
            .defaultSuccessUrl("/")			
            .failureUrl("/login?error=true")
            .usernameParameter("username")		//el nombre de usuario va a estar en un campo que se llama username en /login
            .passwordParameter("password")
            .and()
        .logout()
            .permitAll()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            .and()
        .csrf().disable();	
    }
	
}
