package com.serviceimpl;

import com.bean.Students;
import com.dao.StudentsMapper;
import com.service.StudentsService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentsServiceImpl implements StudentsService {
    @Autowired
    private StudentsMapper studentsMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<Students> queryStuByPage(Integer page, Integer rows) {
        try {
            Page<Students> pages = new Page<>(page, rows);
            pages.setRows(studentsMapper.queryStuByPage(pages.getStartIndex(), pages.getPagesize()));
            pages.setTotal(studentsMapper.queryCountStu());
            return pages;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int updateStu(Students student) {
        return studentsMapper.updateStu(student);
    }

    @Override
    public int deleteStu(Integer id) {
        return studentsMapper.deleteStu(id);
    }

    @Override
    public int addStu(Students students) {
        return studentsMapper.addStu(students);
    }
}
