package io.jongbeom.springboot.vscode.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    //JPA/Hibernate >Database
    //UserDaoService 정적 리스트로 우선 db 대체 사용

    private static List<User> users =new ArrayList<>();

    private static int usersCount=0;

    static {
        users.add(new User(usersCount++,"Adam", LocalDate.now().minusMonths(30)));
        users.add(new User(usersCount++,"Eve", LocalDate.now().minusMonths(25)));
        users.add(new User(usersCount++,"Jim", LocalDate.now().minusMonths(20)));
    }

   ;

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public User findOne(int id){
        Predicate<? super User> predicate= user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);//else 반환값 함수형으로
    }

    public void deleteById(int id){
        Predicate<? super User> predicate= user -> user.getId().equals(id);
        users.removeIf(predicate);
    }



//    public List<User> findAll();
//    public User save(User user);
//    public User findOne(int id);

}
