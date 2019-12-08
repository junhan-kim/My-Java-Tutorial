import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

// array : random access, ���� O(1), �߰� �Ǵ� ���� O(n)
// list : iterator access, ���� O(n), �߰� �Ǵ� ���� O(1)
// set : Ű �ȿ��� ���� �ߺ��� ������� ����.
// map : Ű�� ���� 1��1 ���ε�. ���� O(1)
// Vector�� ArrayList�� ���� ���� ����(�迭) + ����� �ڵ����� 2�辿 �þ(����Ʈ)
//                    �þ���ϴµ�, �߰� �Ǵ� ������ �ּ������� �� ��
// Vector : sync ��� ������ ���� ó���� ����
// ArrayList : async�̹Ƿ� ���� ó���� �Ҹ�.
//             �̿� �۾����� Vector���� ����


public class MyCollection {
	public static void main(String[] args) {
		ArrayList<String> arr = new ArrayList<>();
		Vector<String> v = new Vector<>();

		// ArrayList �޼��� ���  (�ٸ� Collection�� ���)
		arr.add("Sejong");
		arr.add("Univ");
		arr.add("not");
		arr.set(2, "Software");
		arr.remove(0);
		for(int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}
		// �� ���� ���� �޼���
		// .contains : ���Ұ� �����ϴ��� üũ
	    // .addAll : �� �÷����� ���Ҹ� ���� ��ħ
		// .capacity : ���� �迭 ��ü�� ũ��  (size�� ���Ұ� ä���� ũ��)
		// .clear : ��ü ����
		// .copyInto : ����
		// .indexOf : Ư�� ������ �ε��� ��ȯ

		// ArrayList to array
		Object[] old_arr1 = arr.toArray();
		// ArrayList to String array
		String[] old_arr2 = arr.toArray(new String[arr.size()]);
		// String array to ArrayList
		for(String e : old_arr2) {
			arr.add(e);
		}
		
		for(String s : arr) {   // foreach ���
			System.out.println(s);
		}
		// ���� : foreach�� read-only�θ� ���. ���� ���� �����ϴ� ���� ���� �� ��
		//       list�� ��ȸ����� ����ϹǷ� concurrent modification ������ �߻�
		
		// Iterator : ArrayList, Vector, LinkedList ��� �� ��� (�������� ��ȸ)
		Iterator<String> it = arr.iterator();
		while(it.hasNext()) {
			String cur = it.next();  // �����ϰ� �������� �̵�
			if(cur.equals("Univ")) {
				it.remove();  // �������� �̵������� ������ ������ ����
			}
			else System.out.println(cur);
		}
		
		// Integer v;  v.compareTo();  =>  ���� ������ -1, ũ�� 1, ������ 0
		Collections.sort(arr);  // ��������. ���ڿ��� ���� ����
		Collections.reverse(arr);  // ��������.
	}
}
