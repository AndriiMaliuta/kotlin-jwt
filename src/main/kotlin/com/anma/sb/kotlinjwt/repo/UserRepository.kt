package com.anma.sb.kotlinjwt.repo

import com.anma.sb.kotlinjwt.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {

    fun findUserByUsername(username: String?): User
}