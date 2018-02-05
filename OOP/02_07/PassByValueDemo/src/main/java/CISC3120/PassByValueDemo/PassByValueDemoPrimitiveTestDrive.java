package CISC3120.PassByValueDemo;

public class PassByValueDemoPrimitiveTestDrive {
	public static void main(String[] args) {
		PassByValueDemoPrimitive demo = new PassByValueDemoPrimitive();
		
		int x = 3;
		
		System.out.println("main: before calling changeX: x = " + x);
		
		demo.changeX(x);
		
		System.out.println("main: after calling changeX: x = " + x);
	}
}
