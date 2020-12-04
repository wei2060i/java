package com.itheima.controller;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/query")
    public  void  Query(){
       User u= userService.findById(1);
        System.out.println(u);
    }



    // 未授权界面
    @RequestMapping("/unAuth")
    public  String  unAuth(){
            return "noAuth";
    }
    @RequestMapping("/login")
    public  String login(String name,String password,Model model){
        /**
         * 使用Shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        //3.执行登录方法
        try {
            subject.login(token);
            //登录成功
            //跳转
            return "redirect:/user/root";
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //登录失败:用户名不存在
            model.addAttribute("msg", "用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e) {
            //e.printStackTrace();
            //登录失败:密码错误
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("/update.html")
    public String update(){
        return "/user/update";
    }
    @RequestMapping("/add")
    public String add(){
        return "/user/add";
    }
    @RequestMapping("/root")
    public String root(){
        return "root";
    }
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("OK");
        return "ok啦";
    }
}
