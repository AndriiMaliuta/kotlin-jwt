package com.anma.sb.kotlinjwt.controllers

import com.anma.sb.kotlinjwt.model.Blog
import com.anma.sb.kotlinjwt.repo.BlogRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/api/v1/blogs")
class BlogController(private val blogRepository: BlogRepository) {

    @GetMapping
    fun getBlogs(): List<Blog> = blogRepository.findAll()

    @GetMapping("/{id}")
    fun getBlog(@PathVariable id: Int): Blog = blogRepository.findById(id).get()

}