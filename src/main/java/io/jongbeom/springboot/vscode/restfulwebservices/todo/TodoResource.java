package io.jongbeom.springboot.vscode.restfulwebservices.todo;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class TodoResource {

    private TodoRepository todoRepository;

    public TodoResource(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username){
        return todoRepository.findByUsername(username);
    }


    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String username,@PathVariable int id){
        return todoRepository.findById(id);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username,@PathVariable int id){
        //JPA Repository의 메서드로, 해당 ID의 Todo를 데이터베이스에서 삭제
        todoRepository.deleteById(id);
        //성공 시 204 응답
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Object> updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo) {
        todo.setId(id);
        todo.setUsername(username);
        Todo savetodo =todoRepository.save(todo);
                URI location= ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savetodo.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }


    @PostMapping("/users/{username}/todos")
    public ResponseEntity<Object> createTodo(@PathVariable String username, @RequestBody Todo todo) {
        todo.setUsername(username);
        Todo savetodo =todoRepository.save(todo);
                URI location= ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savetodo.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    

}
