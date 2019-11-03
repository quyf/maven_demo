package cn.quyf.demo.spring.spring5;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author quyf
 * @date 2019/11/3 15:38
 */
public class Spring5Demo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext();
        context.register(App.class);
        context.refresh();

        UserService bean = context.getBean(UserService.class);
        bean.sayHello();
    }
}

