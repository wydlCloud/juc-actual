package com.wy.learn.completeFuture.mall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author wy
 * @company
 * @Classname CompletableFutureMall
 * @Description // 这种异步处理好处非常明显，如果平台增多，只要服务器CPU的使用率不超标，以及内存占用不发生溢出
 * 增加平台的比价，并不会对性能造成损耗，如果是同步的情况下，时间就会逐渐变长，随着需求迭代，可能就会导致性能瓶颈。
 */

public class CompletableFutureMall {
    static List<Mall> list = Arrays.asList(
            new Mall("jd"),
            new Mall("tb"),
            new Mall("tmall")
    );

    // 同步
    public static List<String> getPriceByStep(List<Mall> list, String productName) {
        List<String> collect = list.stream().map
                (mall -> String.format(productName + "in %s price is %.2f",
                        mall.getMallName(),
                        mall.calcPrice(productName)))
                .collect(Collectors.toList());
        return collect;
    }

    // 异步处理

    public static List<String> getByPriceAsync(List<Mall> list, String productName) {
        // 和futute的相比，是可以非常便捷的进行流式编程，以及，在某些点上面，不会进行阻塞，这个体现还还不是特别明显
        // 如果有多个任务，但是有的任务会依赖于上一个任务的结果的话，future需要get进行获取，然后在进行执行下一个任务，但是CompletableFuture
        // 就不需要，然后提供了thenApply等方法，来进行下面任务的继续执行。减少了阻塞，和轮询空转cpu
        return list.stream().
                map(mall -> CompletableFuture.supplyAsync(() -> String.format(productName + "in %s price is %.2f", mall.getMallName(), mall.calcPrice(productName))))
                .collect(Collectors.toList()) // 取到List<CompletableFuture>，其实如果还有的业务处理，还可以来进行处理一些其他的业务操作，
                .stream()
                .map(CompletableFuture::join)// 因为如果在这一步，某个平台获取的比较慢，在此处还是会造成阻塞的，看内部是while获取state，来进行report
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> list1 = getPriceByStep(list, "mysql");
        for (String element : list1) {
            System.out.println(element);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("----costTime: " + (endTime - startTime) + " 毫秒");

        System.out.println();

        long startTime2 = System.currentTimeMillis();
        List<String> list2 = getByPriceAsync(list, "mysql");
        for (String element : list2) {
            System.out.println(element);
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("----costTime: " + (endTime2 - startTime2) + " 毫秒");

    }
}
