package cn.quyf.demo.base.autobox;

public class IntegerDemo {

	public static void main(String[] args) {
//		String num = null;
//		System.out.println( Integer.parseInt(num));// Exception java.lang.NumberFormatException
//		System.out.println( Integer.valueOf(num));// Exception java.lang.NumberFormatException
//		System.out.println( String.valueOf(num)); //输出null
//		
//		num = "";
//		System.out.println( Integer.parseInt(num)); // Exception java.lang.NumberFormatException
//		System.out.println( Integer.valueOf(num)); // Exception java.lang.NumberFormatException
//		System.out.println( String.valueOf(num));//空串，啥也不输出
		
		System.out.println( Integer.parseInt("-12"));
		System.out.println( Integer.parseInt("+12"));
	}
}
