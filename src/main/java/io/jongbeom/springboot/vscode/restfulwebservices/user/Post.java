package io.jongbeom.springboot.vscode.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 10)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계 ,fetch 동일한 쿼리에서 게시물과 사용자의 세부 정보를 검색하려고 한다면 EAGER fetch를 요청
    // 게시물 세부정보과 함께 가져오도록 요청하면 사용자 세부정보도 같이 가져오게 된다.
    @JsonIgnore
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post(Integer id, String description) {
        this.id = id;
        this.description = description;
    }


    public Post() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
