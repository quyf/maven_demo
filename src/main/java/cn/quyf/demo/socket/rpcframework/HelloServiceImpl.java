package cn.quyf.demo.socket.rpcframework;

public class HelloServiceImpl  implements HelloService {
    @Override
    public String hello(String name) {
          return "Hello " + name;  
    }  
}