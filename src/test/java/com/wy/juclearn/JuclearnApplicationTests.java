package com.wy.juclearn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JuclearnApplicationTests {

    @Test
    public void contextLoads() {
        outer:
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                System.out.println(j);
                if (j == 980) {
                    break outer;
                }
            }
            System.out.println("外层");
        }
    }

}
