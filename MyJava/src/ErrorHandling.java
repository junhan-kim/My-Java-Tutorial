// ������ �����ϴ� ���� Ŭ������ �ƴ� ���, Ŭ���� ����
// extends Exception
class FuncTooSmallIndexException extends Exception{
	int in;
	// �����ڿ��� ���� �߻��� ����(in)�� ����
	FuncTooSmallIndexException(int in){
		this.in = in;
	}
	// ���� �޽��� ��� �Լ�
	void printError() {
		System.out.println("Error with small index : " + in);
	}
}

class FuncTooBigIndexException extends Exception{
	FuncTooBigIndexException(int in){
		// Exception(super class) �����ڿ��� �޽��� ����
		super("Error with large index : " + in);
	}
}


public class ErrorHandling{
	// �Լ��� ���� �����, ���Ǵ� ���� Ŭ�������� ���
	// throws FuncTooSmallIndexException, FuncTooBigIndexException
	static void func(int in) throws FuncTooSmallIndexException, 
									FuncTooBigIndexException {
		if(in >= 0 && in < 100) {
			int[] arr = new int[in];
			System.out.println("Array is generated");
		}
		else {
			// Ư�� �����϶� [throw ���ܰ�ü] 
			if(in < 0) throw new FuncTooSmallIndexException(in);
			if(in >= 100) throw new FuncTooBigIndexException(in);
		}
	}
	
	// ���� �帧 : 
	// 1. �Լ� ������ ���ܰ� throw�Ǹ� �Լ� ���� �� catch ������ �����Ͽ� �ڵ鸵
	// 2. ���ܰ� �߻����� ������ ���� ���� ���� (break�� ���� ������ ���� - �̶����� finally ��ħ)
	// 3. � ����̴� try �Ǵ� catch ���Ŀ��� �ݵ�� finally�� ��ħ
	public static void main(String[] args) {
		int i = 105;
		while(true) {
			try {
				func(i);
				break;
			} catch(FuncTooSmallIndexException e) {
				i++;
				e.printError();
				/// System.exit(0);  : �ý��� ����
			} catch(FuncTooBigIndexException e) {
				i--;
				// e.getMessage() : Exception�� ���ǵǾ� ����
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