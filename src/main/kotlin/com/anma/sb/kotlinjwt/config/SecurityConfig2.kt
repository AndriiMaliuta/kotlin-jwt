package com.anma.sb.kotlinjwt.config

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.servlet.invoke

@EnableWebSecurity
class SecurityConfig2 : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http {
            authorizeRequests {
                authorize("/my-anon-page", permitAll)
                authorize( anyRequest, authenticated)
            }
            oauth2Login {  }
        }

    }

}