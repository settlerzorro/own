package sanguosha1.gui.main;

import sanguosha1.data.constant.Const_UI;
import sanguosha1.gui.Frame_Debug;
import sanguosha1.service.ModuleManagement;
import sanguosha1.service.ViewManagement;
import sanguosha1.util.ImgUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * �����
 * @author user
 *
 */
public class Panel_Main extends JPanel {
	private static final long serialVersionUID = 5373403541536774127L;
	
	//��ҹ�����
	ModuleManagement pmgr = ModuleManagement.getInstance();
	//���������
	Panel_Control contrl = new Panel_Control(pmgr.getPlayerList().get(0),this);
	//������ҵ������
	List<Panel_Player>  players = new ArrayList<Panel_Player>();
	//ս�����
	Panel_Battlefield bf = Panel_Battlefield.getInstance();
	//����ͼ
	BufferedImage bgimg = ImgUtil.getJpgImgByName("bg");
	//��Ϸ��Ϣ���
	JTextArea msg = new JTextArea();
	JScrollPane jsp = new JScrollPane(msg);
	//������Ϣ���
	JTextArea msgChat = new JTextArea();
	JScrollPane jspChat = new JScrollPane(msgChat);
	//��ʾ��Ϣ���
	Panel_Prompt prompt = new Panel_Prompt();
	//ս����Ϣ��ʾ���
	Panel_Message message = new Panel_Message();
	//debug����
	Frame_Debug fDebug ;
	//�Ƿ����
	boolean isGameOver;

	/*
	 * ����
	 */
	public Panel_Main() {
		this.setSize(Const_UI.FRAME_WIDTH,Const_UI.FRAME_HEIGHT);
		this.setLocation(0,0);
		this.setLayout(null);
		
		this.add(contrl);
		pmgr.getPlayerList().get(0).setPanel(contrl);
		//��ӵ�ȫ��ˢ���б�
		ViewManagement.getInstance().getRefreshList().add(contrl);
		//��������������
		for (int i = 0; i < pmgr.getPlayerList().size()-1; i++) {
			Panel_Player pp = new Panel_Player(pmgr.getPlayerList().get(i+1),this);
			players.add(pp);
			//������ҹ��������
			pmgr.getPlayerList().get(i+1).setPanel(pp);
			this.add(players.get(i));
			//����������ӵ�������������ȡˢ��֪ͨ
			ViewManagement.getInstance().getRefreshList().add(players.get(i));
			
		}
		setPosition();
		setMsgPanels();
		
		this.add(jsp);
		this.add(jspChat);
		this.add(prompt);
		ViewManagement.getInstance().setPrompt(prompt);
		this.add(message);
		this.add(bf);
		ViewManagement.getInstance().setMessage(message);
		
		PaintService.createMain(this);
		validate();
		
		//�������
		//debug���ڣ����Է���Ĳ鿴�ƶ��е��ƺͳ��������˵�����
		//���������Ȥ�����޸ĺ���չ����Ŀ��Ϊ�˸�����Ĳ��ԣ����������������
		//if(fDebug==null)fDebug = new Frame_Debug();
	}
	/**
	 * ���ø�����Ϣ���
	 */
	private void setMsgPanels() {
		ViewManagement.getInstance().setMsg(msg);
		ViewManagement.getInstance().setMsgChat(msgChat);
		msg.setForeground(Color.white);
		msg.setEditable(false);
		msg.setOpaque(false);
		msg.setLineWrap(true);
		msgChat.setForeground(Color.white);
		msgChat.setEditable(false);
		msgChat.setOpaque(false);
		msgChat.setLineWrap(true);
	
		jsp.setLocation(Const_UI.FRAME_WIDTH/5*4+4, 5);
		jsp.setSize(180, Const_UI.PLAYER_PANEL_HEIGHT);
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false);
		TitledBorder tb1 =BorderFactory.createTitledBorder("��Ϸ��Ϣ��");
		tb1.setTitleColor(Color.white);
		jsp.setBorder(tb1);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jspChat.setLocation(Const_UI.FRAME_WIDTH/5*4+4, bf.getY());
		jspChat.setSize(180, Const_UI.PLAYER_PANEL_HEIGHT);
		jspChat.setOpaque(false);
		jspChat.getViewport().setOpaque(false);
		TitledBorder tb2 = BorderFactory.createTitledBorder("������Ϣ��");
		tb2.setTitleColor(Color.white);
		jspChat.setBorder(tb2);
		jspChat.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		prompt.setLocation(contrl.getHand().getX(), contrl.getY()-prompt.getHeight());
		message.setLocation(bf.getX()+Const_UI.CARD_WIDTH-100, bf.getY()-20);
	}

	/**
	 * �������Ĳ���
	 */
	private void setPosition() {
		if(players.get(3)!=null)players.get(3).setLocation(0, 230);
		if(players.get(2)!=null)players.get(2).setLocation(Const_UI.FRAME_WIDTH/5*1, 0);
		if(players.get(1)!=null)players.get(1).setLocation(Const_UI.FRAME_WIDTH/5*2, 0);
		//if(players.get(0)!=null)players.get(0).setLocation(Const_UI.FRAME_WIDTH-Const_UI.PLAYER_PANEL_WIDTH-20, 200);
		if(players.get(0)!=null)players.get(0).setLocation(Const_UI.FRAME_WIDTH/5*3, 0);
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.drawImage(bgimg, 0, 0, this.getWidth(), this
				.getHeight(), null);
	}

	public void paint(Graphics g){
		if(isGameOver){
			drawUnable(g);
		}
		super.paint(g);
		
	}
	/*
	 * ���Ʋ�����
	 * 
	 * @param g
	 */
	private static void drawUnable(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.darkGray);
		g2.fillRect(0,0, Const_UI.FRAME_WIDTH,Const_UI.FRAME_HEIGHT);
		g2.setComposite(AlphaComposite.SrcOver.derive(Const_UI.CARD_UNABLE));
	}
	/**
	 * ����ս�����������
	 * @return
	 */
	public Panel_Battlefield getBf() {
		return bf;
	}
	public List<Panel_Player> getPlayers() {
		return players;
	}
	public boolean isGameOver() {
		return isGameOver;
	}
	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
	
	
}
