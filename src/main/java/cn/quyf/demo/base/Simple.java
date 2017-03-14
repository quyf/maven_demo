package cn.quyf.demo.base;

public class Simple {

	public static int i=5;
	public Simple(){
		System.out.println(i);
	}
	public static void main(String[] args) {
		Simple s1=new Simple();
		s1.i=6;
		System.out.println( s1.i);
		Simple s2=new Simple();
	}
}
