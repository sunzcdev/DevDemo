package com.cnjaj.myapplication.pattern.storage;

import com.tencent.bugly.crashreport.BuglyLog;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 这个工厂模型模拟了生产者,消费者
 * 不过这个生产者是个懒生产者,也就是说有消费才生产,无消费则不生产
 * 只有一个生产者,可以有多个消费者
 * Created by Administrator on 2017/7/17.
 */
public class Factory<P> {
    private static final String TAG = "Factory";
    private final LinkedList<Runnable> consumeRunnables = new LinkedList<>();
    private ExecutorService consumePool = Executors.newCachedThreadPool();
    private ExecutorService producePool = Executors.newCachedThreadPool();
    private boolean producing = false;
    private Runnable produceRunnable;
    /**
     * 仓库
     */
    private Storage<P> storage;

    public Factory() {
        storage = new Storage<>();
    }

    /**
     * 设置生产者,本工厂只能设置一个生产者
     *
     * @param producer 生产者
     */
    public void setProducer(final IProducer<P> producer) {
        produceRunnable = new Runnable() {
            @Override
            public void run() {
                BuglyLog.e(TAG, "开始生产");
                while (producing) {
                    P p = producer.produce();
                    if (p == null) continue;
                    storage.storage(p);
                }
            }
        };
    }

    public void addConsumer(final IConsumer<P> consumer) {
        if (!producing) {
            producing = true;
            producePool.execute(produceRunnable);
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                P p;
                do {
                    p = storage.out();
                }
                while (!consumer.consume(p));
                if (p != null && consumeRunnables.remove(this) && consumeRunnables.isEmpty()) {
                    BuglyLog.e(TAG, "生产停止");
                    producing = false;
                }
            }
        };
        consumeRunnables.add(runnable);
        consumePool.execute(runnable);
    }

    public void exit() {
        producing = false;
        consumePool.shutdownNow();
        producePool.shutdownNow();
    }

    /**
     * 生产者接口
     *
     * @param <P>
     */
    public interface IProducer<P> {
        /**
         * 生产方法
         *
         * @return 生产的商品, 如果生产失败, 则返回空
         */
        P produce();
    }

    /**
     * 消费者接口
     *
     * @param <P> 消费产品类型
     */
    public interface IConsumer<P> {
        /**
         * 消费方法
         *
         * @param t 消费的产品
         * @return 是否成功消费
         */
        boolean consume(P t);
    }
}
