package com.wy.test.Tree;

import lombok.Data;

import java.util.List;

/**
 * @author wy
 * @company
 * @Classname Node
 * @Description TODO
 */
@Data
public class Node {
    private String id;
    private String city;
    private String pid;
    private List<Node> children;

    public Node(String id,String city,String pid){
        this.id = id;
        this.city = city;
        this.pid = pid;
    }
}
