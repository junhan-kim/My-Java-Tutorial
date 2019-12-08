import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

// array : random access, 접근 O(1), 추가 또는 삭제 O(n)
// list : iterator access, 접근 O(n), 추가 또는 삭제 O(1)
// set : 키 안에서 값의 중복을 허용하지 않음.
// map : 키와 값이 1대1 매핑됨. 접근 O(1)
// Vector와 ArrayList는 임의 접근 가능(배열) + 사이즈가 자동으로 2배씩 늘어남(리스트)
//                    늘어나긴하는데, 추가 또는 삭제는 최소한으로 할 것
// Vector : sync 기능 때문에 병렬 처리가 안전
// ArrayList : async이므로 병렬 처리에 불리.
//             이외 작업에는 Vector보다 유리


public class MyCollection {
	public static void main(String[] args) {
		ArrayList<String> arr = new ArrayList<>();
		Vector<String> v = new Vector<>();

		// ArrayList 메서드 사용  (다른 Collection도 비슷)
		arr.add("Sejong");
		arr.add("Univ");
		arr.add("not");
		arr.set(2, "Software");
		arr.remove(0);
		for(int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}
		// 그 외의 여러 메서드
		// .contains : 원소가 존재하는지 체크
	    // .addAll : 두 컬렉션의 원소를 전부 합침
		// .capacity : 현재 배열 자체의 크기  (size는 원소가 채워진 크기)
		// .clear : 전체 비우기
		// .copyInto : 복사
		// .indexOf : 특정 원소의 인덱스 반환

		// ArrayList to array
		Object[] old_arr1 = arr.toArray();
		// ArrayList to String array
		String[] old_arr2 = arr.toArray(new String[arr.size()]);
		// String array to ArrayList
		for(String e : old_arr2) {
			arr.add(e);
		}
		
		for(String s : arr) {   // foreach 출력
			System.out.println(s);
		}
		// 참고 : foreach는 read-only로만 사용. 절대 값을 수정하는 데에 쓰지 말 것
		//       list의 순회방식을 사용하므로 concurrent modification 문제가 발생
		
		// Iterator : ArrayList, Vector, LinkedList 등에서 다 사용 (순차적인 순회)
		Iterator<String> it = arr.iterator();
		while(it.hasNext()) {
			String cur = it.next();  // 리턴하고 다음으로 이동
			if(cur.equals("Univ")) {
				it.remove();  // 다음으로 이동했지만 삭제는 이전을 삭제
			}
			else System.out.println(cur);
		}
		
		// Integer v;  v.compareTo();  =>  내가 작으면 -1, 크면 1, 같으면 0
		Collections.sort(arr);  // 오름차순. 문자열도 정렬 가능
		Collections.reverse(arr);  // 내림차순.
	}
}
