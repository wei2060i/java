package com.bean;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private Integer id;
    private String text;
    private Integer pid;
    private String url;

    private List<TreeNode> children = new ArrayList<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
    public Integer getPid() {
        return pid;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
    @Override
    public String toString() {
        return "id:"+id+"  name:"+text+" pid:"+pid+" url:"+url;
    }
}
