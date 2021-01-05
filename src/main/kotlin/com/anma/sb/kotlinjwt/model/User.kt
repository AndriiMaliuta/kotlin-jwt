package com.anma.sb.kotlinjwt.model

import javax.persistence.*

@Entity
data class User(
    var username: String,
    var password: String,
    var name: String,
    var email: String,
    var roles: String,
    var bio: String,
    @OneToMany(mappedBy = "user") var blogs: MutableList<Blog>,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int
)