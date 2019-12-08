import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Event Driven Programming
// 각 이벤트들은 컴포넌트마다 이미 정의되어 있음, 개발자는 이벤트 핸들러만 작성
// 이벤트는 메시지 큐(priority queue)에 쌓임


/*   방식 1
class ButEventListener implements ActionListener{ 
	public EventHandlingPanel panel;

	@Override 
	public void actionPerformed(ActionEvent e) {
		JButton but = (JButton)e.getSource();
		if(but == panel.but1) {
			panel.label.setText("OK Clicked");
		}
		if(but == panel.but2) {
			panel.label.setText("Cancel Clicked");
		}
		if(but == panel.but3) {
			panel.label.setText("Close Clicked");
		}
	}
}
*/

class EventHandlingPanel extends JPanel{
	JButton but1;
	JButton but2;
	JButton but3;
	JLabel label;
	
	EventHandlingPanel(){
		setBackground(Color.ORANGE);
		// 실제로는 버튼 배열과 텍스트 배열로 버튼을 생성하여 반복작업을 줄일 수 있음
		but1 = new JButton("OK");
		but2 = new JButton("Cancel");
		but3 = new JButton("Close");
		label = new JLabel("label");
	
		ButEventListener listener = new ButEventListener();
		//listener.panel = this;
		
		// 리스너 등록
		but1.addActionListener(listener);
		but2.addActionListener(listener);
		but3.addActionListener(listener);
		
		add(but1);
		add(but2);
		add(but3);
		add(label);
	}
	
	// 방식 2
	// ActionListener 인터페이스를 상속
	// 버튼이 액션되었을 때 사용하는 리스너
	private class ButEventListener implements ActionListener{
		// 반드시 구현해야하는 메서드
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton but = (JButton)e.getSource();
			if(but == EventHandlingPanel.this.but1) {
				// [외부 클래스 이름].this   => 외부 클래스 자체에 접근
				EventHandlingPanel.this.label.setText("OK Clicked");
			}
			if(but == EventHandlingPanel.this.but2) {
				EventHandlingPanel.this.label.setText("Cancel Clicked");
			}
			if(but == EventHandlingPanel.this.but3) {
				EventHandlingPanel.this.label.setText("Close Clicked");
			}
		}
	}
	
}

public class MySwingEventHandling extends JFrame{
	public static void main(String[] args) {
		new MySwingEventHandling();
	}
	
	MySwingEventHandling(){
		setTitle("Event Handling");
		setSize(300, 300);
		
		add(new EventHandlingPanel());
		
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
