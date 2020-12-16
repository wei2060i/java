package com.redis_study.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sky
 * @date 2020/12/8 11:27
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private List<Person> list = new ArrayList<>();

    /**
     * key 是SpEL表达式
     * key = "'list1'" 字符串list1 作为key
     *
     * @return
     */
    @Cacheable(value = {"list"}, key = "#root.method.name")
    @GetMapping("test1")
    public List<Person> list() {
        list.add(new Person("a", 1));
        return list;
    }
    //allEntries = true list下所有key
    //@CacheEvict(value = {"list"},allEntries = true)
    @CacheEvict(value = {"list"}, key = "'list'")
    @GetMapping("test2")
    public List<Person> del() {
        list.remove(list.size() - 1);
        return list;
    }

    @Caching(evict = {
            //可以写多个
    }, cacheable = {
    }, put = {
            @CachePut(value = {"list"}, key = "'list'")
    })
    @GetMapping("test3")
    public List<Person> add() {
        list.add(new Person("a", 1));
        return list;
    }


}

@Data
@AllArgsConstructor
class Person {
    private String name;
    private Integer age;
}