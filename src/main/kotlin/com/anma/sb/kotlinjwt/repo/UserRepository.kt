package com.anma.sb.kotlinjwt.repo

import com.anma.sb.kotlinjwt.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Int> {

    fun findUserByLogin(login: String): User
}