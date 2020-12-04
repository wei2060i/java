package com.jwt.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@Accessors(chain = true)
public class Department extends Model<Department> implements Serializable {
    private String id;

    //TableField 自己写的sql语句 仍然无法映射，需要起别名映射
    @TableField(value = "dept_name")
    private String departmentName;

    @TableField(exist = false)
    private List<Empleyee> emps;
    @Override
    protected Serializable pkVal() {
        return id;
    }
}
