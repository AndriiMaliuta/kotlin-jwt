package com.anma.sb.kotlinjwt.config

import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component

class JwtRequestFilter(val userDetailsService: MyUserDetailsService, val jwtUtils: JwtUtils) : OncePerRequestFilter() {

    val logger = LoggerFactory.getLogger(JwtRequestFilter::class.java)

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        val authHeader = request.getHeader("Authorization")

        var username: String? = null
        var jwt: String? = null

        if (authHeader != null && authHeader.startsWith("Bearer")) {
            jwt = authHeader.substring(7)
            username = jwtUtils.extractUsername(jwt)
        }

        if (username != null && SecurityContextHolder.getContext().authentication == null) {

            val userDetails = userDetailsService.loadUserByUsername(username)

            if (jwtUtils.validateToken(jwt!!, userDetails)) {

                logger.info(">>>>>>>>>>>> authorities: ${userDetails.authorities}")
                val token = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                token.details = WebAuthenticationDetailsSource().buildDetails(request)

                SecurityContextHolder.getContext().authentication = token
            }
        }

        filterChain.doFilter(request, response)

    }
}