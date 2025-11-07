package io.jongbeom.springboot.intellij.restfulwebservices.filitering;

import com.fasterxml.jackson.annotation.JsonFilter;

//@JsonIgnoreProperties({"field1","field2"}) //JsonIgnore를 클래스 단위로 지정
@JsonFilter("SomeBeanFilter")   // 동적 필터 ID값등록
public class SomeBean {
    private String field1;

    //정적 필터링
    //@JsonIgnore
    private String field2;

    private String field3;

    public SomeBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }

    @Override
    public String toString() {
        return "SomeBean{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                '}';
    }
}
