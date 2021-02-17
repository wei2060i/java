package com.nettydemo.controller;


import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wei
 * @since 2020-03-19
 */
@RestController
@RequestMapping("/chatMessage")
public class ChatMessageController {

    private final List<Integer> list = new ArrayList<>();

    @GetMapping
    public void A () {
        System.out.println(this);
        System.out.println(this.getClass());
        list.add(1);
        System.out.println(list.size());
        list.add(11);
        System.out.println(list.size());
    }

}