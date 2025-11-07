package io.jongbeom.springboot.vscode.restfulwebservices.user;

//WebMvcLinkBuilder에 있는 모든 메소드에 대한 정적 임포트
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service){
        this.service=service;
    }

    //GET /users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    //EntityModel 객체 모델
    //WebMvcLinkBuilder
    //GET /users/{id}
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){//EntityModel 도메인 객체를 래핑하여 링크를 추가
        User user =service.findOne(id);

        //페이지 예외 처리
        if(user==null)
            throw new UserNotFoundException("id:"+id);

        //래퍼(Wrapper) 객체로 변환
        EntityModel<User> entityModel =EntityModel.of(user);
        // spring mvc 컨트롤러를 가리키는 링크 인스턴스의 구축을 용이하게 하는 빌더
        //
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()) //현재 컨트롤러 프록시 인스턴스 생성, 실제 실행되지 않고, 메소드의 매핑 정보만 추출
                .retrieveAllUsers()); // retrieveAllUsers 메소드를 "가짜로" 호출, @RequestMapping 정보(URL 경로)를 가져온다.
        entityModel.add(link.withRel("all-users")); //생성된 링크에 "all-users"라는 관계(relation) 이름을 부여하여 entityModel에 추가

        return entityModel;
    }

    //GET /users/{id}
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        service.deleteById(id);
    }




    //POST /users
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        //@Valid 프로퍼티나 메소드인자 반환 타입의 유효성을 확인하기 위해 표시
        User savedUser =service.save(user);
        // /user/4 => /user/{id} user.getId
        URI location= ServletUriComponentsBuilder   // spring의 http 요청정보 기반 URI를 쉽게 만들어주는 헬퍼 클래스
                .fromCurrentRequest()               // 현재 요청 URL을 가져온다.
                .path("/{id}")                      // 경로 추가
                .buildAndExpand(savedUser.getId())  // {id} 변수를 실제값으로 치환 savedUser.getId값
                .toUri();                           // URI 객체로 변환
        //스프링 프레임워크 클래스, http 패키지 URI loaction 헤더를 받는다
        return ResponseEntity.created(location).build();
    }



}
