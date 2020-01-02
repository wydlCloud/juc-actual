package com.wy.juclearn.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wy
 * @Classname ErrorMessage
 * @Description TODO
 * @Date 2019/12/18 4:15 下午
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorMessage {
    private int code;
    private String message;

}
