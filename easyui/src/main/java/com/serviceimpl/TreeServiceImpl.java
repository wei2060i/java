package com.serviceimpl;

import com.bean.TreeNode;
import com.dao.TreeNodeMapper;
import com.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TreeServiceImpl implements TreeService {
    @Autowired
    private TreeNodeMapper treeNodeMapper;
    @Override
    public List<TreeNode> getTree() {
        List<TreeNode> nodes = treeNodeMapper.getTreeNodes();
        Map<Integer,TreeNode> map = new HashMap<>();
        List<TreeNode> parents = new ArrayList<>();
        for (TreeNode node : nodes) {
            map.put(node.getId(), node);
            if (node.getPid() == null) {
                parents.add(node);
            }
        }
        for (TreeNode node : nodes) {
            TreeNode parent = map.get(node.getPid());
            if (parent != null) {
                parent.getChildren().add(node);
            }
        }
        return parents;
    }


}
