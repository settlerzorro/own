package sanguosha1.gui.main;

import javax.swing.*;
import java.awt.*;

/**
 * ��ʾ������ʾ��Ϣ�����
 * 
 * @author user
 * 
 */
public  class Panel_Prompt extends JPanel  {
	private static final long serialVersionUID = 8764890627705088325L;
	private static final String[] types = { "ɱ", "��", "��" };
	
	private String message = "����--��ʾ��Ϣ���";
	private Font font;

	/**
	 * ����
	 */
	public Panel_Prompt() {
		this.setSize(550, 40);
		this.setOpaque(false);
		this.font = new Font("����", Font.BOLD, 30);
	}

	/**
	 * ��������
	 */
	public synchronized void paintComponent(Graphics g) {
		g.setFont(font);
		g.setColor(Color.white);
		if (message != null) {
			g.drawString(message, 10, 30);
		}
	}

	/**
	 * ��дpaint
	 *//*
	public void paint1(Graphics g) {
		if (isPainting()) { // ���ݵ�ǰ֡��ʾ��ǰ͸���ȵ��������
			float alpha = (float) index / (float) FRAMENUMBER;
			Graphics2D g2d = (Graphics2D) g;
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, alpha));
			// Renderer��Ⱦ����
			super.paint(g2d);
		} else {
			// ����ǵ�һ�Σ���������ʱ��
			index = 0;
			timer = new Timer(INTERVAL, this);
			timer.start();
		}
	}*/

	/**
	 * ��ʾ����Ҫ��ĳ������n��
	 * 
	 * @param type
	 * @param num
	 */
	public void show_RemindToUse(int type, int num) {
		message = "����Ҫ��" + num + "��" + types[type-1];
		repaint();
	}

	/**
	 * ��ʾ����
	 */
	public void show_RemindToThrow(int num) {
		message = "����Ҫ����" + num + "������";
		repaint();
	}

	/**
	 * ��ʾ��Ϣ
	 */
	public void show_Message(String msg){
		message = msg;
		repaint();
	}
	/**
	 * �����ʾ
	 * 
	 * @return
	 */
	public void clear() {
		message = null;
		repaint();
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
