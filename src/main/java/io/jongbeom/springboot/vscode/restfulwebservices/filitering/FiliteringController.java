package io.jongbeom.springboot.intellij.restfulwebservices.filitering;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FiliteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue filtering(){
        SomeBean someBean = new SomeBean("Value1","Value2","Value3");
        //동적 필터링 방식
        //Jackson이 JSON 변환 시 추가 설정(필터링 등)을 적용할 수 있도록 객체를 래핑
        MappingJacksonValue mappingJacksonValue =new MappingJacksonValue(someBean);
        //어떤 필드를 포함/제외할지 결정하는 필터 생성
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
        //필터에 이름을 붙여서 등록
        FilterProvider filters= new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        //생성한 필터를 MappingJacksonValue에 적용
        mappingJacksonValue.setFilters(filters);
        
        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList(){

        List<SomeBean> beanList = Arrays.asList(new SomeBean("Value1","Value2","Value3"),
                new SomeBean("Value4","Value5","Value6"));

        //Jackson이 JSON 변환 시 추가 설정(필터링 등)을 적용할 수 있도록 객체를 래핑
        MappingJacksonValue mappingJacksonValue =new MappingJacksonValue(beanList);
        //어떤 필드를 포함/제외할지 결정하는 필터 생성
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
        //필터에 이름을 붙여서 등록
        FilterProvider filters= new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        //생성한 필터를 MappingJacksonValue에 적용
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }






}
