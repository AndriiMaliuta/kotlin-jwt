package com.anma.sb.kotlinjwt.repo

import com.anma.sb.kotlinjwt.model.Blog
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BlogRepository : JpaRepository<Blog, Int> {
}