// 기존에 존재하던 예외 클래스가 아닌 경우, 클래스 선언
// extends Exception
class FuncTooSmallIndexException extends Exception{
	int in;
	// 생성자에서 예외 발생시 정보(in)를 받음
	FuncTooSmallIndexException(int in){
		this.in = in;
	}
	// 에러 메시지 출력 함수
	void printError() {
		System.out.println("Error with small index : " + in);
	}
}

class FuncTooBigIndexException extends Exception{
	FuncTooBigIndexException(int in){
		// Exception(super class) 생성자에게 메시지 전달
		super("Error with large index : " + in);
	}
}


public class ErrorHandling{
	// 함수에 예외 적용시, 사용되는 예외 클래스들을 등록
	// throws FuncTooSmallIndexException, FuncTooBigIndexException
	static void func(int in) throws FuncTooSmallIndexException, 
									FuncTooBigIndexException {
		if(in >= 0 && in < 100) {
			int[] arr = new int[in];
			System.out.println("Array is generated");
		}
		else {
			// 특정 조건일때 [throw 예외객체] 
			if(in < 0) throw new FuncTooSmallIndexException(in);
			if(in >= 100) throw new FuncTooBigIndexException(in);
		}
	}
	
	// 실행 흐름 : 
	// 1. 함수 내에서 예외가 throw되면 함수 종료 후 catch 문으로 점프하여 핸들링
	// 2. 예외가 발생하지 않으면 다음 문장 실행 (break에 의해 루프를 나감 - 이때에도 finally 거침)
	// 3. 어떤 경우이던 try 또는 catch 이후에는 반드시 finally를 거침
	public static void main(String[] args) {
		int i = 105;
		while(true) {
			try {
				func(i);
				break;
			} catch(FuncTooSmallIndexException e) {
				i++;
				e.printError();
				/// System.exit(0);  : 시스템 종료
			} catch(FuncTooBigIndexException e) {
				i--;
				// e.getMessage() : Exception에 정의되어 있음
				System.out.println(e.getMessage());
			} finally {
				System.out.println("End");
			}
		}
		
		int x = 10;
		int y = 0;
		int[] arr = new int[4];
		try {
			int z = x / y;  // ArithmeticException
			arr[4] = 5; // ArrayIndexOutOfBoundsException
		} catch(ArithmeticException e) {
			System.out.println(e.getMessage());
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {  // Exception
			System.out.println(e.getMessage());
		} finally {
		}
	}
}