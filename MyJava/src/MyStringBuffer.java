class MyStringBuffer{
	public static void main(String[] args) {
		// primitive type <-> Wrapper Class
		// Integer, Character �̿ܿ��� �빮�ڷ� �ٲ��ָ� ��.
		
		// int -> Integer  (Boxing)
		Integer age = new Integer(30);
		age = Integer.valueOf(50);
		// Integer -> int  (UnBoxing)
		int a = age.intValue(); 
		// �ֽ� ������ intó�� get set ����  (AutoBoxing, AutoUnBoxing)
		age = 60;
		a = age;
		
		// ����ؼ� String ��ü�� ����� �Ǿ� �������� ���̹Ƿ� ��ȿ����
		// += �� ���� String ����
		String str = "S";
		str += "e";
		str += "j";
		// = �� ���� �� ����
		str = "o";
		
		// StringBuffer Ŭ������ ����ϸ� String ���긶�� ��ü�� ������ �ʾ� ȿ����
		StringBuffer str3 = new StringBuffer("sejong");
		// append�� ���� String ����
		str3.append("ok");
		// ���۷��� ����
		StringBuffer str4 = str3;
		// �� �ܿ� ���� String ���� �޼��带 ��� ����
		
		// ���� �Լ�
		int b = (int)((Math.random() * 200) - 100);
	}
}
