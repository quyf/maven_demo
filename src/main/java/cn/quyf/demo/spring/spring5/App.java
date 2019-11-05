package cn.quyf.demo.spring.spring5;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Hello world!
 *
 */
@Configuration
@ComponentScan({"cn.quyf.demo.spring.spring5"})
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
