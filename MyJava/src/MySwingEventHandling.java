import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Event Driven Programming
// �� �̺�Ʈ���� ������Ʈ���� �̹� ���ǵǾ� ����, �����ڴ� �̺�Ʈ �ڵ鷯�� �ۼ�
// �̺�Ʈ�� �޽��� ť(priority queue)�� ����


/*   ��� 1
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
		// �����δ� ��ư �迭�� �ؽ�Ʈ �迭�� ��ư�� �����Ͽ� �ݺ��۾��� ���� �� ����
		but1 = new JButton("OK");
		but2 = new JButton("Cancel");
		but3 = new JButton("Close");
		label = new JLabel("label");
	
		ButEventListener listener = new ButEventListener();
		//listener.panel = this;
		
		// ������ ���
		but1.addActionListener(listener);
		but2.addActionListener(listener);
		but3.addActionListener(listener);
		
		add(but1);
		add(but2);
		add(but3);
		add(label);
	}
	
	// ��� 2
	// ActionListener �������̽��� ���
	// ��ư�� �׼ǵǾ��� �� ����ϴ� ������
	private class ButEventListener implements ActionListener{
		// �ݵ�� �����ؾ��ϴ� �޼���
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton but = (JButton)e.getSource();
			if(but == EventHandlingPanel.this.but1) {
				// [�ܺ� Ŭ���� �̸�].this   => �ܺ� Ŭ���� ��ü�� ����
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
