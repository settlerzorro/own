package sanguosha1.gui.main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ս����Ϣ�����ʾ
 * 
 * @author user
 * 
 */
public class Panel_Message extends JPanel {
	private static final long serialVersionUID = -2483544218392211358L;
	// private String message = "����--";
	// �����ʾN��
	//private static final int SIZE = 3;
	private List<String> strList = new ArrayList<String>();
	private Font font;
	// ��ʱ����ĵ�ǰ��
	private int time = 0;

	public Panel_Message() {
		// this.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
		this.setSize(500, 100);
		this.setOpaque(false);
		this.font = new Font("����", Font.BOLD, 30);
		clsThread.start();
	}

	/**
	 * ����
	 */
	public void paintComponent(Graphics g) {
		g.setFont(font);
		g.setColor(Color.white);
		for (int i = 0; i < strList.size(); i++) {
			g.drawString(strList.get(i), 10, font.getSize() * i + 30);
		}
		super.paintComponents(g);
	}

	/**
	 * ����������һ����Ϣ ����������Ƚ��ȳ�
	 */
	public void addMessage(final String msg) {
		// ���¼�ʱ
		time = 0;
		strList.add(msg);
		/*if (strList.size() > SIZE) {
			strList.remove(0);
			//repaint();
		}*/
	}

	/**
	 * ��ʱ������߳�
	 */
	private Thread clsThread = new Thread() {
		@Override
		public void run() {
			while (true) {
				
				if (time >= 10) {
					if (!strList.isEmpty()) {
						strList.remove(0);
						time = 0;
						repaint();
					}
				}
				try {
					Thread.sleep(100);
					time++;

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	};
}
