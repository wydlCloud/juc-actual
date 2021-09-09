package com.wy.learn.completeFuture.mall;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @company
 * @Classname Mall
 * @Description TODO
 */
@Data
@AllArgsConstructor
public class Mall {
    private String mallName;

    public double calcPrice(String productName)
    {
        //检索需要1秒钟
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}
