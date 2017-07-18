package com.cnjaj.myapplication.pattern.storage;

import java.util.LinkedList;
import java.util.List;

/**
 * 生产者消费者模式下，仓库类的通用写法
 * 使用时，可以使用多线程进行入库，出库操作。
 * Created by Administrator on 2017/7/17.
 */
public class Storage<P> {
    /**
     * 仓库最大存储量
     */
    private static final int MAX_SIZE = 10;

    /**
     * 仓库
     */
    private final List<P> storage = new LinkedList<>();

    /**
     * 入库一个商品
     *
     * @param product
     */
    public void storage(P product) {
        // 同步代码段
        synchronized (storage) {
            // 如果仓库剩余容量不足
            if (storage.size() + 1 > MAX_SIZE) {
                storage.clear();
            }
            storage.add(product);
            System.out.println(Thread.currentThread().getId() + "【已经入库产品数】:" + 1 + "，product【现仓储量为】:" + storage.size());
            storage.notifyAll();
        }
    }

    /**
     * 出库一个商品
     *
     * @return 商品
     */
    public P out() {
        // 同步代码段
        synchronized (storage) {
            // 如果仓库存储量不足
            while (storage.isEmpty()) {
                System.out.println(Thread.currentThread().getId() + "【要出库的产品数量】:" + 1 + "，【库存量】:"
                        + storage.size() + "，仓库暂时没有商品可以出库!");
                try {
                    // 由于条件不满足，消费阻塞
                    storage.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 消费条件满足情况下，消费num个产品
            P ts = storage.remove(0);
            System.out.println(Thread.currentThread().getId() + "【已经出库产品数】:" + 1 + "，【现仓储量为】:" + storage.size());
            return ts;
        }
    }


    /**
     * 入库多个商品
     *
     * @param products 入库的商品
     */
    public void storage(List<P> products) {
        synchronized (storage) {
            while (storage.size() + products.size() > MAX_SIZE) {
                System.out.println(Thread.currentThread().getId() + "--【要生产的产品数量】:" + products.size() + "，【库存量】:"
                        + storage.size() + "商品暂时不能入库!");
                try {
                    storage.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 入库产品
            storage.addAll(products);
            System.out.println(Thread.currentThread().getId() + "【已经入库产品数】:" + products.size() + "，【现仓储量为】:" + storage.size());
            storage.notifyAll();
        }
    }

    /**
     * 消费num个产品
     *
     * @param num 出库商品的数量
     * @return 出库的商品列表
     */
    public List<P> out(int num) {
        // 同步代码段
        synchronized (storage) {
            // 如果仓库存储量不足
            while (storage.size() < num) {
                System.out.println(Thread.currentThread().getId() + "【要消费的产品数量】:" + num + "，【库存量】:"
                        + storage.size() + "，仓库暂时没有商品可以出库!");
                try {
                    // 由于条件不满足，消费阻塞
                    storage.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 消费条件满足情况下，消费num个产品
            List<P> ps = storage.subList(0, num);
            if (storage.removeAll(ps)) {
                System.out.println(Thread.currentThread().getId() + "【已经消费产品数】:" + ps.size() + "，【现仓储量为】:" + storage.size());
            }
            storage.notifyAll();
            return ps;
        }
    }
}
