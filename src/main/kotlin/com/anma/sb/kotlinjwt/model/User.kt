package com.anma.sb.kotlinjwt.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User (var login: String,
                 var password: String,
                 var name: String,
                 var email: String,
                 @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int)