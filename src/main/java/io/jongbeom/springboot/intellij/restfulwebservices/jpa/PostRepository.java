package io.jongbeom.springboot.intellij.restfulwebservices.jpa;

import io.jongbeom.springboot.intellij.restfulwebservices.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
