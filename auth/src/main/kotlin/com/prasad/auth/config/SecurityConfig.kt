package com.prasad.auth.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@Configuration
class SecurityConfig
{
    @Bean
    fun  userDetailService():UserDetailsService
    {
        return UserInfoService()
    }

    @Bean
    fun passwordEncoder():PasswordEncoder
    {
        return BCryptPasswordEncoder()
    }


    @Bean
    fun securityFilterChain(http:HttpSecurity):SecurityFilterChain
    {
        return http.csrf().disable()
            .authorizeHttpRequests().requestMatchers("/auth/hello","/auth/add").permitAll()
            .and()
            .authorizeHttpRequests().requestMatchers("/auth/secure").authenticated()
            .and().formLogin().and().build()


    }

    @Bean
    fun authenticationProvider():AuthenticationProvider
    {
        val daoAuthenticationProvider:DaoAuthenticationProvider= DaoAuthenticationProvider()
        daoAuthenticationProvider.setUserDetailsService(userDetailService())
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder())
        return daoAuthenticationProvider
    }

}