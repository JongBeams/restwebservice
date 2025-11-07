package io.jongbeom.springboot.vscode.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//버전에 따른 URL을 선택하여 API의 특정버전 사용 가능
@RestController
public class VersioningPersonController {

    //uri 버전 -트위터 방식
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson(){
        return  new PersonV1("종장범");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson(){
        return  new PersonV2(new Name("종범","장"));
    }

    //스프링을 통한 처리방법 - 아마존 방식
    // /person?version=1 일 때 호출
    @GetMapping(path = "/person",params = "version=1")
    public PersonV1 getFirstVersionOfPersonRequestParameter(){
        return  new PersonV1("종장범");
    }

    // /person?version=1 일 때 호출
    @GetMapping(path = "/person",params = "version=2")
    public PersonV2 getSecondVersionOfPersonRequestParameter(){
        return  new PersonV2(new Name("종범","장"));
    }

    // 커스텀 헤더 사용 버전 - 마이크로소프트
    // spring mvc api header 사용
    @GetMapping(path = "/person/header",headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonRequestHeader(){
        return  new PersonV1("종장범");
    }

    @GetMapping(path = "/person/header",headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonRequestHeader(){
        return  new PersonV2(new Name("종범","장"));
    }

    //미디어 방식 -git hub
    /*
    RFC 6838 표준 구조
    type/[tree.]subtype[+suffix]구체적인 구조
    application/vnd.company.app-v2+json
        ↓         ↓      ↓       ↓    ↓
      type     tree  vendor  product suffix
    */
    @GetMapping(path = "/person/accept",produces = "application/vnd.company.app-v1+json") //accept 로 전송된것이 무엇이든 스프링 mvc가 수신하여 이것과 비교, 값 일치시 해당 메소드 실행
    public PersonV1 getFirstVersionOfPersonAcceptHeader(){
        return  new PersonV1("종장범");
    }

    @GetMapping(path = "/person/accept",produces = "application/vnd.company.app-v2+json") //accept 로 전송된것이 무엇이든 스프링 mvc가 수신하여 이것과 비교, 값 일치시 해당 메소드 실행
    public PersonV2 getSecondVersionOfPersonAcceptHeader(){
        return  new PersonV2(new Name("종범","장"));
    }


}
