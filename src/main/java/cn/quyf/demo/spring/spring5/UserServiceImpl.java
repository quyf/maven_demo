package cn.quyf.demo.spring.spring5;


import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public String getName(int id) {
		System.out.println("------getName------");
		return "Tom";
	}

	@Override
	public Integer getAge(int id) {
		System.out.println("------getAge------");
		return 10;
	}

    @Override
    public void sayHello() {
        System.out.println("hello world");
    }
}
