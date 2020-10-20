package sanguosha1.gui.main;

import sanguosha1.data.enums.GameOver;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.util.ImgUtil;

import java.awt.*;
import java.util.List;

/**
 * ������Ч��
 * 
 * @author user
 * 
 */
public class PaintService {
	public static Panel_Main main;
	public static final int SLEEPTIME= 1000;
	// ��ʼ��
	public static void createMain(Panel_Main pm) {
		main = pm;
	}

	/**
	 * ���֮�仭һ����
	 * Ĭ��ʱ��������
	 * @param p
	 * @param toP
	 */
	public static void drawLine(AbstractPlayer p, AbstractPlayer toP) {
		drawLineOnly(p,toP);
		clearAfter(0);
	}
	
	/**
	 * ����ҵ������֮�仭��
	 */
	public static void drawLine(AbstractPlayer p, List<AbstractPlayer> players) {
		for (int i = 0; i < players.size(); i++) {
			drawLineOnly(p, players.get(i));
		}
		clearAfter(0);
	}
	
	/**
	 * ����ʱû�ҵ����ʵ��زģ����Ա�����δʹ�õ���
	 * �������˶���
	 */
	public static void drawHurt(AbstractPlayer p){
		Graphics g = main.getGraphics();
		Image img = ImgUtil.getPngImgByName("hurt");
		g.drawImage(img, p.getPanel().getX(), p.getPanel().getY(), null);
		clearAfter(1500);
	}
	
	/**
	 * �����Ƶ�Ч��ͼ
	 * @param img
	 */
	public static void drawEffectImage(Image img,AbstractPlayer p){
		Graphics g = main.getGraphics();
		g.drawImage(img, main.getWidth()/2, main.getHeight()/2-img.getHeight(null),250,250, null);
	}
	
	
	/*
	 * �ڲ����� ����
	 * @param p
	 * @param toP
	 */
	private static  void drawLineOnly(AbstractPlayer p, AbstractPlayer toP){
		Graphics g = main.getGraphics();
		g.setColor(Color.red);
		// g.draw3DRect(0, 0, 400, 400, true);
		Point p1 = ((PaintEffectIF) p.getPanel()).getPaintPoint();
		Point p2 = ((PaintEffectIF) toP.getPanel()).getPaintPoint();
		g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2
				.getY());
	}
	
	/**
	 * ���ƽ���
	 */
	public static void paintGameOver(GameOver go){
		main.removeAll();
		main.add(new Panel_GameOver(go),0);
		main.validate();
		main.repaint();
	}
	
	/**
	 * N������
	 * ���n<=0����ʹ��Ĭ������
	 */
	public static void clearAfter(int n){
		int sleep = n>0?n:SLEEPTIME;
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		main.repaint();
	}
}
