package JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Aircondition{
    private int number=0;
    private Lock lock = new ReentrantLock();
    private Condition condition= lock.newCondition();
        public  void  increment() throws Exception{
        lock.lock();
               try{

            while (number!=0){
                        condition.await();
            }
            //2 干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3 通知
            condition.signalAll();//this.notifyAll();
               }catch (Exception e){
                   e.printStackTrace();
               }finally {
                lock.unlock();
               }

    }

    public  void  decrement() throws Exception{
        lock.lock();
        try{
            while (number==0){
                condition.await();
            }
            //2 干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3 通知
            condition.signalAll();//this.notifyAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
//    public synchronized void  increment() throws Exception{
//        //1 判断
//        while (number!=0){
//                this.wait();
//        }
//        //2 干活
//        number++;
//        System.out.println(Thread.currentThread().getName()+"\t"+number);
//        //3 通知
//        this.notifyAll();
//    }
//    public synchronized void decrement() throws Exception{
//        //1 判断
//        while (number==0){
//            this.wait();
//        }
//        //2 干活
//        number--;
//        System.out.println(Thread.currentThread().getName()+"\t"+number);
//        //3 通知
//        this.notifyAll();
//    }
}

/**
 *
  1、判断、干活、通知
  2、防止虚假唤醒 判断要用while
 */
public class ProdConsumerDemo04 {
    public static void main(String[] args) throws Exception {
    Aircondition aircondition = new Aircondition();

    new Thread(()->{
        for (int i = 1; i <= 10 ; i++) {
            try {
                aircondition.increment();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    },"A").start();

        new Thread(()->{
            for (int i = 1; i <= 10 ; i++) {
                try {
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 1; i <= 10 ; i++) {
                try {
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 1; i <= 10 ; i++) {
                try {
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
