package cn.quyf.demo.resourcebundle;
/**
 * 类说明 
 *
 * @author Van
 * @date 2016-12-20
 */
public class Test {

	public static void main(String[] args) {
		StringManager sm = StringManager.getManager("cn.quyf.demo.resourcebundle");
		System.out.println( sm.getString("arrays.srcoffset.outOfBounds"));
		System.out.println( sm.getString("hello"));
	}
}
