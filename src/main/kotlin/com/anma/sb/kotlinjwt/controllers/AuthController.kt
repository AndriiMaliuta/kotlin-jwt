package com.anma.sb.kotlinjwt.controllers

import com.anma.sb.kotlinjwt.config.JwtUtils
import com.anma.sb.kotlinjwt.config.MyUserDetailsService
import com.anma.sb.kotlinjwt.model.User
import com.anma.sb.kotlinjwt.model.dto.UserDto
import com.anma.sb.kotlinjwt.model.ui.request.AuthUserRequest
import com.anma.sb.kotlinjwt.model.ui.request.CreateUserRequest
import com.anma.sb.kotlinjwt.model.ui.response.AuthResponse
import com.anma.sb.kotlinjwt.model.ui.response.UserRest
import com.anma.sb.kotlinjwt.repo.UserRepository
import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:3000"])
class SignupController(
    val authenticationManager: AuthenticationManager,
    val jwtUtils: JwtUtils,
    val userDetailsService: MyUserDetailsService,
    val userRepository: UserRepository
) {

    @PostMapping("/signup")
    fun signupUser(@RequestBody createUserRequest: CreateUserRequest): UserRest {
        val mapper = ModelMapper()
        val user = userRepository.save(mapper.map(createUserRequest, User::class.java))
        return mapper.map(user, UserRest::class.java)
    }

    @PostMapping("/login")
    fun login(@RequestBody authUserRequest: AuthUserRequest): ResponseEntity<AuthResponse> {

        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authUserRequest.username,
                authUserRequest.password
            )
        )
        val userDetails = userDetailsService.loadUserByUsername(authUserRequest.username)
        val jwt = jwtUtils.generateToken(userDetails)
        return ResponseEntity.ok(AuthResponse(jwt))

    }


}