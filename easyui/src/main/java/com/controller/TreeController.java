package com.controller;

import com.bean.Students;
import com.bean.TreeNode;
import com.service.StudentsService;
import com.service.TreeService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TreeController {
    @Autowired
    private TreeService treeService;
    @Autowired
    private StudentsService studentsService;

    @ResponseBody
    @RequestMapping("/addStu")
    public Object addStu(Students students){
       try{
          int s= studentsService.addStu(students);
           System.out.println(s+"......id....");
           if(s >0)
           return "OK";
       }catch (Exception e){
           e.printStackTrace();
       }
        return "error";
    }
    @ResponseBody
    @RequestMapping("/deleteStu")
    public Object deleteStu(Integer id){
        try {
            int flag = studentsService.deleteStu(id);
            if(flag==1){
                return "OK";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }
    @ResponseBody
    @RequestMapping("/updateStu")
    public Object updateStu(Students student){
        try {
            int flag = studentsService.updateStu(student);
            if(flag==1){
                return "OK";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }
    @RequestMapping("/queryStuByPage")
    @ResponseBody
    public Page<Students> queryAll(Integer page, Integer rows) {
        try{
            Page<Students> pages= studentsService.queryStuByPage(page,rows);
            return pages;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping("/menu")
    @ResponseBody
    public List<TreeNode> tree() {
        return treeService.getTree();
    }
}
