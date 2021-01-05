package com.anma.sb.kotlinjwt.config

import com.anma.sb.kotlinjwt.repo.UserRepository
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User as SpringUser

@Service
class MyUserDetailsService(val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(name: String?): UserDetails {

        val anma = userRepository.findUserByUsername(name)

        val rolesAndAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(anma.roles)

//        for (role in anma.roles.split(",")) {
//            rolesAndAuthoritis.add(SimpleGrantedAuthority(role))
//        }

        return SpringUser(anma.username, anma.password, rolesAndAuthorities)

    }
}