package cn.quyf.demo.base.lambda.functioninterface;

/**
 * @author quyf
 * @date 2019/11/12 10:38
 */
public class FunctionInterfaceDemo {
    public static void main(String[] args) {
        //相当于赋一个方法实现
        TestFunctionalInterface in = (name, age) -> System.out.println("hello "+name +", age:"+age);
        in.say("lily", 18);
    }
}

/**
 * 定义函数接口，只能有一个抽象方法 或者
 * 声明注解不是必须的，如果没有声明，但也只有一个抽象函数，依然会被认为是函数接口
 * 多一个或者少一个抽象函数都不能定义为函数接口，如果使用了函数接口注解又不止一个抽象函数，
 * 那么编译器会拒绝编译。函数接口在使用时候可以隐式的转换成 Lambda 表达式。
 */
@FunctionalInterface
interface TestFunctionalInterface{
    void say(String name, int age);
}