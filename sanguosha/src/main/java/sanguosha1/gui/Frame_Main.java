package sanguosha1.gui;

import sanguosha1.Main;
import sanguosha1.data.constant.Const_UI;
import sanguosha1.data.enums.GameOver;
import sanguosha1.data.enums.Identity;
import sanguosha1.gui.main.PaintService;
import sanguosha1.gui.main.Panel_Main;
import sanguosha1.gui.select.Panel_Select;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ModuleManagement;

import javax.swing.*;
import java.util.List;

/**
 * ��Ϸ������
 * 
 * @author user
 * 
 */
public class Frame_Main extends JFrame {
	
	private static final long serialVersionUID = -6905419069196737546L;
	public static Frame_Main me;
	public static GameThread gt;
	public static boolean isGameOver;
	// ��Ϸ�����
	public static Panel_Main main;
	
	//ѡ�����
	Panel_Select select;
	AbstractPlayer boss;

	public Frame_Main() {
		me = this;
		createSelectUI();
		createUI();
	}
	public void startThread(){
		gt = new GameThread();
		gt.start();
	}
	
	//����ѡ�����
	private void createSelectUI(){
		System.out.println("��ʼ�������");
		select = new Panel_Select();
	}
	//���������
	public void loadMain(){
		remove(select);
		repaint();
		init();
		//createUI();
		// ������ս�����
		main = new Panel_Main();
		add(main);
		startThread();
	}
	// ����UI����
	private void createUI() {
		System.out.println("��ʼ��������");
		this.setTitle("����ɱ"+"--"+Main.version);
		this.setSize(Const_UI.FRAME_WIDTH, Const_UI.FRAME_HEIGHT);
		this.setDefaultCloseOperation(3);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.add(select);
		System.out.println("׼����ʾ����");
		Main.isFinished = true;
		
		this.setVisible(true);
		System.out.println("���");
	}

	private void init() {
		List<AbstractPlayer> players = ModuleManagement.getInstance()
				.getPlayerList();
		// Ѱ����������ʼ��
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getState().getId() == Identity.ZHUGONG) {
				boss = players.get(i);
				initBoss(players.get(i));
				break;
			}
		}

	}

	/**
	 * ��ʼ������Ϸ�Ļغ�
	 */
	public void startGame() {
		isGameOver = false;
		boss.process();
	}

	/**
	 * ��ʼ������
	 */
	private void initBoss(AbstractPlayer boss) {
		// ��1��Ѫ
		boss.getInfo().setMaxHP(boss.getInfo().getMaxHP() + 1);
		boss.getState().setCurHP(boss.getInfo().getMaxHP());
		// ����������

	}

	/**
	 * ��Ϸ����
	 */
	public  void gameOver(final GameOver type) {
		isGameOver = true;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(gt.getState());
				while(gt.getState()!=Thread.State.TERMINATED){					
					PaintService.paintGameOver(type);
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					me.remove(main);
					me.repaint();
				
					/*main = new Panel_Main();
					me.add(main);*/
					//me.validate();
					/*me.repaint();*/
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//startThread();
					createSelectUI();
					createUI();
					//ModuleManagement.reset();
					//ViewManagement.reset();
					break;
				}
				
				System.out.println(gt.getState());				
			}
		}).start();
	}

	/**
	 * ��Ϸ���߳�
	 */
	class GameThread extends Thread {
		public void run() {
			startGame();
			System.out.println("��Ϸ�߳̽���");
		}

	}
}
