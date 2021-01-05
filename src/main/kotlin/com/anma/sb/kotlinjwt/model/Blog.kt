package com.anma.sb.kotlinjwt.model

import java.util.*
import javax.persistence.*

@Entity
data class Blog(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int,
    var title: String,
    var body: String,
    @ManyToOne var user: User
)