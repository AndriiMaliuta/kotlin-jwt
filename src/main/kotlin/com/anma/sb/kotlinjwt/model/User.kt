package com.anma.sb.kotlinjwt.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User (var username: String,
                 var password: String,
                 var name: String,
                 var email: String,
                 var roles: String,
                 var bio: String,
                 @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int)