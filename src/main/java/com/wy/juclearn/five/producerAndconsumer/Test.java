package com.wy.juclearn.five.producerAndconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author wy
 * @Classname Test
 * @Description
 * 生产者-消费者模式很好的对生产线程和消费者线程进行解耦，优化了系统整体架构。
 * 同时，由于缓冲区的作用，允许生产者线程和消费者线程存在执行上的性能差异。
 * 从一定程度上缓解了性能瓶颈时对系统性能的影响。
 * @Date 2019/10/12 7:01 下午
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<PCData> queue = new LinkedBlockingQueue<PCData>(10);
        Producer producer = new Producer(queue);
        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(producer);
        service.execute(producer1);
        service.execute(producer2);
        service.execute(consumer);
        service.execute(consumer1);
        service.execute(consumer2);
        Thread.sleep(10 * 1000);
        producer.stop();
        producer1.stop();
        producer2.stop();
        Thread.sleep(3000);
        service.shutdown();


    }
}
