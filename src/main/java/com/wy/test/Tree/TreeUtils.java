package com.wy.test.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author wy
 * @company
 * @Classname TreeUtils
 * @Description TODO
 */

public class TreeUtils {
    //把一个List转成树
    static List<Node> buildTree(List<Node> list,String pid){
        List<Node> tree=new ArrayList<>();
        for(Node node:list){
            if(Objects.equals(node.getPid(),pid)){
                tree.add(findChild(node,list));
            }
        }
        return tree;
    }

    static Node findChild(Node node, List<Node> list){
        for(Node n:list){
            if(Objects.equals(n.getPid(),node.getId())){
                if(node.getChildren() == null){
                    node.setChildren(new ArrayList<Node>());
                }
                node.getChildren().add(findChild(n,list));
            }
        }
        return node;
    }
}
