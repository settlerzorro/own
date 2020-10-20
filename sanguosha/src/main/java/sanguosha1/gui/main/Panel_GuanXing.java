package sanguosha1.gui.main;

import sanguosha1.card.AbstractCard;
import sanguosha1.service.ModuleManagement;
import sanguosha1.util.ImgUtil;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * ����������ǡ����
 * ע���������Ӳ����ղ���
 * @author user
 *
 */
public class Panel_GuanXing extends JPanel{
	private static final long serialVersionUID = 916877278591539946L;
	private static final Border BD_NORMAL = BorderFactory.createLineBorder(Color.darkGray,2);
	private static final Border BD_SELECT = BorderFactory.createLineBorder(Color.red,2);
	// �Ƶ���ʾ�ߴ�
	private static final int CARDWIDTH = 100;
	private static final int CARDHEIGHT = 150;
	//��Ҫ������ƶ�
	List<AbstractCard> cardList ;
	//��ǰ����
	int n;
	//�ϲ������弯��
	List<Panel_Card> upList = new ArrayList<Panel_Card>();
	//�²����弯��
	List<Panel_Card> downList = new ArrayList<Panel_Card>();
	//��ǰѡ�е����
	Panel_Card curPanel ;
	//���������
	Panel_Main pm;
	public Panel_GuanXing(Panel_Main pm){
		this.pm = pm;
		getCards();
		createUI();
	}
	/*
	 * ��ȡN����
	 */
	private void getCards(){
		cardList = new ArrayList<AbstractCard>();
		//��ȡ��ǰ����
		n = ModuleManagement.getInstance().getPlayerList().size();
		n = n>=5?5:n;
		for (int i = 0; i < n; i++) {
			cardList.add(ModuleManagement.getInstance().getOneCard());
		}
	}
	
	/*
	 * ����UI
	 */
	private void createUI(){
		this.setLayout(null);
		this.setSize(CARDWIDTH * 5 + 50, CARDHEIGHT * 2 + 50);
		//����ȡ������
		for (int i = 0; i < n; i++) {
			Panel_Card pc = new Panel_Card(cardList.get(i), CARDWIDTH, CARDHEIGHT-10, true);
			pc.setLocation((CARDWIDTH+5)*i+10, 20);
			pc.addMouseListener(new MouseClick(pc));
			pc.setBorder(BD_NORMAL);
			upList.add(pc);
			add(pc);
		}
		//���Ƶײ�n��
		for (int i = 0; i < n; i++) {
			Panel_Card pc = new Panel_Card(null, CARDWIDTH, CARDHEIGHT-10, true);
			pc.setLocation((CARDWIDTH+5)*i+10, CARDHEIGHT+20);
			pc.addMouseListener(new MouseClick(pc));
			pc.setBorder(BD_NORMAL);
			downList.add(pc);
			add(pc);
		}
	}
	
	/**
	 * ��ɹ��ǣ����ư����úõ�˳������ƶ�
	 */
	public void finish(){
		//�ϲ����ƶ���
		List<AbstractCard> list = new ArrayList<AbstractCard>();
		for (int i = 0; i < upList.size(); i++) {
			AbstractCard c = upList.get(i).getCard();
			if(c!=null){
				list.add(c);
			}
		}
		ModuleManagement.getInstance().getCardList().addAll(0, list);
		//�²������ƶ�
		list = new ArrayList<AbstractCard>();
		for (int i = 0; i < downList.size(); i++) {
			AbstractCard c = downList.get(i).getCard();
			if(c!=null){
				list.add(c);
			}
			ModuleManagement.getInstance().getCardList().addAll(list);
		}
		pm.remove(this);
		pm.validate();
		pm.repaint();
	}
	/**
	 * ���Ʊ���
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(ImgUtil.getJpgImgByName("bg_selectcard"), 0, 0, getWidth(),
				getHeight(), null);
	}
	
	/**
	 * �ڲ���������
	 * @author user
	 *
	 */
	class MouseClick extends MouseAdapter{
		Panel_Card pc ;
		public MouseClick(Panel_Card pc){
			this.pc = pc;
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(curPanel==null){
				curPanel = pc;
				pc.setBorder(BD_SELECT);
			}else{
				//����2����
				AbstractCard c = pc.getCard();
				pc.setCard(curPanel.getCard());
				curPanel.setCard(c);
				//���״̬
				curPanel.setBorder(BD_NORMAL);
				pc.setBorder(BD_NORMAL);
				curPanel =null;
			}
			repaint();
		}
	}
}
