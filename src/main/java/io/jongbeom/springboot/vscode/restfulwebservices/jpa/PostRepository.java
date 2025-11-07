package io.jongbeom.springboot.vscode.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import io.jongbeom.springboot.vscode.restfulwebservices.user.Post;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
