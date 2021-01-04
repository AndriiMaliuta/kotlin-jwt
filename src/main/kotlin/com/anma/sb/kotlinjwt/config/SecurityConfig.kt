package com.anma.sb.kotlinjwt.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class SecurityConfig(val userDetailsService: MyUserDetailsService, val jwtRequestFilter: JwtRequestFilter) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(userDetailsService)

    }

    override fun configure(http: HttpSecurity?) {
        http {
            cors {  }
            csrf { disable() }
//            formLogin {  }
//            httpBasic {}
            authorizeRequests {
                authorize("/resources/**", permitAll)
                authorize("/signup", permitAll)
                authorize("/login", permitAll)
                authorize("/rest/**", hasAuthority("READ"))
                authorize( anyRequest, authenticated)
            }
            exceptionHandling {  }
            sessionManagement { sessionConcurrency { SessionCreationPolicy.STATELESS } }
        }
        http {
            addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
        }

    }

    @Bean
    fun passwordENcoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}