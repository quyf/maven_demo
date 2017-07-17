package cn.quyf.demo.socket.rpcframework;

public class RpcProvider {
    public static void main(String[] args) throws Exception {  
        HelloService service = new HelloServiceImpl();  
        RpcFramework.export(service, 1234);  
    }  
}
