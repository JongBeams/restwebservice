package io.jongbeom.springboot.intellij.restfulwebservices.jpa;

import io.jongbeom.springboot.intellij.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
