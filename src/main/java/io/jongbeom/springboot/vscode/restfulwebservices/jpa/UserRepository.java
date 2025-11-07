package io.jongbeom.springboot.vscode.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import io.jongbeom.springboot.vscode.restfulwebservices.user.User;

public interface UserRepository extends JpaRepository<User,Integer> {
}
