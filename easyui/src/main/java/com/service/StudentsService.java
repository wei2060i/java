package com.service;

import com.bean.Students;
import com.util.Page;

public interface StudentsService {

    Page<Students> queryStuByPage(Integer page, Integer rows);

    int updateStu(Students student);

    int deleteStu(Integer id);

    int addStu(Students students);
}
