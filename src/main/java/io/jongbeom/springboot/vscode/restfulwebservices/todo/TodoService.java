package io.jongbeom.springboot.vscode.restfulwebservices.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

//정적 변수 리스트로 우선 저장
@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    private  static int todosCount =0;

    static {
        todos.add(new Todo(todosCount++,"jongbeom","자바 백엔드 학습종료", LocalDate.now().plusWeeks(1),false));
        todos.add(new Todo(todosCount++,"jongbeom","풀스텍 프로젝트 완성", LocalDate.now().plusMonths(2),false));
        todos.add(new Todo(todosCount++,"jongbeom","이력서 면접 준비 완료", LocalDate.now().plusMonths(3),false));
    }

    public List<Todo> findByUserName(String username){
        Predicate<? super Todo> predicate   // 모든 Todo의 유저명이 조건에 매칭되는지 확인 후 조건에 매칭되는 Todo만 호출
                = todo -> todo.getUsername().equalsIgnoreCase(username) ; //equalsIgnoreCase : 대소문자 무시 비교
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String username,String description,LocalDate targetDate,boolean done){
        Todo todo= new Todo(todosCount++,username,description,targetDate,done);
        todos.add(todo);
    }

    public void deleteById(int id){
        Predicate<? super Todo> predicate   // 모든 Todo의 id가 조건에 매칭되는지 확인 후 조건에 매칭되는 Todo만 호출
                = todo -> todo.getId() == id; //람다 사용
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate
                = todo -> todo.getId() == id; //람다 사용
        Todo todo=todos.stream().filter(predicate).findFirst().get(); //첫번째 결과 값 가져오기
        return todo;
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }



}