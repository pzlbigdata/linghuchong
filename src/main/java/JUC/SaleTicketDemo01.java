package JUC;



import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{//变量+方法
    private int number=300;
    Lock lock = new ReentrantLock();//可重入锁
    public void sale(){
        lock.lock();
               try{
                    if(number>0){
//                        Thread.sleep(100);
                        System.out.println(Thread.currentThread().getName()+"\t卖出第："+(number--)+"\t张票，还剩下： "+number);
                    }
               }catch (Exception e){
                   e.printStackTrace();
               }finally {
                lock.unlock();
               }

    }
}

/**
 * 三个售票员卖30张票
 *1、高内聚低耦合线程 操作 资源类
 *   1.1创建资源类
 *
 *   多态 父类的引用指向子类的实现
 *      线程的六种状态
 *          1、新建 2、运行 3、阻塞 4、等待（死等） 5、等待（过时不候） 6、结束
 */
public class SaleTicketDemo01 {

    public static void main(String[] args) {
       Ticket ticket = new Ticket();


      new Thread(() -> {for(int i =1;i<=40;i++) ticket.sale();},"A").start();
      new Thread(() -> {for(int i =1;i<=40;i++) ticket.sale();},"B").start();
      new Thread(() -> {for(int i =1;i<=40;i++) ticket.sale();},"C").start();
//    new Thread(new Runnable() {
//        //匿名内部类
//        public void run() {
//            for (int i = 0; i < 40; i++) {
//
//                ticket.sale();
//            }
//        }
//    }, "AAA").start();
//
//        new Thread(new Runnable() {
//            //匿名内部类
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//                    ticket.sale();
//                }
//            }
//        }, "BBB").start();
//
//        new Thread(new Runnable() {
//            //匿名内部类
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//
//                    ticket.sale();
//                }
//            }
//        }, "CCC").start();




    }

}
