package sanguosha1.gui.main;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.VirtualCardIF;
import sanguosha1.card.equipment.AbstractEquipmentCard;
import sanguosha1.data.constant.Const_UI;
import sanguosha1.data.enums.Colors;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ModuleManagement;
import sanguosha1.service.ViewManagement;
import sanguosha1.util.ImgUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * �Ƶ�ѡ�����
 * �ڡ����Ӳ��š���˳��ǣ�򡿵ȷ���ʱ�����ѡ��
 * ����������Ҫ���ѡ���ʱ��
 * @author user
 * 
 */
public class Panel_SelectCard extends JPanel {
	private static final long serialVersionUID = 7833183629398050914L;
	//�Ƶ���ʾ�ߴ�
	private static final int CARDWIDTH = 100;
	private static final int CARDHEIGHT = 150;
	//�������ͣ�����˳
	public static final int CHAI = 0;
	public static final int SHUN = 1;
	
	public static final int ONLY_EQ=3;
	public static final int ONLY_CHECK=4;
	public static final int ONLY_HAND=5;
	AbstractPlayer p_select ;
	AbstractPlayer p_target;
	JPanel jp_handCard = new JPanel();
	JPanel jp_eqCard = new JPanel();
	JPanel jp_checkCard = new JPanel();
	//���Ͳ���˳
	int type ;
	/**
	 * ����
	 * @param p_select
	 * @param p_target
	 */
	public Panel_SelectCard(AbstractPlayer p_select , AbstractPlayer p_target,int type) {
		this.p_select = p_select;
		this.p_target = p_target;
		this.type = type;
		this.setLayout(new GridLayout(3, 1));
		this.setSize(CARDWIDTH*4+30, CARDHEIGHT*3+50);
		createUI_hand();
		createUI_eq();
		createUI_check();
	}

	
	/**
	 * ���ع��죬ֻ��Ҫ����
	 */
	public Panel_SelectCard(AbstractPlayer p_select , AbstractPlayer p_target,int type,int cardType){
		this.p_select = p_select;
		this.p_target = p_target;
		this.type = type;
		this.setLayout(new GridLayout(1, 1));
		this.setSize(CARDWIDTH*4+30, CARDHEIGHT+50);
		switch(cardType){
		case ONLY_HAND:
			createUI_hand();
			break;
		case ONLY_EQ:
			createUI_eq();
			break;
		case ONLY_CHECK:
			break;
		}
	}
	/*
	 * �������
	 */
	private void createUI_hand() {
		
		//�������򲼾�
		jp_handCard.setLayout(null);
		jp_handCard.setOpaque(false);
		int interval = CARDWIDTH;
		int size = p_target.getState().getCardList().size();
		if ( size > 4) {
			interval = (Const_UI.CARD_WIDTH * 4 - Const_UI.CARD_WIDTH)
					/ (size - 1);
		}
		for (int i = 0; i < size; i++) {
			UnknowCard uc = new UnknowCard(i);
			uc.setLocation(i * interval+10, 20);
			if(i>0){				
				jp_handCard.add(uc,0);
			}else{
				jp_handCard.add(uc);	
			}
		}
		this.add(jp_handCard);
		
	}
	//�ж���
	private void createUI_check() {
		jp_checkCard.setLayout(null);
		jp_checkCard.setOpaque(false);
		if(p_target.getState().getCheckedCardList().isEmpty()){
			this.setLayout(new GridLayout(2, 1));
			this.setSize(CARDWIDTH*4+30, CARDHEIGHT*2+50);
			return;
		}
		for (int i = 0; i<p_target.getState().getCheckedCardList().size(); i++) {
			Card4Select c4s = new Card4Select(p_target.getState().getCheckedCardList().get(i),false);
			c4s.setLocation(i*CARDWIDTH+10, 0);
			jp_checkCard.add(c4s);
		}
		this.add(jp_checkCard);
	}
	private void createUI_eq(){
		//װ���Ʋ���
		jp_eqCard.setLayout(null);
		jp_eqCard.setOpaque(false);
		Card4Select[] ec = new Card4Select[4];
		AbstractCard c0 = p_target.getState().getEquipment().getWeapons();
		AbstractCard c1 = p_target.getState().getEquipment().getArmor();
		AbstractCard c2 = p_target.getState().getEquipment().getAttHorse();
		AbstractCard c3 = p_target.getState().getEquipment().getDefHorse();
		ec[0] = new Card4Select(c0,true);
		ec[1] = new Card4Select(c1,true);
		ec[2] = new Card4Select(c2,true);
		ec[3] = new Card4Select(c3,true);
		for (int i = 0; i < ec.length; i++) {
			ec[i].setLocation(i*CARDWIDTH+10, 10);
			jp_eqCard.add(ec[i]);
		}
		this.add(jp_eqCard);
	}
	/**
	 * ���Ʊ���
	 */
	public void paintComponent(Graphics g){
		g.drawImage(ImgUtil.getJpgImgByName("bg_selectcard"), 0, 0, getWidth(), getHeight(), null);
	}
	
	/**
	 * δ֪���Ƶ���ʾ���
	 * @author user
	 *
	 */
	class UnknowCard extends JPanel {
		private static final long serialVersionUID = 2125016833830841675L;
		int index;
		public UnknowCard(int index){
			this.index = index;
			this.setSize(CARDWIDTH,CARDHEIGHT);
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			this.addMouseListener(new Mouse() );
			this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		}
		
		public void paintComponent(Graphics g){
			g.drawImage(ImgUtil.getJpgImgByName("bg_card"),0, 0, getWidth(), getHeight(), null);
		}
		
		class Mouse extends MouseAdapter{
			@Override
			public void mousePressed(MouseEvent e) {
				AbstractCard c = p_target.getState().getCardList().get(index);
				if(type==CHAI){
					ModuleManagement.getInstance().getBattle().addOneCard(c);
					c.gc();
				}else if(type == SHUN){
					p_select.getAction().addCardToHandCard(c);
				}
				p_target.getAction().removeCard(index);
				Panel_Main pm = (Panel_Main) getParent().getParent().getParent();
				pm.remove(getParent().getParent());
				pm.validate();
				pm.repaint();
				p_select.refreshView();
				p_target.refreshView();
				synchronized (p_select.getProcess()) {
					p_select.getProcess().notify();
				}
			}
		}
	}
	//---------------------------------
	/**
	 * ��ѡ�����
	 */
	class Card4Select extends JPanel{
		private static final long serialVersionUID = -1524971965915633649L;
		boolean isEquipment;
		AbstractCard c;
		
		public Card4Select(AbstractCard c,boolean isEquipment){
			this.c = c;
			if(c instanceof VirtualCardIF){
				VirtualCardIF vc = (VirtualCardIF)c;
				this.c = vc.getRealCard();
			}
			this.setSize(CARDWIDTH,CARDHEIGHT);
			this.isEquipment = isEquipment;
			if(c!=null){
				this.addMouseListener(ml);
				this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		}
		//����
		public void paintComponent(Graphics g){
			if(c!=null){
				g.drawImage(c.showImg(),0, 0, getWidth(), getHeight(), null);
				Image color = c.getColorImg();
				g.drawImage(color, 5, 5, 20, 20, null);
				if (c.getColor() == Colors.FANGKUAI
						|| c.getColor() == Colors.HONGXIN) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.BLACK);
				}
				g.setFont(new Font(g.getFont().getName(), Font.BOLD, 18));
				g.drawString(c.getNumberToString(), 7, 40);
			}
		}
		MouseListener ml = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Panel_Main pm = (Panel_Main) getParent().getParent().getParent();
				pm.remove(getParent().getParent());
				pm.validate();
				pm.repaint();
				if(type==CHAI){
					//�˴���ж���¼��ظ������ж���
					if(!(c instanceof AbstractEquipmentCard)){	
						sleep(300);
						ModuleManagement.getInstance().getBattle().addOneCard(c);						
						c.gc();
						ViewManagement.getInstance().printBattleMsg(p_select.getInfo().getName()+"����"+c.toString());
					}
				}else if(type == SHUN){
					sleep(300);
					p_select.getAction().addCardToHandCard(c);
				}
				if(isEquipment){
					p_target.getAction().unloadEquipmentCard(c);
				}else{
					p_target.getState().getCheckedCardList().remove(c);
				}
				p_select.refreshView();
				p_target.refreshView();
			}
		};
	}
	
	private void sleep(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
