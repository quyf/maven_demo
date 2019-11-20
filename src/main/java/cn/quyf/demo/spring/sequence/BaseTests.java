package cn.quyf.demo.spring.sequence;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@Transactional
public class BaseTests {

	@Test
	public void test() {
		System.out.println("service test is running !");
	}
}
