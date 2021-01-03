package com.anma.sb.kotlinjwt.config

import com.anma.sb.kotlinjwt.model.User
import com.anma.sb.kotlinjwt.repo.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*
import org.springframework.security.core.userdetails.User as SpringUser

@Service
class MyUserDetailsService(val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(name: String?): UserDetails {

        val anma = name?.let { userRepository.findUserByLogin(it) }

        return SpringUser(anma?.name, anma?.password, Collections.emptyList())

    }
}