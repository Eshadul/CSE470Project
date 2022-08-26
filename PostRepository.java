package com.programming.techie.Blogers.Point.repository;

import com.programming.techie.Blogers.Point.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
