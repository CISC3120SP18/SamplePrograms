package CISC3120.PassByValueDemo;

public class PassByValueDemoPrimitive {

	void changeX(int x) {
		System.out.println("changeX: at beginning: x = " + x);
		x = 2;
		System.out.println("changeX: at the end: x = " + x);
	}
}
