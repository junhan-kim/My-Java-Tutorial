// 실무에서 템플릿은 권장되지 않는다.  다만 가져다 쓸 일은 많다.

// 템플릿 클래스 .  T, K는 임의의 클래스 (primitive 사용 불가)
class MyBox<T, K>{
	T value;
	K key;
	void setValue(T in, K k) {
		value = in;
		key = k;
	}
	T getValue() {
		return value;
	}
}

public class MyGeneric {
	public static void main(String[] args) {
		// 인스턴스 부분의 < > 에는 타입 생략 가능
		MyBox<String, Integer> c = new MyBox<>();
		c.setValue("ok", 34);
		String str = c.getValue();
		
		// < > 전체에 타입을 생략하면, raw type = object type  (deprecated)
		MyBox d = new MyBox();
	}
}
