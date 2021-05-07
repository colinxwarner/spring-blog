package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String title); // select * from ads where title = ?
    Post findFirstByTitle(String title); // select * from ads where title = ? limit 1

    // The following method shows you how to use named parameters in a HQL custom query:
    @Query("from Post p where p.title like %:term%")
    List<Post> searchByTitleLike(@Param("term") String term);

    // The following method is equivalent to the built in `getOne` method, there's no need to create this example
    @Query("from Post p where p.id = ?1")
    Post getPostById(long id);
}