package com.anma.sb.kotlinjwt.model.ui.request

data class CreateUserRequest(var name: String, var login: String, var password: String, var email: String)