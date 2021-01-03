package com.anma.sb.kotlinjwt.controllers

import com.anma.sb.kotlinjwt.model.Blog
import com.anma.sb.kotlinjwt.repo.BlogRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BlogController(val blogRepository: BlogRepository) {

    @GetMapping("/rest/api/v1/blogs")
    fun getBlogs(): List<Blog> = blogRepository.findAll()
}