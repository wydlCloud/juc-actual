package com.wy.test;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wy
 * @company
 * @Classname User
 * @Description TODO
 */
@Data
@AllArgsConstructor
public class User {

    private String name;

    private Integer age;

    @Override
    protected void finalize() throws Throwable {
        System.out.println("gc invoker end");
    }
}
