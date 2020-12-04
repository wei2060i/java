package com.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
/*
  注解字段对应  @TableField(fill = FieldFill.INSERT)

  @Component
 */
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //获取需要被填充的字段值
        Object name = getFieldValByName("name", metaObject);
        if(name==null){
            setFieldValByName("name","舞",metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object name = getFieldValByName("name", metaObject);
        if(name==null){
            setFieldValByName("name","舞",metaObject);
        }
    }
}
