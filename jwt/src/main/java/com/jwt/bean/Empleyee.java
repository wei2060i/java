package com.jwt.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
// xx_xx  -->XxXx
@Data
@ToString(exclude ={"deptId"})
@Accessors(chain = true)
public class Empleyee extends Model<Empleyee> implements Serializable{

    private String id;
    private String lastName;
    private String email;
    private String gender;

    private String deptId;

    @TableField(exist = false)
    private Department depart;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
