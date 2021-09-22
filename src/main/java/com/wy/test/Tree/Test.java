package com.wy.test.Tree;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author wy
 * @company
 * @Classname Test
 * @Description TODO
 */

public class Test {
    public static void main(String[] args) {
        Node node0=new Node("0","中国","-1");
        Node node1=new Node("1","湖北省","0");
        Node node2=new Node("2","武汉市","1");
        Node node3=new Node("3","洪山区","2");
        Node node4=new Node("4","宜昌市","1");
        Node node5=new Node("5","上海市","0");
        Node node6=new Node("6","静安区","5");
        List<Node> list=new ArrayList<>();


        list.add(node3);
        list.add(node4);
        list.add(node1);
        list.add(node2);
        list.add(node5);
        list.add(node6);
        list.add(node0);
        List<Node> nodes = TreeUtils.buildTree(list,"-1");
        System.out.println(JSON.toJSONString(nodes));

        String a="123";
        String b="123";

        boolean equals = Objects.equals(a, b);
        System.out.println(equals);
    }
}
