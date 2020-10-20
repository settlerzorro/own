package sanguosha1.gui.main;

import sanguosha1.data.constant.Const_UI;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.util.ImgUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * ��ҿ������
 * @author user
 *
 */

public class Panel_Control extends JPanel implements RefreshbleIF,PaintEffectIF{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1048990093893639234L;
	//����ģ��
	AbstractPlayer player ;
	//ͷ������
	Panel_HeadImg img;
	//��������
	Panel_HandCards hand ;
	//װ������
	Panel_Equipments eq;
	//����������
	Panel_Main main ;
	//����ͼ
	BufferedImage bgimg = ImgUtil.getJpgImgByName("bg_control");
	
	/**
	 * ������
	 * @param p
	 */
	public Panel_Control(AbstractPlayer p,Panel_Main main){
		this.main = main;
		this.player = p;
		//ͷ������
		img = new Panel_HeadImg(p ,this);
		//��������
		hand = new Panel_HandCards(p,main);
		//װ������
		eq = new Panel_Equipments(p,this,18);
		
		this.setLayout(null);
		this.setSize(Const_UI.FRAME_WIDTH,Const_UI.FRAME_HEIGHT/3-30);
		this.setLocation(0, Const_UI.FRAME_HEIGHT-this.getHeight()-30);
		//this.setBorder(BorderFactory.createLineBorder(Color.darkGray, 4));
		this.setOpaque(false);
		
		this.add(eq);
		//eq.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		eq.setSize(Const_UI.FRAME_WIDTH/6-20,this.getHeight()-50);
		eq.setLocation(20, 25);
		eq.setOpaque(false);
		
		this.add(img);
		img.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		img.setSize(this.getWidth()/5,this.getHeight());
		img.setLocation(Const_UI.FRAME_WIDTH - img.getWidth()-10, 0);
		img.setOpaque(false);
		
		this.add(hand);
		//hand.setBorder(BorderFactory.createLineBorder(Color.green, 1));
		hand.setSize(eq.getX()+this.getWidth()-eq.getWidth()-img.getWidth(),this.getHeight());
		hand.setLocation(eq.getWidth()+20, 0);
		hand.setOpaque(false);
	}
	
	public void paint(Graphics g){
		if(bgimg!=null){
			g.drawImage(bgimg, 0, 0,Const_UI.FRAME_WIDTH,getHeight(), null);
		}
		super.paint(g);
	}
	
	public Panel_Control(){

	}
	
	/**
	 * ��������������
	 */
	public void playersRefresh(){
		List<Panel_Player> list = getMain().getPlayers();
		for (Panel_Player pp : list) {
			pp.setNormal();
		}
	}
	/**
	 * ʵ��ˢ�·���
	 */
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		img.refresh();
		hand.refresh();
		eq.refresh();
	}

	public Panel_HeadImg getImg() {
		return img;
	}

	public void setImg(Panel_HeadImg img) {
		this.img = img;
	}

	public Panel_HandCards getHand() {
		return hand;
	}

	public void setHand(Panel_HandCards hand) {
		this.hand = hand;
	}

	public Panel_Equipments getEq() {
		return eq;
	}

	public void setEq(Panel_Equipments eq) {
		this.eq = eq;
	}

	public Panel_Main getMain() {
		return main;
	}

	public void setMain(Panel_Main main) {
		this.main = main;
	}

	public void setPlayer(AbstractPlayer player) {
		this.player = player;
	}

	

	//��ȡ����ģ��
	public AbstractPlayer getPlayer() {
		return player;
	}

	@Override
	public Point getPaintPoint() {
		int x = getWidth()/2+getX();
		int y = getY();
		return new Point(x, y);
	}
}
