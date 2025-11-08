package io.jongbeom.springboot.vscode.restfulwebservices.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

//Rest API 사용
@RestController
public class HelloWorldController {

    //MessageSource 메시지의 매개변수화나 국제화 대한 지원을 통해 처리하는 전략 인터페이스
    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource){
        this.messageSource =messageSource;
    }


    @GetMapping(path = "/basicauth")
    public String basicAuthCheck(){
        return "Success";
    }

    //@RequestMapping(method = RequestMethod.GET,path = "/hello-world")   //path : value와 같음(최신버전이 path)
    @GetMapping(path = "/hello-world") //@GetMapping 사용 시 method = RequestMethod.GET 지정할 필요 없다
    public String helloWorld(){
        return "Hello World hello";
    }


    //@RequestMapping(method = RequestMethod.GET,path = "/hello-world")   //path : value와 같음(최신버전이 path)
    @GetMapping(path = "/hello-world-bean") //@GetMapping 사용 시 method = RequestMethod.GET 지정할 필요 없다
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }

    // 패스 변수
    // /user/{id}/todos/{id} => /users/2/todos/200
    // /hello-world/path-variable/{name}
    // /hello-world/path-varialbe/Ranga

    
    @GetMapping(path = "/hello-world/path-variable/{name}") //@GetMapping 사용 시 method = RequestMethod.GET 지정할 필요 없다
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World,%s",name));
    }


    @GetMapping(path = "/hello-world-Internationalized") //@GetMapping 사용 시 method = RequestMethod.GET 지정할 필요 없다
    public String helloWorldInternationalized(){
        //지역 언어 관련 객체
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Default Message",locale);
    }




}
