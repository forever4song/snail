package cn.foreversong.snail.extra.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.LockSupport;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/25
 * Time: 20:43
 * Description: 并发相关
 */
public class Test {

    public static void main(String[] args) throws Exception {
//        testPark();
//        testWait();
        testCallable();
    }


    public static void testCallable() throws ExecutionException, InterruptedException {
        long currentTime = System.currentTimeMillis();
        // 创建多线程任务
        Callable<String> userInfoCallable = () -> {
            // 执行业务代码
            String userResult = userSrv();
            return userResult;
        };

        Callable<String> pictureInfoCallable = () -> {
            // 执行业务代码
            String pictureResult = pictSrv();
            return pictureResult;
        };

        // 封装成任务
        FutureTask<String> userInfoTask = new FutureTask<>(userInfoCallable);
        FutureTask<String> pictureInfoTask = new FutureTask<>(pictureInfoCallable);

        // 启动线程(可以通过线程池进行控制)
        new Thread(userInfoTask).start();
        new Thread(pictureInfoTask).start();

        // 获取结果
        List<String> resulList = new ArrayList<>();
        // 等待task执行完毕,线程阻塞，如何实现？
        resulList.add(userInfoTask.get());
        resulList.add(pictureInfoTask.get());
        System.out.println("执行业务代码总耗时:" + (System.currentTimeMillis() - currentTime));
        /*
        public class FutureTask<V> implements RunnableFuture<V>  ->  FutureTask 实现了 RunnableFutrue
        public interface RunnableFuture<V> extends Runnable, Future<V>   -> RunnableFutrue 继承 Runnable 和 Future
        线程数：
            1. 主线程 2.userInfoTask线程 3.pictureInfoTask线程

        消费者-生产者模型
        park 挂起
        unprk 唤醒

        wait notify
        FutureTask 源码：
        1. get > 进入等待（链表中加入线程，线程进入挂起状态）
        2. run > 执行结束（遍历列表，唤醒线程）
        */
    }

    public static String userSrv() {
        long currentTime = System.currentTimeMillis();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("获取用户信息耗时:" + (System.currentTimeMillis() - currentTime));
        return "用户信息";
    }

    public static String pictSrv() {
        long currentTime = System.currentTimeMillis();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("获取图片信息耗时:" + (System.currentTimeMillis() - currentTime));
        return "图片信息";
    }


    private static Object object = new Object();

    public static void testWait() {
        final Thread thread1 = new Thread(() -> {
            try {
                synchronized (object) {
                    Thread.sleep(2000);
                    System.out.println("Mike 老师,到了酒店门口");
                    object.wait();
                    System.out.println("愉快的度过了一个晚上");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        final Thread thread2 = new Thread(() -> {
            try {
                synchronized (object) {
                    object.notifyAll();
                    System.out.println("女神到了酒店门口");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
    }


    public static void testPark() throws Exception {
        final Thread thread1 = new Thread(() -> {
            try {
                System.out.println("Mike 老师,到了酒店门口");
                // 等待
                LockSupport.park();
                System.out.println("愉快的度过了一个晚上");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        final Thread thread2 = new Thread(() -> {
            try {
                LockSupport.unpark(thread1);
                System.out.println("女神到了酒店门口");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();
    }

}