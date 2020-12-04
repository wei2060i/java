package com.dao;

import com.bean.TreeNode;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TreeNodeMapper {
    @Select("select id,text,pid,url from menu  ORDER BY id")
    List<TreeNode> getTreeNodes();
}
