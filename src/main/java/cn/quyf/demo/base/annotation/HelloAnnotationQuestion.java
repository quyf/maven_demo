package cn.quyf.demo.base.annotation;

/**
 * @author quyf
 * @date 2019/7/9 9:53
 * https://duhouan.github.io/Java-Notes/#/./Java/JavaBasics/08%E6%B3%A8%E8%A7%A3
 * 注解的实例是jvm在运行时生成的动态代理类，
 */
@HelloAnnotation(sayHi = "hello annotation")
public class HelloAnnotationQuestion {
    public static void main(String[] args) {
        //可以通过此参数设置 保存该注解的动态代理类
       // System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        HelloAnnotation annotation = HelloAnnotationQuestion.class.getAnnotation(HelloAnnotation.class);
        System.out.println(annotation.sayHi());
    }
}


