package com.anma.sb.kotlinjwt.config

import org.springframework.core.env.Environment
import io.jsonwebtoken.Claims
import java.util.*
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class JwtUtils(val environment: Environment) {

    fun extractUsername(token: String?): String? {
        return extractAllClaims(token)?.subject
    }

    fun extractExpirationDate(token: String?): Date? {
        return extractAllClaims(token)?.expiration
    }

//    fun extractClaims(token: String?, claimsResolver: Function<Claims?, it>): String {
//        val claims: Claims? = extractAllClaims(token)
//        return claimsResolver.apply(claims)
//    }

    private fun extractAllClaims(token: String?): Claims? {
        return Jwts.parser()
            .setSigningKey(environment.getProperty("jwt.secret"))
            .parseClaimsJws(token)
            .body
    }

    private fun isTokenExpired(token: String): Boolean? {
        return extractExpirationDate(token)?.before(Date())
    }

    fun generateToken(userDetails: UserDetails): String {
        return creatToken(hashMapOf(), userDetails.username)
    }

    private fun creatToken(claims: Map<String, Unit>, subject: String): String {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + (environment.getProperty("jwt.expiration")?.toInt() ?: 600000)))
            .signWith(SignatureAlgorithm.HS256, environment.getProperty("jwt.secret")).compact()
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return (username.equals(userDetails.username) && !isTokenExpired(token)!!)
    }


}