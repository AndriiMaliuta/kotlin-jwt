package com.anma.sb.kotlinjwt.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Blog (@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int, var title: String, var body: String)