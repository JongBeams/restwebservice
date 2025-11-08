package io.jongbeom.springboot.vscode.restfulwebservices.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Integer> {

     public List<Todo> findByUsername(String username);

     //public Todo findById(int id);

     
}
