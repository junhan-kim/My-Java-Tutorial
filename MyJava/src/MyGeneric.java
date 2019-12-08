// �ǹ����� ���ø��� ������� �ʴ´�.  �ٸ� ������ �� ���� ����.

// ���ø� Ŭ���� .  T, K�� ������ Ŭ���� (primitive ��� �Ұ�)
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
		// �ν��Ͻ� �κ��� < > ���� Ÿ�� ���� ����
		MyBox<String, Integer> c = new MyBox<>();
		c.setValue("ok", 34);
		String str = c.getValue();
		
		// < > ��ü�� Ÿ���� �����ϸ�, raw type = object type  (deprecated)
		MyBox d = new MyBox();
	}
}
