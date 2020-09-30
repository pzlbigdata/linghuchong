package JUC;

import java.util.concurrent.TimeUnit;

class Phone{
    public static synchronized void sendEmail() throws Exception{
        TimeUnit.SECONDS.sleep(2);
        System.out.println("@@@@@sendEmail");
    }
    public  synchronized void sendSMS() throws Exception{
        System.out.println("@@@@@sendSMS");
    }
    public void sayHello() throws Exception{
        System.out.println("@@@@@sayHello");
    }
}


/**
 *  8lock
 * 1.标准访问，先打印哪个？ 邮件
 * 2.在邮件停四秒，先打印哪个？ 邮件
 * 3.新增不加所sayhello方法，先打印哪个？hello
 * 4.两部手机 ，先打印哪个？ 短信
 * 5.两个静态同步方法,同一手机 邮件
 * 6.两个静态同步方法,两部手机 短信
 * 7.1个静态同步方法,1个普通方法 一个手机，先打印哪个？ 短信
 * 8.1个静态同步方法,1个普通方法  两个手机，先打印哪个？ 短信
 */

public class Lock8Demo05 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{try {
            phone.sendEmail();
        }catch (Exception e)
        {e.printStackTrace();}
        },"A").start();
        try {
            Thread.sleep(100);
        }catch (Exception e)
        {e.printStackTrace();}

        new Thread(()->{try {
           phone.sendSMS();
          //  phone.sayHello();
           // phone2.sendSMS();
        }catch (Exception e)
        {e.printStackTrace();}
        },"B").start();

    }
}
