package JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharData {
    private int number = 1;//A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            //1判断
            while (number != 1) {
                condition1.await();
            }
            //2干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3通知
            number = 2;
            //如何通知第二个
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            //1判断
            while (number != 2) {
                condition2.await();
            }
            //2干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3通知
            number = 3;
            //如何通知第二个
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            //1判断
            while (number != 3) {
                condition3.await();
            }
            //2干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3通知
            number = 1;
            //如何通知第二个
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ConditionDemo {
    public static void main(String[] args) {
        SharData sharData = new SharData();

       new Thread(()->{
           for (int i = 1; i <=10 ; i++) {
                sharData.print5();
           }
               },"A").start();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                sharData.print10();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                sharData.print15();
            }
        },"C").start();
    }
}
