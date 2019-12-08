// interface with a single abstract function
interface MyInterface{
	void func();
}
interface MyInterface2{
	void func(int a, int b);
}

public class WaitnotifyPractice1 {
	static void test(MyInterface in) {
		in.func();
	}
	static void test(MyInterface2 in) {
		in.func(10, 20);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test(() -> System.out.println("hello lambda"));
		test((a,b) -> System.out.println(a+b));
	}
}
