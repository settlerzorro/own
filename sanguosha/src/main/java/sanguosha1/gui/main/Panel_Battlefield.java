package sanguosha1.gui.main;

import sanguosha1.card.AbstractCard;
import sanguosha1.data.constant.Const_UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ս�����
 * �ڷŸ����Ƶ�
 * @author user
 *
 */
public class Panel_Battlefield extends JPanel implements RefreshbleIF{

	private static final long serialVersionUID = 966033103314447302L;
	//����Ƶ�ģ��
	List<AbstractCard> cards = new ArrayList<AbstractCard>();
	//������Ƶ����
	List<Panel_Card> cardList = new ArrayList<Panel_Card>();
	//����Ƶ�ͼ�� 
	List<Image> cardImgs = new ArrayList<Image>();
	//Ч��ͼ
	Image effectImg ;
	/*
	 * ��������
	 */
	private static Panel_Battlefield instance ;
	private Panel_Battlefield(){
		this.setLayout(null);
		//this.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
		this.setOpaque(false);
		this.setSize(Const_UI.FRAME_WIDTH/5*3,Const_UI.PLAYER_PANEL_HEIGHT+20);
		this.setLocation(Const_UI.FRAME_WIDTH/5, Const_UI.PLAYER_PANEL_HEIGHT+40);
	}
	public static Panel_Battlefield getInstance(){
		if(instance==null){
			instance = new Panel_Battlefield(); 
		}
		return instance;
	}
	
	/**
	 * �ػ�
	 */
	public void paintComponent1(Graphics g){
		for (int i = 0; i < cards.size(); i++) {
			Image img = cards.get(i).showImg();
			if(img==null)System.out.println("null");
			g.drawImage(img, Const_UI.CARD_WIDTH*i, 20, null);
		}
	}
	
	/**
	 * ��ս������һ����
	 */
	public void addOneCard(AbstractCard c){
		cards.add(c);	
		int i = cards.size()-1;
		Panel_Card pc = new Panel_Card(cards.get(i), null, false);
		this.add(pc);
		pc.setLocation(pc.getWidth()*i, 20);
		repaint();
		//showCard();
	}
	/**
	 * ��ʾ��
	 */
	public void showCard(){
		//����cards������������
		/*for (int i = 0; i < cards.size(); i++) {
			Panel_Card pc = new Panel_Card(cards.get(i), null, false);
			this.add(pc);
			pc.setLocation(pc.getWidth()*i, 20);
		}*/
		repaint();
	}
	
	/**
	 * ��õ�ǰս��������
	 * @return
	 */
	public List<AbstractCard> getCards() {
		return cards;
	}
	
	/**
	 * ���ս��
	 */
	public void clear(){
		cards.clear();
		this.removeAll();
		repaint();
	}
	
	/**
	 * ˢ��
	 */
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		showCard();
	}
}
