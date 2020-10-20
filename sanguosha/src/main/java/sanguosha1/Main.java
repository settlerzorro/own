package sanguosha1;

import sanguosha1.gui.Frame_Main;
import sanguosha1.gui.start.Frame_Load;

/**
 * �������
 * 
 * @author wangfuyuan
 * @authorID ˮ�·�˪��
 * @version alpha-0.5.11
 */
public class Main {
	public static String version = "alpha-0.5.11";
	//�Ƿ���ɼ���
	public static boolean isFinished;
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {				
				//��������
				new Frame_Load();
			}
		}).start();
		//������
		new Frame_Main();
	}
}
