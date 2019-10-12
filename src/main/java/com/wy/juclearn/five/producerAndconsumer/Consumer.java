package com.wy.juclearn.five.producerAndconsumer;



import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author wy
 * @Classname Consumer
 * @Description TODO
 * @Date 2019/10/12 6:54 下午
 */
public class Consumer implements Runnable {
    private BlockingQueue<PCData> queue;
    private static final int SLEEPTIME=1000;
    public Consumer(BlockingQueue<PCData> queue){
        this.queue=queue;
    }
    @Override
    public void run() {
        System.out.println("start Consumer id="+Thread.currentThread().getId());
        Random random=new Random();
        try {
            while (true){
                PCData pcdata=queue.take();
                if (null!=pcdata){
                    int re=pcdata.getData()* pcdata.getData(); //计算平方
                    System.out.println(MessageFormat.format("{0}*{1}={2}",pcdata.getData(),pcdata.getData(),re));
                    Thread.sleep(random.nextInt(SLEEPTIME));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

    }
}
