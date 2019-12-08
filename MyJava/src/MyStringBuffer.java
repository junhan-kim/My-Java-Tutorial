class MyStringBuffer{
	public static void main(String[] args) {
		// primitive type <-> Wrapper Class
		// Integer, Character 이외에는 대문자로 바꿔주면 됨.
		
		// int -> Integer  (Boxing)
		Integer age = new Integer(30);
		age = Integer.valueOf(50);
		// Integer -> int  (UnBoxing)
		int a = age.intValue(); 
		// 최신 버전은 int처럼 get set 가능  (AutoBoxing, AutoUnBoxing)
		age = 60;
		a = age;
		
		// 계속해서 String 객체를 만들게 되어 가비지가 쌓이므로 비효율적
		// += 를 통한 String 연결
		String str = "S";
		str += "e";
		str += "j";
		// = 를 통한 값 복사
		str = "o";
		
		// StringBuffer 클래스를 사용하면 String 연산마다 객체를 만들지 않아 효율적
		StringBuffer str3 = new StringBuffer("sejong");
		// append를 통한 String 연결
		str3.append("ok");
		// 레퍼런스 복사
		StringBuffer str4 = str3;
		// 이 외에 여러 String 전용 메서드를 사용 가능
		
		// 랜덤 함수
		int b = (int)((Math.random() * 200) - 100);
	}
}
