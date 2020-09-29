package JUC;
//@FunctionalInterface
interface Foo{
   // public void sayHello();
    public int add(int a,int b);
     default int mul(int a ,int b){
        return a*b;
    };
      static float div(int a ,int b){
       return a/b;
     };
}
/**
 * 1、函数式编程
 *      int age = 28;
 *  1.1口诀拷贝小括号 写死右箭头 落地大括号
 *  1.2FunctionalInterface
 *  1.3default
 *  4 static
 */
public class LambdaExpressDemo2 {
    public static void main(String[] args) {
        //接口就是来new的
       /*Foo foo =  new Foo() {
            @Override
            public void sayHello() {
                System.out.println("hello word");
            }

           @Override
           public int add(int a, int b) {
               return 0;
           }
       };
       foo.sayHello();*/
      //lambda表达式解决匿名内部类代码冗余
  /*  Foo foo = () ->{ System.out.println("hello word lambda");};
    foo.sayHello();*/
        Foo foo = (int a,int b) ->{
            System.out.println("come in add method");
            return a+b;
        };
        System.out.println(foo.add(5,7));
        System.out.println(foo.mul(5,7));
        System.out.println(Foo.div(5, 2));
    }
}
