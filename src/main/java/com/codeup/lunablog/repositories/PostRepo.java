package com.codeup.lunablog.repositories;

import com.codeup.lunablog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {

}
